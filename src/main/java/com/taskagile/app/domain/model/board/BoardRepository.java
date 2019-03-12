package com.taskagile.app.domain.model.board;

import java.util.List;

import com.taskagile.app.domain.model.team.TeamId;

public interface BoardRepository {
  /**
   * Find the boards that blong to a team
   *
   * @param teamId the id of the team
   * @return the list of boards or an empty list if none found
   */
  List<Board> findBoardsByTeamId(TeamId teamId);

  /**
   * Save a board
   *
   * @param board the board to store
   */
  void save(Board board);
}
