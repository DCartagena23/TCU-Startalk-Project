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

    /**
     * Constructor for TimeStamp without arguments
     */
    public TimeStamp() {
        super();
    }

    /**
     * Get index of timestamp
     * @return index of timestamp
     */
    public String getId() {
        return id;
    }

    /**
     * Set index of timestamp
     * @param id index of timestamp
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get time of timestamp
     * @return time of timestamp
     */
    public String getTime() {
        return time;
    }

    /**
     * Set time of timestamp
     * @param time time of timestamp
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Get location in the chapter of timestamp
     * @return location in the chapter
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set location in the chapter of timestamp
     * @param location location in the chapter
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get chapter that contains this timestamp
     * @return chapter containing this timestamp
     */
    public Chapter getChapter() {
        return chapter;
    }

    /**
     * Set chapter that contains this timestamp
     * @param chapter Chapter object that contains this timestamp
     */
    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
