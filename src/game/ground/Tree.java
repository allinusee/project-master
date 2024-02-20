package game.ground;

import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;
import game.Status;

import java.util.Random;

/**
 * Abstract class that represents Tree.
 */
public abstract class Tree extends HighGround implements Resettable {

    /**
     * Age of the Tree.
     */
    private int age;
    /**
     * Chance of spawning an object.
     */
    private int spawnRate;
    /**
     * Random number generator.
     */
    private Random rand = new Random();

    /**
     * Constructor of a Tree
     * @param displayChar Display character of the Tree
     * @param jumpRate Chance of success for a jump attempt.
     * @param fallDamage Damage of the fall when jump attempt is unsuccessful.
     * @param spawnRate Chance of spawning an object.
     */
    public Tree(char displayChar, int jumpRate, int fallDamage, int spawnRate) {
        super(displayChar, jumpRate, fallDamage);
        this.age = 0;
        this.spawnRate = spawnRate;
        this.registerInstance();
    }

    @Override
    public void tick(Location location){
        super.tick(location);
        if (this.hasCapability(Status.RESET)){
            if (rand.nextInt(100) <= 50){
                location.setGround(new Dirt());
            }
            this.removeCapability(Status.RESET);
        }
    }
    /**
     * Method to get the spawnRate of the Tree
     * @return int representing the spawnRate
     */
    public int getSpawnRate() {
        return this.spawnRate;
    }

    /**
     * Method to get the age of the Tree
     * @return int representing the age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Method to set the age of the Tree
     * @param age int representing the new age of the Tree
     */
    public void setAge(int age) {
        this.age = age;
    }



    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }
}
