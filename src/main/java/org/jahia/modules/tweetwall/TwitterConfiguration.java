package org.jahia.modules.tweetwall;

public class TwitterConfiguration {

	private boolean debugEnabled;

	private String OAuthConsumerKey;
	private String OAuthConsumerSecret;
	private String OAuthAccessToken;
	private String OAuthAccessTokenSecret;

	public TwitterConfiguration(boolean debug, String OAuthConsumerKey, String OAuthConsumerSecret, String OAuthAccessToken,
			String OAuthAccessTokenSecret) {
		this.debugEnabled = debug;
		this.OAuthConsumerKey = OAuthConsumerKey;
		this.OAuthConsumerSecret = OAuthConsumerSecret;
		this.OAuthAccessToken = OAuthAccessToken;
		this.OAuthAccessTokenSecret = OAuthAccessTokenSecret;
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
