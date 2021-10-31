package edu.cs.tcu.tcustartalkproject.Word;

import edu.cs.tcu.tcustartalkproject.Word.GrammarWord;
import edu.cs.tcu.tcustartalkproject.Word.GrammarWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrammarWordService {
    @Autowired
    private GrammarWordRepository grammarWordRepository;

    public List<GrammarWord> findAll() {
        return grammarWordRepository.findAll();
    }

    public GrammarWord findById(final String id) {
        return grammarWordRepository.findById(id).get();
    }

    public void delete(String id) {
        grammarWordRepository.deleteById(id);
    }

    public GrammarWord save(GrammarWord grammarWord) {
        return grammarWordRepository.save(grammarWord);
    }

    public GrammarWord update(GrammarWord grammarWord) {
        return grammarWordRepository.save(grammarWord);
    }
}
