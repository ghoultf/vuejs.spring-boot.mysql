package com.taskagile.app.web.results;

import com.taskagile.app.domain.model.card.Card;

import org.springframework.http.ResponseEntity;

public class CardResult {

  public static ResponseEntity<ApiResult> build(Card card) {
    ApiResult apiResult = ApiResult.blank().add("id", card.getId().value()).add("title", card.getTitle())
        .add("boardId", card.getBoardId().value()).add("description", card.getDescription())
        .add("cardListId", card.getCardListId().value());
    return Result.ok(apiResult);
  }
}
