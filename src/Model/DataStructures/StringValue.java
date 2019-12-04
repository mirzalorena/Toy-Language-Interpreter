package Model.DataStructures;

public class StringValue implements Value {

    private String s;

    public StringValue(String str)
    {
        this.s=str;
    }

    @Override
    public Object getVal()
    {
        return s;
    }

    @Override
    public Type getType()
    {
        return new StringType();
    }

    @Override
    public int hashCode() {
        return s.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        //return obj instanceof StringValue;
        /*if(obj.toString().equals(s))
            return true;
        return false;*/
        if(obj instanceof StringValue)
        {
            StringValue x=(StringValue)obj;
            if(x.getVal().equals(s))
                return true;
        }
        return false;
    }

    public String toString()
    {
        return s;
    }
}
