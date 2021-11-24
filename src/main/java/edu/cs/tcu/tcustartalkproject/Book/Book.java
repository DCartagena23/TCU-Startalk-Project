package edu.cs.tcu.tcustartalkproject.Book;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;

import javax.persistence.*;
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


    /**
     * Constructor for Book objects with arguments
     * @param id index in database
     * @param title title of the book
     * @param desc brief description of the book
     * @param author authors of the book as a String
     * @param price price of the book
     */
    public Book(String id, String title, String desc, String author, double price) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.author = author;
        this.price = price;
    }

    /**
     * Construct for Book objects with no arguments
     */
    public Book() {

    }

    /**
     * Method to get index of the book in the database
     * @return index of the book
     */
    public String getId() {
        return id;
    }

    /**
     * Method to set index of the book in the database
     * @param id index to be set in the database
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method to get description of the book in the database
     * @return description of the book in the database
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Method to set description of the book in the database
     * @param desc description to be set in the database
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Method to get title of the book in the database
     * @return title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method to set title of the book in the database
     * @param title title to be set in the database
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method to get authors of the book in the database
     * @return author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Method to set author of the book in the database
     * @param author author to be set in the database
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Method to get price of the book in the database
     * @return price of the book
     */
    public double getPrice() {
        return price;
    }

    /**
     * Method to set price of the book in the database
     * @param price price to be set in the database
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Method to get chapters of the book in the database
     * @return list of chapters of in the book
     */
    public List<Chapter> getChapter() {
        return chapter;
    }

    /**
     * Method to set chapters of the book in the database
     * @param chapter chapters to be set in the database
     */
    public void setChapter(List<Chapter> chapter) {
        this.chapter = chapter;
    }

    /**
     * Method to add a chapter to the book in the database.
     * @param chapter chapter to be added
     */
    public void addChapter(Chapter chapter){
        this.chapter.add(chapter);
        chapter.setBook(this);
    }
}
