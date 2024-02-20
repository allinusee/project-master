package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;

/**
 * Abstract base class representing a consumable physical object in the game.
 */
public abstract class ConsumableItem extends Item {

    /**
     * Constructor.
     *
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public ConsumableItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.addAction(new ConsumeAction(this));
    }

    /**
     * Abstract method for an Actor on the GameMap to consume the ConsumableItem.
     *
     * @param actor an Actor that will consume the item
     * @param map the GameMap that the Actor is on
     */
    public abstract void consume(Actor actor, GameMap map);
}
