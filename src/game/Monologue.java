package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.ArrayList;

/**
 * Helper class to store dialogue of actors that can speak.
 */
public class Monologue {

    /**
     * Method that stores the dialogue for a Toad object. Also checks conditionals of the Player's condition
     * and removes dialogue when appropriate.
     *
     * @param actor the actor that is speaking with Toad
     * @return the filtered list of possible monologue to be displayed
     */
    public ArrayList<String> toadMonologue(Actor actor){
        ArrayList<String> monologue = new ArrayList<String>();
        Weapon weapon = actor.getWeapon();
        monologue.add("You might need a wrench to smash Koopa's hard shells.");
        monologue.add("You better get back to finding the Power Stars.");
        monologue.add("The Princess is depending on you! You are our only hope.");
        monologue.add("Being imprisoned in these walls can drive a fungus crazy :(");

        // PH: if player has Wrench
        if (weapon.verb().equals("bonks") && !actor.hasCapability(Status.INVINCIBLE)){
            monologue.remove(0);
        }
        // PH: if player has Power Star effect
        if (!weapon.verb().equals("bonks") && actor.hasCapability(Status.INVINCIBLE)) {
            monologue.remove(1);
        }
        // PH: if player has Wrench and Power Star effect
        if (weapon.verb().equals("bonks") && actor.hasCapability(Status.INVINCIBLE)){
            monologue.remove(0);
            monologue.remove(0);
        }
        return monologue;
    }
}
