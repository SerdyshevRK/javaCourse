public class Library {
    private Book[] shelves = new Book[20];      // library can store 20 books with different titles.
    private long[] booksQuantity = new long[20];     // quantity of books with one title has no limit

    public String put(Book book, int quantity){
        for (int i = 0; i < shelves.length; i++) {
            if (shelves[i] != null){
                if (shelves[i].getTitle().equals(book.getTitle())){
                    booksQuantity[i] += quantity;
                    return "В библиотеку добавлено " + quantity + " книг " + book.getTitle();
                }
            } else {
                shelves[i] = book;
                booksQuantity[i] += quantity;
                return "В библиотеку добавлено " + quantity + " новых книг " + "'" + book.getTitle() + "'";
            }
        }
        return "Нет возможности положить новые книги в библиотеку.";
    }
    public int get(Book book, int quantity){
        for (int i = 0; i < shelves.length; i++) {
            if (shelves[i] == null)
                break;
            if (shelves[i].getTitle() == book.getTitle()){
                if (booksQuantity[i] >= quantity){
                    booksQuantity[i] -= quantity;
                    return quantity;
                } else {
                    return -1;
                }
            }
        }
        return -1;
    }
}
