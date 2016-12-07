package testoptional;

import java.util.OptionalDouble;
import java.util.Optional;
import java.util.function.DoubleSupplier;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This java 8 example shows example how to use
 * OptionalDouble class
 * 
 * The most important methods of OptionalDouble are:
 * of()
 * ifPresent()
 * getAsDouble()
 * orElse
 * @author   Juha Peltomäki
 */
public class OptionalPrimitiveTest {

    private static final double DELTA = 1e-15; // the maximum delta between expected and actual for which both values are still considered equal.
    @Test
    public void testEmpty() {
        OptionalDouble maybeVar = OptionalDouble.empty();        
        assertFalse(maybeVar.isPresent());
    }


    @Test
    public void testIsPresent() {
        OptionalDouble maybeVar = OptionalDouble.of(50);
        assertTrue(maybeVar.isPresent());
    }


    @Test
    public void testGetAsDouble() {
        OptionalDouble maybeVar = OptionalDouble.of(100.20);
        assertEquals(100.20, maybeVar.getAsDouble(), DELTA);
    }


    @Test
    public void testOrElse() {
        OptionalDouble maybeVar = OptionalDouble.empty();        
        assertEquals(60, maybeVar.orElse(60), DELTA);
    }

    @Test
    public void testOrElseGet() {
        OptionalDouble maybeVar = OptionalDouble.empty();
        assertEquals(42, maybeVar.orElseGet(() -> 42), DELTA);
        
    }


    @Test
    public void testSupplier() {
        OptionalDouble maybeVar = OptionalDouble.empty();

        DoubleSupplier supplier = new DoubleSupplier() { // set value using anonymous function
            @Override
            public double getAsDouble() {
                return 42;
            }
        };
        assertEquals(42, maybeVar.orElseGet(supplier), DELTA);

        DoubleSupplier supplier2  = () -> 0.05;  // set value with lambda expression

        assertEquals(0.05, maybeVar.orElseGet(supplier2), DELTA);
    }

    @Test(expected=IllegalStateException.class)
    public void testOrElseThrow() {        
        Optional<Double> maybeVar = Optional.empty();
        maybeVar.orElseThrow(IllegalStateException::new);
    }



}