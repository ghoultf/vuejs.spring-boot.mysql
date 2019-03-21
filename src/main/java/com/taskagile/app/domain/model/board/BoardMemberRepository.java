package com.taskagile.app.domain.model.board;

import java.util.List;

import com.taskagile.app.domain.model.user.User;

public interface BoardMemberRepository {

  /**
   * Find members of a board
   *
   * @param boardId the id of the board
   * @return a list of board members
   */
  List<User> findMembers(BoardId boardId);

  /**
   * Save board member
   *
   * @param boardMember the board member to save
   */
  void save(BoardMember boardMember);
}
