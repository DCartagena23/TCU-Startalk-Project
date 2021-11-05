package edu.cs.tcu.tcustartalkproject.GrammarWord;

import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrammarWordRepository extends JpaRepository<GrammarWord, String> {
}
