package Model.DataStructures;

public class StringType implements Type {
    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof StringType;
    }

    @Override
    public Value defaultValue()
    {
        return new StringValue("");
    }

    public String toString()
    {
        return "string";
    }
}
