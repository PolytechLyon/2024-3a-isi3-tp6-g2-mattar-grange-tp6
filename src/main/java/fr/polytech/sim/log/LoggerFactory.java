package fr.polytech.sim.log;


public class LoggerFactory {

    private LoggerFactory() {
    }
    public static Logger CreateLogger(String name)    {

        return new TimestampedLoggerDecorator(new ConsoleLogger(name));
    }
}