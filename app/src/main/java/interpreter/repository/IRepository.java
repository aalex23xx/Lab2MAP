
package interpreter.repository;

import interpreter.model.PrgState;
import interpreter.exceptions.MyException;

public interface IRepository {

    PrgState getCrtPrg();

    // A3: Saves the current program state to a log file
    void logPrgStateExec() throws MyException;
}
