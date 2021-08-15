import java.util.ArrayList;
import java.util.List;
class PriorityQ {
        private int maxSize;
        List<Musteri> pqList;
        List<Musteri> pqList2;
        private int nItems;

        public PriorityQ(int s) {              // Main.java içerisinde takip çok zorlaştığı için priority q'yu burada çalıştırdım
                pqList2 = new ArrayList<>(maxSize);
                pqList = new ArrayList<>(maxSize);      // 2 farklı delete methodu istendiği için 2 adet List oluşturuyorum burada
                nItems = 0;
        }
        public void insert(Musteri item){
                pqList.add(item);           // O(1) zamanlı ekleme yapıyorum
                nItems++;
        }
        public void insert2(Musteri item){
                pqList2.add(item);
                nItems++;
        }

        public void delete() {              // En büyük elemanı aratarak ilk önce onun silinmesi sağlıyorum
                int max = 0;

                for (int i = 0; i < pqList.size(); i++) {
                        if (pqList.get(max).getUrunSayisi() < pqList.get(i).getUrunSayisi()) {
                                max = i;
                        }
                }
                Musteri deleted = pqList.get(max);
                pqList.remove((deleted));
                System.out.print(deleted.toString());

        }
        public void delete_Low_First(){        // Burada ise en küçük elemanı bulup,silip ekrana yazdırdım

                int min = 0;
                for(int i =0;i<pqList2.size();i++){
                        if(pqList2.get(min).getUrunSayisi() > pqList2.get(i).getUrunSayisi()){
                                min = i;
                        }
                }
                Musteri deleted2 = pqList2.get(min);
                pqList2.remove((deleted2));
                System.out.print(deleted2.toString());
        }

        public boolean isEmpty1() {
                return (pqList.isEmpty());
        }
        public boolean isEmpty2(){
                return (pqList2.isEmpty());
        }
}
class PriorityQ_Uygulama{
        public static void main(String[] args){
                String[] Musteriler4 = {"Ali", "Merve", "Veli", "Gülay", "Okan", "Zekiye", "Kemal", "Banu", "İlker", "Songül", "Nuri", "Deniz"};
                int[] Urunsayisi4 = {8, 11, 16, 5, 15, 14, 19, 3, 18, 17, 13, 15};
                PriorityQ PQ_example = new PriorityQ(Musteriler4.length);        //Verileri elde tutacağım eferans Queue lerimi oluşturdum
                PriorityQ PQ_example2 = new PriorityQ(Musteriler4.length);
                for(int i=0;i<Musteriler4.length;i++){
                        Musteri musteri4 = new Musteri(Musteriler4[i], Urunsayisi4[i]);   // Müşteri objelerini oluşturup kuyruğa ekledim.
                        PQ_example.insert(musteri4);

                }
                for(int j =0;j<Musteriler4.length;j++){
                        Musteri mus = new Musteri(Musteriler4[j], Urunsayisi4[j]);     // Müşteri objelerini oluşturup kuyruğa ekledim.
                        PQ_example2.insert2(mus);
                }

                System.out.println("Priority Q'dan atılan Objeler");
                while(!PQ_example.isEmpty1()) {                    // büyük elemanın önce silinip yazdırıldığı döngü
                        PQ_example.delete();
                }
                System.out.println();
                System.out.println();
                System.out.println("Artan sırayla nesneleri çıkartma işlemi");
                while(!PQ_example2.isEmpty2()){                   // küçük elemanın önce silinip yazdırıldığı döngü
                        PQ_example2.delete_Low_First();
                }


        }
}

