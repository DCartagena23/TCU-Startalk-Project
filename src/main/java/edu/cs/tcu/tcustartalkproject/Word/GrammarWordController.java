package edu.cs.tcu.tcustartalkproject.Word;


import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.Chapter.ChapterService;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/grammarWords")
public class GrammarWordController {

    private final ChapterService chapterService;
    private final GrammarWordService grammarWordService;

    @Autowired
    public GrammarWordController(ChapterService chapterService, GrammarWordService grammarWordService){
        this.chapterService = chapterService;
        this.grammarWordService = grammarWordService;
    }

    @RequestMapping("/findOne/{id}")
    @ResponseBody
    public Result getGrammarWord(@PathVariable String id) {
        GrammarWord grammarWord = grammarWordService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find One Grammar Word Success", grammarWord);
    }

    @PutMapping
    @RequestMapping("/{chapterId}")
    @ResponseBody
    public Result update(@PathVariable String chapterId, @RequestBody GrammarWord grammarWord) {
        // TO-DO: Review update. This method has unclear name. Should it be addGrammarWord or updateGrammarWord?
        // Review the request mapping path
        // Why does the chapter add a new grammarWord  every time updating grammarWord?
        // Where is POST mapping?
        Chapter chapter = chapterService.findById(chapterId);
        chapter.addGrammarWords(grammarWord);
        chapterService.save(chapter);
        return new Result(StatusCode.SUCCESS, "Grammar Word Updated!", grammarWord);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable String id) {
        grammarWordService.delete(id);
        return new Result(StatusCode.SUCCESS, "Grammar Word Deleted!", null);
    }

}