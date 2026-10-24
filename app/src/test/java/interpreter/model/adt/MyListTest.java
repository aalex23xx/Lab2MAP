
package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyListTest {

    @Test
    void addGetAndSizeWork() throws ADTException {
        MyList<Integer> list = new MyList<>();

        list.add(5);
        list.add(10);

        assertEquals(5, list.get(0));
        assertEquals(10, list.get(1));
        assertEquals(2, list.size());
    }
}
