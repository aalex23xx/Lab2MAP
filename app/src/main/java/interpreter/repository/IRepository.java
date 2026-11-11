
package interpreter.repository;

import interpreter.model.PrgState;
import interpreter.exceptions.MyException;

public interface IRepository {

    PrgState getCrtPrg();

    // A3: save current program state log file
    void logPrgStateExec() throws MyException;
}
