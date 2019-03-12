package com.taskagile.app.web.results;

import com.taskagile.app.domain.model.board.Board;

import org.springframework.http.ResponseEntity;

public class CreateBoardResult {
  public static ResponseEntity<ApiResult> build(Board board) {
    ApiResult apiResult = ApiResult.blank().add("teamId", board.getTeamId()).add("id", board.getId())
        .add("name", board.getName()).add("description", board.getDescription());
    return Result.ok(apiResult);
  }
}
