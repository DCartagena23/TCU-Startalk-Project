package edu.cs.tcu.tcustartalkproject.controller;

import edu.cs.tcu.tcustartalkproject.service.VocabWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/vocabWord")
public class VocabWordController {
    private final VocabWordService vocabWordService;

    //    @Autowired
    public VocabWordController(VocabWordService vocabWordService){
        this.vocabWordService = vocabWordService;
    }

    @GetMapping("{vocabWordId}")
    public String viewWord(@PathVariable String vocabWordId){
        return vocabWordService.viewWord(vocabWordId);
    }

}
