
package interpreter.model;

import interpreter.model.type.*;
import interpreter.model.value.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TypeValueTest {

    @Test
    void testIntType() {
        IntType type = new IntType();

        assertEquals(new IntValue(0), type.defaultValue());
        assertEquals("int", type.toString());
    }

    @Test
    void testBoolType() {
        BoolType type = new BoolType();

        assertEquals(new BoolValue(false), type.defaultValue());
        assertEquals("bool", type.toString());
    }

    @Test
    void testIntValue() {
        IntValue value = new IntValue(5);

        assertEquals(new IntType(), value.getType());
        assertEquals("5", value.toString());
        assertEquals(new IntValue(5), value);
    }

    @Test
    void testBoolValue() {
        BoolValue value = new BoolValue(true);

        assertEquals(new BoolType(), value.getType());
        assertEquals("true", value.toString());
        assertEquals(new BoolValue(true), value);
    }
}
