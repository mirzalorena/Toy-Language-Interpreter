package Model.DataStructures;

public class RefValue implements Value {

    private int adress;
    private Type locationType;

    public RefValue(int adress,Type locationType)
    {
        this.adress=adress;
        this.locationType=locationType;
    }

    public int getAdress()
    {
        return this.adress;
    }

    public String toString()
    {
        return "( " +Integer.toString(adress)+" , "+locationType.toString()+" )";
    }

    @Override
    public Type getType()
    {
        return new RefType(locationType);
    }

    public void setAdress(int a)
    {
        this.adress=a;
    }


    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof RefValue;
    }


    @Override
    public Object getVal()
    {
        return this.locationType;
    }

}
