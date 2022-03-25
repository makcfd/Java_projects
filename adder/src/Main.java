import java.util.concurrent.atomic.LongAdder;

public class Main {

        public static void main(String[] args) throws InterruptedException {

            Store store1 = new Store(5);
            Store store2 = new Store(3);
            Store store3 = new Store(2);

            LongAdder stat = new LongAdder();

            Thread thread1 = new Thread(null, () -> {
                for (int i: store1.getArr()) {
                    System.out.println(i);
                    stat.add(i);
                }
            }, "Earned in store 1");

            Thread thread2 = new Thread(null, () -> {
                System.out.println("-------");
                for (int j: store2.getArr()) {
                    System.out.println(j);
                    stat.add(j);
                }
            }, "Earned in store 2");

            Thread thread3 = new Thread(null, () -> {
                System.out.println("-------");
                for (int k: store3.getArr()) {
                    System.out.println(k);
                    stat.add(k);
                }
            }, "Earned in store 2");

            thread1.start();

            thread2.start();
            thread3.start();
            thread3.join();
            thread2.join();
            thread1.join();

            System.out.println("\nResult: " + stat.sum());
        }
}
