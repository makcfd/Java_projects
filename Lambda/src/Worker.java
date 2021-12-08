public class Worker {

    private OnTaskDoneListener callback;
    private OnTaskErrorListener errorCallback;

    public Worker(OnTaskDoneListener callback) {
        this.callback = callback;
    }

    public void start() {
        for (int i = 0; i < 100; i++) {
            if (i != 33) {
                callback.onDone("Task " + i + " is done");
            } else {
                OnTaskErrorListener brokenTask = System.out::println;
                brokenTask.onError("===================");
                brokenTask.onError("Task " + i + " is DAMAGED");
                brokenTask.onError("===================");
            }
        }
    }
}
