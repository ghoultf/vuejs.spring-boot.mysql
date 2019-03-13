package com.taskagile.app.domain.application;

import java.util.List;

import com.taskagile.app.domain.application.commands.CreateBoardCommand;
import com.taskagile.app.domain.model.board.Board;
import com.taskagile.app.domain.model.user.UserId;

public interface BoardService {

  /**
   * Find the boards that a user is a member, including those boards the user
   * created as well as joined.
   *
   * @param userId the id of the user
   * @return a list of boards or an empty list if none found
   */
  List<Board> findBoardsByMembership(UserId userId);

  /**
   * Create a new board
   *
   * @param command the command instance
   * @return the new board just created
   */
  Board createBoard(CreateBoardCommand command);
}
