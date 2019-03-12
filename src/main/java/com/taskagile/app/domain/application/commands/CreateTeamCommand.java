package com.taskagile.app.domain.application.commands;

import com.taskagile.app.domain.model.user.UserId;

public class CreateTeamCommand {
  private String name;
  private UserId userId;

  public CreateTeamCommand(UserId userId, String name) {
    this.userId = userId;
    this.name = name;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the userId
   */
  public UserId getUserId() {
    return userId;
  }

}
