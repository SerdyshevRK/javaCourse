package com.library;
import com.accumulator.Operation;
import com.lists.BidirectionalList;
import com.lists.List;

public class Library {
    private List[] shelves = new List[8];

    public Library(){
        for (int i = 0; i < shelves.length; i++) {
            shelves[i] = new BidirectionalList();
        }
    }

    public void put(Book book, int quantity){
        BookCase bookCase;
        int i = Math.abs(book.hashCode()) % shelves.length;
        if (shelves[i] != null) {
            for (int j = 0; j < shelves[i].size(); j++) {
                if ((bookCase = (BookCase) shelves[i].get(j)).book.equals(book)) {
                    bookCase.changeQuantity(quantity, new Operation() {
                        @Override
                        public double doOperation(double firstNumber, double secondNumber) {
                            return firstNumber + secondNumber;
                        }
                    });
                    return;
                }
            }
        }
        bookCase = new BookCase(book, quantity);
        shelves[i].add(bookCase);
    }

    public int get(Book book, int quantity){
        BookCase bookCase;
        int retVal;
        int i = Math.abs(book.hashCode()) % shelves.length;
        if (shelves[i] == null)
            return -1;
        for (int j = 0; j < shelves[i].size(); j++) {
            if ((bookCase = (BookCase) shelves[i].get(j)).book.equals(book)) {
                if (bookCase.quantity <= quantity) {
                    retVal = bookCase.quantity;
                    shelves[i].remove(j);
                    return retVal;                              // we have less books, than we need to get
                }
                bookCase.changeQuantity(quantity, new Operation() {
                    @Override
                    public double doOperation(double firstNumber, double secondNumber) {
                        return firstNumber - secondNumber;
                    }
                });
                return quantity;
            }
        }
        return -2;                                              // there are no books like this
    }

    class BookCase{
        private Book book;
        private int quantity;

        public BookCase(Book book, int quantity){
            this.book = book;
            this.quantity = quantity;
        }

        public void changeQuantity(int value, Operation operation){
            this.quantity = (int)operation.doOperation(this.quantity, value);
        }
    }
}
