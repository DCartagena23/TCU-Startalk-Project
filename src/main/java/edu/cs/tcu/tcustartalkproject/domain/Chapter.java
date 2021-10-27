package edu.cs.tcu.tcustartalkproject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Chapter {
    @Id
    private String id;

    private Integer number = null;
    private String title = null;
    private String text = null;

    @JsonBackReference
    @ManyToOne
    private Book book;


    public Chapter() {
        super();
    }

    public Chapter(String id, Integer number, String title, String text) {
        this.id = id;
        this.number = number;
        this.title = title;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString(){
        String str = this.title + " " + this.text;
        return str;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
