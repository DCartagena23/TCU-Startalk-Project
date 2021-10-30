package edu.cs.tcu.tcustartalkproject.service;

import antlr.Grammar;
import edu.cs.tcu.tcustartalkproject.dao.VocabWordDao;
import edu.cs.tcu.tcustartalkproject.domain.VocabWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class VocabWordService {
    private VocabWordDao vocabWordDao;

    //    @Autowired
    public VocabWordService(VocabWordDao vocabWordDao){
        this.vocabWordDao = vocabWordDao;
    }

    public String viewWord(String vocabWordId){
        Optional<VocabWord> optionalVocabWord = vocabWordDao.findById(vocabWordId);
        if (optionalVocabWord.isEmpty()){
            throw new IllegalStateException("Vocab word with id " + vocabWordId + " is not present.");
        }
        VocabWord vocabWord = optionalVocabWord.get();
        return (vocabWord.getWord() + "<br/>" + vocabWord.getDesc());
    }
}