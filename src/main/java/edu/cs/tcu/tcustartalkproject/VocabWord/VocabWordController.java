package edu.cs.tcu.tcustartalkproject.VocabWord;

import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vocabWords")
public class VocabWordController {
    private final VocabWordService vocabWordService;

    @Autowired
    public VocabWordController(VocabWordService vocabWordService){
        this.vocabWordService = vocabWordService;
    }

    @GetMapping("/findOne/{id}")
    @ResponseBody
    public Result getVocabWord(@PathVariable String id) {
        VocabWord vocabWord = vocabWordService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find One Vocab Word Success", vocabWord);
    }

    @PostMapping("/saveVocabWord")
    @ResponseBody
    public Result saveVocabWord(@RequestBody VocabWord vocabWord) {
        VocabWord savedVocabWord = vocabWordService.save(vocabWord);
        return new Result(StatusCode.SUCCESS, "Vocab Word Saved!", savedVocabWord);
    }

    @PutMapping("/updateVocabWord")
    @ResponseBody
    public Result updateVocabWord(@RequestBody VocabWord vocabWord) {
        VocabWord updatedWord = vocabWordService.update(vocabWord);
        return new Result(StatusCode.SUCCESS, "Vocab Word Updated!", updatedWord);
    }

    @DeleteMapping("/deleteVocabWord/{id}")
    @ResponseBody
    public Result deleteVocabWord(@PathVariable String id) {
        vocabWordService.delete(id);
        return new Result(StatusCode.SUCCESS, "Vocab Word Deleted!", null);
    }
}
