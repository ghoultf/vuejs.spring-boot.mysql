package com.taskagile.app.domain.application;

import java.util.List;

import com.taskagile.app.domain.application.commands.AddCardCommand;
import com.taskagile.app.domain.application.commands.ChangeCardPositionsCommand;
import com.taskagile.app.domain.model.board.BoardId;
import com.taskagile.app.domain.model.card.Card;

public interface CardService {

  /**
   * Find all the cards of a board
   *
   * @param boardId the id of the board
   * @return a list of card instanses or an empty list if none found
   */
  List<Card> findByBoardId(BoardId boardId);

  /**
   * Add card
   *
   * @param command the command instance
   * @return the newly added card
   */
  Card addCard(AddCardCommand command);

  /**
   * Change card positions
   *
   * @param command the command instance
   */
  void changePositions(ChangeCardPositionsCommand command);
}
