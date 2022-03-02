package edu.cs.tcu.tcustartalkproject.Course;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.cs.tcu.tcustartalkproject.Authentication.Models.User;
import edu.cs.tcu.tcustartalkproject.Book.Book;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWord;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Document(collection= "Course")
public class Course implements Serializable {
    @Id
    private String id;

    private String title = null;
    private boolean active;

    @DBRef(lazy = true)
    @JsonIgnoreProperties({"courseCreate","courseJoin","target"})
    private User teacher;


    @DBRef(lazy = true)
    @JsonIgnoreProperties({"courseCreate","courseJoin","target"})
    private List<User> students = new ArrayList<User>();

    @DBRef(lazy = true)
    @JsonIgnoreProperties({"course"})
    private List<Book> books = new ArrayList<Book>();

    public Course() {
    }

    public Course(String id, String title) {
        this.id = id;
        this.title = title;
        this.active = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getTeacher() {
        return teacher;
    }


    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }
    public void addStudent(User student) {this.students.add(student);}

    public void setActive(boolean active) { this.active = active; }
    public boolean getActive() { return active; }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public void addBook(Book book) {
        this.books.add(book);
        book.setCourse(this);
    }
}
