package com.taskagile.app.domain.application.commands;

import com.taskagile.app.domain.model.board.BoardId;
import com.taskagile.app.domain.model.card.CardPosition;

import java.util.List;

public class ChangeCardPositionsCommand {

  private BoardId boardId;
  private List<CardPosition> cardPositions;

  public ChangeCardPositionsCommand(BoardId boardId, List<CardPosition> cardPositions) {
    this.boardId = boardId;
    this.cardPositions = cardPositions;
  }

  public BoardId getBoardId() {
    return boardId;
  }

  public List<CardPosition> getCardPositions() {
    return cardPositions;
  }
}
