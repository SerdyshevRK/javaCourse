import com.library.Book;
import com.linearProbing.Library;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.put(new Book("Иосиф Бродский", "Малое собрание сочинений", 880), 200);
        library.put(new Book("Иоганн Вольфганг Гете", "Фауст", 528), 200);
        library.put(new Book("Публий Овидий Назон", "Метаморфозы", 396), 200);
        library.put(new Book("Данте Алигьери", "Божественная Комедия. Новая Жизнь", 768), 200);
        library.put(new Book("Уильям Шекспир", "Трагедии", 672), 200);
        library.put(new Book("Николай Некрасов", "Кому на Руси жить хорошо", 288), 200);
        library.put(new Book("Германский эпос", "Песнь о нибелунгах", 384), 200);
        library.put(new Book("Эдгар Аллан По", "Ворон", 288), 200);
        library.put(new Book("Сергей Есенин", "Полное собрание лирики в 1 томе", 768), 200);
        library.put(new Book("Гомер", "Одиссея", 480), 200);
        library.put(new Book("Шарль Бодлер", "Цветы зла", 448), 200);
        library.put(new Book("Джон Милтон", "Потерянный и Возвращенный рай", 416), 200);
        library.put(new Book("Александр Пушкин", "Евгений Онегин", 352), 200);
        library.put(new Book("Публий Вергилий Марон", "Энеида", 384), 200);
        library.put(new Book("Андрей Вознесенский", "Ты меня никогда не забудешь...", 288), 200);

        System.out.println(library.get(new Book("Публий Овидий Назон", "Метаморфозы", 396), 25));
        System.out.println(library.get(new Book("Публий Овидий Назон", "Метаморфозы", 396), 180));
        System.out.println(library.get(new Book("Джон Милтон", "Потерянный и Возвращенный рай", 416), 201));
        System.out.println(library.get(new Book("Джон Милтон", "Потерянный и Возвращенный рай", 416), 1));

        library.put(new Book("Андрей Вознесенский", "Ты меня никогда не забудешь...", 288), 200);
        System.out.println(library.get(new Book("Андрей Вознесенский", "Ты меня никогда не забудешь...", 288), 401));
        System.out.println(library.get(new Book("Андрей Вознесенский", "Ты меня никогда не забудешь...", 288), 401));
    }
}
