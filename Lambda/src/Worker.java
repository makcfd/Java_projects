public class Worker {

    private OnTaskDoneListener callback;
    private OnTaskErrorListener errorCallback;

    public Worker(OnTaskDoneListener callback, OnTaskErrorListener errorCallback) {
        this.callback = callback;
        this.errorCallback = errorCallback;
    }

    public void start() {
        for (int i = 0; i < 100; i++) {
            if (i != 33) {
                callback.onDone("Task " + i + " is done");
            } else {
                //OnTaskErrorListener brokenTask = System.out::println;
                errorCallback.onError("===================");
                errorCallback.onError("Task " + i + " is DAMAGED");
                errorCallback.onError("===================");
            }
        }
    }
}
