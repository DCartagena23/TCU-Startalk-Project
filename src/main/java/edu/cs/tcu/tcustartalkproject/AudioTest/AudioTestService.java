package edu.cs.tcu.tcustartalkproject.AudioTest;

import edu.cs.tcu.tcustartalkproject.Book.Book;
import edu.cs.tcu.tcustartalkproject.Book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AudioTestService {

    private final AudioTestRepository testRepository;


    @Autowired
    public AudioTestService(AudioTestRepository testRepository){
        this.testRepository = testRepository;
    }

    public List<AudioTest> findAll() {
        return testRepository.findAll();
    }

    public AudioTest findById(final String id) {
        return testRepository.findById(id).get();
    }

    public void delete(String id) {
        testRepository.deleteById(id);
    }

    public AudioTest save(AudioTest test) {
        return testRepository.save(test);
    }

    public AudioTest update(AudioTest test) {
        return testRepository.save(test);
    }
}