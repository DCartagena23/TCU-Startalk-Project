package edu.cs.tcu.tcustartalkproject.Chapter;
import java.io.Serializable;

public class TimeStamp implements Serializable {
    private String time;

    private String location;

    /**
     * Constructor for TimeStamp without arguments
     */
    public TimeStamp() {
        super();
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
}
