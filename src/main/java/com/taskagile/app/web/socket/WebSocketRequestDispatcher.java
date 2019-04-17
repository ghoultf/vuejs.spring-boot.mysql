package com.taskagile.app.web.socket;

import com.taskagile.app.domain.common.security.TokenManager;
import com.taskagile.app.domain.model.user.UserId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import io.jsonwebtoken.JwtException;

@Component
public class WebSocketRequestDispatcher extends TextWebSocketHandler {

  private static final Logger log = LoggerFactory.getLogger(WebSocketRequestDispatcher.class);

  private TokenManager tokenManager;

  public WebSocketRequestDispatcher(TokenManager tokenManager) {
    this.tokenManager = tokenManager;
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession webSocketSession) {
    log.debug("WebSocket connection established");
    RealTimeSession session = new RealTimeSession(webSocketSession);
    String token = session.getToken();

    try {
      UserId userId = tokenManager.verifyJwt(token);
      session.addAttribute("userId", userId);
      session.reply("authenticated");
    } catch (JwtException e) {
      log.debug("Invalid JWT token value: {}", token);
      session.fail("authentication failed");
    }
  }

}
