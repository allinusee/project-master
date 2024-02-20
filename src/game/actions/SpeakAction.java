package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Monologue;

import java.util.ArrayList;
import java.util.Random;

/**
 *  Special class that performs the action of speaking.
 */
public class SpeakAction extends Action {


    /**
     * Random number generator
     */
    private Random rand = new Random();
    /**
     * An instance of a Monologue object to display text.
     */
    private Monologue monologue;
    /**
     * The actor who will be speaking out the dialogue.
     */
    private Actor speaker;

    /**
     * Public constructor that initialises the action of speaking.
     * @param actor the actor that is the one displaying the dialogue. In terms of REQ6, it is Toad
     */
    public SpeakAction(Actor actor){
        this.monologue = new Monologue();
        this.speaker = actor;
    }

    /**
     * Allows the current actor to speak.
     *
     * Overrides Action.execute()
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the line of dialogue the actor is speaking.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ArrayList<String> monologue = this.monologue.toadMonologue(actor);
        int dialogueNo = rand.nextInt(monologue.size());
        return monologue.get(dialogueNo);
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " speaks to " + speaker;
    }
}
