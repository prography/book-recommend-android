package org.techtown.a1006_bibly;

import android.media.Image;
import android.widget.ImageView;

import java.io.Serializable;

public class BookInfo implements Serializable {
    public int book; //책표지
    public String title;
    public String author;
    public String review;
    public float rate; //평점
    public String publisher; //출판사

    public BookInfo(int book) {
        this(book, "", "");
    }

    public BookInfo(int book, String title, String author) {
        this.book = book;
        this.title = title;
        this.author = author;
        this.review = "한줄평이 입력되지 않았습니다.";
        this.rate = 1;
        this.publisher = "default publisher";
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
