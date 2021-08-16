package test.integration.engine;

import org.junit.jupiter.api.Test;
import se.jbee.spacecrafts.sim.engine.Maybe;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TestMaybe {

    @Test
    void some_0() {
        assertThrows(NullPointerException.class, () -> Maybe.some(null));
    }

    @Test
    void some() {
        var some = Maybe.some(1);
        assertTrue(some.isSome());
        assertEquals(1, some.get());
    }

    @Test
    void some_orElse() {
        assertEquals(42, Maybe.some(42).orElse(13));
    }

    @Test
    void some_orElseThrow() {
        assertEquals(42,
                Maybe.some(42).orElseThrow(IllegalStateException::new));
    }

    @Test
    void some_map() {
        assertEquals(5, Maybe.some("Hello").map(String::length).get());
    }

    @Test
    void nothing() {
        var nothing = Maybe.nothing();
        assertFalse(nothing.isSome());
        assertThrows(NoSuchElementException.class, nothing::get);
    }

    @Test
    void nothing_orElse() {
        assertEquals(42, Maybe.nothing().orElse(42));
    }

    @Test
    void nothing_map() {
        assertFalse(Maybe.<String>nothing().map(String::length).isSome());
    }

    @Test
    void nothing_orElseThrow() {
        var nothing = Maybe.nothing();
        assertThrows(IllegalStateException.class,
                () -> nothing.orElseThrow(IllegalStateException::new));
    }

    @Test
    void ofThrowing_0() {
        assertFalse(Maybe.ofThrowing(() -> {
            throw new IllegalStateException("test");
        }).isSome());
    }

    @Test
    void ofThrowing() {
        assertEquals(42, Maybe.ofThrowing(() -> 42).get());
    }
}