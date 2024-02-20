package game.ground;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Goomba;

import java.util.Random;

/**
 * Class that represents Sprout.
 */
public class Sprout extends Tree{

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor for Sprout
     */
    public Sprout(){
        super('+', 90, 10, 10);
    }

    /**
     * Allow sprout to experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        this.setAge(this.getAge() + 1);

        if ((rand.nextInt(100) <= this.getSpawnRate())) {
            if (!location.containsAnActor()) {
                location.addActor(new Goomba());
            }
        }

        if (this.getAge() == 10) {
            location.setGround(new Sapling());
        }
    }
}
