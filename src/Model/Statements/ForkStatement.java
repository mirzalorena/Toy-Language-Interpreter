package Model.Statements;

import Collection.InterfaceMyDictionary;
import Collection.MyStack;
import Model.DataStructures.BoolType;
import Model.DataStructures.Type;
import Model.MyException;
import Model.ProgramState;

public class ForkStatement implements IStatement{

    private IStatement statement;

    public ForkStatement(IStatement statement)
    {
        this.statement=statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
        ProgramState newProgramState=new ProgramState(new MyStack<>(),state.getSymbolTable().deepCopy(),
                state.getOutputList(),statement,state.getFileTable(),state.getHeapTable(),state.getNewId());
        return newProgramState;
    }

    @Override
    public IStatement deepCopy()
    {
        return new ForkStatement(statement);
    }

    @Override
    public String toString()
    {
        return "fork("+statement.toString()+")";
    }

    @Override
    public InterfaceMyDictionary<String, Type> typecheck(InterfaceMyDictionary<String,Type> typeEnv) throws MyException
    {
        return statement.typecheck(typeEnv.deepCopy());

    }


}
