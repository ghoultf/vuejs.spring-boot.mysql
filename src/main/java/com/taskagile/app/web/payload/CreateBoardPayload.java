package com.taskagile.app.web.payload;

import com.taskagile.app.domain.application.commands.CreateBoardCommand;
import com.taskagile.app.domain.model.team.TeamId;
import com.taskagile.app.domain.model.user.UserId;

public class CreateBoardPayload {
  private long teamId;
  private String name;
  private String description;

  public CreateBoardCommand toCommand(UserId userId) {
    return new CreateBoardCommand(userId, new TeamId(teamId), name, description);
  }

  /**
   * @param teamId the teamId to set
   */
  public void setTeamId(long teamId) {
    this.teamId = teamId;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

}
