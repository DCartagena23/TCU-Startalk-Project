package edu.cs.tcu.tcustartalkproject.AudioTest;

import com.amazonaws.services.s3.model.Bucket;
import edu.cs.tcu.tcustartalkproject.Book.Book;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.Chapter.ChapterService;
import edu.cs.tcu.tcustartalkproject.Course.Course;
import edu.cs.tcu.tcustartalkproject.Course.CourseService;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWord;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWordService;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/homeworks")
public class HomeworkController {
    /**
     * Service for basic operations: findAll(), findById(), delete(), save(), update()
     */
    private final HomeworkService homeworkService;
    private final CourseService courseService;

    @Autowired
    public HomeworkController(CourseService courseService, HomeworkService homeworkService){
        this.homeworkService = homeworkService;
        this.courseService = courseService;
    }

    @GetMapping("/findOne/{id}")
    @ResponseBody
    public Result getHw(@PathVariable String id) {
        Homework hw = homeworkService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find Hw Success", hw);
    }


    @PostMapping("/save/{id}")
    @ResponseBody
    public Result saveHw(@PathVariable String id, @RequestBody Homework hw) {
        Course course = courseService.findById(id);
        Homework savedHw = homeworkService.save(hw);
        course.addHw(savedHw);
        courseService.save(course);
        return new Result(StatusCode.SUCCESS, "Hw Saved!", savedHw);
    }

    @PutMapping("/update")
    @ResponseBody
    public Result updateHw(@RequestBody Homework hw) {
        Homework updateHw = homeworkService.update(hw);
        return new Result(StatusCode.SUCCESS, "Hw Updated!", updateHw);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable String id) {
        Homework hw = homeworkService.findById(id);
        hw.setActive(false);
        homeworkService.save(hw);
        return new Result(StatusCode.SUCCESS, "Hw Deleted!", null);
    }

    @GetMapping("/getTestList/{id}")
    @ResponseBody
    public Result getTestList(@PathVariable String id) {
        Homework hw = homeworkService.findById(id);
        List<AudioTest> tests = hw.getTests();
        return new Result(StatusCode.SUCCESS, "Get Test List!", tests);
    }
}