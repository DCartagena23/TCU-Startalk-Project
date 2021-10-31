package edu.cs.tcu.tcustartalkproject.Word;

import edu.cs.tcu.tcustartalkproject.Word.GrammarWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrammarWordRepository extends JpaRepository<GrammarWord, String> {
}
