package Model.Statements.Heap;

import Collection.InterfaceHeap;
import Collection.InterfaceMyDictionary;
import Model.DataStructures.*;
import Model.Expressions.Expression;
import Model.MyException;
import Model.ProgramState;
import Model.Statements.IStatement;

public class NewStatement implements IStatement {

    private StringValue var_name;
    private Expression expression;

    public NewStatement(StringValue var_name,Expression exp)
    {
        this.var_name=var_name;
        this.expression=exp;
    }

    @Override
    public String toString(){
        return "new( " + var_name + ", " + expression + " )";
    }

    @Override
    public  IStatement deepCopy()
    {
        return new NewStatement(var_name,expression);
    }

    @Override
   public  ProgramState execute(ProgramState state) throws MyException
    {
        if (state.getSymbolTable().containsKey(var_name.toString()) && state.getSymbolTable().get(var_name.toString()).getType() instanceof RefType){
            try {
                Value aux = expression.eval(state.getSymbolTable(), state.getHeapTable());

                RefValue valueSymTable = (RefValue)state.getSymbolTable().get(var_name.toString());
                InterfaceHeap<Integer, Value> heapTable = state.getHeapTable();

                if ( aux.getType().equals(valueSymTable.getVal())){
                    RefValue newValue = new RefValue((Integer) heapTable.getCurrentFree(), aux.getType());
                    newValue.setAdress((Integer) heapTable.getCurrentFree());
                    state.getSymbolTable().put(var_name.toString(), newValue);
                    heapTable.update(aux);
                }
            }
            catch (MyException error){
                throw new MyException("Error on executing expression");
            }
        }
        else
            throw new MyException("Error on executing the new statement");

        return state;



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
