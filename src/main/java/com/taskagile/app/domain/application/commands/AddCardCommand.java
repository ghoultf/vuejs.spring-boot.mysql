package com.taskagile.app.domain.application.commands;

import com.taskagile.app.domain.model.board.BoardId;
import com.taskagile.app.domain.model.cardlist.CardListId;
import com.taskagile.app.domain.model.user.UserId;

public class AddCardCommand {
  private BoardId boardId;
  private CardListId cardListId;
  private UserId userId;
  private String title;
  private int position;

  public AddCardCommand(BoardId boardId, CardListId cardListId, UserId userId, String title, int position) {
    this.boardId = boardId;
    this.cardListId = cardListId;
    this.userId = userId;
    this.title = title;
    this.position = position;
  }

  public BoardId getBoardId() {
    return boardId;
  }

  public CardListId getCardListId() {
    return cardListId;
  }

  public UserId getUserId() {
    return userId;
  }

  public String getTitle() {
    return title;
  }

  public int getPosition() {
    return position;
  }
}
