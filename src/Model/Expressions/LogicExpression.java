package Model.Expressions;

import Collection.InterfaceHeap;
import Collection.InterfaceMyDictionary;
import Model.DataStructures.*;
import Model.MyException;

public class LogicExpression implements Expression {
    private Expression e1;
    private Expression e2;
    private int op;

    public LogicExpression(Expression e1,Expression e2)
    {
        this.e1=e1;
        this.e2=e2;
    }

    @Override
    public Value eval(InterfaceMyDictionary<String,Value> tbl, InterfaceHeap<Integer,Value> heapTbl) throws MyException
    {
        //int finalResult;
        Value rez1=this.e1.eval(tbl,heapTbl);
        Value rez2=this.e2.eval(tbl,heapTbl);

        rez1=e1.eval(tbl,heapTbl);
        if(rez1.getType().equals(new BoolType()))
        {
            rez2=e2.eval(tbl,heapTbl);
            if(rez2.getType().equals(new BoolType()))
            {
                BoolValue bool1=(BoolValue)rez1;
                BoolValue bool2=(BoolValue)rez1;
                boolean n1,n2;
                n1= (boolean)bool1.getVal();
                n2=(boolean)bool2.getVal();
                if(op==1)
                    return new BoolValue(n1&&n2);
                if(op==2)
                    return new BoolValue(n1||n2);
            }
        }
        else
            throw new MyException("second operand not boolean");


        return null;
    }

    @Override
    public Type typecheck(InterfaceMyDictionary<String,Type> typeEnv) throws MyException
    {
        Type typ1,typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);

        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");

    }

}
