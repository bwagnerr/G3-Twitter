package g3.twitter.controller;

import g3.twitter.model.Tweet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class RealTwitter implements ITwitter{
	
	Twitter twitter;
	
	public RealTwitter(){
		twitter = new TwitterFactory().getInstance();
	}
	@Override
	public void tweet(String mensagem) throws TwitterException {
		twitter.updateStatus(mensagem);
	}

	@Override
	public List<Tweet> searchTweets(String searchQuery) throws TwitterException {
		
		List<Tweet> searchResults = new ArrayList<Tweet>();
	    
		Query query = new Query(searchQuery);
	    QueryResult result = twitter.search(query);
	    
	    for(twitter4j.Tweet tweet:result.getTweets()){
			searchResults.add(new Tweet(tweet.getFromUser(), tweet.getText(), tweet.getCreatedAt()));
	    }
	    
	    return searchResults;
	}

	@Override
	public List<Tweet> timeline() throws TwitterException {
		
		List<Tweet> tweets = new ArrayList<Tweet>();
	    List<Status> statuses = twitter.getHomeTimeline();
	    
	    for(Status status:statuses){
	    	tweets.add(new Tweet(status.getUser().getScreenName(),status.getText(),status.getCreatedAt()));
	    }
	    
	    return tweets;
	}
	
	@Override
	public String currentUser() throws TwitterException {
		return twitter.verifyCredentials().getScreenName();
	}
	
	public void followUser(String userScreenName) throws TwitterException{
		if(userScreenName.charAt(0) == '@')
		userScreenName.replaceFirst("@", "");
		twitter.createFriendship(userScreenName);
	}
	
	public void connect() throws IOException, TwitterException{
	twitter.setOAuthConsumer("[consumer key]", "[consumer secret]");
	    RequestToken requestToken = twitter.getOAuthRequestToken();
	    AccessToken accessToken = null;
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    while (null == accessToken) {
	      System.out.println("Open the following URL and grant access to your account:");
	      System.out.println(requestToken.getAuthorizationURL());
	      System.out.print("Enter the PIN(if available) or just hit enter.[PIN]:");
	      String pin = br.readLine();
	      try{
	         if(pin.length() > 0){
	           accessToken = twitter.getOAuthAccessToken(requestToken, pin);
	         }else{
	           accessToken = twitter.getOAuthAccessToken();
	         }
	      } catch (TwitterException te) {
	        if(401 == te.getStatusCode()){
	          System.out.println("Unable to get the access token.");
	        }else{
	          te.printStackTrace();
	        }
	      }
	    }
	}
}

