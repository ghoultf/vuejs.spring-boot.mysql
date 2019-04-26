package com.taskagile.app.web.payload;

import com.taskagile.app.domain.application.commands.ChangeCardDescriptionCommand;
import com.taskagile.app.domain.model.card.CardId;

public class ChangeCardDescriptionPayload {

  private String description;

  public ChangeCardDescriptionCommand toCommand(long cardId) {
    return new ChangeCardDescriptionCommand(new CardId(cardId), description);
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
