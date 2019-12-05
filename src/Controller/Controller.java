package Controller;

import Collection.InterfaceMyStack;
import Model.Statements.IStatement;
import Model.MyException;
import Model.ProgramState;
import Repository.InterfaceRepository;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {

    private InterfaceRepository repository;
    private GarbageColector myGarbageCollector;
    private ExecutorService executor;

    public Controller(InterfaceRepository repository)
    {
        this.repository=repository;
        this.myGarbageCollector=new GarbageColector();
    }

    private ArrayList<ProgramState> removeCompletedProgram(List<ProgramState> inProgress)
    {
        return (ArrayList) inProgress.stream().filter(p->p.isNotCompleted()).collect(Collectors.toList());
    }




    private void oneStepForAllProgram(ArrayList<ProgramState> programs) throws MyException,InterruptedException
    {
        //print in the log file
        programs.forEach(program-> {
            try
            {
                repository.logPrgStateExec(program);
            }
            catch (MyException e)
            {
                System.out.println(e.toString());
            }
        });

        //prepare the list of callables
        List<Callable<ProgramState>> callList=programs.stream().map((ProgramState p) -> (Callable<ProgramState>)()->{return p.oneStepExecution();}).collect(Collectors.toList());

        //start the execution of the callables
        List<ProgramState> newProgramList=null;
        try
        {
            newProgramList=executor.invokeAll(callList).stream().
                    map(future-> {
                        try
                        {
                            return future.get();
                        }
                        catch(InterruptedException | ExecutionException e)
                        {
                            System.out.println(e.toString());
                        }
                        return null;
                    }).filter(Objects::nonNull).collect(Collectors.toList());

        }
        catch (InterruptedException e)
        {
            System.out.println(e.toString());
        }

        //add the new created threads to the list of existing threads
        programs.addAll(newProgramList);
        programs.forEach(program->
        {
            try
            {
                repository.logPrgStateExec(program);
                System.out.println(repository.getCurrentProgram().toString()+"\n");
            }
            catch(MyException e)
            {
                System.out.println(e.toString());
            }
        });

        programs.addAll(newProgramList);
        programs.forEach(program->
        {
            try
            {
                repository.logPrgStateExec(program);
                System.out.println(program.toString()+'\n');
            }
            catch(MyException e)
            {
                System.out.println(e.toString());
            }
        });

        repository.setProgramStateList(programs);



    }

    public void allStep() throws MyException,InterruptedException
    {
        executor= Executors.newFixedThreadPool(2);

        ArrayList<ProgramState> programList=removeCompletedProgram(repository.getProgramStateList());

        while(programList.size()>0)
        {
            repository.getCurrentProgram().getHeapTable().setContent(
                    myGarbageCollector.safeGarbageCollector(myGarbageCollector.addIndirections(
                            myGarbageCollector.getAdresFromTables(repository.getProgramStateList()),repository.getCurrentProgram().getHeapTable()
                    ), repository.getCurrentProgram().getHeapTable()));
            oneStepForAllProgram(programList);
            programList=removeCompletedProgram(repository.getProgramStateList());


        }
        executor.shutdownNow();
        repository.setProgramStateList(programList);

    }

    private ProgramState OLDoneStep(ProgramState state) throws MyException
    {
        InterfaceMyStack<IStatement> stack=state.getExecutionStack();
        if(stack.isEmpty()) {
            throw new MyException("program state stack is empty");
        }
        IStatement crtStmt=stack.pop();
        return crtStmt.execute(state);

    }

    public void OLDallStep() throws MyException
    {

        ProgramState prg=repository.getCurrentProgram();

        repository.deleteContent(prg);

       System.out.print(prg.toString());
        repository.logPrgStateExec(repository.getCurrentProgram());
        while(!prg.getExecutionStack().isEmpty())
        {
            try
            {
                OLDoneStep(prg);
                prg.getHeapTable().setContent(myGarbageCollector.safeGarbageCollector(myGarbageCollector.addIndirections(myGarbageCollector.getAddressFromSymbolTable(prg.getSymbolTable().values()),prg.getHeapTable()),prg.getHeapTable()));
                System.out.println(prg.toString());
                repository.logPrgStateExec(prg);
            }
            catch(MyException e)
            {
                e.getMessage();
            }
        }
    }

}
