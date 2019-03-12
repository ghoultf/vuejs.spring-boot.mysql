package com.taskagile.app.web.payload;

import com.taskagile.app.domain.application.commands.CreateTeamCommand;
import com.taskagile.app.domain.model.user.UserId;

public class CreateTeamPayload {
  private String name;

  public CreateTeamCommand toCommand(UserId userId) {
    return new CreateTeamCommand(userId, name);
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

}
