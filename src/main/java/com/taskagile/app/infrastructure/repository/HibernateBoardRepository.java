package com.taskagile.app.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.taskagile.app.domain.model.board.Board;
import com.taskagile.app.domain.model.board.BoardRepository;
import com.taskagile.app.domain.model.user.UserId;

import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateBoardRepository extends HibernateSupport<Board> implements BoardRepository {

  HibernateBoardRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  public List<Board> findBoardsByMembership(UserId userId) {
    String sql = "SELECT b.* FROM board b LEFT JOIN board_member bm ON b.id = bm.board_id WHERE bm.user_id = :userId";
    NativeQuery<Board> query = getSession().createNativeQuery(sql, Board.class);
    query.setParameter("userId", userId.value());
    return query.list();
  }

}
