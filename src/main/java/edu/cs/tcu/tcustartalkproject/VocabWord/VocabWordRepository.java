package edu.cs.tcu.tcustartalkproject.VocabWord;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VocabWordRepository extends MongoRepository<VocabWord, String> {
}