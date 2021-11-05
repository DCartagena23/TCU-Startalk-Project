package edu.cs.tcu.tcustartalkproject.VocabWord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabWordService {
    private final VocabWordRepository vocabWordRepository;
    @Autowired
    public VocabWordService(VocabWordRepository vocabWordRepository){
        this.vocabWordRepository = vocabWordRepository;
    }

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
