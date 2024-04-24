package fr.polytech.sim.cycling;

import fr.polytech.sim.transport.Wheel;

/**
 * Two-wheel bike.
 */
public class TagAlongBike extends Bike {

    /**
     * Constructor.
     */
    public TagAlongBike() {
        components.add(new Wheel(this));
        components.add(new Wheel(this));
        components.add(new SimpleBike());
    }
}
