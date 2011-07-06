package g3.twitter.controller;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class RealTwitter implements ITwitter{
	
	Twitter twitter = new TwitterFactory().getInstance();
	
	@Override
	public void tweet(String mensagem) throws TwitterException {
		Status status = twitter.updateStatus(mensagem);
	}

	@Override
	public List<Tweet> searchTweets(String pMsg) throws TwitterException {
	    Query query = new Query(pMsg);
	    QueryResult result = twitter.search(query);
	    return result.getTweets();
	}

	@Override
	public List<User> searchUser(String userName) throws TwitterException {
		return twitter.searchUsers(userName,50);
	}

	@Override
	public List<Status> tweets() throws TwitterException {
	    List<Status> statuses = twitter.getUserTimeline();
	    return statuses;
	    }

//	public List<Tweet> convertStatustoTweets(List<Status> statuses){
//		List<Tweet> tweets = new ArrayList<Tweet>();
//		statuses = twitter.getUserTimeline();
//		    for (Status status : statuses) {
//		    	Tweet tweet = new Tweet();
//		    }
//	}
	
	
	@Override
	public User user() throws TwitterException {
		return twitter.verifyCredentials();
	}

}
