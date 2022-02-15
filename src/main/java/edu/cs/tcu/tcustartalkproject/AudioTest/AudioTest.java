package edu.cs.tcu.tcustartalkproject.AudioTest;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

@Document(collection= "AudioTest")
public class AudioTest {
    @Id
    private String id;

    private Integer prepTime = null;
    private String title = null;
    private String prompt = null;

    private List<AudioAnswer> answers = new ArrayList<AudioAnswer>();

    /**
     * Constructor for Chapter without arguments
     */
    public AudioTest() {
        super();
    }


    public AudioTest(String id, String title, Integer prepTime, String prompt) {
        this.id = id;
        this.prepTime = prepTime;
        this.title = title;
        this.prompt = prompt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public List<AudioAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AudioAnswer> answers) {
        this.answers = answers;
    }

    public void addAnswer(AudioAnswer answer){
        this.answers.add(answer);
    }

}
