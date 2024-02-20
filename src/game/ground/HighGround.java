package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.currency.Coin;

public abstract class HighGround extends Ground {

    /**
     * Chance of success for a jump attempt.
     */
    private int jumpRate;
    /**
     * Damage of the fall when jump attempt is unsuccessful.
     */
    private int fallDamage;

    /**
     * Constructor.
     *
     */
    public HighGround(char displayChar, int jumpRate, int fallDamage) {
        super(displayChar);
        this.jumpRate = jumpRate;
        this.fallDamage = fallDamage;
    }

    /**
     * Method to get the jumpRate of a HighGround
     * @return int representing the jumpRate
     */
    public int getJumpRate() {
        return this.jumpRate;
    }

    /**
     * Method to get the fallDamage of the HighGround
     * @return int representing the fallDamage.
     */
    public int getFallDamage() {
        return this.fallDamage;
    }

    /**
     * Prevents access from Actors by walking.
     *
     * @param actor the Actor to check
     * @return true
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Status.INVINCIBLE)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns an Action list that can be executed upon HighGround.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if (!actor.hasCapability(Status.INVINCIBLE) && !location.containsAnActor()) {
            actions.add(new JumpAction(location, this, direction));
        }
        return actions;
    }

    /**
     * Ground can also experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor() && location.getActor().hasCapability(Status.INVINCIBLE)) {
            location.setGround(new Dirt());
            location.addItem(new Coin(5));
        }
    }

    /**
     * Blocks thrown objects but not movement.
     * @return true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
}
