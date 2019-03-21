package com.taskagile.app.domain.application;

import java.util.List;

import com.taskagile.app.domain.application.commands.CreateTeamCommand;
import com.taskagile.app.domain.model.team.Team;
import com.taskagile.app.domain.model.team.TeamId;
import com.taskagile.app.domain.model.user.UserId;

public interface TeamService {

  /**
   * Find the teams that created by a user
   *
   * @param userId the id of the user
   * @return a list of teams or an empty list if none found
   */
  List<Team> findTeamsByUserId(UserId userId);

  /**
   * Find team by its id
   *
   * @param teamId the id of team
   * @return the team instanse, null if not found
   */
  Team findById(TeamId teamId);

  /**
   * Create a new team
   *
   * @param command the command instance
   * @return the newly created team
   */
  Team createTeam(CreateTeamCommand command);
}
