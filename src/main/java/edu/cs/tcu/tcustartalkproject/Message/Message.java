package edu.cs.tcu.tcustartalkproject.Message;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.cs.tcu.tcustartalkproject.Authentication.Models.User;
import edu.cs.tcu.tcustartalkproject.Forum.Forum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "Message")
public class Message implements Serializable {
    @Id
    private String id;
    private User user;
    private String content;
    private String mediaURL;

    @DBRef
    @JsonBackReference
    private Forum forum;

    public Message(){
    }

    public Message(String id, User user, String content, String mediaURL){
        this.id = id;
        this.user = user;
        this.content = content;
        this.mediaURL = mediaURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaURL() {
        return mediaURL;
    }

    public void setMediaURL(String mediaURL) {
        this.mediaURL = mediaURL;
    }

    public Forum getForum(){
        return forum;
    }

    public void setForum(Forum forum){
        this.forum = forum;
    }
}
