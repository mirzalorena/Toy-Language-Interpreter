package Model.Statements;

import Collection.InterfaceMyDictionary;
import Model.DataStructures.BoolType;
import Model.DataStructures.Type;
import Model.MyException;
import Model.ProgramState;

public class NopStatement implements IStatement {

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
        return null;
    }

    @Override
    public String toString()
    {
        return "";
    }

    @Override
    public IStatement deepCopy()
    {
        return new NopStatement();
    }

    @Override
    public InterfaceMyDictionary<String, Type> typecheck(InterfaceMyDictionary<String,Type> typeEnv) throws MyException
    {
        return null;

    }
}
