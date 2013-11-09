package org.jahia.modules.tweetwall.websocket;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class TweetWallClientEndPoint {
	@OnOpen
	public void onOpen(Session session) {

	}

	@OnMessage
	public void onMessage(Session session, String message) {
		System.out.println("message on " + TweetWallClientEndPoint.class.getName() + " : " + message);
	}

	@OnClose
	public void onClose(Session session) {

	}
}
