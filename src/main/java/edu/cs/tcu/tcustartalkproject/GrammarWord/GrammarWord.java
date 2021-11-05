package edu.cs.tcu.tcustartalkproject.GrammarWord;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class GrammarWord implements Serializable {
    @Id
    private String id;
    private String word;
    @Column(length=10000)
    private String desc;

    @JsonBackReference
    @ManyToOne
    private Chapter chapter;

    public GrammarWord() {
        super();
    }

    public GrammarWord(String id, String word, String desc){
        this.id = id;
        this.word = word;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
