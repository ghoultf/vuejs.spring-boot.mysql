package com.taskagile.app.web.payload;

import com.taskagile.app.domain.application.commands.ChangeCardTitleCommand;
import com.taskagile.app.domain.model.card.CardId;

public class ChangeCardTitlePayload {

  private String title;

  public ChangeCardTitleCommand toCommand(long cardId) {
    return new ChangeCardTitleCommand(new CardId(cardId), title);
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
