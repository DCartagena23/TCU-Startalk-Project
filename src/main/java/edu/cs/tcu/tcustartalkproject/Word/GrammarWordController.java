package edu.cs.tcu.tcustartalkproject.Word;


import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.Chapter.ChapterService;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gwords")
public class GrammarWordController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private GrammarWordService gWordService;


    @RequestMapping("/findOne/{id}")
    @ResponseBody
    public Result getProduct(@PathVariable String id) {
        GrammarWord gword = gWordService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find One Product Success", gword);
    }

    @PutMapping
    @RequestMapping("/{chapterId}")
    @ResponseBody
    public Result update(@PathVariable String chapterId, @RequestBody GrammarWord gword) {
        Chapter chapter = chapterService.findById(chapterId);
        chapter.addGrammarWords(gword);
        chapterService.save(chapter);

        return new Result(StatusCode.SUCCESS, "GWord Updated!", gword);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable String id) {
        gWordService.delete(id);
        return new Result(StatusCode.SUCCESS, "GWord Deleted!", null);
    }

}