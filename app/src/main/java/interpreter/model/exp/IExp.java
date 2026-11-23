
package interpreter.model.exp;

import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
// A4: Imports the Heap interface
import interpreter.model.adt.IHeap;
import interpreter.model.value.IValue;

public interface IExp {

    // A4: Evaluates expressions using both SymTable and Heap
    IValue eval(IDictionary<String, IValue> symTable, IHeap heap) throws MyException;
}
