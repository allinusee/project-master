package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * Item subclass representing a Power Star in the game world.
 * Twinkle twinkle little star.
 */
public class PowerStar extends ConsumableItem implements Purchasable {
    private int fadingTimer = 10;
    private int buffDuration = 10;
    private final int price = 600;

    /**
     * Constructor.
     *
     * @param portable true if and only if the Item can be picked up
     */
    public PowerStar(boolean portable) {
        super("Power Star", '*', portable);
    }

    /**
     * Actor on the GameMap consumes the PowerStar.
     *
     * @param actor an Actor that will consume the item
     * @param map the GameMap that the Actor is on
     */
    @Override
    public void consume(Actor actor, GameMap map) {
        actor.addCapability(Status.INVINCIBLE);
        actor.heal(200);
    }

    /**
     * The effect of time (game turns).
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (actor.hasCapability(Status.INVINCIBLE)) {

            if (buffDuration == 10) {
                this.togglePortability();
                Action consume = this.getAllowableActions().get(0);
                this.removeAction(consume);
            }

            buffDuration -= 1;

            if (buffDuration > 0) {
                this.expiring();
            } else {
                actor.removeItemFromInventory(this);
                actor.removeCapability(Status.INVINCIBLE);
                System.out.println("Invincibility from the Power Star has worn off...");
            }
        } else {
            fadingTimer -= 1;

            if (fadingTimer > 0) {
                this.fading();
            } else {
                actor.removeItemFromInventory(this);
                System.out.println("The Power Star dims and fades away...");
            }
        }
    }

    /**
     * The effect of time (game turns).
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        Actor player = currentLocation.getActor();
        if (player != null && player.hasCapability(Status.INVINCIBLE)) {

            if (buffDuration == 10) {
                player.addItemToInventory(this);
                this.togglePortability();
                Action consume = this.getAllowableActions().get(0);
                this.removeAction(consume);
                currentLocation.removeItem(this);
            }

            buffDuration -= 1;
            this.expiring();
        } else {
            fadingTimer -= 1;
            if (fadingTimer > 0) {
                this.fading();
            } else {
                currentLocation.removeItem(this);
                System.out.println("The Power Star dims and fades away...");
            }
        }
    }

    /**
     * Prints out the remaining time left before PowerStar is removed from the game.
     */
    public void fading() {
        System.out.println(fadingTimer + " turns left until the Power Star fades out of existence. :(");
    }

    /**
     * Prints out the remaining time for the INVINCIBLE status.
     */
    public void expiring() {
        System.out.println("Mario is INVINCIBLE for another " + buffDuration + " turns!");
    }

    /**
     * Returns the selling price of a Power Star.
     *
     * @return the selling price
     */
    public int getPrice() {
        return this.price;
    }
}
