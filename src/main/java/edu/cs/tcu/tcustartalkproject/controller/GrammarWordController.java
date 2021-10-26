package edu.cs.tcu.tcustartalkproject.controller;

import edu.cs.tcu.tcustartalkproject.service.GrammarWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/grammarWord")
public class GrammarWordController {
    private final GrammarWordService grammarWordService;

//    @Autowired
    public GrammarWordController(GrammarWordService grammarWordService){
        this.grammarWordService = grammarWordService;
    }

    @GetMapping("{grammarWordId}")
    public String viewWord(@PathVariable String grammarWordId){
        return grammarWordService.viewWord(grammarWordId);
    }

}
