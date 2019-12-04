package Model.DataStructures;

public class BoolType implements Type {

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof BoolType;
    }

    @Override
    public Value defaultValue()
    {
        return new BoolValue(false);
    }

    public String toString()
    {
        return "bool";
    }
}
