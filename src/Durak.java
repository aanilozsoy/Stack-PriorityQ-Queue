import java.util.ArrayList;

class Durak {    // durak nesnesi

    String durak_Adi;     // gerekli değişkenler
    int bos_Park_Sayisi;
    int tandem_Bisiklet;
    int normal_Bisiklet;
    ArrayList<Musteri> myArray;

    public String getDurak_Adi() {
        return durak_Adi;
    }   // Getter setterler

    public void setDurak_Adi(String durak_Adi) {
        this.durak_Adi = durak_Adi;
    }

    public void setBos_Park_Sayisi(int bos_Park_Sayisi) {
        this.bos_Park_Sayisi = bos_Park_Sayisi;
    }

    public void setTandem_Bisiklet(int tandem_Bisiklet) {
        this.tandem_Bisiklet = tandem_Bisiklet;
    }

    public void setNormal_Bisiklet(int normal_Bisiklet) {
        this.normal_Bisiklet = normal_Bisiklet;
    }

    public ArrayList<Musteri> getMyArray() {
        return myArray;
    }

    public void setMyArray(ArrayList<Musteri> myArray) {
        this.myArray = myArray;
    }

    public int getBos_Park_Sayisi() {
        return bos_Park_Sayisi;
    }

    @Override
    public String toString() {    // Override lı toString methodu
        return "Boş Park Sayısı: " + getBos_Park_Sayisi() +
                " & Tandem Bisiklet Sayısı: " + getTandem_Bisiklet() +
                " & Normal Bisiklet Sayısı: " + getNormal_Bisiklet();

    }

    public int getTandem_Bisiklet() {
        return tandem_Bisiklet;
    }

    public int getNormal_Bisiklet() {
        return normal_Bisiklet;
    }

    public Durak(){}
    public Durak(String durak_Adi, int bos_Park_Sayisi, int tandem_Bisiklet, int normal_Bisiklet,ArrayList<Musteri> myArray) {  // Constructor
        this.myArray = myArray;
        this.durak_Adi = durak_Adi;
        this.bos_Park_Sayisi = bos_Park_Sayisi;
        this.tandem_Bisiklet = tandem_Bisiklet;
        this.normal_Bisiklet = normal_Bisiklet;
    }

}