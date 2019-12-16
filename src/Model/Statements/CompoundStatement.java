package Model.Statements;


import Collection.InterfaceMyDictionary;
import Collection.InterfaceMyStack;
import Model.DataStructures.Type;
import Model.MyException;
import Model.ProgramState;

public class CompoundStatement implements IStatement {

    private IStatement first;
    private IStatement second;

    public CompoundStatement(IStatement first,IStatement second)
    {
        this.first=first;
        this.second=second;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
        InterfaceMyStack<IStatement> stack=state.getExecutionStack();
        stack.push(second);
        stack.push(first);


        return null;
    }


    @Override
    public String toString()
    {
        return first.toString()+"; "+second.toString();
    }

    @Override
    public IStatement deepCopy()
    {
        return new CompoundStatement(first,second);
    }

    @Override
    public InterfaceMyDictionary<String, Type> typecheck(InterfaceMyDictionary<String,Type> typeEnv) throws MyException
    {

        return second.typecheck(first.typecheck(typeEnv));

    }

}
