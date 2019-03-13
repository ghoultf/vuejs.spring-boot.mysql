package com.taskagile.app.domain.model.board;

import java.util.List;

import com.taskagile.app.domain.model.user.UserId;

public interface BoardRepository {

  /**
   * Find the boards that a user is a member, including those boards the user
   * created as well as joined.
   *
   * @param userId the id of the user
   * @return a list of boards or an empty list if none found
   */
  List<Board> findBoardsByMembership(UserId userId);

  /**
   * Save a board
   *
   * @param board the board to save
   */
  void save(Board board);
}
