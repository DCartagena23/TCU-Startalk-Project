package edu.cs.tcu.tcustartalkproject.service;

import edu.cs.tcu.tcustartalkproject.domain.Chapter;
import edu.cs.tcu.tcustartalkproject.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;

    public List<Chapter> findAll() {
        return chapterRepository.findAll();
    }

    public Chapter findById(final String id) {
        return chapterRepository.findById(id).get();
    }

    public void delete(String id) {
        chapterRepository.deleteById(id);
    }

    public Chapter save(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    public Chapter update(Chapter chapter) {
        return chapterRepository.save(chapter);
    }
}
