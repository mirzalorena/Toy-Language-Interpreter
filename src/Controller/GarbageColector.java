package Controller;

import Collection.InterfaceHeap;
import Model.DataStructures.RefValue;
import Model.DataStructures.Value;
import Model.ProgramState;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

class GarbageColector {

    Map<Integer, Value> safeGarbageCollector(List<Integer> symbolTableAdr, InterfaceHeap heap)
    {
        Set<Map.Entry<Integer,Value>> heapSet=heap.getEntrySet();

        return heapSet.stream().filter(e->symbolTableAdr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    List<Integer> getAdresFromTables(List<ProgramState> programs)
    {
        return programs.stream()
                .map(prgState -> prgState.getSymbolTable().values().stream())
                .flatMap(Function.identity())
                .collect(Collectors.toList())
                .stream()
                .filter(element -> element instanceof RefValue)
                .map(element -> ((RefValue) element).getAdress())
                .collect(Collectors.toList());
    }

    List<Integer> getAddressFromSymbolTable(List<Value> content)
    {
        return content.stream().filter(element->element instanceof RefValue)
                .map(element->((RefValue)element).getAdress()).collect((Collectors.toList()));
    }


    List<Integer> addIndirections(List<Integer> addressesFromSymbolTable, InterfaceHeap heapTable){
        boolean change = true;
        Set<Map.Entry<Integer, Value>> heapSet = heapTable.getEntrySet();//get entry set that needs modifications
        List<Integer> newAddressList = addressesFromSymbolTable.stream().collect(Collectors.toList()); //copy of list in order to add indirections

        //we go through heapSet again and again and each time we add to the address list new indirection level and new addresses which must NOT be deleted
        while (change){
            List<Integer> appendingList = null;
            change = false;
            appendingList = heapSet.stream()
                    .filter(e -> e.getValue() instanceof RefValue)//check if val in heap is RefValue so it can have indirections todo SEMINAR
                    .filter(e -> newAddressList.contains(e.getKey()))//check if address list contains ref to this
                    .map(e -> (((RefValue) e.getValue()).getAdress()))//map the reference to its address so we can add it
                    .filter(e -> !newAddressList.contains(e))//check if the address list already has that reference from prev elems
                    .collect(Collectors.toList());//collect to list

            if(!appendingList.isEmpty()){
                //we still have indirect references so we have to keep checking
                change = true;
                newAddressList.addAll(appendingList);
            }
        }
        return newAddressList;
    }

}
