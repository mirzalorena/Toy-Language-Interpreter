package Model.Statements;

import Collection.InterfaceHeap;
import Collection.InterfaceMyDictionary;
import Model.*;
import Model.DataStructures.Type;
import Model.DataStructures.Value;
import Model.Expressions.Expression;


public class AssignStatement implements IStatement {

    private String id;
    private Expression expression;

    public AssignStatement(String id,Expression expression)
    {
        this.id=id;
        this.expression=expression;
    }

    @Override
    public String toString()
    {
        return id+" = "+expression.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
       /* InterfaceMyStack<IStatement> stack=state.getExecutionStack();
        InterfaceMyDictionary<String,Value> symbolTable=state.getSymbolTable();

        Model.DataStructures.Value value=expression.eval(symbolTable);

        try
        {
            value=expression.eval(symbolTable);
        }
        catch(MyException e)
        {
            throw new MyException(e.getMessage());
        }
        symbolTable.put(id,value);

        return null;*/
       // InterfaceMyStack<IStatement> stk = state.getExecutionStack();
        InterfaceMyDictionary<String, Value> symTbl = state.getSymbolTable();
        InterfaceHeap<Integer,Value> heapTable=state.getHeapTable();
        Value val = expression.eval(symTbl,heapTable);
        if (symTbl.containsKey(id)){
            Type typId = (symTbl.get(id)).getType();
            if(val.getType().equals(typId)){
                symTbl.put(id, val);
            }
            else{
                throw new MyException("declared type of variable " + id + " and type of the assigned expression do not match");
            }
        }
        else{
            throw new MyException("the used variable " + id + " was not declared before");
        }
        return null;
    }

    @Override
    public IStatement deepCopy()
    {
        return new AssignStatement(id,expression);
    }

    @Override
    public InterfaceMyDictionary<String, Type> typecheck(InterfaceMyDictionary<String,Type> typeEnv) throws MyException
    {
        Type typevar=typeEnv.lookup(id);
        Type typexp=expression.typecheck(typeEnv);
        if(typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("assign");

    }

}
