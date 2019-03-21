package com.taskagile.app.infrastructure.repository;

import com.taskagile.app.domain.model.board.BoardId;
import com.taskagile.app.domain.model.cardlist.CardList;
import com.taskagile.app.domain.model.cardlist.CardListRepository;
import org.hibernate.query.NativeQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernateCardListRepository extends HibernateSupport<CardList> implements CardListRepository {

  private JdbcTemplate jdbcTemplate;

  HibernateCardListRepository(EntityManager entityManager, JdbcTemplate jdbcTemplate) {
    super(entityManager);
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<CardList> findByBoardId(BoardId boardId) {
    String sql = "SELECT cl.* FROM card_list cl WHERE cl.board_id = :boardId";
    NativeQuery<CardList> query = getSession().createNativeQuery(sql, CardList.class);
    query.setParameter("boardId", boardId.value());
    return query.list();
  }
}
