package Model.Statements;

import Collection.InterfaceMyDictionary;
import Model.DataStructures.*;
import Model.MyException;
import Model.ProgramState;

public class VarDeclStmt implements IStatement {
    private String name;
    private Type typ;

    public VarDeclStmt(String name,Type typ)
    {
        this.name=name;
        this.typ=typ;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
        InterfaceMyDictionary<String,Value> symTable=state.getSymbolTable();
        if(symTable.containsKey(name))
            throw  new MyException("already there");
        if(typ.equals(new IntType()))
        {
            symTable.put(name, new IntValue(0));
        }
        else if(typ.equals(new BoolType()))
        {
            symTable.put(name, new BoolValue(true));
        }
        state.getSymbolTable().put(name, typ.defaultValue());
        //state.setSymbolTable(symTable);

        return null;
    }

    @Override
    public String toString()
    {
        return typ.toString()+" "+name;
    }

    @Override
    public IStatement deepCopy()
    {
        return new VarDeclStmt(name,typ);
    }

}
