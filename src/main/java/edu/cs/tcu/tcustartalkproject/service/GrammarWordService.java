package edu.cs.tcu.tcustartalkproject.service;

import antlr.Grammar;
import edu.cs.tcu.tcustartalkproject.dao.GrammarWordDao;
import edu.cs.tcu.tcustartalkproject.domain.GrammarWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class GrammarWordService {
    private GrammarWordDao grammarWordDao;

//    @Autowired
    public GrammarWordService(GrammarWordDao grammarWordDao){
        this.grammarWordDao = grammarWordDao;
    }

    public String viewWord(String grammarWordId){
        Optional<GrammarWord> optionalGrammarWord = grammarWordDao.findById(grammarWordId);
        if (optionalGrammarWord.isEmpty()){
            throw new IllegalStateException("Grammar word with id " + grammarWordId + " is not present.");
        }
        GrammarWord grammarWord = optionalGrammarWord.get();
        return (grammarWord.getWord() + "<br/>" + grammarWord.getDesc());
    }
}
