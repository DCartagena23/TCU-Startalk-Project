package edu.cs.tcu.tcustartalkproject.Course;

import edu.cs.tcu.tcustartalkproject.Book.Book;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWord;
import edu.cs.tcu.tcustartalkproject.Book.BookService;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWordService;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Controller
@RequestMapping("/courses")
public class CourseController {
    /**
     * Service for basic operations related to chapter: findAll(), findById(), delete(), save(), update()
     */
    private final CourseService courseService;
    /**
     * MongoDB template
     */
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CourseController(CourseService courseService, MongoTemplate mongoTemplate){
        this.courseService = courseService;
        this.mongoTemplate = mongoTemplate;
    }


    /**
     * Method to find one chapter.
     * @param id index of the chapter to be sought.
     * @return Result object that contains status code, message, and found chapter.
     */
    @GetMapping("/findOne/{id}")
    @ResponseBody
    public Result getCourse(@PathVariable String id) {
        Course course = courseService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find Course Success", course);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public Result findAllCourses() {
        final List<Course> courses = courseService.findAll();
        return new Result(StatusCode.SUCCESS, "Find All Course Success", courses);
    }

    @PostMapping("/saveCourse")
    @ResponseBody
    public Result saveCourse(@RequestBody Course course) {
        Course savedCourse = courseService.save(course);
        return new Result(StatusCode.SUCCESS, "Course Saved!", savedCourse);
    }

    @PutMapping("/updateCourse")
    @ResponseBody
    public Result updateCourse(@RequestBody Course course) {
        Course updateCourse = courseService.update(course);
        return new Result(StatusCode.SUCCESS, "Course Updated!", updateCourse);
    }

    /**
     * Method to delete a book.
     * @param id index of the book to be deleted.
     * @return Result object that contains status code and message.
     */
    @DeleteMapping("/deleteCourse/{id}")
    @ResponseBody
    public Result deleteCourse(@PathVariable String id) {
        Course course = courseService.findById(id);
        course.setActive(false);
        courseService.save(course);
        return new Result(StatusCode.SUCCESS, "Course Deleted!", null);
    }
}