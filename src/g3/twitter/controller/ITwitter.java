package g3.twitter.controller;


import g3.twitter.model.Tweet;

import java.util.List;

import twitter4j.TwitterException;

public interface ITwitter {
	
	public void tweet(String message) throws TwitterException;
	
	public List<Tweet> searchTweets(String searchquery) throws TwitterException;
				
	public List<Tweet> timeline() throws TwitterException;
		
	public String currentUser() throws TwitterException;
	
	public void followUser(String screenUserName) throws TwitterException;
}
