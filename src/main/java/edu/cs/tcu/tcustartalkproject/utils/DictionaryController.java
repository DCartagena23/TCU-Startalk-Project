package edu.cs.tcu.tcustartalkproject.utils;

import edu.cs.tcu.tcustartalkproject.Book.BookService;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController  {
    private final CCCEDictParser parser;

    @Autowired
    public DictionaryController() throws IOException {
        CCCEDictParser parser = new CCCEDictParser();
        this.parser = parser;
    }


    @GetMapping("/pinyin/{word}")
    @ResponseBody
    public Result getPinyin(@PathVariable String word){
        String str = "";
            try{
                str = parser.getDict("simple").get(word).getMandarinPinyin();
            }
            catch (Exception e){
                str = "";
            }
        return new Result(StatusCode.SUCCESS, "Pinyin Success", str);
    }

}