package edu.cs.tcu.tcustartalkproject.Chapter;

import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChapterRepository extends MongoRepository<Chapter, String> {
}
