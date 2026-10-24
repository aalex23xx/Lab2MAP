
package interpreter.model;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.*;
import interpreter.model.exp.*;
import interpreter.model.stmt.*;
import interpreter.model.type.*;
import interpreter.model.value.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StatementTest {

    private PrgState createState() {
        return new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new NopStmt()
        );
    }

    // S1 - CompStmt

    @Test
    void testCompStmt() throws MyException {
        PrgState state = createState();
        state.getExeStack().pop();

        IStmt first = new VarDeclStmt("x", new IntType());
        IStmt second = new PrintStmt(new VarExp("x"));

        CompStmt stmt = new CompStmt(first, second);
        stmt.execute(state);

        assertSame(first, state.getExeStack().pop());
        assertSame(second, state.getExeStack().pop());
    }

    // S2 - AssignStmt

    @Test
    void testAssignStmt() throws MyException {
        PrgState state = createState();
        state.getSymTable().put("x", new IntValue(0));

        AssignStmt stmt = new AssignStmt("x",
                new ValueExp(new IntValue(5)));

        stmt.execute(state);

        assertEquals(new IntValue(5),
                state.getSymTable().lookup("x"));
    }

    @Test
    void testAssignUndeclared() {
        PrgState state = createState();

        AssignStmt stmt = new AssignStmt("x",
                new ValueExp(new IntValue(5)));

        assertThrows(StatementException.class,
                () -> stmt.execute(state));
    }

    @Test
    void testAssignWrongType() {
        PrgState state = createState();
        state.getSymTable().put("x", new IntValue(0));

        AssignStmt stmt = new AssignStmt("x",
                new ValueExp(new BoolValue(true)));

        assertThrows(StatementException.class,
                () -> stmt.execute(state));
    }

    // S3 - VarDeclStmt

    @Test
    void testVarDeclInt() throws MyException {
        PrgState state = createState();

        VarDeclStmt stmt = new VarDeclStmt("x", new IntType());
        stmt.execute(state);

        assertEquals(new IntValue(0),
                state.getSymTable().lookup("x"));
    }

    @Test
    void testVarDeclBool() throws MyException {
        PrgState state = createState();

        VarDeclStmt stmt = new VarDeclStmt("a", new BoolType());
        stmt.execute(state);

        assertEquals(new BoolValue(false),
                state.getSymTable().lookup("a"));
    }

    @Test
    void testVarDeclDuplicate() throws MyException {
        PrgState state = createState();

        VarDeclStmt stmt = new VarDeclStmt("x", new IntType());
        stmt.execute(state);

        assertThrows(StatementException.class,
                () -> stmt.execute(state));
    }

    // S4 - PrintStmt

    @Test
    void testPrintStmt() throws MyException {
        PrgState state = createState();

        PrintStmt stmt = new PrintStmt(
                new ValueExp(new IntValue(7)));

        stmt.execute(state);

        assertEquals("[7]", state.getOut().toString());
    }

    @Test
    void testPrintError() {
        PrgState state = createState();

        PrintStmt stmt = new PrintStmt(new VarExp("x"));

        assertThrows(ExpressionException.class,
                () -> stmt.execute(state));
    }

    // S5 - IfStmt

    @Test
    void testIfTrue() throws MyException {
        PrgState state = createState();
        state.getExeStack().pop();

        IStmt thenStmt = new NopStmt();
        IStmt elseStmt = new PrintStmt(
                new ValueExp(new IntValue(3)));

        IfStmt stmt = new IfStmt(
                new ValueExp(new BoolValue(true)),
                thenStmt, elseStmt);

        stmt.execute(state);

        assertSame(thenStmt, state.getExeStack().pop());
    }

    @Test
    void testIfFalse() throws MyException {
        PrgState state = createState();
        state.getExeStack().pop();

        IStmt thenStmt = new NopStmt();
        IStmt elseStmt = new PrintStmt(
                new ValueExp(new IntValue(3)));

        IfStmt stmt = new IfStmt(
                new ValueExp(new BoolValue(false)),
                thenStmt, elseStmt);

        stmt.execute(state);

        assertSame(elseStmt, state.getExeStack().pop());
    }

    @Test
    void testIfWrongType() {
        PrgState state = createState();

        IfStmt stmt = new IfStmt(
                new ValueExp(new IntValue(5)),
                new NopStmt(),
                new NopStmt());

        assertThrows(StatementException.class,
                () -> stmt.execute(state));
    }

    // S6 - NopStmt

    @Test
    void testNopStmt() throws MyException {
        PrgState state = createState();
        state.getExeStack().pop();

        NopStmt stmt = new NopStmt();
        PrgState result = stmt.execute(state);

        assertSame(state, result);
        assertTrue(state.getExeStack().isEmpty());
        assertEquals("{}", state.getSymTable().toString());
        assertEquals("[]", state.getOut().toString());
    }
}
