package tools;

public class Logger {

    private static ThreadLocal<org.apache.log4j.Logger> log4J = ThreadLocal.withInitial(()
            -> org.apache.log4j.Logger.getLogger(String.valueOf(Thread.currentThread().getId())));

    private static ThreadLocal<Logger> instance = ThreadLocal.withInitial(Logger::new);

    public static Logger getInstance() {
        return instance.get();
    }

    public void debug(String message) {
        log4J.get().debug(message);
    }

    public void debug(String message, Throwable throwable) {
        log4J.get().debug(message, throwable);
    }

    public void info(String message) {
        log4J.get().info(message);
    }

    public void warn(String message) {
        log4J.get().warn(message);
    }

    public void error(String message) {
        log4J.get().error(message);
    }

}
