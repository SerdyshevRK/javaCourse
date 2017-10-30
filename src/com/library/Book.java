package com.library;

public class Book {
    private String author;
    private String title;
    private int pagesNum;

    public Book(String author, String title, int pages){
        this.author = author;
        this.title = title;
        this.pagesNum = pages;
    }

    public String getAuthor(){
        return this.author;
    }
    public String getTitle(){
        return this.title;
    }
    public int getPagesNum(){
        return this.pagesNum;
    }

    @Override
    public int hashCode() {
        int retVal = 0;
        retVal += author.hashCode();
        retVal += title.hashCode();
        retVal += pagesNum;
        return retVal;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Book))
            return false;
        Book book = (Book)obj;
        if (this.author.equals(book.getAuthor()))
            if (this.title.equals(book.getTitle()))
                if (this.pagesNum == book.getPagesNum())
                    return true;
        return false;
    }

    @Override
    public String toString() {
        return "Book: " + "название: " + title + " автор: " + author + " количество страниц: " + pagesNum + "\n";
    }
}
