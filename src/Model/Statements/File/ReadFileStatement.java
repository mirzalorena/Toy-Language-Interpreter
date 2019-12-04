package Model.Statements.File;

import Collection.InterfaceMyDictionary;
import Model.DataStructures.IntType;
import Model.DataStructures.IntValue;
import Model.DataStructures.StringValue;
import Model.DataStructures.Value;
import Model.Expressions.Expression;
import Model.MyException;
import Model.ProgramState;
import Model.Statements.IStatement;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement {

    private Expression exp;
    private StringValue var_name;

    public ReadFileStatement(Expression e, StringValue vn)
    {
        this.exp=e;
        this.var_name=vn;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
        //state.getExecutionStack().pop();
        //Value result=exp.eval(state.getSymbolTable());

        if(state.getSymbolTable().containsKey((String)var_name.getVal()) && state.getSymbolTable().get((String)var_name.getVal()).getType().equals(new IntType()))
        {
            try
            {
                Value result=exp.eval(state.getSymbolTable(),state.getHeapTable());
                if(result instanceof StringValue)
                {
                    BufferedReader openedFile=state.getFileTable().get((StringValue)result);
                    String line=openedFile.readLine();
                    IntValue newVal;


                    if(line==null)
                    {
                        newVal=new IntValue(0);
                    }
                    else
                    {
                        newVal=new IntValue(Integer.parseInt(line));

                    }
                    state.getSymbolTable().put((String)var_name.getVal(),newVal);

                }
                else
                {
                    throw new MyException("not string value");
                }
            }
            catch(IOException e)
            {
                throw new MyException(e.getMessage());
            }
        }

        return state;
    }

   /* @Override
    public ProgramState execute(ProgramState state) throws MyException
    {

        InterfaceMyDictionary<String, Value> symbolTable=state.getSymbolTable();
        InterfaceMyDictionary<Value,BufferedReader> fileTable=state.getFileTable();


        if(var_name instanceof IntValue)
        {
            Value uniq=exp.eval(symbolTable);
            if(uniq instanceof StringValue)
            {
                BufferedReader b=fileTable.get(uniq);
                //BufferedReader b=pair.getBuffferedReader();
                String line=null;

                try
                {
                    line=b.readLine();
                }
                catch(IOException e)
                {
                    throw new MyException("cannot read\n");
                }

                IntValue value;
                if(line==null)
                {
                    new IntValue(0);
                }
                else
                {
                    try
                    {
                        new IntValue(Integer.parseInt(line));
                    }
                    catch(NumberFormatException e)
                    {
                        new IntValue(0);
                    }
                    //symboltTable=....
                }

            }
            else
            {
                throw new MyException("no string value\n");
            }

        }
        else
        {
            throw new MyException("no entry for this key and not an int value\n");
        }


        return state;
    }*/

    @Override
    public IStatement deepCopy()
    {
        return new ReadFileStatement(exp,var_name);
    }

    @Override
    public String toString()
    {
        return "readFile( "+exp.toString()+" , "+var_name+" )";
    }



}
