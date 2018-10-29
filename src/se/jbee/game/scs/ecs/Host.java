package se.jbee.game.scs.ecs;

import se.jbee.game.any.ecs.Fabrication;
import se.jbee.game.any.ecs.comp.ShortRef;
import se.jbee.game.scs.ecs.system.System;

/**
 * Something that can hold a {@link Platform}.
 */
public abstract class Host extends Fabrication {

	public static abstract class Ref<T extends Host> extends ShortRef<T> {
	
		public Ref(short serial) {
			super(serial);
		}
	}
	public SolarSystem.Ref hostSystem;
	/**
	 * A rectangular 2D area of size N x M given for a {@link Planet} row by row. True means the grid
	 * cell accepts {@link System}s, false does not.
	 */
	boolean[][] planetGrid;

	//TODO add occurrences of "resources" that can be minded in some way or that just block usage.
}
