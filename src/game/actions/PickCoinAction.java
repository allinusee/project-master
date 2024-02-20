package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;
import game.actors.WalletOwner;
import game.currency.Coin;
import game.currency.WalletManager;


/**
 * Special Action for picking up coins.
 */
public class PickCoinAction extends Action {
    /**
     * The coin to be picked up.
     */
    private final Coin coin;

    /**
     * Constructor.
     *
     * @param coin the coin to pick up
     */
    public PickCoinAction(Coin coin) {
        this.coin = coin;
    }

    /**
     * Executes the current Action: picks up a Coin
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(coin);
        ResetManager.getInstance().cleanUp(coin);
        WalletManager wallet = WalletManager.getInstance();
        WalletOwner owner = wallet.getOwner(actor);
        if (owner != null) {
            owner.addBalance(coin.getValue());
            return menuDescription(actor);
        }
        return null;
    }

    /**
     * Display an allowed action to pick up a Coin in the menu.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player picks up the coin"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the " + coin.getDisplayChar() + coin.getValue() + " " + coin ;
    }
}
