package Collection;
import Model.DataStructures.Value;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface InterfaceMyDictionary<K,V> {

    V get(K key);
    void put(K key, V value);
    String toString();
    //int size();
    boolean containsKey(K name);
    void remove(Value id);
    List<V> values();

    InterfaceMyDictionary<K,V> deepCopy();

   // boolean containsValue(V element);
    Set<K> keySet();
    Set<Map.Entry<K,V>> entrySet();
   // void setContent(Set<Map.Entry<K,V>> set);
    //K getKey(V value);
    InterfaceMyDictionary<K,V> clone_dictionary();

    V lookup(K key);



}
