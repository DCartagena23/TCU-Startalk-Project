//package edu.cs.tcu.tcustartalkproject.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import java.io.IOException;
//
//@Controller
//@RequestMapping("/dictionary")
//public class DictionaryController  {
//    private final ArchivedCCCEDictParser parser;
//
//    @Autowired
//    public DictionaryController() throws IOException {
//        ArchivedCCCEDictParser parser = new ArchivedCCCEDictParser();
//        this.parser = parser;
//    }
//
//    @GetMapping("/pinyin/{word}")
//    @ResponseBody
//    public Result getPinyin(@PathVariable String word){
//        String str = "";
//            try{
//                str = parser.getDict("simple").get(word).getMandarinPinyin();
//            }
//            catch (Exception e){
//                str = "";
//            }
//        return new Result(StatusCode.SUCCESS, "Pinyin Success", str);
//    }
//}