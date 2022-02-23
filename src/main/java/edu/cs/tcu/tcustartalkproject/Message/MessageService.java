package edu.cs.tcu.tcustartalkproject.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public List<Message> findAll(){
        return messageRepository.findAll();
    }

    public Message findById(final String id){
        return messageRepository.findById(id).get();
    }

    public void delete(String id){
        messageRepository.deleteById(id);
    }

    public Message save(Message message){
        return messageRepository.save(message);
    }

    public Message update(Message message){
        return messageRepository.save(message);
    }
}
