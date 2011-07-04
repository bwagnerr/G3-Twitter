package g3.twitter.controller;

import g3.twitter.model.Tweet;
import g3.twitter.model.User;

import java.util.List;

public interface Twitter {
	
	public boolean tweet(String mensagem);
	
	public List<Tweet> searchTweets(String pMsg);
		
	public List<User> searchUser(String userName);
		
	public List<Tweet> tweets();
		
	public User user();
}
