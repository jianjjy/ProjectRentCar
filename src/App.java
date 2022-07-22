import java.util.ArrayList;
import java.util.Scanner;

public class App  {
    static ArrayList <Mobil> mobils = new ArrayList <>();
    static ArrayList <TransaksiPeminjaman> pinjams = new ArrayList <>();
    static ArrayList <TransaksiPengembalian> kembalis = new ArrayList<>();
    static ArrayList <Pelanggan> pelanggans = new ArrayList<>();
    static ArrayList <Motor> motors = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        util.updateAll(mobils, pinjams, kembalis, pelanggans,motors);
        String yn = "y";
        do {
            util.clearScreen();
            int pilihan = CetakMenu();
            if (pilihan == 1) {
                util.clearScreen();
                System.out.println("Sewa Kendaraan");
                int pilihan2 = CetakSubMenu();
                if (pilihan2 == 1) {
                    Mobil.SewaMobil(mobils, pinjams, pelanggans);
                } else if (pilihan2 == 2){
                    Motor.SewaMotor(motors, pinjams, pelanggans);
                } else throw new Exception("Harus masukkan nilai \"1\" sampai \"2\"");

            } else if (pilihan == 2) {
                util.clearScreen();
                System.out.println("Kembali Kendaraan");
                int pilihan2 = CetakSubMenu();
                if (pilihan2 == 1) {
                    Mobil.kembaliMobil(mobils, pinjams, pelanggans, kembalis);
                } else if (pilihan2 == 2){
                    Motor.kembaliMotor(motors, pinjams, pelanggans, kembalis);
                } else throw new Exception("Harus masukkan nilai \"1\" sampai \"2\"");

            } else if (pilihan == 3) {
                util.clearScreen();
                System.out.println("Cetak Kendaraan Tersedia");
                int pilihan2 = CetakSubMenu();
                if (pilihan2 == 1) {
                    Mobil.displayAturanMobil("Tersedia");
                } else if (pilihan2 == 2){
                    Motor.displayAturanMotor("Tersedia");
                } else throw new Exception("Harus masukkan nilai \"1\" sampai \"2\"");

            } else if (pilihan == 4) {
                util.clearScreen();
                System.out.println("Transaksi Berlangsung");
                int pilihan2 = CetakSubMenu();
                if (pilihan2 == 1) {
                    TransaksiPeminjaman.displayAturanPinjam("Meminjam", "m");
                } else if (pilihan2 == 2){
                    TransaksiPeminjaman.displayAturanPinjam("Meminjam", "n");
                } else throw new Exception("Harus masukkan nilai \"1\" sampai \"2\"");
                
            } else if (pilihan == 5) {
                util.clearScreen();
                System.out.println("Daftar Kendaraan Baru");
                int pilihan2 = CetakSubMenu();
                if (pilihan2 == 1) {
                    Mobil.DaftarMobil(mobils);
                } else if (pilihan2 == 2){
                    Motor.DaftarMotor(motors);
                } else throw new Exception("Harus masukkan nilai \"1\" sampai \"2\"");
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
        System.out.println("Menu Rental Kendaraan");
        System.out.println("-----------------");
        System.out.println("1. Sewa Kendaraan");
        System.out.println("2. Kembali Kendaraan");
        System.out.println("3. Cetak kendaraan yang tersedia");
        System.out.println("4. Cetak transaksi berlangsung");
        System.out.println("5. Daftar Kendaraan Baru");
        System.out.println("6. Keluar");
        System.out.print("Masukkan pilihan anda : ");
        int pilihan = input.nextInt();
        return pilihan;
    }

    public static int CetakSubMenu () {
        System.out.println("Tentukan Kendaraan");
        System.out.println("-----------------");
        System.out.println("1. Mobil");
        System.out.println("2. Motor");
        System.out.print("Masukkan pilihan anda : ");
        int pilihan = input.nextInt();
        return pilihan;
    }
}   