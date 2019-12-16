package Model.DataStructures;

public class BoolValue implements Value {
    private boolean val;

    public BoolValue(boolean v)
    {
        this.val=v;
    }

    @Override
    public Object getVal()
    {
        return val;
    }

    @Override
    public Type getType()
    {
        return new BoolType();
    }


    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof BoolValue;
    }


    public String toString()
    {
        if(val)
            return "true";
        else
            return "false";
    }


}
