import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class Node {     // Node sınıfı
    Durak key;
    Node leftChild, rightChild;

    public Node(Durak item){
        key = item;
        leftChild = rightChild = null;
    }
}

class BinaryTree {   // BinaryTree sınıfı
    Node root;

    public void insert(Durak durak){  //BinaryTree mantığında çalışan insert methodu

        Node node = new Node(durak);
        if(root == null){  // Kök boş işe yollanan argümanın root'a ataması yapılır.
            root = node;

        }
        else { // Kök boş değilse
            Node current = root;
            Node parent;
            while(true){
                parent = current;
                int a = durak.getDurak_Adi().compareTo(current.key.getDurak_Adi());  // compareTo methodu ile stringler karşılaştırılır
                if(a < 0){ //compareTo sonucu negatif ise
                   current = current.leftChild;  //Argüman olan durağın ismi kökteki duraktan önce geliyorsa leftChilde atanır
                   if ( current == null){
                       parent.leftChild =node;
                       return;
                   }
                }
                else if( a > 0){ //compareTo sonucu pozitif ise
                    current = current.rightChild; //Argüman olan durağın ismi kökteki duraktan sonra geliyorsa rightChilde atanır
                    if(current == null){
                        parent.rightChild = node;
                        return;
                    }
                }
            }
        }

    }

    public void inOrder(Node localRoot){ //Durakları konsolda rahatça görmek için yazdırma işlemini inOrder şeklinde yazdım
        if(localRoot != null)
        {
            inOrder(localRoot.leftChild); // recursive olarak methodun çağırılması sağlandı
            System.out.print(localRoot.key.getDurak_Adi() + " Durağı" + "\n");  //Önce durak adını yazdırıyorum
            System.out.println("----------------------");
            System.out.println("Durağa ait tüm bilgiler: ");
            System.out.println(localRoot.key.toString());  //toString ile durağa ait bilgiler yazılıyor
            System.out.println(localRoot.key.getDurak_Adi() + " durağında kiralama işlemi yapan müşteri sayısı: " + localRoot.key.myArray.size());
            System.out.println("Kiralama işlemi yapan müşterilerin bilgileri: ");
            System.out.println();
            for(int i =0;i<localRoot.key.myArray.size();i++){
                System.out.println(localRoot.key.myArray.get(i).toString());
            }
            System.out.println();
            inOrder(localRoot.rightChild); // rightChild içinde recursive yapıldı
        }
    }
    public void musterinin_islemleri(Node localRoot, int a){ // Hangi müşterinin hangi işlemi yaptığını görmemizi sağlayan method
        if(localRoot != null){
            musterinin_islemleri(localRoot.leftChild,a);
            for(int i =0; i< localRoot.key.myArray.size();i++){
                if( a == localRoot.key.myArray.get(i).getMusteri_ID()){ // ID'si , hangi duraktan hangi saatte kiralama işlemi yaptığı bilgileri
                    System.out.println(a + " ID'li müşteri " + localRoot.key.getDurak_Adi() + " durağından saat " + localRoot.key.myArray.get(i).getKiralama_Saati() + "'ta/te bisiklet kiralamıştır.");
                }

            }
            musterinin_islemleri(localRoot.rightChild,a);
        }
    }
    public void kiralama_Islemi(Node localRoot, String durak, int musteri_id){ // Kiralama methodu
        if(localRoot !=null){
            kiralama_Islemi(localRoot.leftChild,durak,musteri_id);  //Seçilen durağı tarıyorum önce
            kiralama_Islemi(localRoot.rightChild,durak,musteri_id);
            if(durak.equals(localRoot.key.getDurak_Adi())){ // Seçilen durağı bulduğum zaman
                System.out.println(durak + " durağına ait boş park sayısı: " + localRoot.key.getBos_Park_Sayisi()); //Önce duraktaki boş park sayısını yazdırıyorum
                System.out.println();
                int temp_Saat = RandomNumber2(0,24); // Bu kısımda tamamen kendi yöntemimle random saat ve dakika üretip,birleştirip stringe çeviriyorum
                int temp_Dakika = RandomNumber2(0,60);

                String s1 = String.valueOf(temp_Saat);
                if(temp_Saat<10){
                    s1 = String.valueOf(temp_Saat);
                    s1 = "0" + s1;
                }
                String s2 = String.valueOf(temp_Dakika);
                if(temp_Dakika <10){
                    s2 = String.valueOf(temp_Dakika);
                    s2 = "0" + s2;
                }
                String temp_kiralama_saati = s1 + ":" + s2;
                Musteri yeni_musteri = new Musteri(musteri_id,temp_kiralama_saati);
                localRoot.key.myArray.add(yeni_musteri); // Durağımdaki List'e oluşan random müşteriyi ekliyorum
                localRoot.key.bos_Park_Sayisi += 1; // Boşpark sayısı güncelleniyor
                System.out.println("Kiralama işleminden sonra " + durak + " istasyonundaki güncellenmiş bilgiler");
                System.out.println();
                for(int a =0; a < localRoot.key.myArray.size(); a++ ){ // Kiralama işleminden sonra değişen değerleri yazdırıyorum
                    System.out.println(localRoot.key.myArray.get(a).toString());
                }
                System.out.println();
                System.out.println("Duraktaki güncellenmiş boş park alan sayısı: " + localRoot.key.getBos_Park_Sayisi());

            }
        }
    }
    public int maxDepth(Node node){  // Ağacın derinliğini bulan method, A4 kağıdına yazıp
        if (node == null)           // ve debug yapıp test ettiğim kod doğru değeri döndürmektedir
            return 0;
        else
        {
            int left_Depth = maxDepth(node.leftChild); // recursive ile sürekli bir sol çocuk daha var mı veya
            int right_Depth = maxDepth(node.rightChild); // sağ çocuk var mı diye kontrol edilir

            if (left_Depth > right_Depth)
                return (left_Depth + 1);
            else
                return (right_Depth + 1);
        }
    }
    public static int RandomNumber2(int x, int y) {
        return ThreadLocalRandom.current().nextInt(x, y); // range değer almış(x,y) random metodu
    }
}

class Uygulama {  // 1. soruda 2. soruda istenenlerin uygulandığı main.java kısmı
    public static void main(String[] args){

        String[] duraklar = {"Inciraltı, 28, 2, 10", "Sahilevleri, 8, 1, 11", "Doğal Yaşam Parkı, 17, 1, 6", "Bostanlı İskele, 7, 0, 5","Uçkuyular, 8, 1, 8","Güzelyalı, 15, 0, 4","Karantina, 21, 3, 6","Cesme, 13, 5, 2","Liman, 24, 2, 6"};

        BinaryTree DurakAgaci = new BinaryTree();

        ArrayList<String> durak_Adlari = new ArrayList<>();  // Durak adları ve müşteriler değişken sayıda olduğu için List yapısı oluşturdum
        ArrayList<Musteri> Musteriler = new ArrayList<Musteri>();
        Hashtable<String, Durak> durak_Hash = new Hashtable<String, Durak>();

        for (String value : duraklar) {

            String[] s = value.split(", ");  // duraklar listenini ", " ile elementlerine ayırdım.
            String temp_durak_Adi = s[0];
            int temp_bos_park_sayisi = Integer.parseInt(s[1]); //elementleri dönüştürerek atamasını yaptım.
            int temp_tandem_bisiklet = Integer.parseInt(s[2]);
            int temp_normal_bisiklet = Integer.parseInt(s[3]);
            int temp_toplam_bisiklet = temp_normal_bisiklet + temp_tandem_bisiklet;

            int musteri_sayisi = RandomNumber(1,11); // random sayıda müşteri sayısı üretimi

            for(int j =0;j< musteri_sayisi;j++){

                int temp_id = RandomNumber(1,21);
                int temp_Saat = RandomNumber(0,24);  // random sayıda ID ve saat üretimi
                int temp_Dakika = RandomNumber(0,60);

                String s1 = String.valueOf(temp_Saat);
                if(temp_Saat<10){
                    s1 = String.valueOf(temp_Saat);   //Burası saatin ve dakikanın güzel gözükmesi için ekstra yazdığım bir yer
                    s1 = "0" + s1;                    //Örneğin saat 1:1 gözükmek yerine 01:01 olarak gözükecektir.
                }
                String s2 = String.valueOf(temp_Dakika);
                if(temp_Dakika <10){
                    s2 = String.valueOf(temp_Dakika);
                    s2 = "0" + s2;
                }
                String temp_kiralama_saati = s1 + ":" + s2;
                Musteri Musteri = new Musteri(temp_id,temp_kiralama_saati); // oluşan random değerleri constructor a yolladım ve objeleri oluşturdum
                Musteriler.add(Musteri); // Oluşan objeleri List'e attım

            }
            ArrayList<Musteri> clone_list = new ArrayList<Musteri>(); // Müşterileri clonelayıp durağın içine gömdüm.
            clone_list.addAll(Musteriler);
            temp_bos_park_sayisi = temp_bos_park_sayisi+musteri_sayisi;

            Durak Durak = new Durak(temp_durak_Adi, temp_bos_park_sayisi, temp_tandem_bisiklet, temp_normal_bisiklet,clone_list);


            durak_Adlari.add(Durak.getDurak_Adi()); // Durak objelerini de oluşturup gerekli List'e attım
            Musteriler.clear();
            DurakAgaci.insert(Durak);
            durak_Hash.put(Durak.getDurak_Adi(),Durak);



        }
        System.out.println("inOrder methoduyla durakları yazdırma işlemi"); // kodun aşağıda kalan kısmı tamamen çıktılardan oluşmaktadır.
        System.out.println("-----------------------------------------------");
        System.out.println("Ağacın maksimum derinliği: " + DurakAgaci.maxDepth(DurakAgaci.root));
        System.out.println();
        DurakAgaci.inOrder(DurakAgaci.root);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Müşteri ID'si giriniz: ");
        int id = scanner.nextInt();
        DurakAgaci.musterinin_islemleri(DurakAgaci.root,id);
        System.out.println();

        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Bisiklet kiralanacak durağı giriniz: ");
        String durak_kira = scanner.next();
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Bisiklet kiralayan müşterinin ID'sini giriniz: ");
        int id_kira = scanner.nextInt();
        DurakAgaci.kiralama_Islemi(DurakAgaci.root,durak_kira,id_kira);
        System.out.println("--------------------------------------------------------------");
        System.out.println();
        System.out.println("----Hashtable çalışması----");
        System.out.println();
        System.out.println("Duraklara 5'er adet bisiklet eklemeden önce boş park sayısı: ");
        System.out.println();
        System.out.println(String.format("%-15s","Durak adı") + String.format("%20s","Boş Park Sayısı") );
        System.out.println(String.format("%-15s","---------") + String.format("%20s", "---------------"));
        for(String a : durak_Hash.keySet()){
            System.out.println(String.format("%-20s",a) +  String.format("%8d",durak_Hash.get(a).getBos_Park_Sayisi()));
        }

        System.out.println("----------------------------------------------------");
        System.out.println();
        for (String s : durak_Adlari) {
            if (durak_Hash.get(s).getBos_Park_Sayisi() > 5) {
                durak_Hash.replace(s, new Durak(durak_Hash.get(s).getDurak_Adi(), (durak_Hash.get(s).getBos_Park_Sayisi()) - 5, durak_Hash.get(s).getTandem_Bisiklet(), (durak_Hash.get(s).getNormal_Bisiklet() + 5), durak_Hash.get(s).getMyArray()));
            }
        }
        System.out.println("Duraklara 5'er adet bisiklet eklendikten sonra boş park sayısı: ");
        System.out.println();
        System.out.println(String.format("%-15s","Durak adı") + String.format("%20s","Boş Park Sayısı") );
        System.out.println(String.format("%-15s","---------") + String.format("%20s", "---------------"));
        for(String itm : durak_Hash.keySet()){
            System.out.println(String.format("%-20s",itm) + String.format("%8d",durak_Hash.get(itm).getBos_Park_Sayisi()));
        }






    }
    public static int RandomNumber(int x, int y) {
        return ThreadLocalRandom.current().nextInt(x, y);
    }
}



