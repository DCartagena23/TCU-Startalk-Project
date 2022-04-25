package edu.cs.tcu.tcustartalkproject.Authentication.JWTRepos;

import edu.cs.tcu.tcustartalkproject.Authentication.JWTRepos.UserService;
import edu.cs.tcu.tcustartalkproject.Authentication.Mail.EmailService;
import edu.cs.tcu.tcustartalkproject.Authentication.Models.User;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import edu.cs.tcu.tcustartalkproject.Authentication.Mail.EmailServiceImpl;

@Controller
@RequestMapping("/users")
public class UserController {
    /**
     * Service for basic operations related to chapter: findAll(), findById(), delete(), save(), update()
     */
    private final UserService userService;
    /**
     * MongoDB template
     */
    private final MongoTemplate mongoTemplate;

    private final EmailServiceImpl emailServiceImpl;

    @Autowired
    public UserController(UserService userService, MongoTemplate mongoTemplate, EmailServiceImpl emailServiceImpl){
        this.userService = userService;
        this.mongoTemplate = mongoTemplate;
        this.emailServiceImpl = emailServiceImpl;
    }


    /**
     * Method to find one chapter.
     * @param id index of the chapter to be sought.
     * @return Result object that contains status code, message, and found chapter.
     */
    @GetMapping("/findOne/{id}")
    @ResponseBody
    public Result getUser(@PathVariable String id) {
        User user = userService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find User Success", user);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public Result findAllUser() {
        final List<User> users = userService.findAll();
        return new Result(StatusCode.SUCCESS, "Find All User Success", users);
    }
    /*
    @PostMapping("/forgot-password")
    public Result forgotPassword(@RequestParam String email) {

        String response = userService.forgotPassword(email);

        if (!response.startsWith("Invalid")) {
            response = "http://localhost:8081/reset-password?token=" + response;
            //send email with URL
            emailServiceImpl.sendTextEmail(response, email);
            return new Result(StatusCode.SUCCESS, "Email sent Success", response);
        }
        return new Result(StatusCode.FAILURE, "Email Invalid", response);
    }

    @PutMapping("/reset-password")
    public Result resetPassword(@RequestParam String token,
                                @RequestParam String password) {
        String response = userService.resetPassword(token, password);
        if (!response.startsWith("Invalid") || !response.startsWith("Expired")){
            return new Result(StatusCode.SUCCESS, "Email reset Success", response);
        }
        return new Result(StatusCode.FAILURE, "Email reset failed", response);
    }
     */
}