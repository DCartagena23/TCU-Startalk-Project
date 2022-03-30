package edu.cs.tcu.tcustartalkproject.Board;

import edu.cs.tcu.tcustartalkproject.utils.Result;
import edu.cs.tcu.tcustartalkproject.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {
        private final BoardService boardService;

        @Autowired
        public BoardController(BoardService boardService){
            this.boardService = boardService;
        }

        @GetMapping("/findAll")
        @ResponseBody
        public Result findAllForums(){
            List<Board> boards = boardService.findAll();
            return new Result(StatusCode.SUCCESS, "Find All Board Success", boards);
        }

        @GetMapping("/findOne/{id}")
        @ResponseBody
        public Result getBoard(@PathVariable String id) {
            Board board = boardService.findById(id);
            return new Result(StatusCode.SUCCESS, "Find Board Success", board);
        }

        @PostMapping("/saveBoard")
        @ResponseBody
        public Result saveBoard(@RequestBody Board board) {
            Board saveBoard = boardService.save(board);
            return new Result(StatusCode.SUCCESS, "Board Saved!", saveBoard);
        }

        @PutMapping("/updateBoard")
        @ResponseBody
        public Result updateBoard(@RequestBody Board board) {
            Board updateBoard = boardService.update(board);
            return new Result(StatusCode.SUCCESS, "Board Updated!", updateBoard);
        }

        @DeleteMapping("/deleteBoard/{id}")
        @ResponseBody
        public Result deleteBoard(@PathVariable String id){
            boardService.delete(id);
            return new Result(StatusCode.SUCCESS, "Board Deleted!", null);
        }
    }
