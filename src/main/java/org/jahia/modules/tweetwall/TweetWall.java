package org.jahia.modules.tweetwall;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class TweetWall {

	public static void main(String[] args) {
		String[] keywords = { "Microsoft" };
		String[] languages = { "fr" };
		TwitterListener.listen(new StatusListener() {
			public void onStatus(Status status) {
				System.out.println("" + status.getUser().getScreenName()
						+ " - " + status.getText());
			}

			public void onDeletionNotice(
					StatusDeletionNotice statusDeletionNotice) {
			}

			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			}

			public void onScrubGeo(long userId, long upToStatusId) {
			}

			public void onException(Exception ex) {
			}

			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub

			}
		}, keywords, languages);
	}
}
