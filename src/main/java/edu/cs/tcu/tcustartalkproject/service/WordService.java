package edu.cs.tcu.tcustartalkproject.service;


import edu.cs.tcu.tcustartalkproject.domain.Word;
import edu.cs.tcu.tcustartalkproject.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {
    @Autowired
    private WordRepository wordRepository;

    public List<Word> findAll() {
        return wordRepository.findAll();
    }

    public Word findById(final String id) {
        return wordRepository.findById(id).get();
    }

    public void delete(String id) {
        wordRepository.deleteById(id);
    }

    public Word save(Word word) {
        return wordRepository.save(word);
    }

    public Word update(Word word) {
        return wordRepository.save(word);
    }
}
