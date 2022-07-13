import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class App  {
    static ArrayList <Mobil> mobils = new ArrayList <>();
    static ArrayList <TransaksiPeminjaman> pinjams = new ArrayList <>();
    static ArrayList <TransaksiPengembalian> kembalis = new ArrayList<>();
    static ArrayList <Pelanggan> pelanggans = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        util.updateAll(mobils, pinjams, kembalis, pelanggans);
        String yn = "y";
        do {
            util.clearScreen();
            int pilihan = CetakMenu();
            if (pilihan == 1) {
                SewaMobil();
            } else if (pilihan == 2) {
                kembaliMobil();
            } else if (pilihan == 3) {
                Mobil.displayAturanMobil("Tersedia");
            } else if (pilihan == 4) {
                TransaksiPeminjaman.displayAturanPinjam("Meminjam");
            } else if (pilihan == 5) {
                Mobil.DaftarMobil(mobils);
            } else if (pilihan == 6) {
                break;
            } else {
                throw new Exception("Harus masukkan nilai \"1\" sampai \"6\"");
            }
            System.out.print("Apakah anda ingin melakukan transaksi lainnya? (y/n) : ");
            yn = input.next();
        } while (yn.equalsIgnoreCase("y"));
    }

    public static int CetakMenu () {
        System.out.println("Menu Rental Mobil");
        System.out.println("-----------------");
        System.out.println("1. Sewa Mobil");
        System.out.println("2. Kembali Mobil");
        System.out.println("3. Cetak kendaraan yang tersedia");
        System.out.println("4. Cetak transaksi berlangsung");
        System.out.println("5. Daftar Mobil Baru");
        System.out.println("6. Keluar");
        System.out.print("Masukkan pilihan anda : ");
        int pilihan = input.nextInt();
        return pilihan;
    }

    public static void SewaMobil () throws Exception {
        //input tanggal pinjam
        System.out.println("Masukkan tanggal peminjaman");
        System.out.print("Tanggal (1-31) : ");
        int day = input.nextInt();
        System.out.print("Bulan (1-12): ");
        int month = input.nextInt();
        System.out.print("Tahun : ");
        int year = input.nextInt();
            //gabung jadi 1 string
        String tanggalPinjam = Integer.toString(day) + "/" + Integer.toString(month) + "/" +Integer.toString(year);
        tanggalPinjam = util.changeToDate(tanggalPinjam);

        //input durasi pinjam
        System.out.print("Masukkan durasi peminjaman anda : ");
        int durasi = input.nextInt();

        //input lokasi pinjam
        System.out.print("Masukkan lokasi peminjaman anda : ");
        String lokasiPinjam = input.next();

        util.clearScreen();

        //print all cars dgn status tersedia
        Mobil.displayAturanMobil( "Tersedia");
        //input kode mobil yang disewa
        System.out.print("Masukkan kode mobil yang ingin disewakan : ");
        String kodeInput = input.next();
        kodeInput = kodeInput.toUpperCase();
        // util.clearScreen();
        //cetak detail mobil yang kodenya sama dengan inputan
        for (Mobil mobil : mobils) {
            if (mobil.getKodeMobil().equalsIgnoreCase(kodeInput) && mobil.getStatusMobil().equalsIgnoreCase("Tersedia")) {
                System.out.println("Kode Mobil : " + mobil.getKodeMobil());
                System.out.println("Nama Mobil : " + mobil.getNamaMobil());
                System.out.println("Plat Mobil : " + mobil.getPlatTransportasi());
                System.out.println("Harga Sewa per Hari : Rp" + mobil.getHargaSewa());
                int deposit = mobil.getHargaSewa()/10;
                System.out.println("Harga Deposito : Rp" + deposit);
                System.out.println("-----------------------------------");
                int hargaTotal =  mobil.getHargaSewa()*durasi + deposit;
                System.out.println("Harga total : Rp" + hargaTotal);
                System.out.print("Apakah anda yakin?(y/n) : ");
                String pilyakin = input.next();

                //jika yakin input data pelanggan
                if (pilyakin.equalsIgnoreCase("y")) {
                    util.clearScreen();
                    System.out.println("Masukkan detail pelanggan");
                    System.out.println("-------------------------");
                    System.out.print("Nama Pelanggan : ");
                    String namaPelanggan = input.next();
                    System.out.print("Nomor Telepon : ");
                    String noTelp = input.next();
                    System.out.print("Umur : ");
                    int umurPelanggan = input.nextInt();
                    if (umurPelanggan < 18) {
                        throw new Exception("Umur pelanggan tidak mencukupi");
                    }
                    System.out.print("Email : ");
                    String emailPelanggan = input.next();

                    String kodePelanggan = "P0" + Integer.toString(pelanggans.size()+1);
                    String kodePinjam = "T0" + Integer.toString(pinjams.size()+1);

                    //masukkan data pelanggan ke ArrayList
                    pelanggans.add(new Pelanggan(kodePelanggan, namaPelanggan, noTelp, umurPelanggan, emailPelanggan, "meminjam"));
                    //masukkan data pelanggan ke file
                    try (FileWriter pwPelanggan = new FileWriter("data/pelanggan.txt", true)) {
                        pwPelanggan.append("\n" + kodePelanggan + "," + namaPelanggan + "," + noTelp + "," + umurPelanggan + "," + emailPelanggan + "," + "meminjam");
                    }

                    //masukkan data pinjam ke ArrayList
                    pinjams.add(new TransaksiPeminjaman(kodePinjam, kodeInput, kodePelanggan, lokasiPinjam, tanggalPinjam, deposit, hargaTotal, durasi, "Meminjam"));
                    //masukkan data pinjam ke file
                    try (FileWriter pwPinjam = new FileWriter("data/peminjaman.txt", true)) {
                        pwPinjam.append( "\n" +kodePinjam + "," + kodeInput + "," + kodePelanggan + "," + lokasiPinjam + "," + tanggalPinjam + "," + deposit + "," + hargaTotal + "," + durasi + ",Meminjam");
                    }
                    Mobil.updateMobil(kodeInput, "Dipinjam");

                    //cetak reciept
                    util.clearScreen();
                    TransaksiPeminjaman.cetakRecieptPinjam(kodePinjam,pinjams);
                }
                break;
            }
        }
    }

    public static void kembaliMobil () throws FileNotFoundException, IOException, ParseException{
        TransaksiPeminjaman.displayAturanPinjam ("Meminjam");
        //masukkan nomor peminjaman yang mau dikembalikan
        System.out.print("Masukkan kode transaksi : ");
        String kodeInput = input.next();
        for (TransaksiPeminjaman pinjam : pinjams) {
            if (pinjam.getNomorTransaksi().equalsIgnoreCase(kodeInput) && pinjam.getStatusPinjam().equalsIgnoreCase("Meminjam")) {
                System.out.println("Nomor Transaksi : " + pinjam.getNomorTransaksi());
                System.out.println("Nama Mobil : " + pinjam.getMobilPinjam().getNamaMobil());
                System.out.println("Plat Mobil : " + pinjam.getMobilPinjam().getPlatTransportasi());
                System.out.println("Peminjam : " + pinjam.getPelangganPinjam().getNamaPelanggan());
                System.out.println("Tanggal Pengembalian");
                System.out.print("Tanggal (1-31) : ");
                int day = input.nextInt();
                System.out.print("Bulan (1-12): ");
                int month = input.nextInt();
                System.out.print("Tahun : ");
                int year = input.nextInt();
                    //gabung jadi 1 string
                String tanggalKembali = Integer.toString(day) + "/" + Integer.toString(month) + "/" +Integer.toString(year);
                System.out.print("Masukkan lokasi pengembalian : ");
                String lokasiKembali = input.next();
                tanggalKembali = util.changeToDate(tanggalKembali);
                String tanggalKembaliSeharusnya = util.addToDate(pinjam.getTanggalPinjam(), pinjam.getLamaSewa());
                int dendaHari = 0;
                int bedaHari;
                if (tanggalKembali.equalsIgnoreCase(tanggalKembaliSeharusnya)) {
                    System.out.println("Mobil dikembalikan tepat waktu");
                    dendaHari += 0;
                } else {
                    if ((bedaHari = util.getDifferenceDays(tanggalKembali, tanggalKembaliSeharusnya)) > 0) {
                        System.out.printf("Mobil dikembalikan %d hari terlambat\n", bedaHari);
                        dendaHari = bedaHari * 50000;
                    } else {
                        System.out.println("Mobil dikembalikan lebih awal");
                        dendaHari += 0;
                    }
                }
                //cek mobil from class mobil
                int dendaCek = Mobil.cekMobil() * 50000;
                //hitung denda total
                int totalDenda = dendaHari + dendaCek - pinjam.getDeposit();

                TransaksiPeminjaman peminjaman = TransaksiPeminjaman.cariTransaksiPinjam(kodeInput, pinjams);
                Pelanggan.updatePelanggan(peminjaman.getPelangganPinjam().getKodePelanggan(), "lunas");
                Mobil.updateMobil(peminjaman.getMobilPinjam().getKodeMobil(), "Tersedia");
                TransaksiPeminjaman.updatePinjam(kodeInput, "Berhasil");

                //masukkan data kembali ke ArrayList
                kembalis.add(new TransaksiPengembalian(kodeInput, lokasiKembali, tanggalKembali, totalDenda));
                //masukkan data kembali ke file
                try (FileWriter pwKembali = new FileWriter("data/pengembalian.txt", true)) {
                    pwKembali.append("\n" + kodeInput + "," + lokasiKembali + "," + tanggalKembali + "," + totalDenda);
                }
                util.clearScreen();
                TransaksiPengembalian.cetakRecieptKembali(kodeInput, kembalis, pinjams);
                break;
            }
        }
    }
}