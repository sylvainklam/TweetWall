package org.jahia.modules.tweetwall;

import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class TwitterListener {
	public static final void listen(StatusListener listener, String[] keywords, String[] languages) {
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		FilterQuery query = new FilterQuery();
		query.track(keywords);
		query.language(languages);
		twitterStream.addListener(listener);
		twitterStream.filter(query);
	}
}
