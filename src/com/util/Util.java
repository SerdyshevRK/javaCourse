package com.util;
import com.lists.*;

import java.util.Iterator;

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
    public static Iterable filter(Iterable iterable, Predicate predicate){
        return new Iterable() {
            @Override
            public Iterator iterator() {
                return new  FilterIterator(iterable, predicate);
            }
        };
    }
    public static class FilterIterator implements Iterator{
        Iterable iterable;
        Iterator iterator;
        Predicate predicate;
        Object next;

        public FilterIterator(Iterable iterable, Predicate predicate){
            this.iterable = iterable;
            this.predicate = predicate;
            iterator = this.iterable.iterator();
        }

        private void getNext(){
            while (iterator.hasNext()){
                Object itNext = iterator.next();
                if (predicate.apply(itNext)){
                    next = itNext;
                    return;
                }
            }
            next = null;
        }

        @Override
        public boolean hasNext() {
            if (next == null)
                getNext();
            return next != null;
        }

        @Override
        public Object next() {
            Object retVal = next;
            getNext();
            return retVal;
        }
    }
//    public static List trans(Transformer transformer, List list){
//        List retVal = new LinkedList();
//        for (Object o : list){
//            retVal.add(transformer.transform(o));
//        }
//        return retVal;
//    }
    public static Iterable trans(Iterable iterable, Transformer transformer){
        return new Iterable() {
            @Override
            public Iterator iterator() {
                return new TransformIterator(iterable, transformer);
            }
        };
    }
    public static class TransformIterator implements Iterator{
        Iterable iterable;
        Transformer transformer;
        Iterator iterator;

        public TransformIterator(Iterable iterable, Transformer transformer){
            this.iterable = iterable;
            this.transformer = transformer;
            iterator = this.iterable.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Object next() {
            return transformer.transform(iterator.next());
        }
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
        List list = new LinkedList();
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
    public static Iterable view(Iterable... i){
        return new Iterable() {
            @Override
            public Iterator iterator() {
                return new ViewIterator(i);
            }
        };
    }
    public static class ViewIterator implements Iterator{
        Iterable[] iters;
        Iterator iterator;
        int index;

        public ViewIterator(Iterable... iters) {
            this.iters = iters;
        }

        @Override
        public boolean hasNext() {
            if (index >= iters.length)
                return false;
            if (iterator == null)
                iterator = iters[index].iterator();
            if (iterator.hasNext())
                return true;
            index++;
            iterator = null;
            return hasNext();
        }

        @Override
        public Object next() {
            return iterator.next();
        }
    }
}
