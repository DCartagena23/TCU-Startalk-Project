package edu.cs.tcu.tcustartalkproject.dao;

import edu.cs.tcu.tcustartalkproject.domain.VocabWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocabWordDao extends JpaRepository<VocabWord, String>{

}