package com.taskagile.app.domain.application.commands;

import com.taskagile.app.domain.model.team.TeamId;
import com.taskagile.app.domain.model.user.UserId;

public class CreateBoardCommand {
  private UserId userId;
  private TeamId teamId;
  private String name;
  private String description;

  public CreateBoardCommand(UserId userId, TeamId temId, String name, String description) {
    this.userId = userId;
    this.teamId = temId;
    this.name = name;
    this.description = description;
  }

  /**
   * @return the teamId
   */
  public TeamId getTeamId() {
    return teamId;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @return the userId
   */
  public UserId getUserId() {
    return userId;
  }
}
