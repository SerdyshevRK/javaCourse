package com.linearProbing;
import com.accumulator.Operation;
import com.library.Book;

public class Library {
    private final int step = 1;
    private int fullness = 0;
    BookCase[] shelves;

    public Library(){
        shelves = new BookCase[10];
    }

    public void put(Book book, int quantity){
        if (fullness >= shelves.length * 0.75f) {
            extendLibrary();
        }
        int i = Math.abs(book.hashCode()) % shelves.length;
        if (shelves[i] != null) {
            int count = 0;
            while (shelves[i] != null && count < shelves.length) {
                if (shelves[i].book.equals(book)){
                    shelves[i].changeQuantity(quantity, new Operation() {
                        @Override
                        public double doOperation(double firstNumber, double secondNumber) {
                            return firstNumber + secondNumber;
                        }
                    });
                    return;
                }
                i = (i + step) % shelves.length;
                count++;
            }
        }
        shelves[i] = new BookCase(book, quantity);
        fullness++;
    }

    public int get(Book book, int quantity){
        int i = Math.abs(book.hashCode()) % shelves.length;
        if (shelves[i] == null)
            return -1;
        int count = 0;
        while (shelves[i] != null && count < shelves.length){
            if (shelves[i].book.equals(book)) {
                if (shelves[i].quantity <= quantity) {
                    int retVal = shelves[i].quantity;
                    remove(i);
                    return retVal;
                }
                shelves[i].changeQuantity(quantity, new Operation() {
                    @Override
                    public double doOperation(double firstNumber, double secondNumber) {
                        return firstNumber - secondNumber;
                    }
                });
                return quantity;
            }
            i = (i + step) % shelves.length;
            count++;
        }
        return -2;
    }

    private void remove(int index){
        int i = (index + step) % shelves.length;
        while (shelves[i] != null){
            if (shelves[index].book.equals(shelves[i].book))
                break;
            i = (i + step) % shelves.length;
        }
        if (shelves[i] == null){
            shelves[index] = null;
            return;
        }
        shelves[index] = shelves[i];
        remove(i);
    }

    private class BookCase {
        private Book book;
        private int quantity;

        public BookCase(Book book, int quantity) {
            this.book = book;
            this.quantity = quantity;
        }

        public void changeQuantity(int value, Operation operation) {
            this.quantity = (int) operation.doOperation(this.quantity, value);
        }
    }

    private void extendLibrary(){
        BookCase[] tmp = new BookCase[shelves.length];
        System.arraycopy(shelves, 0, tmp, 0, shelves.length);
        shelves = new BookCase[(int)(tmp.length * 2)];
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] == null)
                continue;
            int j = Math.abs(tmp[i].book.hashCode()) % shelves.length;
            shelves[j] = tmp[i];
        }
    }
}
