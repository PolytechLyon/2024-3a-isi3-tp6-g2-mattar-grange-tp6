package fr.polytech.sim.cycling;

import fr.polytech.sim.Simulation;
import fr.polytech.sim.log.FileLogger;
import fr.polytech.sim.log.Logger;

/**
 * Bike simulation.
 */
public class BikeSimulator implements Simulation {
    private final Logger logger = new FileLogger("BikeSimulator");

    public void run() {
        Bike bike = new SimpleBike();
        Bike bikeParents = new TagAlongBike();
        this.logger.log("Bike's speed %.2f Km/h.", bike.getVelocity());
        this.logger.log("Bike's mass %.2f Kg.", bike.getMass());

        this.logger.log("TagAlongBike's speed %.2f Km/h.", bikeParents.getVelocity());
        this.logger.log("TagAlongBike's mass %.2f Kg.", bikeParents.getMass());
    }
}
