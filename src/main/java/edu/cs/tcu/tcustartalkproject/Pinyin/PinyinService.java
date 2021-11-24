package edu.cs.tcu.tcustartalkproject.Pinyin;

import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWord;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PinyinService {
    /**
     * Repository for basic operations: findAll(), findById(), delete(), save(), update()
     */
    private final PinyinRepository pinyinRepository;

    /**
     * Constructor for Pinyin Service
     * @param pinyinRepository Pinyin Repository supports basic operations.
     */
    @Autowired
    public PinyinService(PinyinRepository pinyinRepository){
        this.pinyinRepository = pinyinRepository;
    }

    /**
     * Method to query all pinyins in a chapter.
     * @return List of found pinyins.
     */
    public List<Pinyin> findAll() {
        return pinyinRepository.findAll();
    }

    /**
     * Method to query one pinyin by index.
     * @param id index of the pinyin to be sought.
     * @return Pinyin to be sought.
     */
    public Pinyin findById(final String id) {
        return pinyinRepository.findById(id).get();
    }

    /**
     * Method to delete one pinyin by index.
     * @param id index of the pinyin to be deleted.
     */
    public void delete(String id) {
        pinyinRepository.deleteById(id);
    }

    /**
     * Method to save a pinyin to database.
     * @param pinyin Pinyin to be saved.
     * @return Pinyin that is saved.
     */
    public Pinyin save(Pinyin pinyin) {
        return pinyinRepository.save(pinyin);
    }

    /**
     * Method to update a pinyin to database.
     * @param pinyin Pinyin to be updated.
     * @return Pinyin that is updated.
     */
    public Pinyin update(Pinyin pinyin) {
        return pinyinRepository.save(pinyin);
    }
}
