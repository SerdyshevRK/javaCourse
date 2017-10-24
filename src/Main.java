import com.lists.ArrayList;
import com.lists.LinkedList;
import com.lists.List;
import com.lists.Queue;
import com.lists.Stack;

public class Main {
    public static void main(String[] args) {
        // Linked list test
        List list = new LinkedList();
        List arrayList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
            arrayList.add(i);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println();
        System.out.println(list.remove(4));
        System.out.println(arrayList.remove(4));
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println("\n" + list.get(10));
        System.out.println(arrayList.get(10));
        System.out.println();

        // Stack test
        Stack stack = new LinkedList();
        Stack arrayStack = new ArrayList();
        for (int i = 0; i < 10; i++){
            stack.push(i);
            arrayStack.push(i);
        }
        System.out.print("Стэк: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print(arrayStack.pop() + " ");
        }
        System.out.println();

        // Queue test
        Queue queue = new LinkedList();
        Queue arrayQueue = new ArrayList();
        for (int i = 0; i < 10; i++) {
            queue.add(i);
            arrayQueue.add(i);
        }
        System.out.print("Очередь: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(queue.pull() + " ");
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print(arrayQueue.pull() + " ");
        }
    }
}
