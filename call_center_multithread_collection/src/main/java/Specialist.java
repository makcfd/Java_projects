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
                Integer callNum = callsAwait.poll();

                System.out.println("I am a thread " + getName() + " taking call " + callNum);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
