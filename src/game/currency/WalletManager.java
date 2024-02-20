package game.currency;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.WalletOwner;

import java.util.ArrayList;

/**
 * Singleton Class to manage all WalletOwners in the game.
 */
public class WalletManager {
    private ArrayList<WalletOwner> walletOwnerList;
    private static WalletManager instance;

    private WalletManager() {
        this.walletOwnerList = new ArrayList<>();
    }

    /**
     * Static method to create an instance of WalletManager or return the existing one.
     *
     * @return the WalletManager instance.
     */
    public static WalletManager getInstance() {
        if (instance == null) {
            instance = new WalletManager();
        }
        return instance;
    }

    /**
     * Adds a WalletOwner to the list.
     *
     * @param walletOwner a wallet owner.
     */
    public void addOwner(WalletOwner walletOwner) {
        this.walletOwnerList.add(walletOwner);
    }

    /**
     * Returns the list of WalletOwners.
     *
     * @return a list of WalletOwners.
     */
    public ArrayList<WalletOwner> getOwnerList() {
        return new ArrayList<WalletOwner>(this.walletOwnerList);
    }

    /**
     * Returns the Actor as a WalletOwner if the Actor exists in the list of WalletOwners.
     *
     * @param actor an Actor that may or may not be in the list.
     * @return a WalletOwner if the Actor is in the list.
     */
    public WalletOwner getOwner(Actor actor){
        for (WalletOwner walletOwner : walletOwnerList) {
            if (walletOwner.equals(actor)) {
                return walletOwner;
            }
        }
        return null;
    }
}
