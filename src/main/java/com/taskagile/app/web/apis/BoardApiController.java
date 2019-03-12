package com.taskagile.app.web.apis;

import javax.validation.Valid;

import com.taskagile.app.domain.application.BoardService;
import com.taskagile.app.domain.common.security.CurrentUser;
import com.taskagile.app.domain.model.board.Board;
import com.taskagile.app.domain.model.user.SimpleUser;
import com.taskagile.app.web.payload.CreateBoardPayload;
import com.taskagile.app.web.results.ApiResult;
import com.taskagile.app.web.results.CreateBoardResult;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BoardApiController {
  private BoardService service;

  public BoardApiController(BoardService service) {
    this.service = service;
  }

  @PostMapping("/api/boards")
  public ResponseEntity<ApiResult> createBoard(@Valid @RequestBody CreateBoardPayload payload,
      @CurrentUser SimpleUser currentUser) {
    Board board = service.createBoard(payload.toCommand(currentUser.getUserId()));
    return CreateBoardResult.build(board);
  }
}
