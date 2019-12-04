package Collection;

import java.util.*;

import Model.DataStructures.Value;

public class MyDictionary<K,V> implements InterfaceMyDictionary<K,V> {

    private HashMap<K, V> dictionary;

    public MyDictionary() {
        dictionary = new HashMap<>();
    }

    public MyDictionary(HashMap<K,V> map)
    {
        this.dictionary=map;
    }


    @Override
    public V get(K key) {
        for(K k:this.dictionary.keySet())
        {
                if(k.toString().equals(key.toString()))
                    return dictionary.get(k);
        }
        return null;

        // return dictionary.get(key);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return dictionary.entrySet();
    }

    public V lookup(K key)
    {
        return dictionary.get(key);
    }

    @Override
    public void put(K key, V value) {
        dictionary.put(key, value);
    }

    /*@Override
    public String toString() {
        return dictionary.toString();
    }*/

   @Override
   public String toString()
   {
        StringBuilder result=new StringBuilder();
        //result.append("Symtable: \n");
        for(K key:dictionary.keySet())
        {
            result.append(key.toString()+"-->"+this.dictionary.get(key).toString()).append("\n");
        }
        return result.toString();
   }

    //@Override
   /* public int size()
    {
        return dictionary.size();
    }*/

    @Override
    public boolean containsKey(K name)
    {
        for(K key: this.dictionary.keySet())
        {
            if(key.toString().equals(name.toString()))
                return true;
        }
        return false;
       //return dictionary.containsKey(name);
    }

    @Override
   public void remove(Value id)
    {
        dictionary.remove(id);

    }

    @Override
    public List<V> values()

    {
        return new LinkedList<>(this.dictionary.values());
    }

   // @Override
    /*public boolean containsValue(V element)
    {
        return dictionary.containsValue(element);
    }*/

    @Override
    public Set<K> keySet()
    {
        return dictionary.keySet();
    }

    @Override
    public InterfaceMyDictionary<K,V> deepCopy()
    {
        HashMap<K,V> clone=new HashMap<>();

        for(Map.Entry<K,V> element: dictionary.entrySet())
        {
            clone.put(element.getKey(),element.getValue());
        }
        return new MyDictionary<K,V>(clone);


    }

    @Override
    public InterfaceMyDictionary<K,V> clone_dictionary()
    {
        HashMap<K, V> di = new HashMap<>();
        for(K key : this.keySet())
            di.put(key, dictionary.get(key));
        return new MyDictionary<K,V>(di);
    }





}
