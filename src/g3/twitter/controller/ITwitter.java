package g3.twitter.controller;


import java.util.List;

import twitter4j.Status;
import twitter4j.Tweet;
import twitter4j.TwitterException;
import twitter4j.User;

public interface ITwitter {
	
	public void tweet(String mensagem) throws TwitterException;
	
	public List<Tweet> searchTweets(String pMsg) throws TwitterException;
		
	public List<User> searchUser(String userName) throws TwitterException;
		
	public List<Status> tweets() throws TwitterException;
		
	public User user() throws TwitterException;
}
