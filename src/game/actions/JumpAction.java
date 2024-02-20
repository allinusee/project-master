package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.ground.HighGround;

import java.util.Random;

public class JumpAction extends Action {
    /**
     * The location to be jumped onto
     */
    protected Location jumpToLocation;

    protected HighGround highGround;
    /**
     * One of the 8-d navigation
     */
    protected String direction;
    /**
     * Or the command key
     */
    protected String hotKey;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Public constructor.
     * @param jumpToLocation the location of where the jump will end
     * @param highGround the HighGround object of where the actor will land on
     * @param direction the direction of the jump
     * @param hotKey the input key for jumping
     */
    public JumpAction(Location jumpToLocation, HighGround highGround, String direction, String hotKey) {
        this.jumpToLocation = jumpToLocation;
        this.highGround = highGround;
        this.direction = direction;
        this.hotKey = hotKey;
    }

    /**
     * Public constructor.
     * @param moveToLocation the location of where the jump will end
     * @param highGround the HighGround object of where the actor will land on
     * @param direction the direction of the jump
     */
    public JumpAction(Location moveToLocation, HighGround highGround, String direction) {
        this.jumpToLocation = moveToLocation;
        this.highGround = highGround;
        this.direction = direction;
        this.hotKey = null;
    }

    /**
     * Allows the actor to jump.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a descriptive string describing what has occured
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = menuDescription(actor);
        if (actor.hasCapability(Status.TALL)){
            if (!jumpToLocation.containsAnActor()) {
                map.moveActor(actor, jumpToLocation);
            }
        } else {
            if ((rand.nextInt(100) <= highGround.getJumpRate())) {
                if (!jumpToLocation.containsAnActor()) {
                    map.moveActor(actor, jumpToLocation);
                }
            } else {
                actor.hurt(highGround.getFallDamage());
                result = actor + " took a fall damage of " + highGround.getFallDamage();
            }
            return result;
        }
        return result;
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps onto " + highGround.getClass().getSimpleName() + " at " + direction;
    }

    /**
     * Returns this Action's hotkey.
     *
     * @return the hotkey
     */
    @Override
    public String hotkey() {
        return hotKey;
    }
}
