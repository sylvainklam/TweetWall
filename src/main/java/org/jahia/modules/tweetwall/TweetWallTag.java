package org.jahia.modules.tweetwall;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;
import org.jahia.modules.tweetwall.utils.TweetWallUtils;
import org.jahia.modules.tweetwall.websocket.TweetWallClient;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import com.google.gson.Gson;

public class TweetWallTag extends SimpleTagSupport {

	private String keywords = null;
	private String language = null;

	private String wshost = null;
	private String wsport = null;

	private boolean debugEnabled = false;
	
	private String OAuthConsumerKey = null;
	private String OAuthConsumerSecret = null;
	private String OAuthAccessToken = null;
	private String OAuthAccessTokenSecret = null;

	private static Logger logger = Logger.getLogger(TweetWallTag.class);

	public void doTag() throws JspException, IOException {
		String[] keywords = TweetWallUtils.getInstance().convert2Array(getKeywords());
		String[] languages = TweetWallUtils.getInstance().convert2Array(getLanguage());

		logger.info("keywords : " + TweetWallUtils.getInstance().printArrayValues(keywords) + " - language : "
				+ TweetWallUtils.getInstance().printArrayValues(languages));

		TwitterConfiguration tc = new TwitterConfiguration(isDebugEnabled(), getOAuthConsumerKey(), getOAuthConsumerSecret(), getOAuthAccessToken(),
				getOAuthAccessTokenSecret());

		TwitterListener.listen(new StatusListener() {
			public void onStatus(Status status) {
				Tweet tweet = new Tweet(status);
				String JSONTweet = new Gson().toJson(tweet);
				try {
					TweetWallClient.getSession(wshost, wsport).getBasicRemote().sendText(JSONTweet);
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
				logger.error(ex.getMessage());
			}

			public void onStallWarning(StallWarning arg0) {
				logger.info("onStallWarning");
			}
		}, keywords, languages, tc);
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

	public String getWshost() {
		return wshost;
	}

	public void setWshost(String wshost) {
		this.wshost = wshost;
	}

	public String getWsport() {
		return wsport;
	}

	public void setWsport(String wsport) {
		this.wsport = wsport;
	}

	public String getOAuthConsumerKey() {
		return OAuthConsumerKey;
	}

	public void setOAuthConsumerKey(String oAuthConsumerKey) {
		OAuthConsumerKey = oAuthConsumerKey;
	}

	public String getOAuthConsumerSecret() {
		return OAuthConsumerSecret;
	}

	public void setOAuthConsumerSecret(String oAuthConsumerSecret) {
		OAuthConsumerSecret = oAuthConsumerSecret;
	}

	public String getOAuthAccessToken() {
		return OAuthAccessToken;
	}

	public void setOAuthAccessToken(String oAuthAccessToken) {
		OAuthAccessToken = oAuthAccessToken;
	}

	public String getOAuthAccessTokenSecret() {
		return OAuthAccessTokenSecret;
	}

	public void setOAuthAccessTokenSecret(String oAuthAccessTokenSecret) {
		OAuthAccessTokenSecret = oAuthAccessTokenSecret;
	}

	public boolean isDebugEnabled() {
		return debugEnabled;
	}

	public void setDebugEnabled(boolean debugEnabled) {
		this.debugEnabled = debugEnabled;
	}
}