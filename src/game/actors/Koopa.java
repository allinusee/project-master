package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.actions.AttackAction;
import game.actions.DestroyShellAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;

/**
 * A little turtle guy. Enemy of the game.
 */
public class Koopa extends Enemy{

    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        this.behaviours.put(10, new WanderBehaviour());
    }

    /**
     * Actions allowed upon Koopa.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            this.behaviours.put(5, new FollowBehaviour(otherActor));
            this.behaviours.put(1, new AttackBehaviour(otherActor));
            Weapon weapon = otherActor.getWeapon();

            if (this.getDisplayChar() == 'D' && weapon.verb().equals("bonks")){
                actions.add(new DestroyShellAction(this, direction));
            } else {
                actions.add(new AttackAction(this,direction));
            }
        }
        return actions;
    }

    /**
     * @return  display character of Koopa
     */
    @Override
    public char getDisplayChar() {
        if (this.hasCapability(Status.DORMANT)){
            this.setDisplayChar('D');
        }
        return super.getDisplayChar();
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.RESET)) {
            map.removeActor(this);
        } else if (!this.hasCapability(Status.DORMANT)) {
            for (Behaviour Behaviour : behaviours.values()) {
                Action action = Behaviour.getAction(this, map);
                if (action != null)
                    return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * @return a freshly-instantiated IntrinsicWeapon for Koopa
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punch");
    }

}
