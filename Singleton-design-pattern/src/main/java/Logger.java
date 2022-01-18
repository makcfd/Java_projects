public class Logger {

    protected int num = 1;

    // we hide constructor
    private Logger() {}

    // one instance of the object which we will return when requested
    private static Logger logger = null;

    public void log(String msg) {
        System.out.println("[" + num + "] " + msg);
        num++;
    }

    // developers which need this logger will get always whis only one instance
    public static Logger getInstance() {
        if (logger == null) { logger = new Logger();}
        return logger;
    }

}
