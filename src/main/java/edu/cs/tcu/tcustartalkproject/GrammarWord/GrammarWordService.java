package edu.cs.tcu.tcustartalkproject.GrammarWord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GrammarWordService {
    /**
     * Repository for basic operations: findById(), delete(), save(), update()
     */
    private final GrammarWordRepository grammarWordRepository;

    /**
     * Constructor for Grammar Word Service.
     * @param grammarWordRepository Grammar Word Repository supports basic operations
     */
    @Autowired
    public GrammarWordService(GrammarWordRepository grammarWordRepository){
        this.grammarWordRepository = grammarWordRepository;
    }

    public List<GrammarWord> findAll() {
        return grammarWordRepository.findAll();
    }

    /**
     * Method to query one grammar word by index.
     * @param id index of the grammar word to be sought.
     * @return Grammar word to be sought.
     */
    public GrammarWord findById(final String id) {
        return grammarWordRepository.findById(id).get();
    }

    /**
     * Method to delete one grammar word by index.
     * @param id index of the grammar word to be deleted.
     */
    public void delete(String id) {
        grammarWordRepository.deleteById(id);
    }

    /**
     * Method to save a grammar word to database.
     * @param grammarWord Grammar word to be saved.
     * @return Grammar word that is saved.
     */
    public GrammarWord save(GrammarWord grammarWord) {
        return grammarWordRepository.save(grammarWord);
    }

    /**
     * Method to update a grammar word in database.
     * @param grammarWord Grammar word to be updated.
     * @return Grammar word that is updated.
     */
    public GrammarWord update(GrammarWord grammarWord) {
        return grammarWordRepository.save(grammarWord);
    }
}