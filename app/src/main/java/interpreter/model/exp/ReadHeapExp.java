
package interpreter.model.exp;

import interpreter.exceptions.MyException;
import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.IHeap;
import interpreter.model.value.IValue;
import interpreter.model.value.RefValue;

// A4: E6 - Reads a value from the Heap
public record ReadHeapExp(IExp exp) implements IExp {

    @Override
    public IValue eval(IDictionary<String, IValue> symTable, IHeap heap) throws MyException {

        // A4: Evaluates the expression
        IValue value = exp.eval(symTable, heap);

        // A4: Checks that the result is a reference
        if (!(value instanceof RefValue ref))
            throw new ExpressionException("rH: expression is not a reference");

        // A4: Checks that the address exists in the Heap
        if (!heap.isDefined(ref.address()))
            throw new ExpressionException("rH: address is not in the Heap");

        // A4: Returns the value stored at the address
        return heap.get(ref.address());
    }

    @Override
    public String toString() {
        return "rH(" + exp + ")";
    }
}
