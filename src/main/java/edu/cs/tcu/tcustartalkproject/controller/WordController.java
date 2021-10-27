package edu.cs.tcu.tcustartalkproject.controller;

import edu.cs.tcu.tcustartalkproject.domain.Result;
import edu.cs.tcu.tcustartalkproject.domain.StatusCode;
import edu.cs.tcu.tcustartalkproject.domain.Word;
import edu.cs.tcu.tcustartalkproject.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/words")
public class WordController {

    @Autowired
    private WordService wordService;


    @RequestMapping("/findOne/{id}")
    @ResponseBody
    public Result getWord(@PathVariable String id) {
        Word word = wordService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find One Product Success", word);
    }

    @RequestMapping("/create/{id}")
    @ResponseBody
    public Result createWord(@PathVariable String id) {
        Word word = new Word(id);
        wordService.save(word);
        return new Result(StatusCode.SUCCESS, "Create Word Success", word);
    }

    @PostMapping
    @ResponseBody
    public Result save(@RequestBody Word word) {

        Word savedWord = wordService.save(word);

        return new Result(StatusCode.SUCCESS, "Word Saved!", savedWord);
    }

    @PutMapping
    @ResponseBody
    public Result update(@RequestBody Word word) {

        Word updateWord = wordService.save(word);

        return new Result(StatusCode.SUCCESS, "Word Updated!", updateWord);
    }
}
