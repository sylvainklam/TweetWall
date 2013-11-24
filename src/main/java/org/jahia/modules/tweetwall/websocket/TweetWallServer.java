package org.jahia.modules.tweetwall.websocket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.websocket.DeploymentException;

import org.glassfish.tyrus.server.Server;
import org.jahia.modules.tweetwall.utils.PropertiesLoader;

public class TweetWallServer {

	private static Server server = null;
	private static Properties WSProperties = null;

	private static final String HOST_PROPERTY = "websocket.server.host";
	private static final String PORT_PROPERTY = "websocket.server.port";

	private static final String WS_PATH = "/websocket";
	private static final String ENDPOINT_PATH = "/tw";

	private static URI serverEndPointURI = null;

	private static synchronized Properties getWSProperties() throws FileNotFoundException, IOException {
		if (WSProperties == null)
			WSProperties = PropertiesLoader.load("/websocket.properties");
		return WSProperties;
	}

	private static synchronized Server getInstance() throws FileNotFoundException, IOException {
		if (server == null) {
			String host = getWSProperties().getProperty(HOST_PROPERTY);
			String port = getWSProperties().getProperty(PORT_PROPERTY);
			server = new Server(host, Integer.parseInt(port), WS_PATH, TweetWallServerEndPoint.class);
		}
		return server;
	}

	public static void startServer() throws FileNotFoundException, IOException {
		try {
			getInstance().start();
		} catch (DeploymentException e) {
			e.printStackTrace();
		}
	}

	public static synchronized URI getServerEndPointURI() throws URISyntaxException, FileNotFoundException, IOException {
		if (serverEndPointURI == null) {
			String host = getWSProperties().getProperty(HOST_PROPERTY);
			String port = getWSProperties().getProperty(PORT_PROPERTY);
			serverEndPointURI = new URI("ws://" + host + ":" + Integer.parseInt(port) + WS_PATH + ENDPOINT_PATH);
		}
		return serverEndPointURI;
	}

	public static String getURL() throws FileNotFoundException, IOException {
		String host = getWSProperties().getProperty(HOST_PROPERTY);
		String port = getWSProperties().getProperty(PORT_PROPERTY);
		return "ws://" + host + ":" + Integer.parseInt(port) + WS_PATH + ENDPOINT_PATH;
	}

	public static void stopServer() throws FileNotFoundException, IOException {
		getInstance().stop();
	}
}
