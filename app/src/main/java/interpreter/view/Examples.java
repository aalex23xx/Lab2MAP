
package interpreter.view;

import interpreter.model.stmt.*;
import interpreter.model.exp.*;
import interpreter.model.type.*;
import interpreter.model.value.*;


public class Examples {

    // Ex1: int v; v = 2; print(v)

    public IStmt ex1 = new CompStmt(

            // v with default value 0
            new VarDeclStmt("v", new IntType()),

            // assignment and print
            new CompStmt(

                    // constant value 2 to v
                    new AssignStmt("v",
                            new ValueExp(new IntValue(2))),

                    // Print
                    new PrintStmt(new VarExp("v"))
            )
    );

    // Ex2: int a; int b; a = 2 + 3 * 5; b = a + 1; print(b)

    public IStmt ex2 = new CompStmt(

            // a with default value 0
            new VarDeclStmt("a", new IntType()),

            new CompStmt(

                    //b with default value 0
                    new VarDeclStmt("b", new IntType()),

                    new CompStmt(

                            // result of 2 + 3 * 5 -> a
                            new AssignStmt("a",

                                    // 2 to the result of 3 * 5
                                    new ArithExp(ArithOp.ADD,
                                            new ValueExp(new IntValue(2)),

                                            // 3 * 5 = 15
                                            new ArithExp(ArithOp.MUL,
                                                    new ValueExp(new IntValue(3)),
                                                    new ValueExp(new IntValue(5))
                                            )
                                    )
                            ),

                            new CompStmt(

                                    // Assigns a + 1 to b (17 + 1 = 18)
                                    new AssignStmt("b",

                                            // Reads a from SymTable and adds 1
                                            new ArithExp(ArithOp.ADD,
                                                    new VarExp("a"),
                                                    new ValueExp(new IntValue(1))
                                            )
                                    ),

                                    // Prints b
                                    new PrintStmt(new VarExp("b"))
                            )
                    )
            )
    );

    // Ex3: bool a; int v; a = true;
    // if a then v = 2 else v = 3; print(v)

    public IStmt ex3 = new CompStmt(

            //false to start
            new VarDeclStmt("a", new BoolType()),

            new CompStmt(

                    //  v with default value 0
                    new VarDeclStmt("v", new IntType()),

                    new CompStmt(

                            // true to boolean variable a
                            new AssignStmt("a",
                                    new ValueExp(new BoolValue(true))),

                            new CompStmt(

                                    // Evaluates a and selects the correct branch
                                    new IfStmt(

                                            // Reads the boolean value of a
                                            new VarExp("a"),

                                            // THEN: assigns 2 to v if a is true
                                            new AssignStmt("v",
                                                    new ValueExp(new IntValue(2))),

                                            // ELSE: assigns 3 to v if a is false
                                            new AssignStmt("v",
                                                    new ValueExp(new IntValue(3)))
                                    ),

                                    // Prints
                                    new PrintStmt(new VarExp("v"))
                            )
                    )
            )
    );

    // A3: Ex4 reads two numbers from a file and prints them
    public IStmt ex4 = fileExample("test.in");

    // A3: Creates the file reading example
    public static IStmt fileExample(String fileName) {

        return new CompStmt(
                new VarDeclStmt("varf", new StringType()),

                new CompStmt(
                        new AssignStmt("varf",
                                new ValueExp(new StringValue(fileName))),

                        new CompStmt(
                                new OpenRFileStmt(new VarExp("varf")),

                                new CompStmt(
                                        new VarDeclStmt("varc", new IntType()),

                                        new CompStmt(
                                                new ReadFileStmt(new VarExp("varf"), "varc"),

                                                new CompStmt(
                                                        new PrintStmt(new VarExp("varc")),

                                                        new CompStmt(
                                                                new ReadFileStmt(new VarExp("varf"), "varc"),

                                                                new CompStmt(
                                                                        new PrintStmt(new VarExp("varc")),

                                                                        new CloseRFileStmt(new VarExp("varf"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    // A3: Ex5 - int a; int b; a = 5; b = 7;
    // if (a < b) then print(a) else print(b)
    public IStmt ex5 = new CompStmt(
            new VarDeclStmt("a", new IntType()),

            new CompStmt(
                    new VarDeclStmt("b", new IntType()),

                    new CompStmt(
                            new AssignStmt("a",
                                    new ValueExp(new IntValue(5))),

                            new CompStmt(
                                    new AssignStmt("b",
                                            new ValueExp(new IntValue(7))),

                                    new IfStmt(
                                            new RelationalExp(
                                                    RelOp.LT,
                                                    new VarExp("a"),
                                                    new VarExp("b")
                                            ),

                                            new PrintStmt(new VarExp("a")),
                                            new PrintStmt(new VarExp("b"))
                                    )
                            )
                    )
            )
    );
}
