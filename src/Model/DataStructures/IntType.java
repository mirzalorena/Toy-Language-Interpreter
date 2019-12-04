package Model.DataStructures;

public class IntType implements Type {


    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof IntType;
    }

    @Override
    public Value defaultValue()
    {
        return new IntValue(0);
    }

    public String toString()
    {
        return "int";
    }
}
