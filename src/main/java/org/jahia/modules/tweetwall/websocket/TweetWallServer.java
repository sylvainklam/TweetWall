package org.jahia.modules.tweetwall.websocket;

import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;

import org.glassfish.tyrus.server.Server;

public class TweetWallServer {

	private static Server server = null;

	private static final String HOST = "localhost";
	private static final String WS_PATH = "/websocket";
	private static final String ENDPOINT_PATH = "/tw";

	private static final int PORT = 8000;

	private static URI serverEndPointURI = null;

	private static Server getInstance() {
		if (server == null)
			server = new Server(HOST, PORT, WS_PATH, TweetWallServerEndPoint.class);
		return server;
	}

	public static void startServer() {
		try {
			getInstance().start();
		} catch (DeploymentException e) {
			e.printStackTrace();
		}
	}

	public static URI getServerEndPointURI() throws URISyntaxException {
		if (serverEndPointURI == null)
			serverEndPointURI = new URI("ws://" + HOST + ":" + PORT + WS_PATH + ENDPOINT_PATH);
		return serverEndPointURI;
	}

	public static void stopServer() {
		getInstance().stop();
	}
}
