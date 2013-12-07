package org.jahia.modules.tweetwall;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jahia.modules.tweetwall.websocket.TweetWallClient;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import com.google.gson.Gson;

public class TweetWallStatusListener implements StatusListener {

	private static Logger logger = Logger.getLogger(TweetWallStatusListener.class);

	private String host;
	private String port;

	public TweetWallStatusListener(String host, String port) {
		setHost(host);
		setPort(port);
	}

	public void onStatus(Status status) {
		Tweet tweet = new Tweet(status);
		String JSONTweet = new Gson().toJson(tweet);
		try {
			TweetWallClient.getSession(getHost(), getPort()).getBasicRemote().sendText(JSONTweet);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public void onException(Exception ex) {
		logger.error(ex.getMessage());
	}

	public void onDeletionNotice(StatusDeletionNotice arg0) {
		logger.info("onDeletionNotice");
	}

	public void onScrubGeo(long arg0, long arg1) {
		logger.info("onScrubGeo");
	}

	public void onStallWarning(StallWarning arg0) {
		logger.info("onStallWarning");
	}

	public void onTrackLimitationNotice(int arg0) {
		logger.info("onTrackLimitationNotice");
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
}