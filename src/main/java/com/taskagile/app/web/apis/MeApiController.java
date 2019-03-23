package com.taskagile.app.web.apis;

import com.taskagile.app.domain.application.BoardService;
import com.taskagile.app.domain.application.TeamService;
import com.taskagile.app.domain.application.UserService;
import com.taskagile.app.domain.common.security.CurrentUser;
import com.taskagile.app.domain.model.board.Board;
import com.taskagile.app.domain.model.team.Team;
import com.taskagile.app.domain.model.user.SimpleUser;
import com.taskagile.app.domain.model.user.User;
import com.taskagile.app.web.results.ApiResult;
import com.taskagile.app.web.results.GetMyDataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MeApiController {

  private TeamService teamService;
  private BoardService boardService;
  private UserService userService;

  public MeApiController(TeamService teamService, BoardService boardService, UserService userService) {
    this.teamService = teamService;
    this.boardService = boardService;
    this.userService = userService;
  }

  @GetMapping("/api/me")
  public ResponseEntity<ApiResult> getMyData(@CurrentUser SimpleUser currentUser) {
    User user = userService.findById(currentUser.getUserId());
    List<Team> teams = teamService.findTeamsByUserId(currentUser.getUserId());
    List<Board> boards = boardService.findBoardsByMembership(currentUser.getUserId());
    return GetMyDataResult.build(user, teams, boards);
  }
}
