package edu.cs.tcu.tcustartalkproject.VocabWord;

import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vocabWords")
public class VocabWordController {
    /**
     * Service for basic operations: findById(), delete(), save(), update()
     */
    private final VocabWordService vocabWordService;

    /**
     * Constructor for Vocab Word Controller
     * @param vocabWordService Vocab Word Service supports basic operations.
     */
    @Autowired
    public VocabWordController(VocabWordService vocabWordService){
        this.vocabWordService = vocabWordService;
    }

    /**
     * Method to find one vocab word.
     * @param id index of the vocab word to be sought.
     * @return Result object that contains status code, message, and found vocab word.
     */
    @GetMapping("/findOne/{id}")
    @ResponseBody
    public Result getVocabWord(@PathVariable String id) {
        try{
            VocabWord vocabWord = vocabWordService.findById(id);
            return new Result(StatusCode.SUCCESS, "Find One Vocab Word Success", vocabWord);
        }
        catch (Exception e){
            return new Result(StatusCode.FAILURE, "FAILURE", null);
        }
    }

    /**
     * Method to save a vocab word.
     * @param vocabWord Vocab Word object to be saved.
     * @return Result object that contains status code, message, and saved vocab word.
     */
    @PostMapping("/saveVocabWord")
    @ResponseBody
    public Result saveVocabWord(@RequestBody VocabWord vocabWord) {
        VocabWord savedVocabWord = vocabWordService.save(vocabWord);
        return new Result(StatusCode.SUCCESS, "Vocab Word Saved!", savedVocabWord);
    }

    /**
     * Method to update a vocab word.
     * @param vocabWord Vocab Word object to updated.
     * @return Result object that contains status code, message, and updated vocab word.
     */
    @PutMapping("/updateVocabWord")
    @ResponseBody
    public Result updateVocabWord(@RequestBody VocabWord vocabWord) {
        VocabWord updatedWord = vocabWordService.update(vocabWord);
        return new Result(StatusCode.SUCCESS, "Vocab Word Updated!", updatedWord);
    }

    /**
     * Method to delete a vocab word.
     * @param id index of the vocab word to be deleted.
     * @return Result object that contains status code and message.
     */
    @DeleteMapping("/deleteVocabWord/{id}")
    @ResponseBody
    public Result deleteVocabWord(@PathVariable String id) {
        vocabWordService.delete(id);
        return new Result(StatusCode.SUCCESS, "Vocab Word Deleted!", null);
    }
}