package org.jahia.modules.tweetwall;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;
import org.jahia.modules.tweetwall.utils.TWUtils;
import org.jahia.modules.tweetwall.websocket.TweetWallClient;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import com.google.gson.Gson;

public class TweetWallTag extends SimpleTagSupport {

	private String keywords;
	private String language;

	private static Logger logger = Logger.getLogger(TweetWallTag.class);

	public void doTag() throws JspException, IOException {
		String[] keywords = TWUtils.convert2Array(getKeywords());
		String[] languages = TWUtils.convert2Array(getLanguage());

		logger.info("keywords : " + TWUtils.printArrayValues(keywords) + " - language : " + TWUtils.printArrayValues(languages));

		TwitterListener.listen(new StatusListener() {
			public void onStatus(Status status) {
				Tweet tweet = new Tweet(status);
				String JSONTweet = new Gson().toJson(tweet);
				try {
					TweetWallClient.getSession().getBasicRemote().sendText(JSONTweet);
				} catch (IOException e) {
					logger.error(e.getMessage());
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

			}

			public void onStallWarning(StallWarning arg0) {
				logger.info("onStallWarning");
			}
		}, keywords, languages);
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}