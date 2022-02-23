package edu.cs.tcu.tcustartalkproject.Forum;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ForumRepository extends MongoRepository<Forum, String> {
}
