import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        TaskCallable collableTask1 = new TaskCallable("-- A --");
        TaskCallable collableTask2 = new TaskCallable("-- B --");
        TaskCallable collableTask3 = new TaskCallable("-- C --");
        TaskCallable collableTask4 = new TaskCallable("-- D --");

        final ExecutorService pool = Executors.newFixedThreadPool(4);


        List<TaskCallable> taskList = Arrays.asList(collableTask1,
                                                    collableTask2,
                                                    collableTask3,
                                                    collableTask4);

        List<Future<ResultCallable>> futures = pool.invokeAll(taskList);

        for (Future<ResultCallable> task: futures) {

            System.out.println("Thread: " + task.get().getWorkerName() +
                                " send: " + task.get().getNum() + " messages.");
        }

        // reusing the pool for invokeany() call
        ResultCallable futureBest = pool.invokeAny(taskList);

        System.out.println("The best thread: " + futureBest.getWorkerName() +
                " send " + futureBest.getNum() + " messages");

        pool.shutdown();
    }
}
