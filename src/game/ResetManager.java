package game;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;

/**
 * A global Singleton manager that does soft-reset on the instances.
 */
public class ResetManager {
    /**
     * A list of resettable instances (any classes that implements Resettable,
     * such as Player implements Resettable will be stored in here)
     */
    private ArrayList<Resettable> resettableList;

    /**
     * A singleton reset manager instance
     */
    private static ResetManager instance;

    /**
     * Get the singleton instance of reset manager
     * @return ResetManager singleton instance
     */
    public static ResetManager getInstance(){
        if(instance == null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Constructor
     */
    private ResetManager(){
        resettableList = new ArrayList<>();
    }

    /**
     * Reset the game by traversing through all the list
     * By doing this way, it will avoid using `instanceof` all over the place.
     */
    public void run(){
        for (Resettable resettable : this.resettableList){
            resettable.resetInstance();
        }
    }

    /**
     * Add the Resettable instance to the list
     */
    public void appendResetInstance(Resettable reset){
        this.resettableList.add(reset);
    }

    /**
     * Remove a Resettable instance from the list
     * @param resettable resettable object
     */
    public void cleanUp(Resettable resettable){
        this.resettableList.remove(resettable);
    }

    /**
     * Returns the Actor as a Resettable if the Actor exists in the list of Resettable.
     *
     * @param actor an Actor that may or may not be in the list.
     * @return a Resettable if the Actor is in the list.
     */
    public Resettable getResettable(Actor actor) {
        for (Resettable resettable : resettableList) {
            if (resettable.equals(actor)) {
                return resettable;
            }
        }
        return null;
    }
}
