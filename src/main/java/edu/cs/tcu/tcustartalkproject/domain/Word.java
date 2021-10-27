package edu.cs.tcu.tcustartalkproject.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Word {
    @Id
    private String id;

    private String pinyin = "";
    private String note = "";

    public Word() {
        super();
    }

    public Word(String id) {
        this.id = id;
        this.pinyin = "";
        this.note = "";
    }

    public Word(String id, String pinyin, String note) {
        this.id = id;
        this.pinyin = pinyin;
        this.note = note;
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
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
