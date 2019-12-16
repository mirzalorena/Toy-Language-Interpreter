package Model.Expressions;

import Collection.InterfaceHeap;
import Collection.InterfaceMyDictionary;
import Model.DataStructures.Type;
import Model.MyException;
import Model.DataStructures.Value;

public class VarExpression implements Expression {
    private String id;

    public VarExpression(String nid)
    {
        this.id=nid;
    }
    /*public String getId()
    {
        return id;
    }*/
    /*public void setId(String id)
    {
        this.id=id;
    }*/

    @Override
    public Value eval(InterfaceMyDictionary<String,Value> tbl, InterfaceHeap<Integer,Value> heapTbl) throws MyException
    {
        return tbl.lookup(id);

        //return null;
    }

    @Override
    public String toString()
    {
        return id;
    }

    @Override
    public Type typecheck(InterfaceMyDictionary<String,Type> typeEnv) throws MyException
    {
        return typeEnv.lookup(id);

    }


}
