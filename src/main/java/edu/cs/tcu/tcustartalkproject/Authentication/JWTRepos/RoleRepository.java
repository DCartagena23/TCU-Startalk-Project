package edu.cs.tcu.tcustartalkproject.Authentication.JWTRepos;

import edu.cs.tcu.tcustartalkproject.Authentication.Models.ERole;
import edu.cs.tcu.tcustartalkproject.Authentication.Models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}