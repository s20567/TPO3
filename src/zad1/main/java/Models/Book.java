package Models;

public class Book {
    private String publisher, author, title, isbn, year;
    private Double price;

    public Book(String publisher, String author, String title, Double price, String isbn, String year) {
        this.publisher = publisher;
        this.author = author;
        this.title = title;
        this.price = price;
        this.isbn = isbn;
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIsbn() { return this.isbn; }

    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year = year; }

    @Override
    public String toString() {
        return publisher + " " + author + " " + title + " " + price;
    }
}