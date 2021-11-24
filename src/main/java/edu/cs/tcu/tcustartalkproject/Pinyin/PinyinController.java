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
    /**
     * Service for basic operations related to chapter: findAll(), findById(), delete(), save(), update()
     */
    private final ChapterService chapterService;

    /**
     * Service for basic operations related to pinyin: findAll(), findById(), delete(), save(), update()
     */
    private final PinyinService pinyinService;

    /**
     * Constructor for Pinyin Controller
     * @param chapterService Chapter Service supports basic operations related to chapter
     * @param pinyinService Pinyin Service supports basic operations related to pinyin
     */
    @Autowired
    public PinyinController(ChapterService chapterService, PinyinService pinyinService){
        this.chapterService = chapterService;
        this.pinyinService = pinyinService;
    }

    /**
     * Method to find one pinyin.
     * @param id index of the pinyin to be sought.
     * @return Result object that contains status code, message, and found pinyin.
     */
    @RequestMapping("/findOne/{id}")
    @ResponseBody
    public Result getPinyin(@PathVariable String id) {
        Pinyin pinyin = pinyinService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find One Pinyin Success", pinyin);
    }

    /**
     * Method to save a pinyin given the index of chapter.
     * @param chapterId index of Chapter object to have pinyin saved.
     * @param pinyin Pinyin object to be saved.
     * @return Result object that contains status code, message, and saved pinyin.
     */
    @PostMapping("/savePiniyn/{chapterId}")
    @ResponseBody
    public Result savePinyin(@PathVariable String chapterId, @RequestBody Pinyin pinyin) {
        Chapter chapter = chapterService.findById(chapterId);
        chapter.addPinyin(pinyin);
        chapterService.update(chapter);
        return new Result(StatusCode.SUCCESS, "Pinyin Saved!", pinyin);
    }

    /**
     * Method to update a pinyin given the index of chapter.
     * @param chapterId index of Chapter object to have pinyin updated.
     * @param pinyin Pinyin object to be updated.
     * @return Result object that contains status code, message, and updated pinyin.
     */
    @PutMapping("/updatePinyin/{chapterId}")
    @ResponseBody
    public Result updatePinyin(@PathVariable String chapterId, @RequestBody Pinyin pinyin) {
        Chapter chapter = chapterService.findById(chapterId);
        chapter.addPinyin(pinyin);
        chapterService.update(chapter);
        return new Result(StatusCode.SUCCESS, "Pinyin Updated!", pinyin);
    }

    /**
     * Method to delete a pinyin
     * @param id index of the pinyin to be deleted.
     * @return Result object that contains status code and message.
     */
    @DeleteMapping("/deletePinyin/{id}")
    @ResponseBody
    public Result deletePinyin(@PathVariable String id) {
        pinyinService.delete(id);
        return new Result(StatusCode.SUCCESS, "Pinyin Deleted!", null);
    }

}