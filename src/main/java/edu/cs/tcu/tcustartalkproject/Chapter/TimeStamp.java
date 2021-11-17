package edu.cs.tcu.tcustartalkproject.Chapter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class TimeStamp implements Serializable {
    @Id
    private String id;

    private String time;

    private String location;

    @JsonBackReference
    @ManyToOne
    private Chapter chapter;

    public TimeStamp() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
