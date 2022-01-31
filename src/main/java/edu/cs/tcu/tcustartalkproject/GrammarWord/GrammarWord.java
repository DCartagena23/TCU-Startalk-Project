package edu.cs.tcu.tcustartalkproject.GrammarWord;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Document(collection= "GrammarWord")
public class GrammarWord implements Serializable {
    @Id
    private String id;
    private String word;
    private String desc;

    @DBRef
    @JsonBackReference
    private Chapter chapter;

    public GrammarWord() {
        super();
    }

    /**
     * Constructor for Grammar Word objects with arguments
     * @param id index in database
     * @param word the Grammar Word itself
     * @param desc brief description of the grammar word
     */
    public GrammarWord(String id, String word, String desc){
        this.id = id;
        this.word = word;
        this.desc = desc;
    }

    /**
     * Method to get index of the grammar word in the database
     * @return index of the grammar word
     */
    public String getId() {
        return id;
    }

    /**
     * Method to set index of the grammar word in the database
     * @param id index to be set in the database
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method to get the grammar word in the database
     * @return Grammar word itself
     */
    public String getWord() {
        return word;
    }

    /**
     * Method to set the grammar word in the database
     * @param word grammar word to be set in the database
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Method to get description of the grammar word in the database
     * @return description of the grammar word in the database
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Method to set description of the grammar word in the database
     * @param desc description to be set in the database
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Method to get chapter the grammar word is located in
     * @return chapter the grammar word is in
     */
    public Chapter getChapter() {
        return chapter;
    }

    /**
     * Method to set the chapter the grammar word is located in
     * @param chapter chapter
     */
    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
