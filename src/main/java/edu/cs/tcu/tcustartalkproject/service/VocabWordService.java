package edu.cs.tcu.tcustartalkproject.service;

import edu.cs.tcu.tcustartalkproject.domain.Book;
import edu.cs.tcu.tcustartalkproject.domain.GrammarWord;
import edu.cs.tcu.tcustartalkproject.domain.VocabWord;
import edu.cs.tcu.tcustartalkproject.repository.BookRepository;
import edu.cs.tcu.tcustartalkproject.repository.GrammarWordRepository;
import edu.cs.tcu.tcustartalkproject.repository.VocabWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabWordService {
    @Autowired
    private VocabWordRepository vocabWordRepository;

    public List<VocabWord> findAll() {
        return vocabWordRepository.findAll();
    }

    public VocabWord findById(final String id) {
        return vocabWordRepository.findById(id).get();
    }

    public void delete(String id) {
        vocabWordRepository.deleteById(id);
    }

    public VocabWord save(VocabWord vocabWord) {
        return vocabWordRepository.save(vocabWord);
    }

    public VocabWord update(VocabWord vocabWord) {
        return vocabWordRepository.save(vocabWord);
    }
}
