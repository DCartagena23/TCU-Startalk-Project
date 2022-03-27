package edu.cs.tcu.tcustartalkproject.AudioTest;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HomeworkRepository extends MongoRepository<Homework, String> {
}