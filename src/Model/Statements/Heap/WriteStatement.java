package Model.Statements.Heap;

import Collection.InterfaceHeap;
import Collection.InterfaceMyDictionary;
import Model.DataStructures.*;
import Model.Expressions.Expression;
import Model.MyException;
import Model.ProgramState;
import Model.Statements.IStatement;


public class WriteStatement implements IStatement {

    private StringValue var_name;
    private Expression expression;

    public WriteStatement(StringValue var_name,Expression expression)
    {
        this.var_name=var_name;
        this.expression=expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        InterfaceMyDictionary<String, Value> symbolTable = state.getSymbolTable();
        InterfaceHeap<Integer, Value> heapTable = state.getHeapTable();

        if (!symbolTable.containsKey(var_name.toString()))
            throw new MyException("failed");

        Value oldVal = symbolTable.get(var_name.toString());

        if (!(oldVal.getType() instanceof RefType)) {
            throw new MyException("Failed to write to heap. Type is not reference!");
        }

        if (!heapTable.isDefined(((RefValue) oldVal).getAdress()))
            throw new MyException("Failed to write to heap. Address not defined!");

        Value newVal =expression.eval(symbolTable,heapTable);

        if(!newVal.getType().equals(((RefValue)oldVal).getVal()))
        {
            throw new MyException("Failed");
        }

        heapTable.update(((RefValue)oldVal).getAdress(),newVal);
        return state;


    }


    @Override
    public IStatement deepCopy()
    {
        return new WriteStatement(var_name,expression);
    }
    @Override
    public String toString() {
        return "wH( " + var_name + ", " + expression.toString() + ") ";
    }

    @Override
    public InterfaceMyDictionary<String, Type> typecheck(InterfaceMyDictionary<String,Type> typeEnv) throws MyException
    {
        Type typevar = typeEnv.lookup(var_name.toString());
        Type typexp = expression.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("la");

    }



}
