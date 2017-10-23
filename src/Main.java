public class Main {
    public static void main(String[] args) {
        System.out.println("Применение разных операций:");
        Accumulator accumulator = new Accumulator(1, new Plus());
        accumulator.accumulate(2);
        System.out.println("value + 2: " + accumulator.getValue());
        accumulator.setOperation(new Power());
        accumulator.accumulate(2);
        System.out.println("value^2: " + accumulator.getValue());
        accumulator.setOperation(new Multiplay());
        accumulator.accumulate(2);
        System.out.println("value * 2: " + accumulator.getValue());
        accumulator.setOperation(new Minus());
        accumulator.accumulate(2);
        System.out.println("value - 2: " + accumulator.getValue());
        accumulator.setOperation(new Divide());
        accumulator.accumulate(2);
        System.out.println("value / 2: " + accumulator.getValue());

        // Linked list
        LinkedList list = new LinkedList();
        list.add(45);
        list.add("gdf");
        list.add(true);
        for (int i = 0; i < list.length; i++) {
            System.out.println(list.get(i));
        }
        System.out.println();
        list.remove(1);
        for (int i = 0; i < list.length; i++) {
            System.out.println(list.get(i));
        }
    }
}
