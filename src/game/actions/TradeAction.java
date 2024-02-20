package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.WalletOwner;
import game.currency.WalletManager;
import game.items.Purchasable;

/**
 * Special Action for trading.
 */
public class TradeAction extends Action {
    /**
     * The Purchasable Item being sold
     */
    private Purchasable tradeItem;

    /**
     * Constructor.
     *
     * @param tradeItem the Purchasable Item on sale
     */
    public TradeAction(Purchasable tradeItem) {
        this.tradeItem = tradeItem;
    }

    /**
     * Executes the current Action: trading for an Item.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String to indicate if the trade was successful
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        WalletManager wallet = WalletManager.getInstance();
        WalletOwner owner = wallet.getOwner(actor);
        if (owner != null) {
            if (tradeItem.getPrice() <= owner.checkBalance()) {
                Item goods = (Item) tradeItem;
                actor.addItemToInventory(goods);
                owner.minusBalance(tradeItem.getPrice());
                return menuDescription(actor);
            } else {
                return "You don't have enough coins!";
            }
        }
        return null;
        }

    /**
     * Display an allowed Action to trade for an Item in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String indicating the choice to buy an Item.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + tradeItem.toString() + " ($" + tradeItem.getPrice() + ")";
    }
}
