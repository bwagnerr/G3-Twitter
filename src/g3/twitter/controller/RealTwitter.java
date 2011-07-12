package g3.twitter.controller;

import g3.twitter.exception.NoLoggedUserException;
import g3.twitter.exception.NoUserFoundException;
import g3.twitter.exception.PostFailException;
import g3.twitter.exception.SearchFailException;
import g3.twitter.exception.UpdateTimelineFailException;
import g3.twitter.model.Tweet;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class RealTwitter implements TwitterInterface{
	
	Twitter twitter;
	
	public RealTwitter(){
		twitter = new TwitterFactory().getInstance();
	}
	@Override
	public void tweet(String mensagem) throws PostFailException {
		try {
			twitter.updateStatus(mensagem);
		} catch (TwitterException e) {
				throw new PostFailException();			
		}
	}

	@Override
	public List<Tweet> searchTweets(String searchQuery) throws SearchFailException {
		
		List<Tweet> searchResults = new ArrayList<Tweet>();
	    
		Query query = new Query(searchQuery);
	    QueryResult result;
		try {
			result = twitter.search(query);
		} catch (TwitterException e) {
			throw new SearchFailException();
		}
	    
	    for(twitter4j.Tweet tweet:result.getTweets()){
			searchResults.add(new Tweet(tweet.getFromUser(), tweet.getText(), tweet.getCreatedAt()));
	    }
	    
	    return searchResults;
	}

	@Override
	public List<Tweet> timeline() throws UpdateTimelineFailException {
		
		List<Tweet> tweets = new ArrayList<Tweet>();
	    List<Status> statuses;
		try {
			statuses = twitter.getHomeTimeline();
		} catch (TwitterException e) {
			throw new UpdateTimelineFailException();
		}
	    
	    for(Status status:statuses){
	    	tweets.add(new Tweet(status.getUser().getScreenName(),status.getText(),status.getCreatedAt()));
	    }
	    
	    return tweets;
	}
	
	@Override
	public String currentUser() throws NoLoggedUserException {
		try {
			return twitter.verifyCredentials().getScreenName();
		} catch (TwitterException e) {
			throw new NoLoggedUserException();
		}
	}
	
	@Override
	public void followUser(String userScreenName) throws NoUserFoundException {
		if(userScreenName.charAt(0) == '@')
		userScreenName.replaceFirst("@", "");
		try {
			twitter.createFriendship(userScreenName);
		} catch (TwitterException e) {
			throw new NoUserFoundException();
		}
	}
}

