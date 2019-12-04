package Model.DataStructures;

import java.sql.Ref;

public class RefType implements Type {

    private Type inner;

    public RefType(Type inner )
    {
        this.inner=inner;
    }
    public RefType()
    {
        this.inner=null;
    }

    public Type getInner()
    {
        return this.inner;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof RefType)
            return inner.equals(((RefType) obj).getInner());
        else return false;
    }

    @Override
    public Value defaultValue()
    {
        return new RefValue(0,inner);
    }

    public String toString()
    {
        return "Ref("+inner.toString()+")";
    }


}
