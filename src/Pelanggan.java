import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Pelanggan {
    // atribut
    private String kodePelanggan;
    private String namaPelanggan;
    private String noTelpPelanggan;
    private int umurPelanggan;
    private String emailPelanggan;
    private String statusPelanggan; //meminjam, lunas

    //constructors

    public Pelanggan() {
    }

    public Pelanggan(String kodePelanggan, String namaPelanggan, String noTelpPelanggan, int umurPelanggan, String emailPelanggan, String statusPelanggan) {
        this.kodePelanggan = kodePelanggan;
        this.namaPelanggan = namaPelanggan;
        this.noTelpPelanggan = noTelpPelanggan;
        this.umurPelanggan = umurPelanggan;
        this.emailPelanggan = emailPelanggan;
        this.statusPelanggan = statusPelanggan;
    }


    //getter setter

    public String getNamaPelanggan() {
        return this.namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getNoTelpPelanggan() {
        return this.noTelpPelanggan;
    }

    public void setNoTelpPelanggan(String noTelpPelanggan) {
        this.noTelpPelanggan = noTelpPelanggan;
    }

    public int getUmurPelanggan() {
        return this.umurPelanggan;
    }

    public void setUmurPelanggan(int umurPelanggan) {
        this.umurPelanggan = umurPelanggan;
    }

    public String getEmailPelanggan() {
        return this.emailPelanggan;
    }

    public void setEmailPelanggan(String emailPelanggan) {
        this.emailPelanggan = emailPelanggan;
    }


    public String getKodePelanggan() {
        return this.kodePelanggan;
    }

    public void setKodePelanggan(String kodePelanggan) {
        this.kodePelanggan = kodePelanggan;
    }

    public String getStatusPelanggan() {
        return this.statusPelanggan;
    }

    public void setStatusPelanggan(String statusPelanggan) {
        this.statusPelanggan = statusPelanggan;
    }


    @Override
    public String toString() {
        return "{" +
            " namaPelanggan='" + getNamaPelanggan() + "'" +
            ", noTelpPelanggan='" + getNoTelpPelanggan() + "'" +
            ", umurPelanggan='" + getUmurPelanggan() + "'" +
            ", emailPelanggan='" + getEmailPelanggan() + "'" +
            "}";
    }

    public static ArrayList<Pelanggan> updatePelanggan (ArrayList<Pelanggan> pelanggans) throws FileNotFoundException, IOException {
        try (BufferedReader read = new BufferedReader(new FileReader("data/pelanggan.txt"))) {
            String s = "";
            while ((s = read.readLine()) != null) {
                String data[] = s.split(",");
                pelanggans.add(new Pelanggan(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4], data[5]));
            }
        }
        return pelanggans;
    }

    public static void updatePelanggan (String kodePelanggan, String status) throws IOException{
        String FilePath = "data/pelanggan.txt";
        File oldFile = new File ("data/pelanggan.txt");
        File newFile = new File ("data/temp.txt");
        
        try (BufferedReader br = new BufferedReader(new FileReader("data/pelanggan.txt"))) {
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String s = "";
            int i = 0;
            while ((s = br.readLine()) != null) {
                String data[] = s.split(",");
                if (i == 0) {
                    if (data[0].equalsIgnoreCase(kodePelanggan)) {
                        String row = data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + ",";
                        row = row + status;
                        pw.print(row);
                    } else {
                        String row = data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5];
                        pw.print(row);
                    }
                } else {
                    if (data[0].equalsIgnoreCase(kodePelanggan)) {
                        String row =  "\n" + data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + ",";
                        row = row + status;
                        pw.print(row);
                    } else {
                        String row =  "\n" + data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5];
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

}