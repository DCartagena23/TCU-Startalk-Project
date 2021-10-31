package edu.cs.tcu.tcustartalkproject.Word;

import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vocabWords")
public class VocabWordController {
    private final VocabWordService vocabWordService;

    @Autowired
    public VocabWordController(VocabWordService vocabWordService){
        this.vocabWordService = vocabWordService;
    }

    @RequestMapping("/findOne/{id}")
    @ResponseBody
    public Result getVocabWord(@PathVariable String id) {
        VocabWord vocabWord = vocabWordService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find One Product Success", vocabWord);
    }

    @PostMapping
    @ResponseBody
    public Result save(@RequestBody VocabWord vocabWord) {
        VocabWord savedVocabWord = vocabWordService.save(vocabWord);
        return new Result(StatusCode.SUCCESS, "Vocab Word Saved!", savedVocabWord);
    }

    @PutMapping
    @ResponseBody
    public Result update(@RequestBody VocabWord vocabWord) {
        VocabWord updatedWord = vocabWordService.update(vocabWord);
        return new Result(StatusCode.SUCCESS, "Vocab Word Updated!", updatedWord);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable String id) {
        vocabWordService.delete(id);
        return new Result(StatusCode.SUCCESS, "Vocab Word Deleted!", null);
    }
}
