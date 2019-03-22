package com.taskagile.app.domain.application;

import java.util.List;

import com.taskagile.app.domain.application.commands.AddCardListCommand;
import com.taskagile.app.domain.application.commands.ChangeCardListPositionsCommand;
import com.taskagile.app.domain.model.board.BoardId;
import com.taskagile.app.domain.model.cardlist.CardList;

public interface CardListService {

  /**
   * Find card lists of a board
   *
   * @param boardId the id of the board
   * @return a list of card list instance or an empty list if none found
   */
  List<CardList> findByBoardId(BoardId boardId);

  /**
   * Add card list
   *
   * @param command the command instance
   * @return the newly added card list
   */
  CardList addCardList(AddCardListCommand command);

  /**
   * Change card list positions
   *
   * @param command the command instance
   */
  void changePositions(ChangeCardListPositionsCommand command);
}
