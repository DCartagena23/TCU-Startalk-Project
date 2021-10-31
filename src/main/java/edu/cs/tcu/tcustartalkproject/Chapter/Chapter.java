package edu.cs.tcu.tcustartalkproject.Chapter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.cs.tcu.tcustartalkproject.Book.Book;
import edu.cs.tcu.tcustartalkproject.Word.GrammarWord;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<GrammarWord> grammarWords = new ArrayList<GrammarWord>();


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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<GrammarWord> getGrammarWords() {
        return grammarWords;
    }

    public void setGrammarWords(List<GrammarWord> grammarWords) {
        this.grammarWords = grammarWords;
    }
    public void addGrammarWords(GrammarWord grammarWord){
        this.grammarWords.add(grammarWord);
        grammarWord.setChapter(this);
    }
}
