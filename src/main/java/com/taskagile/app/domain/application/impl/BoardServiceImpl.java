package com.taskagile.app.domain.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.taskagile.app.domain.application.BoardService;
import com.taskagile.app.domain.application.commands.CreateBoardCommand;
import com.taskagile.app.domain.common.event.DomainEventPublisher;
import com.taskagile.app.domain.model.board.Board;
import com.taskagile.app.domain.model.board.BoardRepository;
import com.taskagile.app.domain.model.board.events.BoardCreatedEvent;
import com.taskagile.app.domain.model.team.TeamId;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

  private BoardRepository boardRepository;
  private DomainEventPublisher domainEventPublisher;

  public BoardServiceImpl(DomainEventPublisher domainEventPublisher, BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
    this.domainEventPublisher = domainEventPublisher;
  }

  @Override
  public List<Board> findBoardsByTeamId(TeamId teamId) {
    return boardRepository.findBoardsByTeamId(teamId);
  }

  @Override
  public Board createBoard(CreateBoardCommand command) {
    Board board = Board.create(command.getUserId(), command.getName(), command.getDescription(), command.getTeamId());
    boardRepository.save(board);
    domainEventPublisher.publish(new BoardCreatedEvent(this, board));
    return board;
  }

}
