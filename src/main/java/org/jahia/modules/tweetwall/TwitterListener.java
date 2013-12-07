package org.jahia.modules.tweetwall;

import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.internal.logging.Logger;

public class TwitterListener {

	private static Logger logger = Logger.getLogger(TwitterListener.class);

	public static final void listen(StatusListener listener, String[] keywords, String[] languages, TwitterConfiguration tc) {
		logger.info("start listening ...");
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(tc.isDebug()).setOAuthConsumerKey(tc.getConsumerKey()).setOAuthConsumerSecret(tc.getConsumerSecret())
				.setOAuthAccessToken(tc.getAccessToken()).setOAuthAccessTokenSecret(tc.getAccessTokenSecret());
		TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		FilterQuery query = new FilterQuery();
		query.track(keywords);
		query.language(languages);
		twitterStream.addListener(listener);
		twitterStream.filter(query);
	}
}
