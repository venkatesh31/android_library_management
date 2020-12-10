package com.example.library.response;

import java.io.Serializable;
import java.util.Date;

public class UserBook implements Serializable {

    private Integer userBookId;
    private Integer userId;
    private Integer bookId;
    private Date dateCreated;
    private Integer isBookReturned;
    private Book book;
    private User user;

    public Integer getUserBookId() {
        return userBookId;
    }

    public void setUserBookId(Integer userBookId) {
        this.userBookId = userBookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getIsBookReturned() {
        return isBookReturned;
    }

    public void setIsBookReturned(Integer isBookReturned) {
        this.isBookReturned = isBookReturned;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
