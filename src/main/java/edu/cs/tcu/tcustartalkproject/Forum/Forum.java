package edu.cs.tcu.tcustartalkproject.Forum;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.cs.tcu.tcustartalkproject.Message.Message;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "Forum")
public class Forum implements Serializable {
    @Id
    private String id;

    @DBRef
    @JsonManagedReference
    private List<Message> messages = new ArrayList<Message>();

    public Forum(String id){
        this.id = id;
    }

    public Forum(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message){
        this.messages.add(message);
        message.setForum(this);
    }
}
