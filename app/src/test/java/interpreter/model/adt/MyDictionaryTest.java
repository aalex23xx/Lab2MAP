
package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyDictionaryTest {

    @Test
    void putAndLookupWork() throws ADTException {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();

        dictionary.put("x", 5);

        assertEquals(5, dictionary.lookup("x"));
    }

    @Test
    void updateChangesValue() throws ADTException {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();

        dictionary.put("x", 5);
        dictionary.update("x", 10);

        assertEquals(10, dictionary.lookup("x"));
    }

    @Test
    void lookupMissingKeyThrows() {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();

        assertThrows(ADTException.class,
                () -> dictionary.lookup("x"));
    }
}
