
package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import java.util.Map;

public interface IDictionary<K, V> {
    boolean isDefined(K key);
    V lookup(K key) throws ADTException;
    void put(K key, V value);
    void update(K key, V value) throws ADTException;

    // removes a key and its value, or throws ADTException if missing
    void remove(K key) throws ADTException;

    // returns a read-only copy of the dictionary
    Map<K, V> toMap();
}
