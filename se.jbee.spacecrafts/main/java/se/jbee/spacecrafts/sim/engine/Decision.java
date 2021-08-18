package se.jbee.spacecrafts.sim.engine;

import se.jbee.spacecrafts.sim.Game;

import java.util.function.Function;

public interface Decision extends Any.Computed {

    interface Processor {

        //Maybe have a boolean arg to say if the decision is optional
        // to have the player chose if that part should happen as well
        <E extends Record & Decision> void manifest(E e);

        default <T, E extends Record & Decision> void manifest(Function<T, E> newDecision, Optional<T> source) {
            if (source.isSome()) manifest(newDecision.apply(source.get()));
        }
    }

    void manifestIn(Game game, Processor processor);

    /**
     * @return the turn in which to apply the event, or -1 if it should be
     * applied immediately
     */
    default int turn() {
        return -1;
    }

    /**
     * A generic wrapper to make any other event a future event in a specific
     * turn.
     */
    record DelayedDecision<E extends Record & Decision>(
            E of,
            int turn
    ) implements Decision {
        @Override
        public void manifestIn(Game game, Processor processor) {
            processor.manifest(of);
        }
    }
}
