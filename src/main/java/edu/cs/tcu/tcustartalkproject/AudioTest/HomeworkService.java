package edu.cs.tcu.tcustartalkproject.AudioTest;

import edu.cs.tcu.tcustartalkproject.Book.Book;
import edu.cs.tcu.tcustartalkproject.Book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkService {

    private final HomeworkRepository homeworkRepository;


    @Autowired
    public HomeworkService(HomeworkRepository homeworkRepository){
        this.homeworkRepository = homeworkRepository;
    }

    public List<Homework> findAll() {
        return homeworkRepository.findAll();
    }

    public Homework findById(final String id) {
        return homeworkRepository.findById(id).get();
    }

    public void delete(String id) {
        homeworkRepository.deleteById(id);
    }

    public Homework save(Homework hw) {
        return homeworkRepository.save(hw);
    }

    public Homework update(Homework hw) {
        return homeworkRepository.save(hw);
    }
}