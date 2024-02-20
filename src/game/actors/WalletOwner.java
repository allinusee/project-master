package game.actors;

import game.currency.WalletManager;

/**
 * An interface for Actors that possess a "wallet".
 */
public interface WalletOwner {
    /**
     * Adds the Actor to the WalletManager.
     */
    default void addToWalletManager() {
        WalletManager.getInstance().addOwner(this);
    }

    /**
     * Returns the balance in the Actor's wallet.
     *
     * @return an int representing the wallet balance.
     */
    int checkBalance();

    /**
     * Increases the balance in the Actor's wallet
     *
     * @param value an int representing the amount to increase.
     */
    void addBalance(int value);

    /**
     * Decreases the balance in the Actor's wallet
     *
     * @param value an int representing the amount to decrease.
     */
    void minusBalance(int value);

}

