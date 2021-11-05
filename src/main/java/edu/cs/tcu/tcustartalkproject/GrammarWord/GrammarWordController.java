package edu.cs.tcu.tcustartalkproject.GrammarWord;


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

    @PostMapping("/saveGrammarWord/{chapterId}")
    @ResponseBody
    public Result saveGrammarWord(@PathVariable String chapterId, @RequestBody GrammarWord grammarWord) {
        Chapter chapter = chapterService.findById(chapterId);
        chapter.addGrammarWords(grammarWord);
        chapterService.update(chapter);
        return new Result(StatusCode.SUCCESS, "Grammar Word Saved!", grammarWord);
    }

    @PutMapping("/updateGrammarWord/{chapterId}")
    @ResponseBody
    public Result updateGrammarWord(@PathVariable String chapterId, @RequestBody GrammarWord grammarWord) {
        Chapter chapter = chapterService.findById(chapterId);
        chapter.addGrammarWords(grammarWord);
        chapterService.update(chapter);
        return new Result(StatusCode.SUCCESS, "Grammar Word Updated!", grammarWord);
    }

    @DeleteMapping("/deleteGrammarWord/{id}")
    @ResponseBody
    public Result deleteGrammarWord(@PathVariable String id) {
        grammarWordService.delete(id);
        return new Result(StatusCode.SUCCESS, "Grammar Word Deleted!", null);
    }

}