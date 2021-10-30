package edu.cs.tcu.tcustartalkproject.repository;

import edu.cs.tcu.tcustartalkproject.domain.VocabWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocabWordRepository extends JpaRepository<VocabWord, String> {
}
