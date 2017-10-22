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
}
