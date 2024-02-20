package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.ResetManager;
import game.actors.Koopa;
import game.items.SuperMushroom;

import java.util.Random;

/**
 * Special Action for attacking other Actors.
 */
public class DestroyShellAction extends Action{
    /**
     * The Actor that is to be attacked
     */
    protected Koopa target;
    /**
     * The direction of incoming attack.
     */
    protected String direction;
    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public DestroyShellAction(Koopa target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Allow the Actor to destroy a shell.
     *
     * Overrides Action.execute()
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the Action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        Weapon weapon = actor.getWeapon();

        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + "'s shell.";
        }
        String result = actor + " " + weapon.verb() + " " + target + "'s shell";
        SuperMushroom superMushroom = new SuperMushroom(true);
        map.locationOf(target).addItem(superMushroom);
        map.removeActor(target);
        ResetManager.getInstance().cleanUp(target);
        result += System.lineSeparator() + target + "'s shell is destroyed. " + superMushroom + " is dropped.";

        return result;
    }

    /**
     * Returns a description of this movement suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " destroys " + target + "'s shell at " + direction;
    }
}


