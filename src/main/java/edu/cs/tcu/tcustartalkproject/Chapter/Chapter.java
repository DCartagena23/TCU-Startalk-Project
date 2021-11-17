package edu.cs.tcu.tcustartalkproject.Chapter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.cs.tcu.tcustartalkproject.Book.Book;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWord;
import edu.cs.tcu.tcustartalkproject.Pinyin.Pinyin;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

@Entity
public class Chapter {
    @Id
    private String id;

    private Integer number = null;
    private String title = null;

    @ElementCollection
    List<String> text = null;

    @JsonBackReference
    @ManyToOne
    private Book book;

    @JsonManagedReference
    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<GrammarWord> grammarWords = new ArrayList<GrammarWord>();

    @JsonManagedReference
    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<Pinyin> pinyin = new ArrayList<Pinyin>();

    @JsonManagedReference
    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<TimeStamp> timeStamp = new ArrayList<TimeStamp>();



    public Chapter() {
        super();
    }

    public Chapter(String id, Integer number, String title) {
        this.id = id;
        this.number = number;
        this.title = title;
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

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public void setTextFormString(String str){
        List<String> text = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str,"\n");
        while (st.hasMoreTokens()){
            StringTokenizer st2 = new StringTokenizer(st.nextToken()," ");
            while (st2.hasMoreTokens()){
                text.add(st2.nextToken());
                if (!st2.hasMoreTokens() && st.hasMoreTokens()){
                    text.add("\n");
                }
            }
        }
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

    public List<Pinyin> getPinyin() {
        return pinyin;
    }

    public void setPinyin(List<Pinyin> pinyin) {
        this.pinyin = pinyin;
    }

    public void addPinyin(Pinyin pinyin){
        this.pinyin.add(pinyin);
        pinyin.setChapter(this);
    }

    public List<TimeStamp> getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(List<TimeStamp> timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void addTimeStamp(TimeStamp timeStamp){
        this.timeStamp.add(timeStamp);
        timeStamp.setChapter(this);
    }
}
