package com.taskagile.app.infrastructure.repository;

import javax.persistence.EntityManager;

import com.taskagile.app.domain.model.board.BoardMember;
import com.taskagile.app.domain.model.board.BoardMemberRepository;

import org.springframework.stereotype.Repository;

@Repository
public class HibernateBoardMemberRepository extends HibernateSupport<BoardMember> implements BoardMemberRepository {
  public HibernateBoardMemberRepository(EntityManager entityManager) {
    super(entityManager);
  }
}
