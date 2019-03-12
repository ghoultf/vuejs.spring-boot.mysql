package com.taskagile.app.domain.model.board;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.taskagile.app.domain.common.model.AbstractBaseEntity;
import com.taskagile.app.domain.model.team.TeamId;
import com.taskagile.app.domain.model.user.UserId;

@Entity
@Table(name = "board")
public class Board extends AbstractBaseEntity {

  private static final long serialVersionUID = 5943180400146382234L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false, length = 128)
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "team_id")
  private Long teamId;

  @Column(name = "archived")
  private boolean archived;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_date")
  private Date createdDate;

  /**
   * @return the id
   */
  public Long getId() {
    return id;
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
  public Long getUserId() {
    return userId;
  }

  /**
   * @return the teamId
   */
  public Long getTeamId() {
    return teamId;
  }

  /**
   * @return the archived
   */
  public boolean isArchived() {
    return archived;
  }

  /**
   * @return the createdDate
   */
  public Date getCreatedDate() {
    return createdDate;
  }

  public static Board create(UserId userId, String name, String description, TeamId teamId) {
    Board board = new Board();
    board.name = name;
    board.description = description;
    board.userId = userId.value();
    board.teamId = teamId.isValid() ? teamId.value() : null;
    board.archived = false;
    board.createdDate = new Date();
    return board;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Board)) {
      return false;
    }
    Board board = (Board) obj;
    return userId == board.userId && teamId == board.teamId && Objects.equals(name, board.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.userId, this.teamId, this.name);
  }

  @Override
  public String toString() {
    return "Board{" + "id=" + id + ", name='" + name + "', description='" + description + "', userId=" + userId
        + ", teamId=" + teamId + ", archived=" + archived + ", createdDate='" + createdDate + "'}";
  }
}
