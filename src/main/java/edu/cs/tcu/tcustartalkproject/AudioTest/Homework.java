package edu.cs.tcu.tcustartalkproject.AudioTest;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.cs.tcu.tcustartalkproject.AudioTest.AudioAnswer;
import edu.cs.tcu.tcustartalkproject.Book.Book;
import edu.cs.tcu.tcustartalkproject.Chapter.Pinyin;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWord;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Document(collection= "Homework")
public class Homework {
    @Id
    private String id;
    private String title = null;
    private boolean active;

    @DBRef(lazy = true)
    @JsonIgnoreProperties({"course"})
    private List<AudioTest> tests = new ArrayList<AudioTest>();

    /**
     * Constructor for Chapter without arguments
     */
    public Homework() {
        super();
    }


    public Homework(String id, String title) {
        this.id = id;
        this.title = title;
        this.active = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AudioTest> getTests() {
        return tests;
    }

    public void setTests(List<AudioTest> tests) {
        this.tests = tests;
    }

    public void addTest(AudioTest test){
        this.tests.add(test);
    }

    public void setActive(boolean active) { this.active = active; }
    public boolean getActive() { return active; }

}
