package edu.cs.tcu.tcustartalkproject.Message;

import edu.cs.tcu.tcustartalkproject.Forum.Forum;
import edu.cs.tcu.tcustartalkproject.Forum.ForumService;
import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;
    private final ForumService forumService;

    @Autowired
    public MessageController(MessageService messageService, ForumService forumService){
        this.messageService = messageService;
        this.forumService = forumService;
    }

    @GetMapping("/findOne/{id}")
    @ResponseBody
    public Result getMessage(@PathVariable String id){
        Message message = messageService.findById(id);
        return new Result(StatusCode.SUCCESS, "Find Message Success", message);
    }

    @PostMapping("/saveMessage/{forumId}")
    @ResponseBody
    public Result saveMessage(@PathVariable String forumId, @RequestBody Message message) {
        Forum forum = forumService.findById(forumId);
        forum.addMessage(message);
        forumService.update(forum);
        messageService.save(message);
        return new Result(StatusCode.SUCCESS, "Message Saved!", message);
    }
}
