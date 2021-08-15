class Stack1 {
    private int maxSize;
    private static Musteri[] stackArray;
    private static int top;

    public Stack1(int s)
    {
        maxSize = s;
        stackArray = new Musteri[maxSize];   // Kuyruğa ait değişkenler yazılı burada
        top = -1;
    }

    public static void push(Musteri X)
    {
        stackArray[++top] = X;   // push methodu yığıtın önce top değerini bir arttırıp sonra veriyi ekliyor
    }
    public static Musteri pop()
    {
        return stackArray[top--]; //pop methodu önce veriyi döndürüp daha sonra top değerini bir azaltıyor
    }

    public static boolean isEmpty()              // Yığıtın içinde eleman olup olmadığını kontrol eden method
    {
        return (top == -1);
    }
}

