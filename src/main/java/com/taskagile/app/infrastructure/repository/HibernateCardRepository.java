package com.taskagile.app.infrastructure.repository;

import com.taskagile.app.domain.model.board.BoardId;
import com.taskagile.app.domain.model.card.Card;
import com.taskagile.app.domain.model.card.CardPosition;
import com.taskagile.app.domain.model.card.CardRepository;
import org.hibernate.query.NativeQuery;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HibernateCardRepository extends HibernateSupport<Card> implements CardRepository {

  private JdbcTemplate jdbcTemplate;

  HibernateCardRepository(EntityManager entityManager, JdbcTemplate jdbcTemplate) {
    super(entityManager);
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Card> findByBoardId(BoardId boardId) {
    String sql = "SELECT c.* FROM card c LEFT JOIN card_list cl ON c.card_list_id = cl.id WHERE cl.board_id = :boardId";
    NativeQuery<Card> query = getSession().createNativeQuery(sql, Card.class);
    query.setParameter("boardId", boardId.value());
    return query.list();
  }

  @Override
  public void changePositions(final List<CardPosition> cardPositions) {
    String sql = " update card set card_list_id = ? , `position` = ? where id = ?";

    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement ps, int i) throws SQLException {
        CardPosition cardPosition = cardPositions.get(i);
        ps.setLong(1, cardPosition.getCardListId().value());
        ps.setInt(2, cardPosition.getPosition());
        ps.setLong(3, cardPosition.getCardId().value());
      }

      @Override
      public int getBatchSize() {
        return cardPositions.size();
      }
    });
  }
}
