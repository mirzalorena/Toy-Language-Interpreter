package Model.Statements.File;

import Collection.InterfaceMyDictionary;
import Model.DataStructures.*;
import Model.Expressions.Expression;
import Model.MyException;
import Model.ProgramState;
import Model.Statements.IStatement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenFileStatement implements IStatement {

    private Expression exp;

    public OpenFileStatement(Expression e)
    {
        this.exp=e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {

        //state.getExecutionStack().pop();
        Value result=exp.eval(state.getSymbolTable(),state.getHeapTable());

        if(!result.getType().equals(new StringType()))
        {
            throw new MyException("Not string type");
        }
        else
        {
            if(state.getFileTable().containsKey((StringValue)result))
                throw new MyException("Key already there");
            else
            {
                try
                {
                    BufferedReader buff=new BufferedReader(new FileReader((String)result.getVal()));
                    state.getFileTable().put((StringValue)result,buff);
                }
                catch (IOException e)
                {
                    throw new MyException(e.getMessage());
                }
            }
        }


        return state;
    }

    @Override
    public IStatement deepCopy()
    {
        return new OpenFileStatement(exp);
    }

    @Override
    public String toString()
    {
        return "OpenFile( "+exp.toString()+" )";
    }

    @Override
    public InterfaceMyDictionary<String, Type> typecheck(InterfaceMyDictionary<String,Type> typeEnv) throws MyException
    {
        Type typexp = exp.typecheck(typeEnv);
            return typeEnv;

    }



}
