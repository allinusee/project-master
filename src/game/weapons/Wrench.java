package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Purchasable;

/**
 * Class representing a Wrench weapon in the game.
 */
public class Wrench extends WeaponItem implements Purchasable {
    private final int price = 200;

    /**
     * Constructor.
     */
    public Wrench() {
        super("Wrench", 'p', 50, "bonks", 80);
    }

    /**
     * Returns the selling price of a Wrench.
     *
     * @return the selling price
     */
    @Override
    public int getPrice() {
        return price;
    }
}
