package Model;

import Collection.*;
import Model.DataStructures.Value;
import Model.Statements.IStatement;
import java.io.*;


public class ProgramState {
    private InterfaceMyStack<IStatement> executionStack;
    private InterfaceMyDictionary<String,Value> symbolTable;
    private InterfaceMyList<Value> outputList;
    private IStatement originalProgram;
    private InterfaceMyDictionary<Value, BufferedReader> fileTable;
    private InterfaceHeap heapTable;
    private int id;
    private static int lastAssigned=1;

    public static int getNewId()
    {
        lastAssigned++;
        return lastAssigned;
    }


    public ProgramState(InterfaceMyStack<IStatement> programStateStack,InterfaceMyDictionary<String,Value> symbolTable,InterfaceMyList<Value> outputList, IStatement originalProgram,InterfaceMyDictionary<Value, BufferedReader> fileTable,InterfaceHeap<Integer,Value> heapTable)
    {
       this.executionStack=programStateStack;
       this.symbolTable=symbolTable;
       this.outputList=outputList;
       this.originalProgram=originalProgram.deepCopy();
       this.fileTable=fileTable;
       this.heapTable=heapTable;
       this.id=getNewId();

       executionStack.push(originalProgram);
    }

    public ProgramState(InterfaceMyStack<IStatement> programStateStack,InterfaceMyDictionary<String,Value> symbolTable,InterfaceMyList<Value> outputList, IStatement originalProgram,InterfaceMyDictionary<Value, BufferedReader> fileTable,InterfaceHeap<Integer,Value> heapTable,int id)
    {
        this.executionStack=programStateStack;
        this.symbolTable=symbolTable;
        this.outputList=outputList;
        this.originalProgram=originalProgram.deepCopy();
        this.fileTable=fileTable;
        this.heapTable=heapTable;
        this.id=id;

        executionStack.push(originalProgram);
    }

    public ProgramState(IStatement initialProgram)
    {
        this.executionStack= new MyStack<>();
        this.symbolTable= new MyDictionary<String,Value>();
        this.outputList= new MyList<>();
        this.originalProgram = initialProgram.deepCopy();
        this.fileTable=new MyDictionary<Value,BufferedReader>();
        this.heapTable=new HeapTable<Integer,Value>();
        this.id=getNewId();

        executionStack.push(originalProgram);
    }



    public InterfaceMyStack<IStatement> getExecutionStack()
    {
        return executionStack;
    }

    public void setExecutionStack(MyStack<IStatement> executionStack)
    {
        this.executionStack=executionStack;
    }

    public InterfaceMyDictionary<String, Value> getSymbolTable()
    {
        return symbolTable;
    }

    public void setSymbolTable(InterfaceMyDictionary<String,Value> symbolTable)
    {
        this.symbolTable=symbolTable;
    }

    public InterfaceMyList<Value> getOutputList()
    {
        return outputList;
    }

    public void setOutputList(InterfaceMyList<Value> outputList)
    {
        this.outputList=outputList;
    }

    public IStatement getOriginalProgram()
    {
        return originalProgram;
    }

    public void setOriginalProgram(IStatement originalProgram)
    {
        this.originalProgram=originalProgram;
    }

    public InterfaceMyDictionary<Value,BufferedReader> getFileTable(){return this.fileTable;}

    public void setFileTable(InterfaceMyDictionary<Value, BufferedReader> fileTable){this.fileTable=fileTable;}

    public InterfaceHeap<Integer,Value> getHeapTable(){return this.heapTable;}

    public void setHeapTable(InterfaceHeap<Integer,Value> heapTable)
    {
        this.heapTable=heapTable;
    }

    public void addOut(Value number)
    {
        this.outputList.add(number);
    }

    public Boolean isNotCompleted()
    {
        return !executionStack.isEmpty();
    }

    public ProgramState oneStepExecution() throws MyException
    {
        if(executionStack.isEmpty())
            throw new MyException("Execution stack is empty\n");
        IStatement currentStatement=executionStack.pop();

            return currentStatement.execute(this);

    }



    @Override
    public String toString()
    {
        return "\nID: "+id +
                "\nExecution stack:\n"+
                executionStack.toString()+"\nSymbol table:\n"+
                symbolTable.toString()+"\nOutput list:\n"+
                outputList.toString()+"\nFile table:\n"+
                fileTable.toString()+"\nHeap table:\n"+
                heapTable.toString()+"\n";

    }







}
