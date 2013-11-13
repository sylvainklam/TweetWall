package org.jahia.modules.tweetwall;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;
import org.jahia.modules.tweetwall.websocket.TweetWallClient;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import com.google.gson.Gson;

public class TweetWallTag extends SimpleTagSupport {
	private static Logger logger = Logger.getLogger(TweetWallTag.class);

	public void doTag() throws JspException, IOException {
		String[] keywords = { "Microsoft" };
		String[] languages = { "en" };
		TwitterListener.listen(new StatusListener() {
			public void onStatus(Status status) {
				Tweet tweet = new Tweet(status);
				String JSONTweet = new Gson().toJson(tweet);
				try {
					TweetWallClient.getSession().getBasicRemote().sendText(JSONTweet);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				logger.info("onDeletionNotice");
			}

			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				logger.info("onTrackLimitationNotice");
			}

			public void onScrubGeo(long userId, long upToStatusId) {
				logger.info("onScrubGeo");
			}

			public void onException(Exception ex) {
				logger.info("onException");
				TwitterListener.stop();
			}

			public void onStallWarning(StallWarning arg0) {
				logger.info("onStallWarning");
			}
		}, keywords, languages);
	}
}