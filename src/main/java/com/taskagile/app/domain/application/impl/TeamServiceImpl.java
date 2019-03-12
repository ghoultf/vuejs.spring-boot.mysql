package com.taskagile.app.domain.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.taskagile.app.domain.application.TeamService;
import com.taskagile.app.domain.application.commands.CreateTeamCommand;
import com.taskagile.app.domain.common.event.DomainEventPublisher;
import com.taskagile.app.domain.model.team.Team;
import com.taskagile.app.domain.model.team.TeamRepository;
import com.taskagile.app.domain.model.team.events.TeamCreatedEvent;
import com.taskagile.app.domain.model.user.UserId;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

  private TeamRepository teamRepository;
  private DomainEventPublisher domainEventPublisher;

  public TeamServiceImpl(DomainEventPublisher domainEventPublisher, TeamRepository teamRepository) {
    this.domainEventPublisher = domainEventPublisher;
    this.teamRepository = teamRepository;
  }

  @Override
  public List<Team> findTeamsByUserId(UserId userId) {
    return teamRepository.findTeamsByUserId(userId);
  }

  @Override
  public Team createTeam(CreateTeamCommand command) {
    Team team = Team.creat(command.getName(), command.getUserId());
    teamRepository.save(team);
    domainEventPublisher.publish(new TeamCreatedEvent(this, team));
    return team;
  }

}
