package se.jbee.spacecrafts.sim;

import se.jbee.spacecrafts.sim.Conquering.*;
import se.jbee.spacecrafts.sim.Crafting.Component;
import se.jbee.spacecrafts.sim.Discovering.Discovery;
import se.jbee.spacecrafts.sim.Resourcing.Influence;
import se.jbee.spacecrafts.sim.engine.Any.*;
import se.jbee.spacecrafts.sim.engine.*;

public interface Governing {

    record Fraction(
            Created header,
            Numbers score,
            Marks properties,
            Flux<Trait> traits,
            Governance governed,
            Awareness awareOf
    ) implements Creation {}

    record Trait(
            Defined header,
            Numbers bonuses,
            Stasis<Influence> influences
    ) implements Definition {}

    record Sphere(
            Defined header,
            Stasis<Trait> members
    ) implements Definition {}

    record Leader(
            Created header,
            Stasis<Influence> influences,
            Numbers multipliers,
            Vary<Asset> assignment
    ) implements Creation {}

    interface Asset extends Creation {

        @Override
        IsGoverned header();

        Crafting.Craft structure();
    }

    interface IsGoverned extends IsCreated {}

    record Governed(
            int serial,
            Text name,
            Fraction origin
    ) implements IsGoverned {}

    /**
     * Ownership by a {@link Fraction} is given when an {@link Asset} is
     * contained in any of the asserts lists of this record.
     * <p>
     * Shared ownership simply means an {@link Asset} occurs in lists of
     * multiple {@link Fraction}s.
     */
    record Governance(
            // Assets
            Flux<Colony> colonies,
            Flux<LunarBase> bases,
            Flux<OrbitalStation> orbitals,
            Flux<Spaceship> spaceships,
            Flux<SpaceStation> stations,

            // Non assets
            Flux<Fleet> fleets,
            Flux<Leader> leaders
    ) implements Embedded {}


    record Awareness(
            // galaxy
            Flux<Galaxy> galaxies,
            Flux<SolarSystem> systems,
            Flux<Planet> planets,
            Flux<Moon> moons,

            // Assets
            Flux<Colony> colonies,
            Flux<LunarBase> bases,
            Flux<OrbitalStation> orbitals,
            Flux<Spaceship> spaceships,
            Flux<SpaceStation> stations,

            // Non assets
            Flux<Fraction> fractions,
            Flux<Leader> leaders,
            Flux<Fleet> fleets,
            Flux<Discovery> discoveries,
            Flux<Component> components
    ) {}
}
