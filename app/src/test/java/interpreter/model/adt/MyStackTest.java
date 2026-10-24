
package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyStackTest {

    @Test
    void pushPopUsesLifoOrder() throws ADTException {
        MyStack<Integer> stack = new MyStack<>();

        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void popFromEmptyStackThrows() {
        MyStack<Integer> stack = new MyStack<>();

        assertThrows(ADTException.class, () -> stack.pop());
    }
}
