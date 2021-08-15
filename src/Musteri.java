import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.PriorityQueue;

public class Musteri  {

    private String Musteri_adi;
    private int urunSayisi;

    public Musteri() { }

    public Musteri(String Musteri_adi, int urunSayisi) {         //Müşteri constructor'ı
        this.Musteri_adi = Musteri_adi;
        this.urunSayisi = urunSayisi; }

    @Override
    public String toString() {           // toString methodunu override ederek istediğim verileri formatladım

        return "\n" + "Müşterinin adı: " + String.format("%-8s", getMusteri_adi()) + " ---     " + "Ürün sayısı: " + String.format("%8s", getUrunSayisi());
    }

    public int getUrunSayisi() {
        return urunSayisi;
    }

    public String getMusteri_adi() {
        return Musteri_adi;
    }

    public void setMusteri_adi(String musteri_adi) {
        Musteri_adi = musteri_adi;
    }

    public void setUrunSayisi(int urunSayisi) {
        this.urunSayisi = urunSayisi;
    }


}


