package fr.polytech.sim.log;

import fr.polytech.sim.log.TimestampedLoggerDecorator;
public class LoggerFactory {

    private LoggerFactory() {
    }
    public static Logger CreateLogger(String name)    {

        return new TimestampedLoggerDecorator(new ConsoleLogger(name));
    }
}
