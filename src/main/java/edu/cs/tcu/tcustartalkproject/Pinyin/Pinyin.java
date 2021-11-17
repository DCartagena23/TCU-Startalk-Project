package edu.cs.tcu.tcustartalkproject.Pinyin;


import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Pinyin implements Serializable {
    @Id
    private String id;

    private String pinyin;

    @JsonBackReference
    @ManyToOne
    private Chapter chapter;

    public Pinyin() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }


    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
