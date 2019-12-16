package Model.Expressions;

import Collection.InterfaceHeap;
import Collection.InterfaceMyDictionary;
import Model.*;
import Model.DataStructures.IntType;
import Model.DataStructures.IntValue;
import Model.DataStructures.Type;
import Model.DataStructures.Value;

public class ArithmeticExpression implements Expression {
    private Expression e1;
    private Expression e2;
    private int op;

    public ArithmeticExpression(char input,Expression e1,Expression e2)
    {
        this.e1=e1;
        this.e2=e2;
        if(input=='+')
        {
            this.op=1;
        }
        else if(input=='-')
        {
            this.op=2;
        }
        else if(input=='*')
        {
            this.op=3;
        }
        else if(input=='/')
        {
            this.op=4;
        }
    }


    @Override
    public Value eval(InterfaceMyDictionary<String,Value> tbl, InterfaceHeap<Integer,Value> heapTbl) throws MyException
    {
        Value rez1=null;
        Value rez2=null;

        rez1=e1.eval(tbl,heapTbl);
        if(rez1.getType().equals(new IntType()))
        {
            rez2=e2.eval(tbl,heapTbl);
            if(rez2.getType().equals(new IntType()))
            {
                IntValue int1=(IntValue)rez1;
                IntValue int2=(IntValue)rez2;
                int n1,n2;
                n1=(int)int1.getVal();
                n2=(int)int2.getVal();
                if(op==1)
                {
                    //System.out.print(n1+ " " + n2);
                    return new IntValue(n1+n2);}
                if(op==2)
                    return new IntValue(n1-n2);
                if(op==3)
                    return new IntValue(n1*n2);
                if(op==4)
                    if(n2==0)
                        throw new MyException("division by 0");
                    else
                        return new IntValue(n1/n2);
           }
        }
        else
            throw new MyException("second operand not an integer");
        return null;
    }

    @Override
    public String toString()
    {
        //String result=this.e1.toString()+this.op+this.e2.toString();

       //
        // return result;

        //String stringToPrint = "(" + e1.toString();
        String stringToPrint = e1.toString();
        if ( op == 1 )
            stringToPrint += " + ";
        else if ( op == 2 )
            stringToPrint += " - ";
        else if ( op == 3 )
            stringToPrint += " * ";
        else if ( op == 4 )
            stringToPrint += " / ";
        //stringToPrint += e2.toString() + ")";
        stringToPrint += e2.toString();
        return stringToPrint;

    }

    @Override
    public Type typecheck(InterfaceMyDictionary<String,Type> typeEnv) throws MyException
    {
        Type typ1,typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);

        if (typ1.equals(new IntType())) {
        if (typ2.equals(new IntType())) {
            return new IntType();
        } else
        throw new MyException("second operand is not an integer");
    }else
        throw new MyException("first operand is not an integer");

    }



}
