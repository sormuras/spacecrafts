package se.jbee.spacecrafts.sim.engine;

import se.jbee.spacecrafts.sim.Game;
import se.jbee.spacecrafts.sim.engine.Any.Indicator;

/**
 * State of the game engine - this is the state that is independent of any
 * {@link Game}.
 */
public interface Engine {

    /**
     * All {@link Indicator}s used in the attributes of {@link Kit}s and {@link
     * Feature}s
     */
    Range<Indicator> ATTRS = Range.newDefault(Indicator.class, 5);

    Indicator ENABLED = ATTRS.add(id -> new Indicator(id, "e-enabled"));

    // 1. load all Modules and Bundles via ServiceLoader
    // 2. build Engine record (bare Modules in synthetic Bundle Kit)
    // 3. user selects kits and features based on Engine
    // 4. when user starts a game selected features get installed in order

    /**
     * Represents the possible options to chose from when creating a {@link
     * Runtime}. These can only be changed before loading/starting a new {@link
     * Game}.
     */
    record Configuration(
            Q<Kit> kits,
            Top<Option<Pools.Factory>> newPools,
            Top<Option<Q.Factory>> newQs,
            Top<Option<Snapshot.Factory>> newSnapshots,
            Top<Option<Top.Factory>> newTops,
            Top<Option<Register.Factory>> newRegisters,
            Top<Option<Index.Factory>> newIndexes,
            Top<Option<Index.Factory>> newRanges,
            Top<Option<Flux.Factory>> newFluxes,
            Top<Option<Marks.Factory>> newMarks,
            Top<Option<Numbers.Factory>> newNumbers,
            Top<Option<NumberPer.Factory>> newNumberPer
    ) {}

    /**
     * A {@link Runtime} is the engine as used by a particular {@link Game}
     */
    record Runtime(
            Pools.Factory newPools,
            Q.Factory newQ,
            Snapshot.Factory newSnapshot,
            Top.Factory newTop,
            Register.Factory newRegister,
            Index.Factory newIndex,
            Range.Factory newRange,
            Flux.Factory newFlux,
            Marks.Factory newMarks,
            Numbers.Factory newNumbers,
            NumberPer.Factory newNumberPer
    ) {}

    /**
     * A {@link Kit} is a semantic package of game functionality.
     */
    record Kit(
            Class<? extends Bundle> from,
            String name,
            Marks attributes,
            Numbers values,
            Q<Feature> features
    ) {}

    record Feature(
            Class<? extends Module> from,
            String name,
            Marks attributes,
            Numbers values,
            Q<Feature> requires
    ) {}

    record Option<T>(
            String name,
            String desc,
            T of
    ) {}

    interface Bundle {

        Class<? extends Bundle> id();

        Class<? extends Module> modules();

        void setup(Configuration config);
    }

    interface Module {

        Class<? extends Module> id();

        Q<Class<? extends Module>> requires();

        void installIn(Game game);
    }

    interface Starter<G> {

        G newGame();
    }

}
