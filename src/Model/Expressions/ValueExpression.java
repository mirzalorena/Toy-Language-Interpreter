package Model.Expressions;

import Collection.InterfaceHeap;
import Collection.InterfaceMyDictionary;
import Model.MyException;
import Model.DataStructures.Value;


public class ValueExpression implements Expression {
    private Value e;

    public ValueExpression(Value e)
    {
        this.e=e;
    }

    @Override
    public Value eval(InterfaceMyDictionary<String,Value> tbl, InterfaceHeap<Integer,Value> heapTbl) throws MyException
    {
        return e;
    }

    public String toString()
    {
        return e.toString();
    }


}
