package edu.cs.tcu.tcustartalkproject.AudioTest;
import java.io.Serializable;

/**
 * Class for special pinyin.
 * Normally, pinyin is pre-defined in CCCEdict. Customized Pinyin is only created
 * if the user desires.
 */

public class Comment implements Serializable {
    private String id;
    private String user;
    private String content;
    private boolean instructor;
    /**
     * Constructor for Pinyin without arguments
     */
    public Comment() {
        super();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) { this.content = content; }

    public boolean getInstructor() {
        return instructor;
    }

    public void setInstructor(boolean instructor) {
        this.instructor = instructor;
    }

}
