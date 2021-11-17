package edu.cs.tcu.tcustartalkproject.Pinyin;

import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWord;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PinyinService {
    private final PinyinRepository pinyinRepository;

    @Autowired
    public PinyinService(PinyinRepository pinyinRepository){
        this.pinyinRepository = pinyinRepository;
    }

    public List<Pinyin> findAll() {
        return pinyinRepository.findAll();
    }

    public Pinyin findById(final String id) {
        return pinyinRepository.findById(id).get();
    }

    public void delete(String id) {
        pinyinRepository.deleteById(id);
    }

    public Pinyin save(Pinyin pinyin) {
        return pinyinRepository.save(pinyin);
    }

    public Pinyin update(Pinyin pinyin) {
        return pinyinRepository.save(pinyin);
    }
}
