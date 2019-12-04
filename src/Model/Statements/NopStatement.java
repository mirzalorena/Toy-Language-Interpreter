package Model.Statements;

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
}
