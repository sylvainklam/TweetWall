package org.jahia.modules.tweetwall;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.jahia.modules.tweetwall.websocket.TweetWallClient;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import com.google.gson.Gson;

public class TweetWallTag extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		String[] keywords = { "Microsoft" };
		String[] languages = { "en" };

		TwitterListener.listen(new StatusListener() {
			public void onStatus(Status status) {
				String JSONStatus = new Gson().toJson(status);
				try {
					TweetWallClient.getSession().getBasicRemote().sendText(JSONStatus);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
			}

			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			}

			public void onScrubGeo(long userId, long upToStatusId) {
			}

			public void onException(Exception ex) {
			}

			public void onStallWarning(StallWarning arg0) {
			}
		}, keywords, languages);
	}
}