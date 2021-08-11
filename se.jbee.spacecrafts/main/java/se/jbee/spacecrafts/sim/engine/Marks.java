package se.jbee.spacecrafts.sim.engine;

import se.jbee.spacecrafts.sim.Resourcing;
import se.jbee.spacecrafts.sim.Resourcing.Indicator;

/**
 * {@link Marks} are like a dynamic enum set. {@link Resourcing.Classification}s
 * can be used for a dynamic groupings.
 */
public interface Marks extends Collection<Indicator> {

    static Marks newDefault(Range<Indicator> of) {
        return new BitsMarks(of);
    }

    boolean has(Indicator key);

    void set(Indicator key, boolean value);

    void clear();

    void zero(Marks zeros);

    default void set(Indicator key) {
        set(key, true);
    }

    default void unset(Indicator key) {
        set(key, false);
    }

    @Override
    default boolean contains(Indicator key) {
        return has(key);
    }
}
