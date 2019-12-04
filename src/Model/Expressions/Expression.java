package Model.Expressions;

import Collection.InterfaceHeap;
import Collection.InterfaceMyDictionary;
import Model.MyException;
import Model.DataStructures.Value;

public interface Expression {

        Value eval(InterfaceMyDictionary<String,Value> tbl, InterfaceHeap<Integer,Value> heapTbl) throws MyException;
         String toString();

}
