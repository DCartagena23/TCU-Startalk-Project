package edu.cs.tcu.tcustartalkproject.repository;

import edu.cs.tcu.tcustartalkproject.domain.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, String> {
}
