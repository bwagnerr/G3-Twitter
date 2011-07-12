package g3.twitter.controller;


import g3.twitter.exception.*;
import g3.twitter.model.Tweet;

import java.util.List;

public interface TwitterInterface {
	
	public void tweet(String message) throws PostFailException;
	
	public List<Tweet> searchTweets(String searchquery) throws SearchFailException;
				
	public List<Tweet> timeline() throws UpdateTimelineFailException;
		
	public String currentUser() throws NoLoggedUserException;
	
	public void followUser(String screenUserName) throws NoUserFoundException;
}
