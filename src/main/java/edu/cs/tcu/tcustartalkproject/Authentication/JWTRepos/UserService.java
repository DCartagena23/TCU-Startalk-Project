package edu.cs.tcu.tcustartalkproject.Authentication.JWTRepos;

import edu.cs.tcu.tcustartalkproject.Authentication.Models.User;
import edu.cs.tcu.tcustartalkproject.Course.Course;
import edu.cs.tcu.tcustartalkproject.Course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    /**
     * Repository for basic operations: findAll(), findById(), delete(), save(), update()
     */
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method to query all chapters in a book.
     * @return List of found chapters.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Method to query one chapter by index.
     * @param id index of the chapter to be sought.
     * @return Chapter to be sought.
     */
    public User findById(final String id) {
        return userRepository.findById(id).get();
    }

    /**
     * Method to delete one chapter by index.
     * @param id index of the chapter to be deleted.
     */
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }
}
