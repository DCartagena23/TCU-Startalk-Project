package edu.cs.tcu.tcustartalkproject.repository;

import antlr.Grammar;
import edu.cs.tcu.tcustartalkproject.domain.Book;
import edu.cs.tcu.tcustartalkproject.domain.GrammarWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrammarWordRepository extends JpaRepository<GrammarWord, String> {
}
