package edu.cs.tcu.tcustartalkproject.Word;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Word {
    // TO-DO: Consider removing this domain. Every word can be Vocab Word.
    @Id
    private String id;
    private String pinyin = "";

    public Word() {
        super();
    }

    public Word(String id) {
        this.id = id;
        this.pinyin = "";
    }

    public Word(String id, String pinyin) {
        this.id = id;
        this.pinyin = pinyin;
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
}
