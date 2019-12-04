package Model.Expressions;

import Collection.InterfaceHeap;
import Collection.InterfaceMyDictionary;
import Model.DataStructures.BoolValue;
import Model.DataStructures.IntType;
import Model.DataStructures.Value;
import Model.MyException;

public class RelationalExpression implements Expression {

    private Expression exp1;
    private Expression exp2;
    private int op;

    public RelationalExpression(String input,Expression exp1,Expression exp2)
    {
        this.exp1=exp1;
        this.exp2=exp2;

        switch (input) {
            case "<":
                op = 1;
                break;
            case "<=":
                op = 2;
                break;
            case "==":
                op = 3;
                break;
            case "!=":
                op = 4;
                break;
            case ">":
                op = 5;
                break;
            case ">=":
                op = 6;
                break;
        }


    }

    @Override
    public Value eval(InterfaceMyDictionary<String,Value> tbl, InterfaceHeap<Integer,Value> heapTbl) throws MyException
    {
        Value v1, v2;
        v1 = exp1.eval(tbl, heapTbl);
        if (v1.getType().equals(new IntType())){
            v2 = exp2.eval(tbl, heapTbl);
            if (v2.getType().equals(new IntType())){
                int n1, n2;
                n1 = (int)v1.getVal();
                n2 = (int)v2.getVal();

        if(op==1)
            return new BoolValue(n1<n2);
        else if(op==2)
            return new BoolValue(n1<=n2);
        else if(op==3)
            return new BoolValue(n1==n2);
        else if(op==4)
            return new BoolValue(n1!=n2);
        else if(op==5)
            return new BoolValue(n1>n2);
        else if(op==6)
            return new BoolValue(n1>=n2);
        else
            throw new MyException("op not found");
            }
            else
                throw new MyException("first operand is not an integer");
        }
        return null;


        //return null;
    }

    @Override
    public String toString()
    {

        String result=exp1.toString();
        if(op==1)
            result+="<";
        else if(op==2)
            result+="<=";
        else if (op==3)
            result+="==";
        else if(op==4)
            result+="!=";
        else if(op==5)
            result+=">";
        else if(op==6)
            result+=">=";
        result+=exp2.toString();


        return result;
    }



}
