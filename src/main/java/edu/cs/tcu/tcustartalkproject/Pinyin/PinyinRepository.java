package edu.cs.tcu.tcustartalkproject.Pinyin;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PinyinRepository extends MongoRepository<Pinyin, String> {
}
