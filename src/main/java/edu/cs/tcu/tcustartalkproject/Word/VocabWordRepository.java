package edu.cs.tcu.tcustartalkproject.Word;

import edu.cs.tcu.tcustartalkproject.Word.VocabWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocabWordRepository extends JpaRepository<VocabWord, String> {
}
