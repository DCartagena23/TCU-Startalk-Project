package edu.cs.tcu.tcustartalkproject.Book;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    @Id
    private String id;
    private String title;
    private String desc;
    private String author;
    private double price;

    @JsonManagedReference
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Chapter> chapter = new ArrayList<Chapter>();

    public Book(String id, String title, String desc, String author, double price) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.author = author;
        this.price = price;
    }

    public Book() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String description) {
        this.desc = description;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Chapter> getChapter() {
        return chapter;
    }

    public void setChapter(List<Chapter> chapter) {
        this.chapter = chapter;
    }
    public void addChapter(Chapter chapter){
        this.chapter.add(chapter);
        chapter.setBook(this);
    }
}
