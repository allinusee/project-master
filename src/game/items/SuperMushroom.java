package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

/**
 * Item subclass representing a Super Mushroom in the game world.
 * Good shroom.
 */
public class SuperMushroom extends ConsumableItem implements Purchasable {
    private final int price = 400;

    /**
     * Constructor.
     *
     * @param portable true if and only if the Item can be picked up
     */
    public SuperMushroom(boolean portable) {
        super("Super Mushroom", '^', portable);
    }

    /**
     * Actor on the GameMap consumes the SuperMushroom.
     *
     * @param actor an Actor that will consume the item
     * @param map the GameMap that the Actor is on
     */
    @Override
    public void consume(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(this);
        actor.addCapability(Status.TALL);
        actor.increaseMaxHp(50);
        actor.removeItemFromInventory(this);
    }

    /**
     * Returns the selling price of a Super Mushroom.
     *
     * @return the selling price
     */
    @Override
    public int getPrice() {
        return this.price;
    }


}
