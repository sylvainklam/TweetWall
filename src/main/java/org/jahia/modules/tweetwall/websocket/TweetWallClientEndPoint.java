package org.jahia.modules.tweetwall.websocket;

import javax.websocket.ClientEndpoint;
import javax.websocket.MessageHandler;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.apache.log4j.Logger;

@ClientEndpoint
public class TweetWallClientEndPoint {
	private static Logger logger = Logger.getLogger(TweetWallClientEndPoint.class);

	@OnOpen
	public void onOpen(final Session session) {
		logger.info("Tweet Wall Client end point opened.");
		session.addMessageHandler(new MessageHandler.Whole<String>() {
			public void onMessage(String message) {
				logger.info(message);
			}
		});
		logger.info("Message handler added.");
	}

	@OnClose
	public void onClose(Session session) {
		logger.info("Tweet Wall Client end point closed.");
	}
}
