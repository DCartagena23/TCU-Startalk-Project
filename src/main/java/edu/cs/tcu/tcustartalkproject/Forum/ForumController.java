package edu.cs.tcu.tcustartalkproject.Forum;

import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/forums")
public class ForumController {
    private final ForumService forumService;

    @Autowired
    public ForumController(ForumService forumService){
        this.forumService = forumService;
    }

    @GetMapping("/findAll")
    @ResponseBody
    public Result findAllForums(){
        List<Forum> forums = forumService.findAll();
        return new Result(StatusCode.SUCCESS, "Find All Forum Success", forums);
    }

    @GetMapping("/findOne/{id}")
    @ResponseBody
    public Result getForum(@PathVariable String id) {
        Forum forum = forumService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find Forum Success", forum);
    }

    @PostMapping("/saveForum")
    @ResponseBody
    public Result saveForum(@RequestBody Forum forum) {
        Forum savedForum = forumService.save(forum);
        return new Result(StatusCode.SUCCESS, "Forum Saved!", savedForum);
    }

    @PutMapping("/updateForum")
    @ResponseBody
    public Result updateForum(@RequestBody Forum forum) {
        Forum updateForum = forumService.update(forum);
        return new Result(StatusCode.SUCCESS, "Forum Updated!", updateForum);
    }

    @DeleteMapping("/deleteForum/{id}")
    @ResponseBody
    public Result deleteForum(@PathVariable String id){
        forumService.delete(id);
        return new Result(StatusCode.SUCCESS, "Forum Deleted!", null);
    }
}
