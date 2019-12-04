package Model.DataStructures;


import Model.DataStructures.Type;

public interface Value {
    Type getType();
    Object getVal();
    boolean equals(Object obj);

}
