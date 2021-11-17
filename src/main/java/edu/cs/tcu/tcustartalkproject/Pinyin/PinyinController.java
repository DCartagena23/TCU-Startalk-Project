package edu.cs.tcu.tcustartalkproject.Pinyin;

import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.Chapter.ChapterService;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pinyins")
public class PinyinController {

    private final ChapterService chapterService;
    private final PinyinService pinyinService;

    @Autowired
    public PinyinController(ChapterService chapterService, PinyinService pinyinService){
        this.chapterService = chapterService;
        this.pinyinService = pinyinService;
    }

    @RequestMapping("/findOne/{id}")
    @ResponseBody
    public Result getPinyin(@PathVariable String id) {
        Pinyin pinyin = pinyinService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find One Pinyin Success", pinyin);
    }

    @PostMapping("/savePiniyn/{chapterId}")
    @ResponseBody
    public Result savePinyin(@PathVariable String chapterId, @RequestBody Pinyin pinyin) {
        Chapter chapter = chapterService.findById(chapterId);
        chapter.addPinyin(pinyin);
        chapterService.update(chapter);
        return new Result(StatusCode.SUCCESS, "Pinyin Saved!", pinyin);
    }

    @PutMapping("/updatePinyin/{chapterId}")
    @ResponseBody
    public Result updatePinyin(@PathVariable String chapterId, @RequestBody Pinyin pinyin) {
        Chapter chapter = chapterService.findById(chapterId);
        chapter.addPinyin(pinyin);
        chapterService.update(chapter);
        return new Result(StatusCode.SUCCESS, "Pinyin Updated!", pinyin);
    }

    @DeleteMapping("/deletePinyin/{id}")
    @ResponseBody
    public Result deleteGrammarWord(@PathVariable String id) {
        pinyinService.delete(id);
        return new Result(StatusCode.SUCCESS, "Pinyin Deleted!", null);
    }

}