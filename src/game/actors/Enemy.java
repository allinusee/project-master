package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import game.Resettable;
import game.Status;
import game.behaviours.Behaviour;

import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends Actor implements Resettable {
    protected final Map<Integer, Behaviour> behaviours; // priority, behaviour

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.ENEMY);
        this.behaviours = new HashMap<>();
        this.registerInstance();
    }

    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items.
     */
    public void resetInstance(){
        this.addCapability(Status.RESET);
    }

}
