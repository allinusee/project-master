package game.currency;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;
import game.Status;
import game.actions.PickCoinAction;

/**
 * Class representing a Coin - an Item that represents the game's currency.
 */
public class Coin extends Item implements Resettable {
    private final int value;

    /**
     * Constructor.
     *
     * @param value the value of the coin.
     */
    public Coin(int value) {
        super("coin", '$', false);
        this.addAction(new PickCoinAction(this));
        this.value = value;
        this.registerInstance();
    }

    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation){
        if (this.hasCapability(Status.RESET)){
            currentLocation.removeItem(this);
            this.removeCapability(Status.RESET);
        }
    }

    /**
     * Returns the value of the coin.
     *
     * @return an int representing the value of the coin.
     */
    public int getValue() {
        return this.value;
    }


    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }


}
