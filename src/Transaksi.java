public abstract class Transaksi{
    // atribut
    String nomorTransaksi;
    int biaya;
    int lamaSewa;

    // constructor
    public Transaksi() {
    }

    public Transaksi(String nomorTransaksi, int biaya, int lamaSewa) {
        this.nomorTransaksi = nomorTransaksi;
        this.biaya = biaya;
        this.lamaSewa = lamaSewa;
    }

    // setter getter
    public String getNomorTransaksi() {
        return this.nomorTransaksi;
    }

    public void setNomorTransaksi(String nomorTransaksi) {
        this.nomorTransaksi = nomorTransaksi;
    }

    public int getBiaya() {
        return this.biaya;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public int getLamaSewa() {
        return this.lamaSewa;
    }

    public void setLamaSewa(int lamaSewa) {
        this.lamaSewa = lamaSewa;
    }

    @Override
    public String toString() {
        return "{" +
            " nomorTransaksi='" + getNomorTransaksi() + "'" +
            ", biaya='" + getBiaya() + "'" +
            ", lamaSewa='" + getLamaSewa() + "'" +
            "}";
    }

}