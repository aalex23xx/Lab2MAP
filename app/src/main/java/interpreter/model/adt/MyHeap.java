
package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import interpreter.model.value.IValue;

import java.util.HashMap;
import java.util.Map;

// A4: Implements the Heap using a HashMap and unique addresses
public class MyHeap implements IHeap {

    private Map<Integer, IValue> heap = new HashMap<>();
    private int nextFree = 1;  // address 0 means null

    @Override
    public int allocate(IValue value) {
        int address = nextFree++;
        heap.put(address, value);
        return address;
    }

    @Override
    public void setContent(Map<Integer, IValue> content) {
        heap = new HashMap<>(content);
    }

    // A4: Gets the value at an address or throws if it does not exist
    @Override
    public IValue get(int address) throws ADTException {
        if (!isDefined(address)) {
            throw new ADTException("Heap address " + address + " is not defined");
        }
        return heap.get(address);
    }

    // A4: Updates an existing address or throws if it does not exist
    @Override
    public void update(int address, IValue value) throws ADTException {
        if (!isDefined(address)) {
            throw new ADTException("Heap address " + address + " is not defined");
        }
        heap.put(address, value);
    }

    // A4: Checks if an address exists in the Heap
    @Override
    public boolean isDefined(int address) {
        return heap.containsKey(address);
    }

    // A4: Returns a read-only copy of the Heap for logging and garbage collection
    @Override
    public Map<Integer, IValue> getContent() {
        return Map.copyOf(heap);
    }

    // A4: Returns the Heap as text
    @Override
    public String toString() {
        return heap.toString();
    }
}
