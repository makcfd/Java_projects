import java.util.concurrent.LinkedTransferQueue;

public class Main {

    public static int calls = 60;

        public static void main (String[]args) throws InterruptedException {

            LinkedTransferQueue<Integer> callsAwait = new LinkedTransferQueue<Integer>();

// ATS Thread
            new Thread(null, () -> {
                while (calls > 0) {
                    callsAwait.add(calls);
                    calls--;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }, "ATS").start();

            Specialist[] allSpecialists = new Specialist[4];
            allSpecialists[0] = new Specialist("Cococo",callsAwait);
            allSpecialists[1] = new Specialist("Brick",callsAwait);
            allSpecialists[2] = new Specialist("Pikachu",callsAwait);
            allSpecialists[3] = new Specialist("Slowpoke",callsAwait);

            for (Specialist thread: allSpecialists) {
                thread.start();
                Thread.sleep(200);
            }
            // Main thread is waiting
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Stopping all threads...");

            for (Specialist thread: allSpecialists) {
                thread.interrupt();
            }

        }
    }

