package org.jahia.modules.tweetwall;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class TweetWallTag extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		String[] keywords = { "Microsoft" };
		String[] languages = { "en" };
		TwitterListener.listen(new StatusListener() {
			public void onStatus(Status status) {
				try {
					getJspContext().getOut().print(
							status.getUser().getScreenName() + " > "
									+ status.getText());
					// getJspBody().invoke(null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
