package edu.cs.tcu.tcustartalkproject.Forum;

import edu.cs.tcu.tcustartalkproject.Board.Board;
import edu.cs.tcu.tcustartalkproject.Board.BoardService;
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
    private final BoardService boardService;

    @Autowired
    public ForumController(BoardService boardService, ForumService forumService){
        this.forumService = forumService;
        this.boardService = boardService;
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

    @PostMapping("/saveForum/{boardId}")
    @ResponseBody
    public Result saveForum(@PathVariable String boardId, @RequestBody Forum forum) {
        Board board = boardService.findById(boardId);
        board.addForum(forum);
        boardService.save(board);
        forumService.save(forum);
        return new Result(StatusCode.SUCCESS, "Forum Saved!", forum);
    }

    @PutMapping("/updateForum")
    @ResponseBody
    public Result updateForum(@RequestBody Forum forum) {
        forumService.update(forum);
        return new Result(StatusCode.SUCCESS, "Forum Updated!", forum);
    }

    @DeleteMapping("/deleteForum/{id}")
    @ResponseBody
    public Result deleteForum(@PathVariable String id){
        forumService.delete(id);
        return new Result(StatusCode.SUCCESS, "Forum Deleted!", null);
    }
}
