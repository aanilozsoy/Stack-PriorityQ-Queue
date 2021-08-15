import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Main {

    public static void main(String[] args) {
        String[] Musteriler = {"Ali", "Merve", "Veli", "Gülay", "Okan", "Zekiye", "Kemal", "Banu", "İlker", "Songül", "Nuri", "Deniz"};
        int[] Urunsayisi = {8, 11, 16, 5, 15, 14, 19, 3, 18, 17, 13, 15};
        List<Musteri> main_List = new ArrayList<>();       // Bize verilen array içerisindeki Musteri adı ve ürün sayısını tutmak için
                                                           // genel bir List oluşturdum.


        for (int k = 0; k < Musteriler.length; k++) {          // for döngüsü ile listelerdeki verilerden objelerimi oluşturdum ve bunları List'e attım
            Musteri musteri = new Musteri(Musteriler[k], Urunsayisi[k]);
            main_List.add(musteri);
        }
        int listedeki_musteri_sayisi = main_List.size();    //Müşterimi sayımı bu değişkende tuttum.

        System.out.println("1. sorunun a şıkkı için output (Bileşik Veri)");
        System.out.println("Müşteri bilgileri");
        for (Musteri musteri : main_List) {             //Bu döngü sahip olduğum objelerimi yazdırıyor ekranda görüp kontrol etmek için
            System.out.print(musteri.toString());
        }

        System.out.println();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();

        ArrayList<List<Musteri>> musteri_list = Musterilerin_tutuldugu_arrayList(main_List); /*Burada tanımladığım method bizden istenen random sayılarla oluşan generic
                                                                                            listler oluşturp bileşke veri yapısını oluşturdum
                                                                                                  */
        System.out.println("1. soruda generic listlerden oluşturulması istenen Array");
        musteri_list.forEach(System.out::println);


        System.out.println();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
        System.out.println("1. sorunun C şıkkında istenen değerler");
        System.out.println();
        arraylistin_Degerleri(musteri_list, listedeki_musteri_sayisi);   // Bu mothod bize arraylistte kaç adet generic list olduğunu ve ortalama eleman sayısını veriyor
        System.out.println();
        System.out.println();
        System.out.println("Queue sınıfı için çalışma");

        Queue1 Queue = new Queue1(main_List.size());              // Müşteri objelerini Kuyruk yapısında saklamak için 2 adet kuyruk oluşturdum.

        for (Musteri musteri : main_List) {
            Musteri musteri2 = new Musteri(musteri.getMusteri_adi(), musteri.getUrunSayisi()); // Burada ekleme yaptım
            Queue.insert(musteri2);
        }
        Queue1 Queue2 = new Queue1(main_List.size());

        for (Musteri musteri : main_List) {
            Musteri musteri3 = new Musteri(musteri.getMusteri_adi(), musteri.getUrunSayisi()); //İlk kuyrukta remove yaptığım için verileri kaybediyordum ben de ikinci kopya kuyruk oluşturdum
            Queue2.insert(musteri3);                                                          //  ben de ikinci kopya kuyruk oluşturdum.
        }

        double islem_suresi2 = 0;
        double musteri_sayisi2 = 0;                //Bu kısım Queue sınıfı için Müşterilerin ortalama işlem sürelerini hesapladı.

        while(!Queue2.isEmpty()){
            Musteri temp = Queue2.remove();
            islem_suresi2 += temp.getUrunSayisi(); // ürün sayılarını bir değişkende topladım
            musteri_sayisi2 += 1;                  //Müşteri sayısını da her bir müşteri de bir arttırdım
        }
        double ortalama_islem = islem_suresi2 / musteri_sayisi2;

        System.out.println("Queue içinden atılan Müşteri Objeleri");
        System.out.println("------------------------------------------------------");
        while (!Queue.isEmpty()) {                                                         //OUTPUT
            System.out.print(Queue.remove());
        }
        System.out.println();
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println();
        System.out.println("Stack sınıfı için çalışma");

        Stack1 stack = new Stack1(main_List.size());
        for (Musteri musteri : main_List) {                          //Bu kısımda müşteri objelerini Stack içinde tuttuğum
            Musteri musteri3 = new Musteri(musteri.getMusteri_adi(), musteri.getUrunSayisi());
            stack.push(musteri3);
        }
        System.out.println("Stack içinden atılan Müşteri Objeleri");
        System.out.println("------------------------------------------------------");
        while (!stack.isEmpty()) {                                                   //OUTPUT
            System.out.print(stack.pop());

        }
        System.out.println();
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println();
        System.out.println("4. sorunun b şıkkında istenen ortalama işlem süresi: " + String.format("%.2f",ortalama_islem));



    }

    public static int RandomNumber() {                          // Random sayı üretmek için kullanmış olduğum method
        return ThreadLocalRandom.current().nextInt(1, 5 + 1);
    }

    public static ArrayList<List<Musteri>> Musterilerin_tutuldugu_arrayList(List<Musteri> list) {  //Burada main_List i güncelleyerek soruda benden istenen generic listlerden oluşan arraylist ürettim
        int liste_boyutu = list.size();   // Listedeki eleman sayısını liste boyutu olarak aldım  //istenen generic listlerden oluşan arraylist ürettim
        int counter = 0;   //sayaç ekledim
        ArrayList<List<Musteri>> a_list = new ArrayList<>(); //Boş bir List oluşturdum

        while (liste_boyutu != 0) {                  //Listede eleman kaldığı sürece dönecek olan bir while
            List<Musteri> temp_list = new ArrayList<>();  // Arraylist içindeki her liste için generic list
            if (liste_boyutu > 5) {                 //5ten fazla elemanlık bir liste geldiği zaman bunu random sayılı genericlistlere paylaştırdım
                for (int i = 0; i < RandomNumber(); i++) {
                    temp_list.add(list.get(counter));
                    counter++;
                    liste_boyutu--;
                }
            } else {
                for (int j = 0; j < liste_boyutu; j++) {   //Kalan eleman sayısı 5'ten küçük olunca direkt sonraki generic liste atama yaptım.
                    temp_list.add(list.get(counter));
                    counter++;
                    liste_boyutu--;
                }
            }
            a_list.add(temp_list);
        }
        return a_list;


    }

    public static void arraylistin_Degerleri(ArrayList<List<Musteri>> array_liste, int a) {

        System.out.println("1. Arraylistin boyutu (Generic Liste sayısı): " + array_liste.size());       //ArrayListteki genericList sayısını veren output
        System.out.println("2. şıkta istenen ortalama eleman değeri: " + a + " / " + array_liste.size() + " = " + String.format("%.2f", a / (double) array_liste.size())); //İstenen ortalama eleman değeri

    }



}




