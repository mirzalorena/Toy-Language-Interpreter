package View;

import Controller.Controller;
import Model.*;
import Model.DataStructures.*;
import Model.Expressions.ArithmeticExpression;
import Model.Expressions.Heap.HeapReading;
import Model.Expressions.RelationalExpression;
import Model.Expressions.ValueExpression;
import Model.Expressions.VarExpression;
import Model.Statements.*;
import Model.Statements.Heap.NewStatement;
import Model.Statements.Heap.WriteStatement;
import Repository.*;
import Model.Statements.File.*;

import java.util.ArrayList;
public class Interpreter {

    /*private static void menu()
    {
        System.out.print("Input program: ");
    }
    private static String read()
    {
        Scanner scanIn=new Scanner(System.in);
        return scanIn.nextLine();
    }


    private static void display(Controller controller)
    {
        try
        {
            controller.allStep();
        }
        catch(MyException e)
        {
            e.getMessage();
        }
    }*/

    public static void main(String[] args)
    {

        IStatement ex1 = new CompoundStatement(new VarDeclStmt("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VarExpression("v"))));
        ArrayList<ProgramState> program1 = new ArrayList<>();
        program1.add(new ProgramState(ex1));
        InterfaceRepository firstRepo = new Repository(program1,"log1.txt");
        Controller firstController = new Controller(firstRepo);

        //display(firstController);

        IStatement ex2 = new CompoundStatement( new VarDeclStmt("a",new IntType()),
                new CompoundStatement(new VarDeclStmt("b",new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression('*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignStatement("b",new ArithmeticExpression('+',new VarExpression("a"), new
                                        ValueExpression(new IntValue(1)))), new PrintStatement(new VarExpression("b"))))));

        ArrayList<ProgramState> program2 = new ArrayList<>();
        program2.add(new ProgramState(ex2));
        InterfaceRepository secondRepo = new Repository(program2,"log2.txt");
        Controller secondController = new Controller(secondRepo);

       // display(secondController);

        IStatement ex3 = new CompoundStatement(new VarDeclStmt("a",new BoolType()),
                new CompoundStatement(new VarDeclStmt("v", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IFStatement(new VarExpression("a"),new AssignStatement("v",new ValueExpression(new
                                        IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VarExpression("v"))))));

        ArrayList<ProgramState> program3 = new ArrayList<>();
        program3.add(new ProgramState(ex3));
        InterfaceRepository thirdRepo = new Repository(program3,"log3.txt");
        Controller thirdController = new Controller(thirdRepo);

        //display(thirdController);

        IStatement ex4 = new CompoundStatement(new CompoundStatement(new VarDeclStmt("a",new IntType()), new VarDeclStmt("b",new IntType())),
                new CompoundStatement(new OpenFileStatement(new ValueExpression(new StringValue("test.in"))), new CompoundStatement( new ReadFileStatement
                        (new ValueExpression(new StringValue("test.in")), new StringValue("a")), new CompoundStatement( new ReadFileStatement
                        (new ValueExpression(new StringValue("test.in")), new StringValue("b")),   new CompoundStatement(new PrintStatement(new VarExpression("a")),
                        new CompoundStatement(new PrintStatement(new VarExpression("b")),new CloseStatement(new ValueExpression(new StringValue("test.in")))))))));

        ArrayList<ProgramState> prg4 = new ArrayList<>();
        prg4.add(new ProgramState(ex4));
        InterfaceRepository repo4 = new Repository(prg4, "log4.txt");
        Controller ctrl4 = new Controller(repo4);
        //display(ctrl4);

        //new stmt
        IStatement ex5 = new CompoundStatement(new CompoundStatement(new VarDeclStmt("v", new RefType(new IntType())), new NewStatement(new StringValue("v"), new ValueExpression(new IntValue(20)))),
                new CompoundStatement(new CompoundStatement(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                        new NewStatement(new StringValue("a"), new VarExpression("v"))),  new CompoundStatement(new PrintStatement(new VarExpression("v")),
                        new PrintStatement(new VarExpression("a")))));

        ArrayList<ProgramState> prg5 = new ArrayList<>();
        prg5.add(new ProgramState(ex5));
        InterfaceRepository repo5 = new Repository(prg5, "log5.txt");
        Controller ctrl5 = new Controller(repo5);

        //read expr
        IStatement ex6 = new CompoundStatement( new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new VarDeclStmt
                ("v", new RefType(new IntType())), new NewStatement(new StringValue("v"), new ValueExpression(new IntValue(20)))),
                new VarDeclStmt("a", new RefType(new RefType(new IntType())))), new NewStatement(new StringValue("a"),
                new VarExpression("v"))), new PrintStatement(new HeapReading(new VarExpression("v")))), new PrintStatement(
                new ArithmeticExpression('+', new HeapReading(new HeapReading(new VarExpression("a"))),
                        new ValueExpression(new IntValue(5)))));

        ArrayList<ProgramState> prg6 = new ArrayList<>();
        prg6.add(new ProgramState(ex6));
        InterfaceRepository repo6 = new Repository(prg6, "log6.txt");
        Controller ctrl6 = new Controller(repo6);

        //write stmt
        IStatement ex7 =new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new VarDeclStmt("v", new RefType(new IntType())), new NewStatement(new StringValue("v"),
                new ValueExpression(new IntValue(20)))), new PrintStatement(new HeapReading(new VarExpression("v")))),
                new WriteStatement(new StringValue("v"), new ValueExpression(new IntValue(30)))), new PrintStatement(new ArithmeticExpression('+', new HeapReading(new VarExpression("v")), new ValueExpression(new IntValue(5)))));

        ArrayList<ProgramState> prg7 = new ArrayList<>();
        prg7.add(new ProgramState(ex7));
        InterfaceRepository repo7 = new Repository(prg7, "log7.txt");
        Controller ctrl7 = new Controller(repo7);

        //while
        IStatement ex8=new CompoundStatement(new VarDeclStmt("v",new IntType()), new CompoundStatement(
                new AssignStatement("v",new ValueExpression(new IntValue(4))), new WhileStatement(
                new RelationalExpression(">", new VarExpression("v"),new ValueExpression(new IntValue(0))),
                new CompoundStatement(new PrintStatement(new VarExpression("v")),
                        new AssignStatement( "v",new ArithmeticExpression('-',new VarExpression("v"),
                                new ValueExpression(new IntValue(1))))))));

        ArrayList<ProgramState> prg8 = new ArrayList<>();
        prg8.add(new ProgramState(ex8));
        InterfaceRepository repo8 = new Repository(prg8, "log8.txt");
        Controller ctrl8 = new Controller(repo8);

      //garbage collector
        IStatement ex9=new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new VarDeclStmt("v", new RefType(new IntType())),
                new NewStatement(new StringValue("v"), new ValueExpression(new IntValue(20)))), new VarDeclStmt("a",
                new RefType(new RefType(new IntType())))), new NewStatement(new StringValue("a"), new VarExpression("v"))),
                new NewStatement(new StringValue("v"), new ValueExpression(new IntValue(30)))), new PrintStatement(new HeapReading(new HeapReading(new VarExpression("a")))));

        ArrayList<ProgramState> prg9 = new ArrayList<>();
        prg9.add(new ProgramState(ex9));
        InterfaceRepository repo9 = new Repository(prg9, "log9.txt");
        Controller ctrl9 = new Controller(repo9);

        IStatement ex10 = new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new VarDeclStmt("v", new IntType()), new VarDeclStmt("a", new RefType(new IntType()))),
                new AssignStatement("v", new ValueExpression(new IntValue(10)))), new NewStatement(new StringValue("a"), new ValueExpression(new IntValue(22)))),
                new ForkStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new WriteStatement(new StringValue("a"), new ValueExpression(new IntValue(30))), new AssignStatement("v", new ValueExpression(new IntValue(32)))),
                        new PrintStatement(new VarExpression("v"))), new PrintStatement(new HeapReading(new VarExpression("a")))))), new PrintStatement(new VarExpression("v"))),
                new PrintStatement(new HeapReading(new VarExpression("a"))));

        ArrayList<ProgramState> prg10 = new ArrayList<>();
        prg10.add(new ProgramState(ex10));
        InterfaceRepository repo10 = new Repository(prg10, "log10.txt");
        Controller controller10 = new Controller(repo10);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),firstController));
        menu.addCommand(new RunExample("2",ex2.toString(),secondController));
        menu.addCommand(new RunExample("3",ex3.toString(),thirdController));
        menu.addCommand(new RunExample("4",ex4.toString(),ctrl4));
        menu.addCommand(new RunExample("5",ex5.toString(),ctrl5));
        menu.addCommand(new RunExample("6",ex6.toString(),ctrl6));
        menu.addCommand(new RunExample("7",ex7.toString(),ctrl7));
        menu.addCommand(new RunExample("8",ex8.toString(),ctrl8));
        menu.addCommand(new RunExample("9",ex9.toString(),ctrl9));
        menu.addCommand(new RunExample("10",ex10.toString(),controller10));
        menu.show();



    }


}
