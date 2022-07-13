public abstract class Transportasi{

    String PlatTransportasi;
    int JumlahPenumpang;
    String StatusMobil;
    int HargaSewa;


    public Transportasi() {
    }

    public Transportasi(String PlatTransportasi, int JumlahPenumpang, String StatusMobil, int HargaSewa) {
        this.PlatTransportasi = PlatTransportasi;
        this.JumlahPenumpang = JumlahPenumpang;
        this.StatusMobil = StatusMobil;
        this.HargaSewa = HargaSewa;
    }

    public String getPlatTransportasi() {
        return this.PlatTransportasi;
    }

    public void setPlatTransportasi(String PlatTransportasi) {
        this.PlatTransportasi = PlatTransportasi;
    }

    public int getJumlahPenumpang() {
        return this.JumlahPenumpang;
    }

    public void setJumlahPenumpang(int JumlahPenumpang) {
        this.JumlahPenumpang = JumlahPenumpang;
    }

    public String getStatusMobil() {
        return this.StatusMobil;
    }

    public void setStatusMobil(String StatusMobil) {
        this.StatusMobil = StatusMobil;
    }

    public int getHargaSewa() {
        return this.HargaSewa;
    }

    public void setHargaSewa(int HargaSewa) {
        this.HargaSewa = HargaSewa;
    }
}