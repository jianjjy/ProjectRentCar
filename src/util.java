import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class util {
    public static String changeToDate (String tanggal) throws ParseException{
        Calendar cal = Calendar.getInstance();
            //string diubah ke date dan calender
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        cal.setTime(date.parse(tanggal));
            //date ubah balek ke string
        tanggal = date.format(cal.getTime());
        return tanggal;
    }

    public static String addToDate (String tanggal, int durasi) throws ParseException{
        Calendar cal = Calendar.getInstance();
            //string diubah ke date dan calender
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        cal.setTime(date.parse(tanggal));
        //nambah tanggal
        cal.add(Calendar.DAY_OF_MONTH, durasi);
            //date ubah balek ke string
        tanggal = date.format(cal.getTime());
        return tanggal;
    }

    public static int getDifferenceDays(String date1, String date2) throws ParseException {
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
        Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse(date2);
        long diff = d1.getTime() - d2.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static void updateAll ( ArrayList <Mobil> mobils,ArrayList <TransaksiPeminjaman> pinjams, ArrayList <TransaksiPengembalian> kembalis, ArrayList <Pelanggan> pelanggans) throws FileNotFoundException, IOException, ParseException{
        mobils.removeAll(mobils);
        Mobil.updateMobil(mobils);
        pelanggans.removeAll(pelanggans);
        Pelanggan.updatePelanggan(pelanggans);
        pinjams.removeAll(pinjams);
        TransaksiPeminjaman.updatePinjam(pinjams);
        kembalis.removeAll(kembalis);
        TransaksiPengembalian.updateKembali(kembalis);
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}