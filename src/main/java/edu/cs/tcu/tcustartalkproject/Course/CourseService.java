package edu.cs.tcu.tcustartalkproject.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    /**
     * Repository for basic operations: findAll(), findById(), delete(), save(), update()
     */
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Method to query all chapters in a book.
     * @return List of found chapters.
     */
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    /**
     * Method to query one chapter by index.
     * @param id index of the chapter to be sought.
     * @return Chapter to be sought.
     */
    public Course findById(final String id) {
        return courseRepository.findById(id).get();
    }

    /**
     * Method to delete one chapter by index.
     * @param id index of the chapter to be deleted.
     */
    public void delete(String id) {
        courseRepository.deleteById(id);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course update(Course course) {
        return courseRepository.save(course);
    }
}
