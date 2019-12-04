package View;
import Controller.*;
import Model.MyException;

import java.io.IOException;

public class RunExample extends Command {
    private Controller ctr;
    RunExample(String key, String desc, Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        try{
            ctr.allStep(); }
        catch (MyException | InterruptedException e) {System.out.println(e.getMessage());}
    }

}
