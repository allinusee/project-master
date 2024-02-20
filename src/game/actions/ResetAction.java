package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;
import game.actors.Player;


/**
 * Special Action for soft resetting the game:
 *  1. removes all enemies in the map
 *  2. resets player status and heals to max.
 *  3. removes all coins in the map
 *  4. Trees have a 50% chance of converting to Dirt.
 */
public class ResetAction extends Action {

    /**
     * Allow the actor to soft reset the game.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a descriptive sentence displaying what has occured
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        player.setHasReset(true);
        ResetManager.getInstance().run();
        return menuDescription(actor);

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " resets game.";
    }

    @Override
    public String hotkey(){
        return "r";
    }
}
