package com.util;
import com.lists.*;

public final class Util {

    private Util(){}

    public static Object find(Predicate predicate, List list){
        for (Object obj: list) {
            if (predicate.apply(obj))
                return obj;
        }
        return null;
    }
    public static List filter(Predicate predicate, List list){
        List retVal = new LinkedList();
        for(Object o : list){
            if (predicate.apply(o))
                retVal.add(o);
        }
        return retVal;
    }
    public static List trans(Transformer transformer, List list){
        List retVal = new LinkedList();
        for (Object o : list){
            retVal.add(transformer.transform(o));
        }
        return retVal;
    }
    public static List toLinkedList(Object[] array){
        List list = new LinkedList();
        for (Object o : array) {
            list.add(o);
        }
        return list;
    }
    public static List toArrayList(Object[] array){
        List list = new ArrayList();
        for (Object o : array) {
            list.add(o);
        }
        return list;
    }
    public static List toBidirectionalList(Object[] array){
        List list = new BidirectionalList();
        for (Object o : array) {
            list.add(o);
        }
        return list;
    }
    public static List intersect(List firstList, List secondList, Predicate2 predicate){
        if (firstList == null || secondList == null)
            return null;
        List retVal = new LinkedList();

        if (predicate == null){
            for (int i = 0; i < firstList.size(); i++) {
                for (int j = 0; j < secondList.size(); j++) {
                    if (firstList.get(i).equals(secondList.get(j))){
                        retVal.add(firstList.get(i));
                        break;
                    }
                }
            }
            return retVal;
        }
        for (int i = 0; i < firstList.size(); i++) {
            for (int j = 0; j < secondList.size(); j++) {
                if (predicate.apply(firstList.get(i), secondList.get(j))){
                    retVal.add(firstList.get(i));
                    break;
                }
            }
        }
        return retVal;
    }
    public static List difference(List firstList, List secondList, Predicate2 predicate){
        if (firstList == null || secondList == null)
            return null;
        List retVal = new LinkedList();
        boolean flag;
        if (predicate == null){
            for (int i = 0; i < firstList.size(); i++) {
                flag = true;
                for (int j = 0; j < secondList.size(); j++) {
                    if (firstList.get(i).equals(secondList.get(j)))
                        flag = false;
                }
                if (flag){
                    retVal.add(firstList.get(i));
                }
            }
            return retVal;
        }
        for (int i = 0; i < firstList.size(); i++) {
            flag = true;
            for (int j = 0; j < secondList.size(); j++) {
                if (predicate.apply(firstList.get(i), secondList.get(j))){
                    flag = false;
                }
            }
            if (flag){
                retVal.add(firstList.get(i));
            }
        }
        return retVal;
    }
    public static List clone(List list){
        List retVal = new LinkedList();
        for (Object o : list) {
            retVal.add(o);
        }
        return retVal;
    }
}
