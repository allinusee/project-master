package game.ground;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Koopa;

import java.util.List;
import java.util.Random;

/**
 * Class that represents Mature.
 */
public class Mature extends Tree{

    /**
     * Chance of a mature to grow a sprout.
     */
    private final int growRate = 20;
    /**
     * Chance of the mature to wither.
     */
    private final int witherRate = 20;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor for Mature
     */
    public Mature(){
        super('T', 70, 30, 15);
    }

    /**
     * Allow mature to experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        this.setAge(this.getAge() + 1);
        // every turn, chance to spawn a Koopa at the location
        if ((rand.nextInt(100) <= this.getSpawnRate())) {
            if (!location.containsAnActor()) {
                location.addActor(new Koopa());
            }
        }

        // every 5 turns, chance to grow a Sprout in a surrounding tile
        if (this.getAge() % 5 == 0) {
            List<Exit> surrounding = location.getExits();
            for (Exit exit : surrounding) {
                Location square = exit.getDestination();
                Ground ground = square.getGround();
                if (ground.getDisplayChar() == '.') {
                    if ((rand.nextInt(100) <= this.growRate)) {
                        square.setGround(new Sprout());
                        break;
                    }
                }
            }
        }

        // every turn, chance to wither (and die)
        if ((rand.nextInt(100) <= this.witherRate)) {
            location.setGround(new Dirt());
        }
    }
}
