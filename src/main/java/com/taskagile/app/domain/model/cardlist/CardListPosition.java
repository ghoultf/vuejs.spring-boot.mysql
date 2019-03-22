package com.taskagile.app.domain.model.cardlist;

public class CardListPosition {
  private long cardListId;
  private int position;

  public CardListId getCardListId() {
    return new CardListId(cardListId);
  }

  public void setCardListId(long cardId) {
    this.cardListId = cardId;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

}
