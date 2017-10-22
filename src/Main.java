public class Main {
    public static void main(String[] args) {
        // Adder class test
        System.out.println("'+1':");
        Adder adder = new Adder(1);
        for (int i = 0; i < 5; i++) {
            adder.add();
            System.out.println(adder.getValue());
        }
        System.out.println("'+2'");
        adder = new Adder(2);
        for (int i = 0; i < 5; i++) {
            adder.add();
            System.out.println(adder.getValue());
        }
        System.out.println();

        // Linked list
        System.out.println("Связный список:");
        IntList list = new IntList();
        for (int i = 10; i > 0; i -= 2) {
            list.add(i);
        }
        for (int i = 0; i < list.length; i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println("\nПосле удаления элемента с индексом 2:");
        list.remove(2);
        for (int i = 0; i < list.length; i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        System.out.println();

        // Library
        System.out.println("Добавление книг в библиотеку:");
        Library library = new Library();
        Book book = new Book("Герберт Шилд", "Java 8. Полное руководство.", 1376);
        System.out.println(library.put(book, 65874));
        System.out.println(library.put(book, 300));
        book = new Book("Бьерн Страуструп", "Язык программирования С++.", 1136);
        System.out.println(library.put(book, 782));
        System.out.println("Удаление книг из библиотеки:");
        System.out.println(library.get(book, 500));
        System.out.println(library.get(book, 283));     // delete more books then we have in library
        System.out.println();

        // Figures
        System.out.println("Создание фигур и вывод их значений:");
        Circle circle = new Circle(new Point(10, 15), 20);
        Triangle triangle = new Triangle(new Point(14, 9), new Point(16, 12), new Point(18, 9));
        Rectangle rectangle = new Rectangle(new Point(14, 9), new Point(19, 9), new Point(14, 6), new Point(19, 6));

        System.out.printf("Площадь круга: %.2f\n", circle.getArea());
        System.out.printf("Периметр круга: %.2f\n", circle.getPerimeter());

        System.out.printf("Площадь треугольника: %.2f\n", triangle.getArea());
        System.out.printf("Периметр треугольника: %.2f\n", triangle.getPerimeter());

        System.out.printf("Площадь прямоуольника: %.2f\n", rectangle.getArea());
        System.out.printf("Периметр прямоуольника: %.2f\n", rectangle.getPerimeter());
    }
}
