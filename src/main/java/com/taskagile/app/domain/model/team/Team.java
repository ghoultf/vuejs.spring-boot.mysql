package com.taskagile.app.domain.model.team;

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
import com.taskagile.app.domain.model.user.UserId;

@Entity
@Table(name = "team")
public class Team extends AbstractBaseEntity {

  private static final long serialVersionUID = 136731809946334126L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false, length = 128)
  private String name;

  @Column(name = "user_id")
  private long userId;

  @Column(name = "archived")
  private boolean archived;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_date", nullable = false)
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
   * @return the userId
   */
  public long getUserId() {
    return userId;
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

  /**
   * Create new team
   */
  public static Team create(String name, UserId creatorId) {
    Team team = new Team();
    team.name = name;
    team.archived = false;
    team.userId = creatorId.value();
    team.createdDate = new Date();
    return team;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Team)) {
      return false;
    }
    Team team = (Team) obj;
    return userId == team.userId && Objects.equals(name, team.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, userId);
  }

  @Override
  public String toString() {
    return "Team{" + "id=" + id + ", name='" + name + '\'' + ", userId=" + userId + ", archived=" + archived
        + ", createdDate=" + createdDate + '}';
  }
}
