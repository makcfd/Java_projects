public class Main {

    public static volatile boolean boxClosed = false;
    public static int rounds = 10;

    public static void main(String[] args) {

        new Thread(null,()->{
            while (rounds>0) {
                if (!boxClosed) {
                System.out.println("Closing the box");
                boxClosed = true;
                rounds--;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.yield();
            }

        }, "Box").start();

        new Thread(null, ()->{
            while(rounds>0) {
                if(boxClosed) {
                System.out.println("Opening the box");
                boxClosed = false;
                rounds--;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.yield();
            }
        },"Gamer").start();


    }
}
