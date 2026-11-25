
package interpreter.controller;

import interpreter.model.value.IValue;
import interpreter.model.value.RefValue;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

// A4: Garbage collector for dynamically allocated Heap values
public final class GarbageCollector {

    private GarbageCollector() {}

    /** The addresses that occur in the RefValues of the SymTable. */
    public static Set<Integer> getAddrFromSymTable(Collection<IValue> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue) v).address())
                .collect(Collectors.toSet());
    }

    /** UNSAFE: keeps only the heap entries referred to directly from the SymTable. */
    public static Map<Integer, IValue> unsafeGarbageCollector(Set<Integer> symTableAddr,
                                                              Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    // Finds all addresses reachable directly or indirectly from SymTable
    public static Set<Integer> reachableAddresses(Set<Integer> roots,
                                                  Map<Integer, IValue> heap) {

        Set<Integer> reachable = new HashSet<>(roots);

        boolean changed = true;

        // Repeats until no new reachable addresses are found
        while (changed) {
            changed = false;

            // Iterates through all Heap locations
            for (Map.Entry<Integer, IValue> entry : heap.entrySet()) {

                // Checks if the location is reachable and contains a reference
                if (reachable.contains(entry.getKey())
                        && entry.getValue() instanceof RefValue ref) {

                    //  Gets the address stored in the reference
                    int address = ref.address();

                    // Checks if the address exists and was not already discovered
                    if (heap.containsKey(address) && !reachable.contains(address)) {

                        // Adds the indirectly reachable address
                        reachable.add(address);

                        // A new address was found, so another iteration is needed
                        changed = true;
                    }
                }
            }
        }

        // directly/indirectly reachable addresses
        return reachable;
    }

    // Keep all reachable Heap locations
    public static Map<Integer, IValue> safeGarbageCollector(Set<Integer> roots,
                                                            Map<Integer, IValue> heap) {

        // Finds all directly and indirectly reachable addresses
        Set<Integer> reachable = reachableAddresses(roots, heap);

        // Creates a new map for the reachable Heap locations
        Map<Integer, IValue> result = new HashMap<>();

        // Iterates through all Heap locations
        for (Map.Entry<Integer, IValue> entry : heap.entrySet()) {

            // Keeps only reachable locations
            if (reachable.contains(entry.getKey())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        // Returns the Heap without unreachable locations
        return result;
    }
}
