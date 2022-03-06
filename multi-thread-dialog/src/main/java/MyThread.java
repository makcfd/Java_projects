public class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            System.out.println("I am a thread " + getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                break;
            }
        }
    }
}
