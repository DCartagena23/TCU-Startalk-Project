package edu.cs.tcu.tcustartalkproject.Word;

import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/words")
public class WordController {
    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService){
        this.wordService = wordService;
    }

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
        Word updatedWord = wordService.update(word);
        return new Result(StatusCode.SUCCESS, "Word Updated!", updatedWord);
    }
}
