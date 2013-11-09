package org.jahia.modules.tweetwall;

import java.io.IOException;

import org.jahia.modules.tweetwall.websocket.TweetWallClient;
import org.jahia.modules.tweetwall.websocket.TweetWallServer;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import com.google.gson.Gson;

public class TweetWall {

	public static void main(String[] args) {
		String[] keywords = { "Microsoft" };
		String[] languages = { "en" };
		TweetWallServer.startServer();
		TwitterListener.listen(new StatusListener() {
			public void onStatus(Status status) {
				String JSONTweet = new Gson().toJson(status);
				try {
					TweetWallClient.getSession().getBasicRemote().sendText(JSONTweet);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
			}

			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			}

			public void onScrubGeo(long userId, long upToStatusId) {
			}

			public void onException(Exception ex) {
			}

			public void onStallWarning(StallWarning arg0) {
			}
		}, keywords, languages);
	}
}
