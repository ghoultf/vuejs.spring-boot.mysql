package com.taskagile.app.domain.application;

import java.util.List;

import com.taskagile.app.domain.application.commands.CreateBoardCommand;
import com.taskagile.app.domain.model.board.Board;
import com.taskagile.app.domain.model.team.TeamId;

public interface BoardService {
  List<Board> findBoardsByTeamId(TeamId teamId);

  Board createBoard(CreateBoardCommand command);
}
