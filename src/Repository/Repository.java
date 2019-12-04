package Repository;
import Model.MyException;
import Model.ProgramState;

import java.io.*;
import java.util.ArrayList;

public class Repository implements InterfaceRepository {

    //private ArrayList<ProgramState> programStateList=new ArrayList<>();

    private ArrayList<ProgramState> repo;
    private String logFilePath;

    /*public Repository(ProgramState prg)
    {
        programStateList=new ArrayList();
        this.programStateList.add(prg);
    }*/

    public Repository()
    {
        repo=new ArrayList<>();
        logFilePath="";
    }

    public Repository(String logFilePath)
    {
        this.repo=new ArrayList<>();
        this.logFilePath=logFilePath;
    }

    public Repository(ArrayList<ProgramState> myrepo)
    {
        this.repo=myrepo;
        this.logFilePath="";
    }

    public Repository(ArrayList<ProgramState> myrepo,String logFilePath)
    {
        this.repo=myrepo;
        this.logFilePath=logFilePath;
    }

    @Override
    public ProgramState getCurrentProgram()
    {
        return repo.get(repo.size()-1);

    }

    @Override
    public void logPrgStateExec(ProgramState state) throws MyException
    {
        try{
        PrintWriter logFile=new PrintWriter(new BufferedWriter(new FileWriter(logFilePath,true)));
        logFile.write(state.toString());
        logFile.close();}
        catch( IOException e)
        {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public void deleteContent(ProgramState state) throws MyException{
        try{
            PrintWriter logFile=new PrintWriter(new BufferedWriter(new FileWriter(logFilePath,true)));
            logFile.write("");
            logFile.close();}
        catch( IOException e)
        {
            throw new MyException(e.getMessage());
        }

    }

    @Override
    public ArrayList<ProgramState> getProgramStateList()
    {
        return this.repo;

    }

    @Override
    public  void setProgramStateList(ArrayList<ProgramState> programStateList)
    {
        this.repo=programStateList;

    }



}
