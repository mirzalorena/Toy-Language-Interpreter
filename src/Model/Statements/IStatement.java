package Model.Statements;


import Collection.InterfaceMyDictionary;
import Model.DataStructures.Type;
import Model.MyException;
import Model.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws MyException;
    String toString();

    IStatement deepCopy();

    InterfaceMyDictionary<String, Type> typecheck(InterfaceMyDictionary<String,Type> typeEnv) throws MyException;


}
