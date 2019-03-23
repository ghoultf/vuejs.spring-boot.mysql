package com.taskagile.app.web.apis;

import java.util.List;

import com.taskagile.app.domain.application.BoardService;
import com.taskagile.app.domain.application.CardListService;
import com.taskagile.app.domain.application.CardService;
import com.taskagile.app.domain.application.TeamService;
import com.taskagile.app.domain.common.security.CurrentUser;
import com.taskagile.app.domain.model.board.Board;
import com.taskagile.app.domain.model.board.BoardId;
import com.taskagile.app.domain.model.card.Card;
import com.taskagile.app.domain.model.cardlist.CardList;
import com.taskagile.app.domain.model.team.Team;
import com.taskagile.app.domain.model.user.SimpleUser;
import com.taskagile.app.domain.model.user.User;
import com.taskagile.app.domain.model.user.UserNotFoundException;
import com.taskagile.app.web.payload.AddBoardMemberPayload;
import com.taskagile.app.web.payload.CreateBoardPayload;
import com.taskagile.app.web.results.ApiResult;
import com.taskagile.app.web.results.BoardResult;
import com.taskagile.app.web.results.CreateBoardResult;
import com.taskagile.app.web.results.Result;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BoardApiController {

  private BoardService boardService;
  private TeamService teamService;
  private CardService cardService;
  private CardListService cardListService;

  public BoardApiController(BoardService boardService, TeamService teamService, CardService cardService,
      CardListService cardListService) {
    this.boardService = boardService;
    this.teamService = teamService;
    this.cardService = cardService;
    this.cardListService = cardListService;
  }

  @PostMapping("/api/boards")
  public ResponseEntity<ApiResult> createBoard(@RequestBody CreateBoardPayload payload,
      @CurrentUser SimpleUser currentUser) {
    Board board = boardService.createBoard(payload.toCommand(currentUser.getUserId()));
    return CreateBoardResult.build(board);
  }

  @GetMapping("/api/boards/{boardId}")
  public ResponseEntity<ApiResult> getBoard(@PathVariable("boardId") long rawBoardId) {
    BoardId boardId = new BoardId(rawBoardId);
    Board board = boardService.findById(boardId);
    if (board == null) {
      return Result.notFound();
    }

    List<User> members = boardService.findMembers(boardId);

    Team team = null;
    if (!board.isPersonal()) {
      team = teamService.findById(board.getTeamId());
    }

    List<CardList> cardLists = cardListService.findByBoardId(boardId);
    List<Card> cards = cardService.findByBoardId(boardId);
    return BoardResult.build(team, board, members, cardLists, cards);
  }

  @PostMapping("/api/boards/{boardId}/members")
  public ResponseEntity<ApiResult> addMember(@PathVariable("boardId") long rawBoardId,
      @RequestBody AddBoardMemberPayload payload) {
    BoardId boardId = new BoardId(rawBoardId);
    Board board = boardService.findById(boardId);
    if (board == null) {
      return Result.notFound();
    }

    try {
      User member = boardService.addMember(boardId, payload.getUsernameOrEmailAddress());

      ApiResult apiResult = ApiResult.blank().add("id", member.getId().value()).add("shortName", member.getInitials());
      return Result.ok(apiResult);
    } catch (UserNotFoundException e) {
      return Result.failure("No user found");
    }
  }
}
