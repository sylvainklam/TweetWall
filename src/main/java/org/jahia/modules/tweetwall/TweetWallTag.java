package org.jahia.modules.tweetwall;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;
import org.jahia.modules.tweetwall.utils.TweetWallUtils;

public class TweetWallTag extends SimpleTagSupport {

	private String keywords = null;
	private String language = null;

	private String wshost = null;
	private String wsport = null;

	private boolean debug = false;

	private String consumerKey = null;
	private String consumerSecret = null;
	private String accessToken = null;
	private String accessTokenSecret = null;

	private static Logger logger = Logger.getLogger(TweetWallTag.class);

	public void doTag() throws JspException, IOException {
		String[] keywords = TweetWallUtils.getInstance().convert2Array(getKeywords());
		String[] languages = TweetWallUtils.getInstance().convert2Array(getLanguage());

		logger.info("keywords : " + TweetWallUtils.getInstance().printArrayValues(keywords) + " - language : "
				+ TweetWallUtils.getInstance().printArrayValues(languages));
		TwitterConfiguration tc = new TwitterConfiguration(isDebug(), getConsumerKey(), getConsumerSecret(), getAccessToken(), getAccessTokenSecret());
		TwitterListener.listen(new TweetWallStatusListener(getWshost(), getWsport()), keywords, languages, tc);
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

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}