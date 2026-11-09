
package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import java.util.ArrayList;
import java.util.List;

// Implements the list ADT using an ArrayList
// Used for Out to store the values produced by print statements
public class MyList<T> implements IList<T> {

    // Stores the elements of the list
    private ArrayList<T> list = new ArrayList<>();

    @Override
    public void add(T value) {
        // Adds an element to the end
        list.add(value);
    }

    @Override
    public T get(int index) throws ADTException {

        if (index < 0 || index >= list.size()) {

            throw new ADTException("Index out of bounds");
        }

        // Returns the element at the specified index
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    // A3: returns a copy of the list in insertion order
    @Override
    public List<T> toList() {
        return new ArrayList<>(list);
    }

    @Override
    public String toString() {

        return list.toString();
    }
}
