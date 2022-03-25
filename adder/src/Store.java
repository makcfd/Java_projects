import java.util.Random;

public class Store {
    Random rd = new Random();
    int[] arr;

    public Store(int n) {
        this.arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = rd.nextInt(2);
        }
    }

    public int[] getArr() {
        return arr;
    }
}

