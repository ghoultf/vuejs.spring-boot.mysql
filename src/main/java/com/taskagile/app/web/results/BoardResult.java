package com.taskagile.app.web.results;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taskagile.app.domain.model.board.Board;
import com.taskagile.app.domain.model.card.Card;
import com.taskagile.app.domain.model.cardlist.CardList;
import com.taskagile.app.domain.model.cardlist.CardListId;
import com.taskagile.app.domain.model.team.Team;
import com.taskagile.app.domain.model.user.User;

import org.springframework.http.ResponseEntity;

public class BoardResult {
  public static ResponseEntity<ApiResult> build(Team team, Board board, List<User> members, List<CardList> cardLists,
      List<Card> cards) {
    Map<String, Object> boardData = new HashMap<>();
    boardData.put("id", board.getId().value());
    boardData.put("name", board.getName());
    boardData.put("personal", board.isPersonal());

    List<MemberData> membersDatas = new ArrayList<>();
    for (User user : members) {
      membersDatas.add(new MemberData(user));
    }

    List<CardListData> cardListsData = new ArrayList<>();
    Map<CardListId, List<Card>> cardsByList = new HashMap<>();
    for (Card card : cards) {
      cardsByList.computeIfAbsent(card.getCardListId(), k -> new ArrayList<>()).add(card);
    }
    for (CardList cardList : cardLists) {
      cardListsData.add(new CardListData(cardList, cardsByList.get(cardList.getId())));
    }

    ApiResult result = ApiResult.blank().add("board", boardData).add("members", membersDatas).add("cardLists",
        cardListsData);
    if (!board.isPersonal()) {
      Map<String, Object> teamData = new HashMap<>();
      teamData.put("name", team.getName());
      result.add("team", teamData);
    }
    return Result.ok(result);
  }

  private static class MemberData {
    private long id;
    private String shortName;

    MemberData(User user) {
      id = user.getId().value();
      shortName = user.getInitials();
    }

    /**
     * @return the id
     */
    public long getId() {
      return id;
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
      return shortName;
    }

  }

  private static class CardListData {
    private long id;
    private String name;
    private int position;
    private List<CardData> cards = new ArrayList<>();

    CardListData(CardList cardList, List<Card> cards) {
      this.id = cardList.getId().value();
      this.name = cardList.getName();
      this.position = cardList.getPosition();
      if (cards != null) {
        for (Card card : cards) {
          this.cards.add(new CardData(card));
        }
      }
    }

    /**
     * @return the id
     */
    public long getId() {
      return id;
    }

    /**
     * @return the name
     */
    public String getName() {
      return name;
    }

    /**
     * @return the position
     */
    public int getPosition() {
      return position;
    }

    /**
     * @return the cards
     */
    public List<CardData> getCards() {
      return cards;
    }
  }

  private static class CardData {
    private long id;
    private String title;
    private int position;

    CardData(Card card) {
      this.id = card.getId().value();
      this.title = card.getTitle();
      this.position = card.getPosition();
    }

    /**
     * @return the id
     */
    public long getId() {
      return id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
      return title;
    }

    /**
     * @return the position
     */
    public int getPosition() {
      return this.position;
    }
  }
}
