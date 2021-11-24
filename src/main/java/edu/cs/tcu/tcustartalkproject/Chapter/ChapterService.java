package edu.cs.tcu.tcustartalkproject.Chapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {
    /**
     * Repository for basic operations: findAll(), findById(), delete(), save(), update()
     */
    private final ChapterRepository chapterRepository;

    /**
     * Constructor for Chapter Service.
     * @param chapterRepository Chapter Repository supports basic operations.
     */
    @Autowired
    public ChapterService(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    /**
     * Method to query all chapters in a book.
     * @return List of found chapters.
     */
    public List<Chapter> findAll() {
        return chapterRepository.findAll();
    }

    /**
     * Method to query one chapter by index.
     * @param id index of the chapter to be sought.
     * @return Chapter to be sought.
     */
    public Chapter findById(final String id) {
        return chapterRepository.findById(id).get();
    }

    /**
     * Method to delete one chapter by index.
     * @param id index of the chapter to be deleted.
     */
    public void delete(String id) {
        chapterRepository.deleteById(id);
    }

    /**
     * Method to save a chapter to database.
     * @param chapter Chapter to be saved.
     * @return Chapter that is saved.
     */
    public Chapter save(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    /**
     * Method to update a chapter in database.
     * @param chapter Chapter to be updated.
     * @return Chapter that is updated.
     */
    public Chapter update(Chapter chapter) {
        return chapterRepository.save(chapter);
    }
}
