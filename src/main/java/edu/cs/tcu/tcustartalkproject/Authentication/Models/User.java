package edu.cs.tcu.tcustartalkproject.Authentication.Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.cs.tcu.tcustartalkproject.Chapter.Chapter;
import edu.cs.tcu.tcustartalkproject.Course.Course;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(max = 120)
    private String password;
    @DBRef
    private Set<Role> roles = new HashSet<>();

    @DBRef(lazy = true)
    @JsonIgnoreProperties({"teacher","students"})
    private List<Course> courseCreate = new ArrayList<Course>();



    @DBRef(lazy = true)
    @JsonIgnoreProperties({"teacher","students"})
    private List<Course> courseJoin = new ArrayList<Course>();

    public User() {
    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Course> getCourseCreate() {
        return courseCreate;
    }

    public void setCourseCreate(List<Course> courseCreate) {
        this.courseCreate = courseCreate;
    }

    public void createCourse(Course course){
        this.courseCreate.add(course);
        course.setTeacher(this);
    }

    public List<Course> getCourseJoin() {
        return courseJoin;
    }

    public void setCourseJoin(List<Course> courseJoin) {
        this.courseJoin = courseJoin;
    }

    public void joinCourse(Course course){
        this.courseJoin.add(course);
        course.addStudent(this);
    }

}