package edu.cs.tcu.tcustartalkproject.VocabWord;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class VocabWord implements Serializable {
    @Id
    private String id;
    private String word;
    private String pinyin;
    @Column(length=10000)
    private String desc;

    public VocabWord() {

    }

    public VocabWord(String id, String type, String word, String pinyin, String desc){
        this.id = id;
        this.word = word;
        this.word = pinyin;
        this.desc = desc;
    }

    public VocabWord(String id) {
        this.id = id;
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

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
