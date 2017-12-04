package com.collections;

import java.util.*;

/**
 * Created by xmitya on 20.10.16.
 */
public class Tasks2 {
    public static void main(String[] args) {
        List<Message> list = new MessageGenerator().generate(10);
        System.out.println(list.toString());
        sortByPriority(list);
        System.out.println(list);

        List<User> users = UserGenerator.generate(10);
        System.out.println(sortedByCompanyAndName(users));
        System.out.println(sortedBySalaryAndName(users));
        System.out.println(sortedBySalaryAgeCompanyAndName(users));
    }

    private static void sortByPriority(List<Message> messages) {
        messages.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o1.getPriority().ordinal() - o2.getPriority().ordinal();
            }
        });
    }

    private static NavigableSet<User> sortedByCompanyAndName(List<User> users) {
        NavigableSet<User> userSet = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int retVal = o1.getCompany().compareToIgnoreCase(o2.getCompany());
                if (retVal == 0){
                    retVal += o1.getName().compareToIgnoreCase(o2.getName());
                }
                return retVal;
            }
        });
        for (User user : users) {
            userSet.add(user);
        }

        return userSet;
    }

    private static NavigableSet<User> sortedBySalaryAndName(List<User> users) {
        NavigableSet<User> userSet = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int retVal = o1.getSalary() - o2.getSalary();
                if (retVal != 0)
                    return retVal;
                retVal += o1.getName().compareToIgnoreCase(o2.getName());
                return retVal;
            }
        });

        for (User user : users) {
            userSet.add(user);
        }

        return userSet;
    }

    private static NavigableSet<User> sortedBySalaryAgeCompanyAndName(List<User> users) {

        NavigableSet<User> userSet = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int retVal = o1.getSalary() - o2.getSalary();
                if (retVal != 0)
                    return retVal;
                retVal += o1.getAge() - o2.getAge();
                if (retVal != 0)
                    return retVal;
                retVal += o1.getCompany().compareToIgnoreCase(o2.getCompany());
                if (retVal != 0)
                    return retVal;
                retVal += o1.getName().compareToIgnoreCase(o2.getName());
                return retVal;
            }
        });

        for (User user : users) {
            userSet.add(user);
        }

        return userSet;
    }

    private static <T> Iterator<T> viewIterator(Iterable<T> it1, Iterable<T> it2) {
        return null;
    }
}
