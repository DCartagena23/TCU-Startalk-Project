package edu.cs.tcu.tcustartalkproject.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    /*
    * This is for the Home Screen and login page, available to public.
    * */
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }
    /*
     * This is for the student view, only available to students logged in.
     * */
    @GetMapping("/student")
    @PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER')")
    public String studentAccess() {
        return "Student Content.";
    }
    /*
     * This is for the teacher view, only available to teachers logged in, has access to whole site.
     * */
    @GetMapping("/teacher")
    @PreAuthorize("hasRole('TEACHER')")
    public String teacherAccess() {
        return "Teacher Board.";
    }
}