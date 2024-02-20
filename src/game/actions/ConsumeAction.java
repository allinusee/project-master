package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.ConsumableItem;

/**
 * Special Action for consuming consumables.
 */
public class ConsumeAction extends Action {

    /**
     * The consumable Item that will be consumed.
     */
    private ConsumableItem consumable;

    /**
     * Constructor.
     *
     * @param consumable the Item to consume
     */
    public ConsumeAction(ConsumableItem consumable) {
        this.consumable = consumable;
    }

    /**
     * Executes the current Action: Consume a consumable Item.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String to indicate the Actor consumed an Item.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = actor + " consumed the " + consumable.toString() + "!";
        consumable.consume(actor, map);
        return result;
    }

    /**
     * Display an allowed action to Consume an Item in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String indicating the choice to Consume an Item.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the " + consumable.toString() ;
    }
}
