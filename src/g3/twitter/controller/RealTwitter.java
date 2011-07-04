package g3.twitter.controller;

import g3.twitter.model.Tweet;
import g3.twitter.model.User;

import java.util.List;

public class RealTwitter implements Twitter{

	@Override
	public boolean tweet(String mensagem) {
		return false;
	}

	@Override
	public List<Tweet> searchTweets(String pMsg) {
		return null;
	}

	@Override
	public List<User> searchUser(String userName) {
		return null;
	}

	@Override
	public List<Tweet> tweets() {
		return null;
	}

	@Override
	public User user() {
		return null;
	}

}
