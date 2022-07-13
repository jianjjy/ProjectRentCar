import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Mobil extends Transportasi{

    private String kodeMobil;
    private String namaMobil;
    private String jenisTransmisi;
    static Scanner input = new Scanner(System.in);
    
    public Mobil() {
    }

    public Mobil(String kodeMobil, String namaMobil, String jenisTransmisi) {
        this.kodeMobil = kodeMobil;
        this.namaMobil = namaMobil;
        this.jenisTransmisi = jenisTransmisi;
    }

    public Mobil(String kodeMobil, String namaMobil, String jenisTransmisi, String PlatTransportasi, int JumlahPenumpang, String StatusMobil, int HargaSewa) {
        this.kodeMobil = kodeMobil;
        this.namaMobil = namaMobil;
        this.jenisTransmisi = jenisTransmisi;
        this.PlatTransportasi = PlatTransportasi;
        this.JumlahPenumpang = JumlahPenumpang;
        this.StatusMobil = StatusMobil;
        this.HargaSewa = HargaSewa;
    }

    public String getNamaMobil() {
        return this.namaMobil;
    }

    public String getKodeMobil() {
        return this.kodeMobil;
    }

    public void setKodeMobil(String kodeMobil) {
        this.kodeMobil = kodeMobil;
    }

    public void setNamaMobil(String namaMobil) {
        this.namaMobil = namaMobil;
    }

    public String getJenisTransmisi() {
        return this.jenisTransmisi;
    }

    public void setJenisTransmisi(String jenisTransmisi) {
        this.jenisTransmisi = jenisTransmisi;
    }

    public static ArrayList<Mobil> updateMobil (ArrayList<Mobil> mobil) throws FileNotFoundException, IOException {
        try (BufferedReader read = new BufferedReader(new FileReader("data/mobil.txt"))) {
            String s = "";
            while ((s = read.readLine()) != null) {
                String data[] = s.split(",");
                mobil.add(new Mobil(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]), data[5], Integer.parseInt(data[6])));
            }
        }
        return mobil;
    }

    public static void updateMobil (String kodeMobil, String status) throws IOException{
        String FilePath = "data/mobil.txt";
        File oldFile = new File ("data/mobil.txt");
        File newFile = new File ("data/temp.txt");
        
        try (BufferedReader br = new BufferedReader(new FileReader("data/mobil.txt"))) {
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String s = "";
            while ((s = br.readLine()) != null) {
                String data[] = s.split(",");
                if (data[0].equalsIgnoreCase(kodeMobil)) {
                    String row = data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," ;
                    row = row + status + "," + data[6];
                    pw.println(row);
                } else {
                    String row = data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5] + "," + data[6];
                    pw.println(row);
                }
            }
            br.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(FilePath);
            newFile.renameTo(dump);
        }
    }

    public static int cekMobil (){
        int total = 0;
        int data[] = new int[4];
        System.out.println("Cek Mobil");
        System.out.println("---------");
        System.out.println("0 jika terpenuhi, 1 jika tidak");
        System.out.print("Minyak di atas 50% : ");
        data[0] = input.nextInt();
        System.out.print("Mesin jalan lancar : ");
        data[1] = input.nextInt()*3;
        System.out.print("Bodi tidak tergores : ");
        data[2] = input.nextInt()*3;
        System.out.print("Interior bersih : ");
        data[3] = input.nextInt();

        for (int i : data) {
            total += i;
        }
        return total;
    }

    public static void displayAturanMobil (String equals) throws FileNotFoundException, IOException{
        try (BufferedReader read = new BufferedReader(new FileReader("data/mobil.txt"))) {
            String s = "";
            System.out.println("|Kode\t|Jenis\t|Transmisi\t|Penumpang\t|Harga\t|");
            while ((s = read.readLine()) != null) {
                // System.out.println(s);
                String data[] = s.split(",");
                
                if (data[5].equalsIgnoreCase(equals)) {
                    for (int i = 0; i < 7; i++) {
                        if ((i == 1) || (i == 6)) {
                            System.out.print(data[i] + "\t|"); 
                        } else if (i == 0){
                            System.out.print("|" +data[i] + "\t|"); 
                        } else if ((i==4) || (i==2) ){
                            System.out.print(data[i] + "\t\t|"); 
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    public static void DaftarMobil (ArrayList<Mobil> mobils) throws Exception {
        System.out.println("Daftar Mobil Baru");
                System.out.println("-----------------");
                System.out.print("Nama mobil : ");
                String namaMobil = input.next();
                System.out.print("Masukkan jumlah penumpang : ");
                int penumpang = input.nextInt();
                System.out.print("Masukkan harga sewa : ");
                int harga = input.nextInt();
                System.out.println("Jenis Transmisi");
                System.out.println("1. Matic");
                System.out.println("2. Manual");
                System.out.print("Masukkan 1/2 : ");
                int pilihanTransmisi = input.nextInt();
                String Transmisi = "";
                if (pilihanTransmisi == 1) {
                    Transmisi = "Matic";
                } else if (pilihanTransmisi == 2) {
                    Transmisi = "Manual";
                } else {
                    throw new Exception("Harus masukkan nilai \"1\" atau \"2\"");
                }
                System.out.print("Masukkan plat transportasi : ");
                String plat1 = input.next();
                String plat2 = input.next();
                String plat3 = input.next();
                String plat = plat1 + " " + plat2 + " " + plat3;

                String kodeMobil = "M0" + Integer.toString(mobils.size()+1);

                //masukkan data pelanggan ke ArrayList
                mobils.add(new Mobil(kodeMobil, namaMobil, Transmisi, plat, penumpang, "Tersedia", harga));
                //masukkan data pelanggan ke file
                try (FileWriter pwMobil = new FileWriter("data/mobil.txt", true)) {
                    pwMobil.append("\n" + kodeMobil + "," + namaMobil + "," + Transmisi + "," + plat + "," + penumpang + "," + "Tersedia" + "," + harga);
                }
    }

}