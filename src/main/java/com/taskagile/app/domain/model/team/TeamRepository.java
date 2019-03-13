package com.taskagile.app.domain.model.team;

import java.util.List;

import com.taskagile.app.domain.model.user.UserId;

public interface TeamRepository {

  /**
   * Find the teams that created by a user
   *
   * @param userId the id of the user
   * @return a list of teams or an empty list if none found
   */
  List<Team> findTeamsByUserId(UserId userId);

  /**
   * Save a team
   *
   * @param team the team to save
   */
  void save(Team team);
}
