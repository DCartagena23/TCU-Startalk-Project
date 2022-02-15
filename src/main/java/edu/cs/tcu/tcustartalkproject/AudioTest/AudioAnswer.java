package edu.cs.tcu.tcustartalkproject.AudioTest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for special pinyin.
 * Normally, pinyin is pre-defined in CCCEdict. Customized Pinyin is only created
 * if the user desires.
 */

public class AudioAnswer implements Serializable {
    private String id;
    private String user;
    private String key;
    private double grade;
    private List<Comment> comments = new ArrayList<Comment>();
    /**
     * Constructor for Pinyin without arguments
     */
    public AudioAnswer() {
        super();
    }

    public AudioAnswer(String id, String user, String key) {
        this.id = id;
        this.user = user;
        this.key =key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

}
