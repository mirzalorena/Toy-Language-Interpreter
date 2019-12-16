package View;
import Collection.InterfaceMyDictionary;
import Collection.MyDictionary;
import Controller.*;
import Model.DataStructures.Type;
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
            MyDictionary<String, Type> typEnv=new MyDictionary<String,Type>();
            ctr.getOriginalProgram().typecheck(typEnv);


            ctr.allStep(); }
        catch (MyException | InterruptedException e) {System.out.println(e.getMessage());}
    }

}
