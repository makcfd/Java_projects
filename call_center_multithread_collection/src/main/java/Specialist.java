import java.util.concurrent.LinkedTransferQueue;
public class Specialist extends Thread {

        private LinkedTransferQueue<Integer> callsAwait;
        public Specialist(String name, LinkedTransferQueue<Integer> callsQueue) {
            super(name);
            this.callsAwait = callsQueue;
        }

        @Override
        public void run() {
            while (!isInterrupted()) {


                Integer callNum = null;
                try {
                    callNum = callsAwait.take();
                } catch (InterruptedException e) {
                    break;
                }


                System.out.println("I am a thread " + getName() + " taking call " + callNum);

            }
        }
    }
