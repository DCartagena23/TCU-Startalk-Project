package edu.cs.tcu.tcustartalkproject.Forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumService{
    private final ForumRepository forumRepository;

    @Autowired
    public ForumService(ForumRepository forumRepository){
        this.forumRepository = forumRepository;
    }

    public List<Forum> findAll(){
        return forumRepository.findAll();
    }

    public Forum findById(final String id){
        return forumRepository.findById(id).get();
    }

    public void delete(String id){
        forumRepository.deleteById(id);
    }

    public Forum save(Forum forum){
        return forumRepository.save(forum);
    }

    public Forum update(Forum forum){
        return forumRepository.save(forum);
    }
}
