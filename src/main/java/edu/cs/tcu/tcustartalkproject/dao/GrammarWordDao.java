package edu.cs.tcu.tcustartalkproject.dao;

import edu.cs.tcu.tcustartalkproject.domain.GrammarWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrammarWordDao extends JpaRepository<GrammarWord, String>{

}
