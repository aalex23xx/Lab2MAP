
package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHeapTest {

    // A4: Checks that heap addresses start at 1 and increase
    @Test
    void testAllocate() throws ADTException {
        IHeap heap = new MyHeap();

        assertEquals(1, heap.allocate(new IntValue(10)));
        assertEquals(2, heap.allocate(new IntValue(20)));
        assertEquals(3, heap.allocate(new IntValue(30)));

        assertEquals(new IntValue(10), heap.get(1));
        assertEquals(new IntValue(20), heap.get(2));
        assertEquals(new IntValue(30), heap.get(3));
    }

    // A4: Checks that a missing address cannot be read
    @Test
    void testGetMissingAddress() {
        IHeap heap = new MyHeap();

        assertThrows(ADTException.class, () -> heap.get(5));
    }

    // A4: Checks that a missing address cannot be updated
    @Test
    void testUpdateMissingAddress() {
        IHeap heap = new MyHeap();

        assertThrows(ADTException.class,
                () -> heap.update(5, new IntValue(20)));
    }

    // A4: Checks that an existing heap value can be updated
    @Test
    void testUpdate() throws ADTException {
        IHeap heap = new MyHeap();

        int address = heap.allocate(new IntValue(10));
        heap.update(address, new IntValue(20));

        assertEquals(new IntValue(20), heap.get(address));
    }
}
