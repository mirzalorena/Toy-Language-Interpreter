package Repository;

import Model.MyException;
import Model.ProgramState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public interface InterfaceRepository {

    ProgramState getCurrentProgram();
    ArrayList<ProgramState> getProgramStateList();
    void setProgramStateList(ArrayList<ProgramState> programStateList);

    void logPrgStateExec(ProgramState state) throws MyException;
    void deleteContent(ProgramState state) throws MyException;


}
