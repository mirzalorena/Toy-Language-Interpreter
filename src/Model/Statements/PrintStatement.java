package Model.Statements;

import Collection.InterfaceHeap;
import Collection.InterfaceMyDictionary;
import Collection.InterfaceMyList;
import Model.DataStructures.Type;
import Model.Expressions.Expression;
import Model.MyException;
import Model.ProgramState;
import Model.DataStructures.Value;

public class PrintStatement implements IStatement {

    private Expression expression;

    public PrintStatement(Expression expression)
    {
        this.expression=expression;
    }

    @Override
    public String toString()
    {
        return "print( "+expression.toString()+")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
        InterfaceMyList<Value> output=state.getOutputList();
        InterfaceMyDictionary<String,Value> symbolTable=state.getSymbolTable();
        InterfaceHeap<Integer,Value> heapTable=state.getHeapTable();

        Value value=expression.eval(symbolTable,heapTable);

        output.add(value);

        return null;
    }

    @Override
    public IStatement deepCopy()
    {
        return new PrintStatement(expression);
    }

    @Override
    public InterfaceMyDictionary<String, Type> typecheck(InterfaceMyDictionary<String,Type> typeEnv) throws MyException
    {
        expression.typecheck(typeEnv);
        return typeEnv;

    }

}
