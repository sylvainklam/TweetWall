package org.jahia.modules.tweetwall.websocket;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import twitter4j.internal.logging.Logger;

@ServerEndpoint(value = "/tw")
public class TweetWallServerEndPoint {

	private static Logger logger = Logger.getLogger(TweetWallServerEndPoint.class);

	@OnMessage
	public void onMessage(Session session, String message) {
		logger.info(message);
		try {
			for (Session sess : session.getOpenSessions()) {
				if (sess.isOpen())
					sess.getBasicRemote().sendText(message);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
