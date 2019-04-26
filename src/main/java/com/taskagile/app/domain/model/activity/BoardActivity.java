package com.taskagile.app.domain.model.activity;

import com.taskagile.app.domain.model.board.BoardId;
import com.taskagile.app.domain.model.user.UserId;
import com.taskagile.app.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class BoardActivity {

  private UserId userId;
  private BoardId boardId;
  private ActivityType type;
  private Map<String, Object> detail = new HashMap<>();

  public UserId getUserId() {
    return userId;
  }

  public BoardId getBoardId() {
    return boardId;
  }

  public ActivityType getType() {
    return type;
  }

  public String getDetailJson() {
    return JsonUtils.toJson(detail);
  }
}
