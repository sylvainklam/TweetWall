package org.jahia.modules.tweetwall;

import twitter4j.Status;

public class Tweet {
	private String status;
	private String user;
	private String screenname;
	private String date;
	private String iconURL;

	public Tweet(Status status) {
		setDate(status.getCreatedAt().toString());
		setStatus(status.getText());
		setUser(status.getUser().getName());
		setIconURL(status.getUser().getProfileImageURL().toString());
		setScreenname(status.getUser().getScreenName());
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getScreenname() {
		return screenname;
	}

	public void setScreenname(String screenname) {
		this.screenname = screenname;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIconURL() {
		return iconURL;
	}

	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}
}