
package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import java.util.List;

public interface IList<T> {
    void add(T value);
    T get(int index) throws ADTException;
    int size();

    // New in A3: returns the list elements in insertion order
    List<T> toList();
}
