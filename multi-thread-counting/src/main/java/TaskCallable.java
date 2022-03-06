import java.util.Random;
import java.util.concurrent.Callable;

public class TaskCallable implements Callable<ResultCallable> {

    private final String name;
    int msgCount=0;

    Random rand = new Random();
    int upperbound = 25;
    int int_random = rand.nextInt(upperbound);

    public TaskCallable(String name) {
        this.name = name;
    }

    @Override
    public ResultCallable call() throws Exception {
        ResultCallable res = new ResultCallable();

        Thread.currentThread().setName(this.name);
        while(int_random > msgCount) {
            Thread.sleep(2000);
            msgCount+=1;
            System.out.println("The thread " +
                    Thread.currentThread().getName() +
                    " sending message №: "
                    + msgCount);
        }
        res.setNum(msgCount);
        res.setWorkerName(Thread.currentThread().getName());
        return res;
    }
}
