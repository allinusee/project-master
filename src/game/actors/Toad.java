package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.SpeakAction;
import game.actions.TradeAction;
import game.items.PowerStar;
import game.items.Purchasable;
import game.items.SuperMushroom;
import game.weapons.Wrench;

import java.util.ArrayList;

/**
 * Class representing the Non-Playable-Character (NPC) Toad.
 */
public class Toad extends Actor {
    private ArrayList<Purchasable> tradeItems = new ArrayList<Purchasable>();

    /**
     * Constructor.
     */
    public Toad() {
        super("Toad", 'O', 9999);
        this.addTradeItem(new PowerStar(false));
        this.addTradeItem(new SuperMushroom(false));
        this.addTradeItem(new Wrench());
    }

    /**
     * Adds a Purchasable Item to a list for sale.
     *
     * @param tradeItem a Purchasable Item for sale
     */
    public void addTradeItem(Purchasable tradeItem) {
        this.tradeItems.add(tradeItem);
    }

    /**
     * Trades with a Buyer and/or speaks with the Player.
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be traded with only by a BUYER
        if (otherActor.hasCapability(Status.BUYER)) {
            for (Purchasable item : tradeItems) {
                actions.add(new TradeAction(item));
            }
        }
        actions.add(new SpeakAction(this));
        return actions;
    }


    /**
     * Figure out what to do next.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}