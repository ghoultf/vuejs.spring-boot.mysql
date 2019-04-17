package com.taskagile.app.web.apis;

import com.taskagile.app.domain.application.BoardService;
import com.taskagile.app.domain.application.TeamService;
import com.taskagile.app.domain.application.UserService;
import com.taskagile.app.domain.common.security.CurrentUser;
import com.taskagile.app.domain.common.security.TokenManager;
import com.taskagile.app.domain.model.board.Board;
import com.taskagile.app.domain.model.team.Team;
import com.taskagile.app.domain.model.user.SimpleUser;
import com.taskagile.app.domain.model.user.User;
import com.taskagile.app.web.results.ApiResult;
import com.taskagile.app.web.results.GetMyDataResult;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MeApiController {

  private String realTimeServerUrl;
  private TeamService teamService;
  private BoardService boardService;
  private UserService userService;
  private TokenManager tokenManager;

  public MeApiController(@Value("${app.real-time-server-url}") String realTimeServerUrl, TeamService teamService,
      BoardService boardService, UserService userService, TokenManager tokenManager) {
    this.realTimeServerUrl = realTimeServerUrl;
    this.teamService = teamService;
    this.boardService = boardService;
    this.userService = userService;
    this.tokenManager = tokenManager;
  }

  @GetMapping("/api/me")
  public ResponseEntity<ApiResult> getMyData(@CurrentUser SimpleUser currentUser) {
    User user = userService.findById(currentUser.getUserId());
    List<Team> teams = teamService.findTeamsByUserId(currentUser.getUserId());
    List<Board> boards = boardService.findBoardsByMembership(currentUser.getUserId());
    String realTimeToken = tokenManager.jwt(user.getId());
    return GetMyDataResult.build(user, teams, boards, realTimeServerUrl, realTimeToken);
  }
}
