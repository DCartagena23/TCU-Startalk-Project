package edu.cs.tcu.tcustartalkproject.Word;


import edu.cs.tcu.tcustartalkproject.Word.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, String> {
}
