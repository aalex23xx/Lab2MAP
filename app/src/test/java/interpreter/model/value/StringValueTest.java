
package interpreter.model.value;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringValueTest {

    @Test
    void testEqualsAndHashCode() {
        StringValue value1 = new StringValue("hello");
        StringValue value2 = new StringValue("hello");

        // Checks that two string values with the same text are equal
        assertEquals(value1, value2);

        // Checks that equal values have the same hash code
        assertEquals(value1.hashCode(), value2.hashCode());
    }
}
