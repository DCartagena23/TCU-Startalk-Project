package edu.cs.tcu.tcustartalkproject.Password.Controllers;

import edu.cs.tcu.tcustartalkproject.Authentication.Mail.EmailServiceImpl;
import edu.cs.tcu.tcustartalkproject.Authentication.Models.User;
import edu.cs.tcu.tcustartalkproject.Password.Services.PasswordService;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping()
public class PasswordController {

    private final PasswordService passwordService;
    private final EmailServiceImpl emailServiceImpl;

    public PasswordController(PasswordService passwordService, EmailServiceImpl emailServiceImpl) {
        this.passwordService = passwordService;
        this.emailServiceImpl = emailServiceImpl;
    }

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/forgot-password")
    @ResponseBody
    public Result forgotPassword(@RequestParam String email) {

        String token = passwordService.forgotPassword(email);
        String response;
        if (!token.startsWith("Invalid")) {
            response = "http://localhost:8081/reset-password?token=" + token;
            //send email with URL
            emailServiceImpl.sendTextEmail(response, email);
            return new Result(StatusCode.SUCCESS, "Email sent Success", response);
        }
        //go back to sign in page
        response = "http://localhost:8081/signin";
        return new Result(StatusCode.FAILURE, "Email Invalid", response);
    }

    //check if  token is valid and send to updatePassword page where they enter new password
    @GetMapping("/reset-password")
    @ResponseBody
    public Result resetPassword(@RequestParam String token) {

        String response = passwordService.resetPassword(token);
        System.out.println(response);
        if (response.startsWith("Invalid")){
            return new Result(StatusCode.FAILURE, "User find by token failed.", response);
        }
        else if (response.startsWith("Expired")){
            return new Result(StatusCode.FAILURE, "User find by token failed.", response);
        }
        System.out.println("Success in getting through to return statement in reset-password");
        return new Result(StatusCode.SUCCESS, "User find by token success.", response);
    }

    @PostMapping("/savePassword")
    @ResponseBody
    public Result updatePassword(@RequestParam String token, @RequestParam String password){
        String result = passwordService.resetPassword(token);
        if (!result.startsWith("Invalid") || !result.startsWith("Expired")){
            Optional<User> userOptional = Optional.ofNullable(passwordService.findByToken(token));
            if(userOptional.isPresent()) {
                User user = userOptional.get();
                user.setPassword(encoder.encode(password));
                passwordService.Save(user);
            }
            return new Result(StatusCode.SUCCESS, "Update password success.", result);
        }
        return new Result(StatusCode.FAILURE, "Update password failed", result);
    }
}