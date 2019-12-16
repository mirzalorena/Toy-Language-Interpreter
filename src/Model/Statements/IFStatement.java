package Model.Statements;

import Collection.InterfaceHeap;
import Collection.InterfaceMyDictionary;
import Collection.InterfaceMyStack;
import Model.DataStructures.*;
import Model.Expressions.Expression;
import Model.MyException;
import Model.ProgramState;

public class IFStatement implements IStatement {

    private Expression expression;
    private IStatement thenStatements;
    private IStatement elseStatement;

    public IFStatement(Expression expression, IStatement
                       i1,IStatement i2)
    {
        this.expression=expression;
        this.elseStatement=i2;
        this.thenStatements=i1;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
        InterfaceMyStack<IStatement> stack=state.getExecutionStack();
        InterfaceMyDictionary<String, Value> symbolTable=state.getSymbolTable();
        InterfaceHeap<Integer,Value> heapTable=state.getHeapTable();
        Value value;

        try{
            value=expression.eval(symbolTable,heapTable);
        }
        catch(MyException e)
        {
            throw new MyException(e.getMessage());
        }
        if(value.equals(new BoolValue(true)))
            stack.push(thenStatements);
        else
            stack.push(elseStatement);

        return null;
    }

    @Override
    public String toString()
    {
        return "IF("+expression.toString()+") then ("+
                thenStatements.toString()+ ") else ("+
                elseStatement.toString()+")\n";
    }

    @Override
    public IStatement deepCopy()
    {
        return new IFStatement(expression,thenStatements,elseStatement);
    }

    @Override
    public InterfaceMyDictionary<String, Type> typecheck(InterfaceMyDictionary<String,Type> typeEnv) throws MyException
    {
        Type typexp= expression.typecheck(typeEnv);
        if(typexp.equals(new IntType()) || typexp.equals(new BoolType()))
        {
            thenStatements.typecheck(typeEnv.deepCopy());
            elseStatement.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else throw new MyException("lala");

    }

}
