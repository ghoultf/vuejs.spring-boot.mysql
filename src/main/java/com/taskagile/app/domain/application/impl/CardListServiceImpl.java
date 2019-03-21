package com.taskagile.app.domain.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.taskagile.app.domain.application.CardListService;
import com.taskagile.app.domain.common.event.DomainEventPublisher;
import com.taskagile.app.domain.model.board.BoardId;
import com.taskagile.app.domain.model.cardlist.CardList;
import com.taskagile.app.domain.model.cardlist.CardListRepository;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class CardListServiceImpl implements CardListService {

  private CardListRepository cardListRepository;
  private DomainEventPublisher domainEventPublisher;

  public CardListServiceImpl(CardListRepository cardListRepository, DomainEventPublisher domainEventPublisher) {
    this.cardListRepository = cardListRepository;
    this.domainEventPublisher = domainEventPublisher;
  }

  @Override
  public List<CardList> findByBoardId(BoardId boardId) {
    return cardListRepository.findByBoardId(boardId);
  }

}
