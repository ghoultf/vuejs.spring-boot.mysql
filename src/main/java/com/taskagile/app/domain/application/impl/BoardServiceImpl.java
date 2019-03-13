package com.taskagile.app.domain.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.taskagile.app.domain.application.BoardService;
import com.taskagile.app.domain.application.commands.CreateBoardCommand;
import com.taskagile.app.domain.common.event.DomainEventPublisher;
import com.taskagile.app.domain.model.board.Board;
import com.taskagile.app.domain.model.board.BoardManagement;
import com.taskagile.app.domain.model.board.BoardRepository;
import com.taskagile.app.domain.model.board.events.BoardCreatedEvent;
import com.taskagile.app.domain.model.user.UserId;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

  private BoardRepository boardRepository;
  private BoardManagement boardManagement;
  private DomainEventPublisher domainEventPublisher;

  public BoardServiceImpl(DomainEventPublisher domainEventPublisher, BoardRepository boardRepository,
      BoardManagement boardManagement) {
    this.boardRepository = boardRepository;
    this.boardManagement = boardManagement;
    this.domainEventPublisher = domainEventPublisher;
  }

  @Override
  public List<Board> findBoardsByMembership(UserId userId) {
    return boardRepository.findBoardsByMembership(userId);
  }

  @Override
  public Board createBoard(CreateBoardCommand command) {
    Board board = boardManagement.createBoard(command.getUserId(), command.getName(), command.getDescription(),
        command.getTeamId());
    domainEventPublisher.publish(new BoardCreatedEvent(this, board));
    return board;
  }

}
