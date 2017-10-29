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
}
