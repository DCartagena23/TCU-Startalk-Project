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
@RequestMapping("/audioTests")
public class AudioTestController {
    /**
     * Service for basic operations: findAll(), findById(), delete(), save(), update()
     */
    private final AudioTestService testService;
    private final CourseService courseService;

    @Autowired
    public AudioTestController(CourseService courseService, AudioTestService testService){
        this.testService = testService;
        this.courseService = courseService;
    }

    /**
     * Method to find all books.
     * @return Result object that contains status code, message, and found books.
     */
    @GetMapping("/findAll")
    @ResponseBody
    public Result findAllTest() {
        final List<AudioTest> tests = testService.findAll();
        return new Result(StatusCode.SUCCESS, "Find All Test Success", tests);
    }

    @GetMapping("/findOne/{id}")
    @ResponseBody
    public Result getTest(@PathVariable String id) {
        AudioTest test = testService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find Test Success", test);
    }


    @PostMapping("/save/{id}")
    @ResponseBody
    public Result saveTest(@PathVariable String id, @RequestBody AudioTest test) {
        Course course = courseService.findById(id);
        AudioTest savedTest = testService.save(test);
        course.addTest(savedTest);
        courseService.save(course);
        return new Result(StatusCode.SUCCESS, "Test Saved!", savedTest);
    }

    @PutMapping("/update")
    @ResponseBody
    public Result updateTest(@RequestBody AudioTest test) {
        AudioTest updateTest = testService.update(test);
        return new Result(StatusCode.SUCCESS, "Test Updated!", updateTest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable String id) {
        AudioTest test = testService.findById(id);
        test.setActive(false);
        testService.save(test);
//        List<Chapter> chapters = book.getChapter();
//        bookService.delete(id);
//        for (Chapter c : chapters){
//            List<GrammarWord> grammarWords = c.getGrammarWords();
//            chapterService.delete(c.getId());
//            for (GrammarWord g : grammarWords){
//                grammarWordService.delete(g.getId());
//            }
//        }
        return new Result(StatusCode.SUCCESS, "Test Deleted!", null);
    }

    @PutMapping("/addAnswer/{id}")
    @ResponseBody
    public Result addAnswer(@PathVariable String id, @RequestBody AudioAnswer answer) {
        AudioTest test = testService.findById(id);
        test.addAnswer(answer);
        AudioTest updateTest = testService.update(test);

        return new Result(StatusCode.SUCCESS, "Answer Added!", updateTest);
    }

    @GetMapping("/getAnswer/{testId}/{answerId}")
    @ResponseBody
    public Result getAnswer(@PathVariable String testId, @PathVariable String answerId) {
        AudioAnswer targetAnswer = null;
        AudioTest test = testService.findById(testId);
        List<AudioAnswer> answers = test.getAnswers();

        for (AudioAnswer answer : answers) {

            if (Objects.equals(answer.getId(), answerId)){
                targetAnswer = answer;
            }
        }

        return new Result(StatusCode.SUCCESS, "Get Answer Success", targetAnswer);
    }

    @PutMapping("/updateAnswer/{testId}/{answerId}")
    @ResponseBody
    public Result updateTest(@PathVariable String testId,
                             @PathVariable String answerId,
                             @RequestBody AudioAnswer updateAnswer) {
        AudioTest test = testService.findById(testId);
        List<AudioAnswer> answers = test.getAnswers();
        for (AudioAnswer answer : answers)
            if (Objects.equals(answer.getId(), answerId)) {
                answer.setKey(updateAnswer.getKey());
                answer.setUser(updateAnswer.getUser());
            }
        AudioTest updateTest = testService.update(test);
        return new Result(StatusCode.SUCCESS, "Update Answer Success", updateTest);
    }

    @PutMapping("/changeGrade/{testId}/{answerId}")
    @ResponseBody
    public Result updateAnswerGrade(@PathVariable String testId,
                                    @PathVariable String answerId,
                                    @RequestBody AudioAnswer updateAnswer) {
        AudioTest test = testService.findById(testId);
        List<AudioAnswer> answers = test.getAnswers();
        for (AudioAnswer answer : answers)
            if (Objects.equals(answer.getId(), answerId)) {
                answer.setGrade(updateAnswer.getGrade());
            }
        AudioTest updateTest = testService.update(test);
        return new Result(StatusCode.SUCCESS, "Update Grade Success", updateTest);
    }

    @PutMapping("/addComment/{testId}/{answerId}")
    @ResponseBody
    public Result addComment(@PathVariable String testId,
                                    @PathVariable String answerId,
                                    @RequestBody Comment comment) {
        AudioTest test = testService.findById(testId);
        List<AudioAnswer> answers = test.getAnswers();
        for (AudioAnswer answer : answers)
            if (Objects.equals(answer.getId(), answerId)) {
                answer.addComment(comment);
            }
        AudioTest updateTest = testService.update(test);
        return new Result(StatusCode.SUCCESS, "Add Comment Success", comment);
    }
//
//    /**
//     * Method to get chapters of a book.
//     * @param id index of the book to get chapters.
//     * @return Result object that contains status code, message, and chapters of the book.
//     */
//    @GetMapping("/getChapters/{id}")
//    @ResponseBody
//    public Result getChapters(@PathVariable String id) {
//        Book book = bookService.findById(id);
//        final List<Chapter> chapters = book.getChapter();
//        return new Result(StatusCode.SUCCESS, "Get Chapter Success", chapters);
//    }
}