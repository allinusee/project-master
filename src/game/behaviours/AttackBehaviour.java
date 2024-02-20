package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

public class AttackBehaviour implements Behaviour {

    private final Actor target;

    /**
     * Constructor.
     *
     * @param subject the Actor to follow
     */
    public AttackBehaviour(Actor subject) {
        this.target = subject;
    }

    /**
     * Returns a AttackAction to attack, if possible.
     * If no attack is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no AttackAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        for (Exit exit : there.getExits()) {
            Location destination = exit.getDestination();
            if (destination.equals(here)) {
                return new AttackAction(target, exit.getName());
            }
        }

        return null;
    }

}
