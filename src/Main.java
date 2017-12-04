import com.lists.LinkedList;
import com.lists.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");

        for (Object o : list) {
            System.out.println(o.toString());
            list.add("3");
        }

    }
}
