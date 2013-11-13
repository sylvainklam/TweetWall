package org.jahia.modules.tweetwall;

import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.internal.logging.Logger;

public class TwitterListener {
	private static Logger logger = Logger.getLogger(TwitterListener.class);
	private static TwitterStream twitterStream = new TwitterStreamFactory().getInstance();

	public static final void listen(StatusListener listener, String[] keywords, String[] languages) {
		FilterQuery query = new FilterQuery();
		query.track(keywords);
		query.language(languages);
		twitterStream.addListener(listener);
		twitterStream.filter(query);
	}

	public static final void stop() {
		logger.info("shutting down TwitterStream ...");
		twitterStream.shutdown();
	}
}
