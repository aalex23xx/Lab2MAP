
package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import java.util.HashMap;
import java.util.Map;

// Implements the dictionary ADT using a HashMap
// store variable names and their values
public class MyDictionary<K, V> implements IDictionary<K, V> {


    private final Map<K, V> dictionary = new HashMap<>();

    @Override
    public boolean isDefined(K key) {
        // Checks if the key exists
        return dictionary.containsKey(key);
    }

    @Override
    public V lookup(K key) throws ADTException {

        if (!isDefined(key)) {
            throw new ADTException("key not found");
        }

        return dictionary.get(key);
    }

    @Override
    public void put(K key, V value) {
        // Add/replace key-value pair
        dictionary.put(key, value);
    }

    @Override
    public void update(K key, V value) throws ADTException {
        // Cannot update a key that does not exist
        if (!isDefined(key)) {
            throw new ADTException("key not found");
        }

        // Updates value
        dictionary.put(key, value);
    }

    //A3: remove a key and its value from the dictionary
    @Override
    public void remove(K key) throws ADTException {

        // Checks if the key exists
        if (!isDefined(key)) {
            throw new ADTException("key not found");
        }

        dictionary.remove(key);
    }

    //return a read-only copy of the dictionary
    @Override
    public Map<K, V> toMap() {
        return Map.copyOf(dictionary);
    }

    @Override
    public String toString() {

        return dictionary.toString();
    }
}
