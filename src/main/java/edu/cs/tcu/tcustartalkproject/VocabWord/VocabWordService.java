package edu.cs.tcu.tcustartalkproject.VocabWord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VocabWordService {
    /**
     * Repository for basic operations: findById(), delete(), save(), update()
     */
    private final VocabWordRepository vocabWordRepository;

    /**
     * Constructor for Vocab Word Service.
     * @param vocabWordRepository Vocab Word Repository supports basic operations
     */
    @Autowired
    public VocabWordService(VocabWordRepository vocabWordRepository){
        this.vocabWordRepository = vocabWordRepository;
    }

    public List<VocabWord> findAll() {
        return vocabWordRepository.findAll();
    }

    /**
     * Method to query one vocab word by index.
     * @param id index of the vocab word to be sought.
     * @return Vocab word to be sought.
     */
    public VocabWord findById(final String id) {
        return vocabWordRepository.findById(id).get();
    }

    /**
     * Method to delete one vocab word by index.
     * @param id index of the vocab word to be deleted.
     */
    public void delete(String id) {
        vocabWordRepository.deleteById(id);
    }

    /**
     * Method to save a vocab word to database.
     * @param vocabWord Vocab word to be saved.
     * @return Vocab word that is saved.
     */
    public VocabWord save(VocabWord vocabWord) {
        return vocabWordRepository.save(vocabWord);
    }

    /**
     * Method to update a grammar word in database.
     * @param vocabWord Vocab word to be updated.
     * @return Vocab word that is updated.
     */
    public VocabWord update(VocabWord vocabWord) {
        return vocabWordRepository.save(vocabWord);
    }
}