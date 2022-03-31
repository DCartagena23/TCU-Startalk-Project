package edu.cs.tcu.tcustartalkproject.Board;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.cs.tcu.tcustartalkproject.Forum.Forum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "Board")
public class Board implements Serializable {
    @Id
    private String id;
    private String author;
    private String title;
    private String desc;

    @DBRef
    @JsonManagedReference
    private List<Forum> forums = new ArrayList<Forum>();

    public Board(){}

    public Board(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Forum> getForums() {
        return forums;
    }

    public void addForum(Forum forum){
        this.forums.add(forum);
        forum.setBoard(this);
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
