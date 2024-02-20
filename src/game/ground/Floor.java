package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
	public Floor() {
		super('_');
	}

	/**
	 * Prevents access from Enemy
	 *
	 * @param actor the Actor to check
	 * @return true
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return !(actor.hasCapability(Status.ENEMY));
	}
}
