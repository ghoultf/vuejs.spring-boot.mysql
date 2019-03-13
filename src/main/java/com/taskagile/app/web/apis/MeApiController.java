package com.taskagile.app.web.apis;

import java.util.List;

import com.taskagile.app.domain.application.BoardService;
import com.taskagile.app.domain.application.TeamService;
import com.taskagile.app.domain.common.event.DomainEventPublisher;
import com.taskagile.app.domain.common.security.CurrentUser;
import com.taskagile.app.domain.model.board.Board;
import com.taskagile.app.domain.model.team.Team;
import com.taskagile.app.domain.model.user.SimpleUser;
import com.taskagile.app.web.results.ApiResult;
import com.taskagile.app.web.results.GetMyDataResult;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MeApiController {

  private TeamService teamService;
  private BoardService boardService;

  public MeApiController(TeamService teamService, BoardService boardService) {
    this.teamService = teamService;
    this.boardService = boardService;
  }

  @GetMapping("/api/me")
  public ResponseEntity<ApiResult> getMyData(@CurrentUser SimpleUser currentUser) {
    List<Team> teams = teamService.findTeamsByUserId(currentUser.getUserId());
    List<Board> boards = boardService.findBoardsByMembership(currentUser.getUserId());
    return GetMyDataResult.build(currentUser, teams, boards);
  }
}
