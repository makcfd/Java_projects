public class Main {

    public static volatile boolean boxClosed = false;
    public static int rounds = 10;

    public static void main(String[] args) {

        new Thread(null,()->{
            while (!boxClosed) {
                System.out.println("Closing the box");
                boxClosed = true;
                Thread.yield();
            }
        }, "Box").start();

        new Thread(null, ()->{
            while(boxClosed) {

                System.out.println("Opening the box");
                boxClosed = false;
                rounds--;
                Thread.yield();
                //System.out.println("Round number: " + rounds);
            }
        },"Gamer").start();


    }
}
