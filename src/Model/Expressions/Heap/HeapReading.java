package Model.Expressions.Heap;

import Collection.InterfaceHeap;
import Collection.InterfaceMyDictionary;
import Model.DataStructures.*;
import Model.Expressions.Expression;
import Model.MyException;

public class HeapReading implements Expression {

    private Expression expression;

    public HeapReading(Expression exp)
    {
        this.expression=exp;
    }

    public Value eval(InterfaceMyDictionary<String,Value> tbl, InterfaceHeap<Integer,Value> heapTbl) throws MyException
    {
        Value val=expression.eval(tbl,heapTbl);

        if(!(val instanceof RefValue))
            throw new MyException("failed");

        Integer adress=((RefValue) val).getAdress();

        if(!heapTbl.isDefined(adress))
            throw new MyException("failed");

        return heapTbl.getValue(adress);


    }

    @Override
    public String toString()
    {
        return "rH( "+expression.toString()+")";
    }

    @Override
    public Type typecheck(InterfaceMyDictionary<String,Type> typeEnv) throws MyException
    {
        Type typ=expression.typecheck(typeEnv);
        if(typ instanceof RefType)
        {
            RefType ref=(RefType) typ;
            return ref.getInner();
        }
        else
            throw new MyException("lala");

    }




}
