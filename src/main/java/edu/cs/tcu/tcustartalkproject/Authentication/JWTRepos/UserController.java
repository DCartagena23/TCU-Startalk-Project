package edu.cs.tcu.tcustartalkproject.Authentication.JWTRepos;

import edu.cs.tcu.tcustartalkproject.Authentication.JWTRepos.UserService;
import edu.cs.tcu.tcustartalkproject.Authentication.Models.User;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @Autowired
    public UserController(UserService userService, MongoTemplate mongoTemplate){
        this.userService = userService;
        this.mongoTemplate = mongoTemplate;
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
        return new Result(StatusCode.SUCCESS, "Find Course Success", user);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public Result findAllUser() {
        final List<User> users = userService.findAll();
        return new Result(StatusCode.SUCCESS, "Find All Course Success", users);
    }
}