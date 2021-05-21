package com.zahar.data;

public class User {

    private int id;
    private String AuthorName;
    private String AuthorLastName;
    private String BookName;

    public User(){
    }

    public User(int id, String authorName, String authorLastName, String bookName) {
        this.id = id;
        this.AuthorName = authorName;
        this.AuthorLastName = authorLastName;
        this.BookName = bookName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public String getAuthorLastName() {
        return AuthorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        AuthorLastName = authorLastName;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }
}
