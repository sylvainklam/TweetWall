package org.jahia.modules.tweetwall.websocket;

import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

public class TweetWallClient {
	private static Session session = null;

	public static synchronized Session getSession() {
		if (session == null) {
			try {
				ClientManager client = ClientManager.createClient();
				session = client.connectToServer(TweetWallClientEndPoint.class, TweetWallServer.getServerEndPointURI());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return session;
	}
}
