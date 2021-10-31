package edu.cs.tcu.tcustartalkproject.Word;


import edu.cs.tcu.tcustartalkproject.Word.Word;
import edu.cs.tcu.tcustartalkproject.Word.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {
    private final WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository){
        this.wordRepository = wordRepository;
    }

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
