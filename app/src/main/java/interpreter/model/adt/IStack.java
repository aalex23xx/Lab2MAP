
package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import java.util.List;

public interface IStack<T> {

    void push(T value);
    T pop() throws ADTException;
    boolean isEmpty();

    // Returns the stack elements as a list, top first (new in A3)
    List<T> toList();
}
