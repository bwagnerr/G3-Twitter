package g3.twitter.controller;

import g3.twitter.model.Tweet;
import g3.twitter.model.User;

import java.util.ArrayList;
import java.util.List;


public class MockTwitter implements Twitter {
	
	private User user;
	private List<User> users;
	private List<Tweet> tweets;

	public MockTwitter(User user){
		
		tweets = new ArrayList<Tweet>();
		users = new ArrayList<User>();
		this.user = user;
	}
	
	@Override
	public boolean tweet(String mensagem){
		boolean tweeted = false;
		Tweet tweet = new Tweet();
	
		if(tweet.post(mensagem, user)){
			tweets.add(tweet);
			tweeted = true;
		}
		return tweeted;
	}
	
	@Override
	public List<Tweet> searchTweets(String search){

		List<Tweet>	results = new ArrayList<Tweet>();
		
		for(Tweet tweet:tweets){
			
			if(tweet.show().contains(search))
				results.add(tweet);
		}
		return results;
	}

	@Override
	public List<User> searchUser(String userName) {

		List<User>	results = new ArrayList<User>();
		
		for(User user:users)
		{
			if(user.name() == userName)
			{
				results.add(user);
			}			
		}
		return results;
	}
	
	@Override
	public List<Tweet> tweets() {
		return tweets;
	}

	@Override
	public User user() {
		return user;
	}	
	
}
