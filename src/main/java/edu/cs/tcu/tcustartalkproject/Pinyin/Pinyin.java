package edu.cs.tcu.tcustartalkproject.Pinyin;


import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Class for special pinyin.
 * Normally, pinyin is pre-defined in CCCEdict. Customized Pinyin is only created
 * if the user desires.
 */
@Entity
public class Pinyin implements Serializable {
    @Id
    private String id;

    private String pinyin;

    @JsonBackReference
    @ManyToOne
    private Chapter chapter;

    /**
     * Constructor for Pinyin without arguments
     */
    public Pinyin() {
        super();
    }

    /**
     * Get index of pinyin in the database.
     * @return index of pinyin
     */
    public String getId() {
        return id;
    }

    /**
     * Set index of pinyin in the database.
     * @param id index of pinyin
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get pinyin.
     * @return pinyin
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * Set pinyin
     * @param pinyin pinyin
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    /**
     * Get chapter that contains this pinyin.
     * @return chapter that contains this pinyin.
     */
    public Chapter getChapter() {
        return chapter;
    }

    /**
     * Set chapter that contains this pinyin.
     * @param chapter chapter that contains this pinyin.
     */
    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
