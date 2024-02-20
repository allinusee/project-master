package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Resettable;
import game.Status;
import game.actions.ResetAction;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements WalletOwner, Resettable {
	private final Menu menu = new Menu();
	private int wallet = 0;
	private boolean hasReset;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.BUYER);
		this.addToWalletManager();
		this.hasReset = false;
		this.registerInstance();
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		if (!this.hasReset) {
			actions.add(new ResetAction());
		}

		System.out.println(this.toString() + "'s HP: " + this.printHp());
		System.out.println(this.toString() + "'s $: " + this.checkBalance());
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Getter for the Player's display character. Ideally should be m or M.
	 * @return char showing m or M
	 */
	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * Setter for the flag of checking if the Player has already performed a reset.
	 * @param hasReset boolean to indicate if the Player has reset the game or not.
	 */
	public void setHasReset(boolean hasReset){
		this.hasReset = hasReset;
	}

	/**
	 * Returns the balance in the Actor's wallet.
	 *
	 * @return an int representing the wallet balance.
	 */
	@Override
	public int checkBalance() {
		return this.wallet;
	}

	/**
	 * Increases the balance in the Actor's wallet
	 *
	 * @param value an int representing the amount to increase.
	 */
	@Override
	public void addBalance(int value) {
		this.wallet += value;
	}

	/**
	 * Decreases the balance in the Actor's wallet
	 *
	 * @param value an int representing the amount to decrease.
	 */
	@Override
	public void minusBalance(int value) {
		this.wallet -= value;
	}

	/**
	 * Allows any classes that use this interface to reset abilities, attributes, and/or items.
	 */
	@Override
	public void resetInstance() {
		if (this.hasCapability(Status.TALL)){
			this.removeCapability(Status.TALL);
		}
		if (this.hasCapability(Status.INVINCIBLE)){
			this.removeCapability(Status.INVINCIBLE);
		}
		int hitPoints = this.getMaxHp();
		this.heal(hitPoints);
	}
}
