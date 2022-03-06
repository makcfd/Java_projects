

public class Main {

    public static void main(String[] args) throws InterruptedException {

        MyThread[] allThreads = new MyThread[4];
        allThreads[0] = new MyThread("Cococo");
        allThreads[1] = new MyThread("Brick");
        allThreads[2] = new MyThread("Pikachu");
        allThreads[3] = new MyThread("Slowpoke");

        for (MyThread thread: allThreads) {
            thread.start();
        }
        // Main thread is waiting
        Thread.sleep(7000);
        System.out.println("Stopping all threads...");

        for (MyThread thread: allThreads) {
            thread.interrupt();
            }

    }
}
