package game.ground;

import edu.monash.fit2099.engine.positions.Location;
import game.currency.Coin;

import java.util.Random;

/**
 * Class that represents Sapling.
 */
public class Sapling extends Tree{

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor for Sapling
     */
    public Sapling(){
        super('t', 80, 20,  10);
    }

    /**
     * Allow sapling to experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        this.setAge(this.getAge() + 1);

        if ((rand.nextInt(100) <= this.getSpawnRate())) {
            location.addItem(new Coin(20));
        }
        if (this.getAge() == 10) {
            location.setGround(new Mature());
        }
    }
}
