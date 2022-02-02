package edu.cs.tcu.tcustartalkproject.GrammarWord;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GrammarWordRepository extends MongoRepository<GrammarWord, String> {
}