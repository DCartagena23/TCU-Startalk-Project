package edu.cs.tcu.tcustartalkproject.GrammarWord;

import edu.cs.tcu.tcustartalkproject.Book.Book;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.Chapter.ChapterService;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/grammarWords")
public class GrammarWordController {
    /**
     * Service for basic operations: findById(), delete(), save(), update()
     */
    private final ChapterService chapterService;
    private final GrammarWordService grammarWordService;

    /**
     * MongoDB Template
     */
    private final MongoTemplate mongoTemplate;

    /**
     * Constructor for Grammar Word Controller
     * @param chapterService Chapter service supports basic operations.
     * @param grammarWordService Grammar Word Service supports basic operations.
     */
    @Autowired
    public GrammarWordController(ChapterService chapterService, GrammarWordService grammarWordService, MongoTemplate mongoTemplate){
        this.chapterService = chapterService;
        this.grammarWordService = grammarWordService;
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Method to find one grammar word.
     * @param id index of the grammar word to be sought.
     * @return Result object that contains status code, message, and found grammar word.
     */
    @RequestMapping("/findOne/{id}")
    @ResponseBody
    public Result getGrammarWord(@PathVariable String id) {
        GrammarWord grammarWord = grammarWordService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find One Grammar Word Success", grammarWord);
    }

    /**
     * Method to save a grammar word.
     * @param chapterId The chapter the grammar word is located.
     * @param grammarWord Grammar Word object to be saved.
     * @return Result object that contains status code, message, and saved grammar word.
     */
    @PostMapping("/saveGrammarWord/{chapterId}")
    @ResponseBody
    public Result saveGrammarWord(@PathVariable String chapterId, @RequestBody GrammarWord grammarWord) {
        Chapter chapter = chapterService.findById(chapterId);
        chapter.addGrammarWords(grammarWord);
        chapterService.update(chapter);
        return new Result(StatusCode.SUCCESS, "Grammar Word Saved!", grammarWord);
    }

    /**
     * Method to update a grammar word.
     * @param chapterId The chapter the grammar word is located.
     * @param grammarWord Grammar Word object to updated.
     * @return Result object that contains status code, message, and updated grammar word.
     */
    @PutMapping("/updateGrammarWord/{chapterId}")
    @ResponseBody
    public Result updateGrammarWord(@PathVariable String chapterId, @RequestBody GrammarWord grammarWord) {
        Chapter chapter = chapterService.findById(chapterId);
        chapter.addGrammarWords(grammarWord);
        chapterService.update(chapter);
        return new Result(StatusCode.SUCCESS, "Grammar Word Updated!", grammarWord);
    }

    /**
     * Method to delete a grammar word.
     * @param id index of the grammar word to be deleted.
     * @return Result object that contains status code and message.
     */
    @DeleteMapping("/deleteGrammarWord/{id}")
    @ResponseBody
    public Result deleteGrammarWord(@PathVariable String id) {
        this.deleteDBRefGrammarWord(id);
        grammarWordService.delete(id);
        return new Result(StatusCode.SUCCESS, "Grammar Word Deleted!", null);
    }

    /**
     * Method to delete DBRef GrammarWord referenced in Chapter.
     * @param grammarWordId index of the grammarWord.
     */
    public void deleteDBRefGrammarWord(String grammarWordId){
        Query query = Query.query(Criteria.where("$id").is(new ObjectId(grammarWordId)));
        Update update = new Update().pull("grammarWords", query);
        mongoTemplate.updateMulti(new Query(), update, Chapter.class);
    }
}