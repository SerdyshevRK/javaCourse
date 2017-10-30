import com.lists.LinkedList;
import com.lists.List;
import com.util.Predicate;
import com.util.Predicate2;
import com.util.Util;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File firstDir = new File("e:/ITMO/Learning/Practice/src/com/itmo/objects3/interfaces/list");
        File secondDir = new File("e:/ITMO/Learning/Practice/_src/com/itmo/objects3/interfaces/list");

        List firstList = Util.toLinkedList(firstDir.listFiles());
        List secondList = Util.toLinkedList(secondDir.listFiles());

        System.out.println("Файлы в первой папке:");
        for (int i = 0; i < firstList.size(); i++) {
            System.out.println(firstList.get(i));
        }
        System.out.println("Файлы во второй папке:");
        for (int i = 0; i < secondList.size(); i++) {
            System.out.println(secondList.get(i));
        }

        List duplicated = Util.intersect(firstList, secondList, new Predicate2() {
            @Override
            public boolean apply(Object firstObject, Object secondObject) {
                return ((File)firstObject).getName().equals(((File)secondObject).getName());
            }
        });
        System.out.println("Одинаковые файлы:");
        for (int i = 0; i < duplicated.size(); i++) {
            System.out.println(duplicated.get(i));
        }

        System.out.println("Поиск файла:");
        System.out.println(Util.find(new Predicate() {
            @Override
            public boolean apply(Object object) {
                return ((File) object).getName().equals("ListMain.java");
            }
        }, firstList));
        System.out.println(Util.find(new Predicate() {
            @Override
            public boolean apply(Object object) {
                return ((File) object).getName().equals("ListMain.java");
            }
        }, secondList));

        System.out.println("Список файлов больше 10 Kб:");
        File dir = new File("e:/ITMO/Learning/Theory");
        List list = Util.toArrayList(dir.listFiles());
        list = Util.filter(new Predicate() {
            @Override
            public boolean apply(Object object) {
                return ((File)object).length() > 10240;
            }
        }, list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("Фильтр файлов:");
        System.out.println("Все файлы в папке:");
        for (int i = 0; i < firstList.size(); i++) {
            System.out.println(firstList.get(i));
        }
        System.out.println("Только файлы '.java':");
        firstList = Util.filter(new Predicate() {
            @Override
            public boolean apply(Object object) {
                String str = ((File)object).getName();
                str = str.substring(str.length() - 4);
                return str.equals("java");
            }
        }, firstList);
        for (int i = 0; i < firstList.size(); i++) {
            System.out.println(firstList.get(i));
        }

        System.out.println("Копирование списка:");
        List intList = new LinkedList();
        for (int i = 0; i < 10; i++) {
            intList.add(i);
        }
        List someList = Util.clone(intList);
        for (int i = 0; i < someList.size(); i++) {
            System.out.print(someList.get(i) + " ");
        }
        System.out.println();
        System.out.println(intList.equals(someList));
    }
}
