package org.jahia.modules.tweetwall;

public class TwitterConfiguration {

	private boolean debug;

	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;

	public TwitterConfiguration(boolean debug, String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
		this.setDebug(debug);
		this.setConsumerKey(consumerKey);
		this.setConsumerSecret(consumerSecret);
		this.setAccessToken(accessToken);
		this.setAccessTokenSecret(accessTokenSecret);
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
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
}
