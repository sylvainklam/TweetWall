package org.jahia.modules.tweetwall.websocket;

import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;

import org.glassfish.tyrus.server.Server;

public class TweetWallServer {

	private static Server server = null;

	private static final String HOST = "tweetwall";
	private static final String WS_PATH = "/websocket";
	private static final String ENDPOINT_PATH = "/tw";

	private static final int PORT = 9999;

	private static URI serverEndPointURI = null;

	private static synchronized Server getInstance() {
		if (server == null)
			server = new Server(HOST, PORT, WS_PATH, TweetWallServerEndPoint.class);
		return server;
	}

	public static boolean startServer() {
		boolean started = false;
		try {
			getInstance().start();
			started = true;
		} catch (DeploymentException e) {
			e.printStackTrace();
		}
		return started;
	}

	public static synchronized URI getServerEndPointURI() throws URISyntaxException {
		if (serverEndPointURI == null)
			serverEndPointURI = new URI("ws://" + HOST + ":" + PORT + WS_PATH + ENDPOINT_PATH);
		return serverEndPointURI;
	}

	public static void stopServer() {
		getInstance().stop();
	}

	public static void main(String[] args) {
		Server server = TweetWallServer.getInstance();
		if (server == null)
			System.err.println("unable to create server");
		else {
			System.out.println("server created");
			if (!startServer())
				System.err.println("unable to start server");
			else {
				System.out.println("server started");
				startServer();
				stopServer();
			}
		}

	}
}
