package edu.cs.tcu.tcustartalkproject.Course;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {
}