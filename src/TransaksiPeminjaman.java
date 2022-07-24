import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

// Merupakan inheritance dari Transaksi
public class TransaksiPeminjaman extends Transaksi{

    private ArrayList <Mobil> mobils = new ArrayList<>();
    private ArrayList <Motor> motors = new ArrayList<>();
    private ArrayList <Pelanggan> pelanggans = new ArrayList<>();
    
    private int deposit;
    private String lokasiPinjam;
    private String tanggalPinjam;
    private Mobil mobilPinjam;
    private Motor motorPinjam;
    private Pelanggan pelangganPinjam;
    private String statusPinjam;

    // constructors
    public TransaksiPeminjaman() {
    }


    public TransaksiPeminjaman( String nomorTransaksi, String kodeTransport, String kodePelanggan, String lokasiPinjam, String tanggalPinjam, int deposit, int biaya, int lamaSewa, String statusPinjam) throws FileNotFoundException, IOException {
        this.nomorTransaksi = nomorTransaksi;
        if (kodeTransport.substring(0, 1).equalsIgnoreCase("m")) {
            Mobil.updateMobil(mobils);
            for (Mobil mobil : mobils) {
                if (mobil.getKodeTransport().equalsIgnoreCase(kodeTransport)) {
                    this.mobilPinjam = mobil;
                    this.motorPinjam = null;
                    break;
                }
            }
        } else if (kodeTransport.substring(0, 1).equalsIgnoreCase("n")) {
            Motor.updateMotor(motors);
            for (Motor motor : motors) {
                if (motor.getKodeTransport().equalsIgnoreCase(kodeTransport)) {
                    this.motorPinjam = motor;
                    this.mobilPinjam = null;
                    break;
                }
            }
        }
        Pelanggan.updatePelanggan(pelanggans);
        for (Pelanggan pelanggan : pelanggans) {
            if (pelanggan.getKodePelanggan().equalsIgnoreCase(kodePelanggan)) {
                this.pelangganPinjam = pelanggan;
                break;
            }
        }
        this.lokasiPinjam = lokasiPinjam;
        this.tanggalPinjam = tanggalPinjam;
        this.deposit = deposit;
        this.biaya = biaya;
        this.lamaSewa = lamaSewa;
        this.statusPinjam = statusPinjam;
    }

    
    // setter getter
    public int getDeposit() {
        return this.deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getLokasiPinjam() {
        return this.lokasiPinjam;
    }

    public void setLokasiPinjam(String lokasiPinjam) {
        this.lokasiPinjam = lokasiPinjam;
    }

    public String getTanggalPinjam() {
        return this.tanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public Mobil getMobilPinjam() {
        return this.mobilPinjam;
    }

    public void setMobilPinjam(Mobil mobilPinjam) {
        this.mobilPinjam = mobilPinjam;
    }

    public Pelanggan getPelangganPinjam() {
        return this.pelangganPinjam;
    }

    public void setPelangganPinjam(Pelanggan pelangganPinjam) {
        this.pelangganPinjam = pelangganPinjam;
    }

    public String getStatusPinjam() {
        return this.statusPinjam;
    }

    public void setStatusPinjam(String statusPinjam) {
        this.statusPinjam = statusPinjam;
    }

    public Motor getMotorPinjam() {
        return this.motorPinjam;
    }

    public void setMotorPinjam(Motor motorPinjam) {
        this.motorPinjam = motorPinjam;
    }


    /* Nama                 : Jian Jeraus Young
     * NIM                  : 03081210009
     * Deskripsi Singkat    : berfungsi untuk membalikkan arraylist dari text file
     */
    public static ArrayList<TransaksiPeminjaman> updatePinjam (ArrayList<TransaksiPeminjaman> pinjams) throws FileNotFoundException, IOException, ParseException {
        try (BufferedReader read = new BufferedReader(new FileReader("data/peminjaman.txt"))) {
            String s = "";
            while ((s = read.readLine()) != null) {
                String data[] = s.split(",");
                pinjams.add(new TransaksiPeminjaman(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5]), Integer.parseInt(data[6]), Integer.parseInt(data[7]), data[8]));
            }
        }
        return pinjams;
    }

    /* Nama                 : Jian Jeraus Young
     * NIM                  : 03081210009
     * Deskripsi Singkat    : berfungsi untuk mengupdate textfile dan arraylist jika ada 1 data yang perlu diganti
     *                        OverLoading dengan method diatas karena mempunyai nama yang sama dengan parameter berbeda
     */
    public static void updatePinjam (String kodeTransaksi, String status, ArrayList<TransaksiPeminjaman> pinjams) throws IOException, ParseException{
        String FilePath = "data/peminjaman.txt";
        File oldFile = new File ("data/peminjaman.txt");
        File newFile = new File ("data/temp.txt");
        
        try (BufferedReader br = new BufferedReader(new FileReader("data/peminjaman.txt"))) {
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String s = "";
            int i = 0;
            while ((s = br.readLine()) != null) {
                String data[] = s.split(",");
                if (i == 0) {
                    if (data[0].equalsIgnoreCase(kodeTransaksi)) {
                        String row = data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5] + "," + data[6] + "," + data[7] + ",";
                        row = row + status;
                        pw.print(row);
                    } else {
                        String row = data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5] + "," + data[6] + "," + data[7] + "," + data[8] ;
                        pw.print(row);
                    }
                } else {
                    if (data[0].equalsIgnoreCase(kodeTransaksi)) {
                        String row = "\n" +  data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5] + "," + data[6] + "," + data[7] + ",";
                        row = row + status;
                        pw.print(row);
                    } else {
                        String row =  "\n" + data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5] + "," + data[6] + "," + data[7] + "," + data[8] ;
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
        pinjams.removeAll(pinjams);
        TransaksiPeminjaman.updatePinjam(pinjams);
    }

    /* Nama                 : Jian Jeraus Young
     * NIM                  : 03081210009
     * Deskripsi Singkat    : berfungsi untuk mencetak bon transaksi peminjaman dari kode Transaksi
     */
    public static void cetakRecieptPinjam (String kodeTransaksi, ArrayList<TransaksiPeminjaman> pinjams) throws ParseException {
        for (TransaksiPeminjaman pinjam : pinjams) {
            if (pinjam.getNomorTransaksi().equalsIgnoreCase(kodeTransaksi)) {
                System.out.println("Kelompok 4 Rental");
                System.out.println("---------------------");
                System.out.println("Transaksi " + kodeTransaksi);
                if (pinjam.getMobilPinjam() != null) {
                    System.out.println("Sewa mobil " + pinjam.getMobilPinjam().getNamaTransport() + " " + pinjam.getMobilPinjam().getPlatTransportasi());
                    System.out.println("\t\tRp" + pinjam.getMobilPinjam().getHargaSewa() + " x " + pinjam.getLamaSewa());
                } else if (pinjam.getMotorPinjam() != null) {
                    System.out.println("Sewa motor " + pinjam.getMotorPinjam().getNamaTransport() + " " + pinjam.getMotorPinjam().getPlatTransportasi());
                    System.out.println("\t\tRp" + pinjam.getMotorPinjam().getHargaSewa() + " x " + pinjam.getLamaSewa());
                }
                System.out.println("Deposit");
                System.out.println("\t\tRp" + pinjam.getDeposit());
                System.out.println("---------------------");
                System.out.println("Total : " + pinjam.getBiaya());
                System.out.println("Peminjaman atas nama " + pinjam.getPelangganPinjam().getNamaPelanggan());
                System.out.println("Harap dikembalikan sebelum " + util.addToDate(pinjam.getTanggalPinjam(), pinjam.getLamaSewa()));
                break;
            }
        }
    }

    /* Nama                 : Jian Jeraus Young
     * NIM                  : 03081210009
     * Deskripsi Singkat    : berfungsi untuk mengembalikan satu transaksi peminjaman dari sebuah kode transaksi
     */
    public static TransaksiPeminjaman cariTransaksiPinjam (String kode, ArrayList<TransaksiPeminjaman> pinjams){
        int i = 0;
        for (TransaksiPeminjaman pinjam : pinjams) {
            if (pinjam.getNomorTransaksi().equalsIgnoreCase(kode)) {
                break;
            }
            i++;
        }
        return pinjams.get(i);
    }

    /* Nama                 : Jian Jeraus Young
     * NIM                  : 03081210009
     * Deskripsi Singkat    : berfungsi untukmenampilkan info mengenai transaksi peminjaman hanya dengan kondisi tertentu
     */
    public static void displayAturanPinjam (String equals1, String kode) throws FileNotFoundException, IOException{
        try (BufferedReader read = new BufferedReader(new FileReader("data/peminjaman.txt"))) {
            String s = "";
            System.out.println("|Kode Transaksi\t|Kode Mobil\t|Kode Penumpang\t|Tanggal Pinjam\t|");
            while ((s = read.readLine()) != null) {
                // System.out.println(s);
                String data[] = s.split(",");
                String nm = data[1].substring(0,1);
                if (data[8].equalsIgnoreCase(equals1) && nm.equalsIgnoreCase(kode)) {
                    for (int i = 0; i < 8; i++) {
                        if ((i == 1) || (i == 2)) {
                            System.out.print(data[i] + "\t\t|"); 
                        } else if (i == 0){
                            System.out.print("|" +data[i] + "\t\t|"); 
                        } else if (i == 4) {
                            System.out.print(data[i] + "\t|"); 
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

}