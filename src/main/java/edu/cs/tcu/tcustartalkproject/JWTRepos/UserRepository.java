package edu.cs.tcu.tcustartalkproject.JWTRepos;

import java.util.Optional;

import edu.cs.tcu.tcustartalkproject.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}