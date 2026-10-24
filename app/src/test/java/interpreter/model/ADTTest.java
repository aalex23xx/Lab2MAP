
package interpreter.model;

import interpreter.exceptions.ADTException;
import interpreter.model.adt.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ADTTest {

    // MyStack

    @Test
    void testStackEmpty() {
        MyStack<Integer> stack = new MyStack<>();

        assertTrue(stack.isEmpty());
    }

    @Test
    void testStackPushPop() throws ADTException {
        MyStack<Integer> stack = new MyStack<>();

        stack.push(5);

        assertFalse(stack.isEmpty());
        assertEquals(5, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testStackOrder() throws ADTException {
        MyStack<Integer> stack = new MyStack<>();

        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void testStackPopEmpty() {
        MyStack<Integer> stack = new MyStack<>();

        assertThrows(ADTException.class, () -> stack.pop());
    }

    // MyDictionary

    @Test
    void testDictionaryPut() throws ADTException {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();

        assertFalse(dictionary.isDefined("x"));

        dictionary.put("x", 5);

        assertTrue(dictionary.isDefined("x"));
        assertEquals(5, dictionary.lookup("x"));
    }

    @Test
    void testDictionaryUpdate() throws ADTException {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();

        dictionary.put("x", 5);
        dictionary.update("x", 10);

        assertEquals(10, dictionary.lookup("x"));
    }

    @Test
    void testDictionaryLookupMissing() {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();

        assertThrows(ADTException.class,
                () -> dictionary.lookup("x"));
    }

    @Test
    void testDictionaryUpdateMissing() {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();

        assertThrows(ADTException.class,
                () -> dictionary.update("x", 10));
    }

    // MyList

    @Test
    void testListAddGet() throws ADTException {
        MyList<Integer> list = new MyList<>();

        list.add(5);
        list.add(10);

        assertEquals(5, list.get(0));
        assertEquals(10, list.get(1));
    }

    @Test
    void testListSize() {
        MyList<Integer> list = new MyList<>();

        assertEquals(0, list.size());

        list.add(5);
        list.add(10);

        assertEquals(2, list.size());
    }

    @Test
    void testListNegativeIndex() {
        MyList<Integer> list = new MyList<>();

        assertThrows(ADTException.class,
                () -> list.get(-1));
    }

    @Test
    void testListInvalidIndex() {
        MyList<Integer> list = new MyList<>();

        list.add(5);

        assertThrows(ADTException.class,
                () -> list.get(1));
    }
}
