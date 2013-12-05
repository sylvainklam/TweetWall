package org.jahia.modules.tweetwall.websocket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;

import org.glassfish.tyrus.server.Server;

public class TweetWallServer {

	private static Server server = null;

	private static final String WS_PATH = "/websocket";
	private static final String ENDPOINT_PATH = "/tw";

	private static URI serverEndPointURI = null;

	private static synchronized Server getInstance(String host, String port) throws FileNotFoundException, IOException {
		if (server == null) {
			server = new Server(host, Integer.parseInt(port), WS_PATH, TweetWallServerEndPoint.class);
		}
		return server;
	}

	public static void startServer(String host, String port) throws FileNotFoundException, IOException {
		try {
			getInstance(host, port).start();
		} catch (DeploymentException e) {
			e.printStackTrace();
		}
	}

	public static synchronized URI getServerEndPointURI(String host, String port) throws URISyntaxException, FileNotFoundException, IOException {
		if (serverEndPointURI == null) {
			serverEndPointURI = new URI("ws://" + host + ":" + port + WS_PATH + ENDPOINT_PATH);
		}
		return serverEndPointURI;
	}

	public static String getURL(String host, String port) throws FileNotFoundException, IOException {
		return "ws://" + host + ":" + port + WS_PATH + ENDPOINT_PATH;
	}

	public static void stopServer(String host, String port) throws FileNotFoundException, IOException {
		getInstance(host, port).stop();
	}
}