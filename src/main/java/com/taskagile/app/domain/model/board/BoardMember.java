package com.taskagile.app.domain.model.board;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.taskagile.app.domain.common.model.AbstractBaseEntity;
import com.taskagile.app.domain.model.user.UserId;

@Entity
@Table(name="board_member")
public class BoardMember extends AbstractBaseEntity {

  private static final long serialVersionUID = 1101935717986500672L;

  @EmbeddedId
  private BoardMemberId id;

  /**
   * @return the id
   */
  public BoardMemberId getId() {
    return id;
  }

  public static BoardMember create(BoardId boardId, UserId userId) {
    BoardMember boardMember = new BoardMember();
    boardMember.id = new BoardMemberId(boardId, userId);
    return boardMember;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof BoardMember)) {
      return false;
    }
    BoardMember that = (BoardMember) obj;
    return Objects.equals(this.id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public String toString() {
    return "BoardMember{" + "id= " + id + "}";
  }

  @Embeddable
  public static class BoardMemberId implements Serializable {

    private static final long serialVersionUID = -5739169913659318896L;

    @Column(name = "board_id")
    private long boardId;

    @Column(name = "user_id")
    private long userId;

    public BoardMemberId() {

    }

    private BoardMemberId(BoardId boardId, UserId userId) {
      this.boardId = boardId.value();
      this.userId = userId.value();
    }

    /**
     * @return the boardId
     */
    public long getBoardId() {
      return boardId;
    }

    /**
     * @return the userId
     */
    public long getUserId() {
      return userId;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (!(obj instanceof BoardMemberId)) {
        return false;
      }
      BoardMemberId boardMemberId = (BoardMemberId) obj;
      return this.boardId == boardMemberId.boardId && this.userId == boardMemberId.userId;
    }

    @Override
    public int hashCode() {
      return Objects.hash(boardId, userId);
    }

    @Override
    public String toString() {
      return "BoardMemberId{" + ", boardId=" + boardId + "userId=" + userId + "}";
    }
  }
}
