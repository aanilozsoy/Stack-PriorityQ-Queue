public class Queue1 {

        private int maxSize;
        private Musteri[] queArray;
        private int front;
        private int rear;
        private int nItems;

        public Queue1(int s) {
            maxSize = s;
            queArray = new Musteri[maxSize];
            front = 0;
            rear = -1;
            nItems = 0;
        }


        public void insert(Musteri j)
        {
            if(rear == maxSize-1)         // Kullanıcı int s değerini 0 girerse diye
                rear = -1;
            queArray[++rear] = j;        // Eleman eklendiği zaman rear bir arttırılır ve veri atamsı yapılır
            nItems++;         // kuyruktaki eleman sayısı da bir arttırılır
        }

        public Musteri remove()
        {
            Musteri temp = queArray[front++];      // ilk sıradaki veriyi geçiçi olarak atarız ve , front kısmını bir adım öne taşırız.
            if(front == maxSize)          // front size'a eşit olduğu zaman artık front 0'dır
                front = 0;
            nItems--;             // kuyruktaki eleman sayısı da bir azaltılır
            return temp;
        }

        public boolean isEmpty()
        {
            return (nItems==0);
        }
}


