package Model.DataStructures;

public class IntValue implements Value {
    private int val;

    public IntValue(int v)
    {
        this.val=v;
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof IntValue;
    }


    @Override
    public Object getVal()
    {
        return val;
    }

    @Override
    public Type getType()
    {
        return new IntType();
    }

    public String toString()
    {
        return Integer.toString(val);
    }


}
