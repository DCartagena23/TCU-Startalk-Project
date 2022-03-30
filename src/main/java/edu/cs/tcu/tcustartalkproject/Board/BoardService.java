package edu.cs.tcu.tcustartalkproject.Board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    public Board findById(final String id){
        return boardRepository.findById(id).get();
    }

    public void delete(String id){
        boardRepository.deleteById(id);
    }

    public Board save(Board board){
        return boardRepository.save(board);
    }

    public Board update(Board board){
        return boardRepository.save(board);
    }
}
