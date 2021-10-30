package edu.cs.tcu.tcustartalkproject.controller;


import edu.cs.tcu.tcustartalkproject.domain.*;
import edu.cs.tcu.tcustartalkproject.service.BookService;
import edu.cs.tcu.tcustartalkproject.service.ChapterService;
import edu.cs.tcu.tcustartalkproject.service.GrammarWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gwords")
public class GWordController {

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