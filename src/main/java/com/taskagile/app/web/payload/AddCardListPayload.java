package com.taskagile.app.web.payload;

import com.taskagile.app.domain.application.commands.AddCardListCommand;
import com.taskagile.app.domain.model.board.BoardId;
import com.taskagile.app.domain.model.user.UserId;

public class AddCardListPayload {
  private long boardId;
  private String name;
  private int position;

  public AddCardListCommand toCommand(UserId userId) {
    return new AddCardListCommand(new BoardId(boardId), userId, name, position);
  }

  public void setBoardId(long boardId) {
    this.boardId = boardId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPosition(int position) {
    this.position = position;
  }

}
