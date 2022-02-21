package edu.cs.tcu.tcustartalkproject.JWTRepos;

import edu.cs.tcu.tcustartalkproject.Models.ERole;
import edu.cs.tcu.tcustartalkproject.Models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}