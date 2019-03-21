package com.taskagile.app.domain.model.cardlist;

import java.util.List;

import com.taskagile.app.domain.model.board.BoardId;

public interface CardListRepository {

  /**
   * Find card lists of a board
   *
   * @param boardId the id of the board
   * @return a list of card lists of a board or an empty list if none found
   */
  List<CardList> findByBoardId(BoardId boardId);
}
