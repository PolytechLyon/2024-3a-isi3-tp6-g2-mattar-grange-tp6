package fr.polytech.sim;

import java.util.Random;

/**
 * A clock used to synchronize simulations.
 */
public class Clock {
    private Clock() {};

    private static final Clock clock = new Clock();

    public static Clock getClockInstance() {
        return clock;
    }

    private final int time = new Random().nextInt(25);

    /**
     * Random integer between 0 and 24 inclusive.
     */
    public int getTime() {
        return this.time;
    }
}
