package com.taskagile.app.domain.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.taskagile.app.domain.application.CardService;
import com.taskagile.app.domain.common.event.DomainEventPublisher;
import com.taskagile.app.domain.model.board.BoardId;
import com.taskagile.app.domain.model.card.Card;
import com.taskagile.app.domain.model.card.CardRepository;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class CardServiceImpl implements CardService {

  private CardRepository cardRepository;
  private DomainEventPublisher domainEventPublisher;

  public CardServiceImpl(CardRepository cardRepository, DomainEventPublisher domainEventPublisher) {
    this.cardRepository = cardRepository;
    this.domainEventPublisher = domainEventPublisher;
  }

  @Override
  public List<Card> findByBoardId(BoardId boardId) {
    return cardRepository.findByBoardId(boardId);
  }

}
