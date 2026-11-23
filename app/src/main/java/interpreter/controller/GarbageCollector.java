
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

    // A4: Finds all addresses reachable directly or indirectly from SymTable
    public static Set<Integer> reachableAddresses(Set<Integer> roots,
                                                  Map<Integer, IValue> heap) {

        Set<Integer> reachable = new HashSet<>(roots);

        boolean changed = true;

        // A4: Repeats until no new reachable addresses are found
        while (changed) {
            changed = false;

            for (Map.Entry<Integer, IValue> entry : heap.entrySet()) {

                if (reachable.contains(entry.getKey())
                        && entry.getValue() instanceof RefValue ref) {

                    int address = ref.address();

                    if (heap.containsKey(address) && !reachable.contains(address)) {
                        reachable.add(address);
                        changed = true;
                    }
                }
            }
        }

        return reachable;
    }

    // A4: Keeps all reachable Heap locations
    public static Map<Integer, IValue> safeGarbageCollector(Set<Integer> roots,
                                                            Map<Integer, IValue> heap) {

        Set<Integer> reachable = reachableAddresses(roots, heap);

        Map<Integer, IValue> result = new HashMap<>();

        for (Map.Entry<Integer, IValue> entry : heap.entrySet()) {

            if (reachable.contains(entry.getKey())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }
}
