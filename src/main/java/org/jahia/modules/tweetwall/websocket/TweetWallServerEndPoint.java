package org.jahia.modules.tweetwall.websocket;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/tw")
public class TweetWallServerEndPoint {
	@OnMessage
	public String onMessage(String message, Session session) {
		return message;
	}
}
