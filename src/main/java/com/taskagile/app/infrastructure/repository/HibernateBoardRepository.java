package com.taskagile.app.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.taskagile.app.domain.model.board.Board;
import com.taskagile.app.domain.model.board.BoardRepository;
import com.taskagile.app.domain.model.team.TeamId;

import org.springframework.stereotype.Repository;

@Repository
public class HibernateBoardRepository extends HibernateSupport<Board> implements BoardRepository {

  HibernateBoardRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public List<Board> findBoardsByTeamId(TeamId teamId) {
    return null;
  }

}
