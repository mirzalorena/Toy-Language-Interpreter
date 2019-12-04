package Model.Statements;


import Model.MyException;
import Model.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws MyException;
    String toString();

    IStatement deepCopy();


}
