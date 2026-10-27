package com.mycompany.demo.database.controller;

import com.mycompany.demo.database.dao.BoardDao;
import com.mycompany.demo.database.dto.Pager;
import com.mycompany.demo.database.entity.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/database/board")
@RequiredArgsConstructor
@Slf4j
public class DatabaseBoardController {
    //boardDao 주입
    private final BoardDao boardDao;

    // DTO 객체로 감싸지 않은 상태로 전개
    @GetMapping("/get-board")
    public Board getBoard(@RequestParam("boardId") long boardId) {
        return boardDao.selectById(boardId);
    }

    @PostMapping("/write-board")
    public Board writeBoard(@RequestBody Board board) {
        boardDao.insert(board);
        return boardDao.selectById(board.getBoardId());
    }

    @PutMapping("update-board")
    public Board updateBoard(@RequestBody Board board) {
        // DB에서 가져오기
        Board postBoard = boardDao.selectById(board.getBoardId());
        postBoard.setTitle(board.getTitle());
        postBoard.setContent(board.getContent());

        boardDao.update(postBoard);
        return boardDao.selectById(postBoard.getBoardId());
    }

    @DeleteMapping("delete-board")
    public String deleteBoard(@RequestParam("boardId") long boardId) {
        int rows = boardDao.deleteById(boardId);
        if(rows > 0) {
            return "boardId: " + boardId + "가 삭제 되었습니다. 삭제된 행 수: " + rows;
        } else {
            return "boardId: " + boardId + "가 존재하지 않습니다.";
        }
    }

    @GetMapping("list-board")
    public Map<String, Object> listBoard(@RequestParam("pageNo") int pageNo) {
        // 예제는 content 찾기로 해보기로 한다
        Map<String, Object> where = Map.of("content", "product10");

        int totalRows = boardDao.selectCount(where);
        Pager pager = new Pager(10, 10, totalRows, pageNo);
        List<Board> list = boardDao.selectPage(pager, where);

        return Map.of(
                "pager", pager,
                "data", list
        );
    }
}
