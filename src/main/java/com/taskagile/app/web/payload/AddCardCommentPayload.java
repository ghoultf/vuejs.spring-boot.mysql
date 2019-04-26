package com.taskagile.app.web.payload;

import com.taskagile.app.domain.application.commands.AddCardCommentCommand;
import com.taskagile.app.domain.model.card.CardId;
import com.taskagile.app.domain.model.user.UserId;

public class AddCardCommentPayload {

  private String comment;

  public AddCardCommentCommand toCommand(CardId cardId, UserId userId) {
    return new AddCardCommentCommand(cardId, comment, userId);
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
