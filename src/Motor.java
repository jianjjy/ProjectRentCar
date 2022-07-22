import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;


// Merupakan inheritance dari Mobil
public class Motor extends Transportasi{

    
    static Scanner input = new Scanner(System.in);
    
    // constructors
    public Motor() {
    }

    public String getNamaMotor() {
        return this.namaTransport;
    }

    public String getKodeMotor() {
        return this.kodeTransport;
    }

    public void setKodeMotor(String kodeMotor) {
        this.kodeTransport = kodeMotor;
    }

    public void setNamaMotor(String namaMotor) {
        this.namaTransport = namaMotor;
    }

    public Motor(String kodeMotor, String namaMotor,String PlatTransportasi,String StatusMotor, int HargaSewa) {
        this.kodeTransport = kodeMotor;
        this.namaTransport = namaMotor;
        this.PlatTransportasi = PlatTransportasi;
        this.StatusTransport = StatusMotor;
        this.HargaSewa = HargaSewa;
    }

    public static ArrayList<Motor> updateMotor (ArrayList<Motor> motors) throws FileNotFoundException, IOException {
        try (BufferedReader read = new BufferedReader(new FileReader("data/motor.txt"))) {
            String s = "";
            while ((s = read.readLine()) != null) {
                String data[] = s.split(",");
                motors.add(new Motor(data[0], data[1], data[2], data[3], Integer.parseInt(data[4])));
            }
        }
        return motors;
    }

    public static void updateMotor (String kodeMotor, String status) throws IOException{
        String FilePath = "data/motor.txt";
        File oldFile = new File ("data/motor.txt");
        File newFile = new File ("data/temp.txt");
        
        try (BufferedReader br = new BufferedReader(new FileReader("data/motor.txt"))) {
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String s = "";
            int i = 0;
            while ((s = br.readLine()) != null) {
                String data[] = s.split(",");
                if (i == 0) {
                    if (data[0].equalsIgnoreCase(kodeMotor)) {
                        String row = data[0] + "," + data[1] + "," + data[2] + ",";
                        row = row + status + "," + data[4];
                        pw.print(row);
                    } else {
                        String row =  data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4];
                        pw.print(row);
                    }
                } else {
                    if (data[0].equalsIgnoreCase(kodeMotor)) {
                        String row = "\n" +  data[0] + "," + data[1] + "," + data[2] + ",";
                        row = row + status + "," + data[4];
                        pw.print(row);
                    } else {
                        String row =  "\n" + data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4];
                        pw.print(row);
                    }
                }
                i++;
            }
            br.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(FilePath);
            newFile.renameTo(dump);
        }
    }

    public static void displayAturanMotor (String equals) throws FileNotFoundException, IOException{
        try (BufferedReader read = new BufferedReader(new FileReader("data/motor.txt"))) {
            String s = "";
            System.out.println("|Kode\t|Jenis\t|Harga\t|");
            while ((s = read.readLine()) != null) {
                // System.out.println(s);
                String data[] = s.split(",");
                
                if (data[3].equalsIgnoreCase(equals)) {
                    for (int i = 0; i < 5; i++) {
                        if ((i == 1) || (i == 4)) {
                            System.out.print(data[i] + "\t|"); 
                        } else if (i == 0){
                            System.out.print("|" +data[i] + "\t|"); 
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    public static void DaftarMotor (ArrayList<Motor> motors) throws Exception {
        System.out.println("Daftar Motor Baru");
                System.out.println("-----------------");
                System.out.print("Nama motor : ");
                String namaMobil = input.next();
                System.out.print("Masukkan harga sewa : ");
                int harga = input.nextInt();
                System.out.print("Masukkan plat transportasi : ");
                String plat1 = input.next();
                String plat2 = input.next();
                String plat3 = input.next();
                String plat = plat1 + " " + plat2 + " " + plat3;

                String kodeMobil = "N0" + Integer.toString(motors.size()+1);

                //masukkan data pelanggan ke ArrayList
                motors.add(new Motor(kodeMobil, namaMobil, plat, "Tersedia", harga));
                //masukkan data pelanggan ke file
                try (FileWriter pwMobil = new FileWriter("data/motor.txt", true)) {
                    pwMobil.append("\n" + kodeMobil + "," + namaMobil + ","  + plat + ","  +  "Tersedia" + "," + harga);
                }
    }

    public static void SewaMotor (ArrayList<Motor> motors, ArrayList<TransaksiPeminjaman> pinjams, ArrayList<Pelanggan> pelanggans) throws Exception {
        
        String tanggalPinjam = util.inputTanggal("peminjaman");
        int durasi = util.inputDurasi("peminjaman");
        String lokasiPinjam = util.inputLokasi("peminjaman");
        util.clearScreen();

        //print all cars dgn status tersedia
        Motor.displayAturanMotor( "Tersedia");
        //input kode mobil yang disewa
        System.out.print("Masukkan kode motor yang ingin disewakan : ");
        String kodeInput = input.next();
        kodeInput = kodeInput.toUpperCase();
        // util.clearScreen();
        //cetak detail mobil yang kodenya sama dengan inputan
        for (Motor motor : motors) {
            if (motor.getKodeMotor().equalsIgnoreCase(kodeInput) && motor.getStatusTransport().equalsIgnoreCase("Tersedia")) {
                System.out.println("Kode Motor : " + motor.getKodeMotor());
                System.out.println("Nama Motor : " + motor.getNamaMotor());
                System.out.println("Plat Motor : " + motor.getPlatTransportasi());
                System.out.println("Harga Sewa per Hari : Rp" + motor.getHargaSewa());
                int deposit = motor.getHargaSewa()/10;
                System.out.println("Harga Deposito : Rp" + deposit);
                System.out.println("-----------------------------------");
                int hargaTotal =  motor.getHargaSewa()*durasi + deposit;
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

    public static void kembaliMotor (ArrayList<Motor> motors, ArrayList<TransaksiPeminjaman> pinjams, ArrayList<Pelanggan> pelanggans, ArrayList<TransaksiPengembalian> kembalis) throws FileNotFoundException, IOException, ParseException{
        TransaksiPeminjaman.displayAturanPinjam ("Meminjam", "n");
        //masukkan nomor peminjaman yang mau dikembalikan
        System.out.print("Masukkan kode transaksi : ");
        String kodeInput = input.next();
        kodeInput = kodeInput.toUpperCase();
        util.clearScreen();
        for (TransaksiPeminjaman pinjam : pinjams) {
            if (pinjam.getNomorTransaksi().equalsIgnoreCase(kodeInput) && pinjam.getStatusPinjam().equalsIgnoreCase("Meminjam")) {
                System.out.println("Nomor Transaksi : " + pinjam.getNomorTransaksi());
                System.out.println("Nama Motor : " + pinjam.getMotorPinjam().getNamaTransport());
                System.out.println("Plat Motor : " + pinjam.getMotorPinjam().getPlatTransportasi());
                System.out.println("Peminjam : " + pinjam.getPelangganPinjam().getNamaPelanggan());
                System.out.println("-----------------------");
                String tanggalKembali = util.inputTanggal("pengembalian");
                String lokasiKembali = util.inputLokasi("pengembalian");

                String tanggalKembaliSeharusnya = util.addToDate(pinjam.getTanggalPinjam(), pinjam.getLamaSewa());
                int dendaHari = 0;
                int bedaHari;
                if (tanggalKembali.equalsIgnoreCase(tanggalKembaliSeharusnya)) {
                    System.out.println("Motor dikembalikan tepat waktu");
                    dendaHari += 0;
                } else {
                    if ((bedaHari = util.getDifferenceDays(tanggalKembali, tanggalKembaliSeharusnya)) > 0) {
                        System.out.printf("Motor dikembalikan %d hari terlambat\n", bedaHari);
                        dendaHari = bedaHari * 50000;
                    } else {
                        System.out.println("Motor dikembalikan lebih awal");
                        dendaHari += 0;
                    } 
                }
                //cek mobil from class mobil
                int dendaCek = Motor.cekTransport() * 30000;
                //hitung denda total
                int totalDenda = dendaHari + dendaCek - pinjam.getDeposit();

                TransaksiPeminjaman peminjaman = TransaksiPeminjaman.cariTransaksiPinjam(kodeInput, pinjams);
                Pelanggan.updatePelanggan(peminjaman.getPelangganPinjam().getKodePelanggan(), "lunas");
                Motor.updateMotor(peminjaman.getMotorPinjam().getKodeTransport(), "Tersedia");
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

    public static void displayAturanMotorAsc (String equals) throws FileNotFoundException, IOException{
        try (BufferedReader read = new BufferedReader(new FileReader("data/motor.txt"))) {
            String s = "";
            ArrayList <Motor> asc = new ArrayList<>();
            while ((s = read.readLine()) != null) {
                
                String data[] = s.split(",");
                
                if (data[3].equalsIgnoreCase(equals)) {
                    asc.add (new Motor(data[0], data[1], data[2], data[3],  Integer.parseInt(data[4])));
                }
            }
            for (int i = 0; i < asc.size()-1; ++i) {
                for (int j = 0; j < asc.size()-i-1; ++j) {
                    if (asc.get(j+1).getHargaSewa() < asc.get(j).getHargaSewa()){
                        Motor temp = new Motor();
                        temp = asc.get(j);
                        asc.set(j, asc.get(j+1));
                        asc.set(j+1, temp);
                    }
                }
            }
            System.out.println("|Kode\t|Jenis\t|Harga\t|");
            for (Motor motor : asc) {
                System.out.println(motor);
            }
        }
    }

    public static int cekTransport (){
        int total = 0;
        int data[] = new int[4];
        System.out.println("Cek Transport");
        System.out.println("---------");
        System.out.println("0 jika terpenuhi, 1 jika tidak");
        System.out.print("Minyak di atas 50% : ");
        data[0] = input.nextInt();
        System.out.print("Mesin jalan lancar : ");
        data[1] = input.nextInt()*2;
        System.out.print("Bodi tidak tergores : ");
        data[2] = input.nextInt()*2;
        for (int i : data) {
            total += i;
        }
        return total;
    }

    @Override
    public String toString() {
        return "|" +  getKodeTransport() + "\t|"
        + getNamaTransport() + "\t|" 
        + getHargaSewa() + "\t|";
    }
}
