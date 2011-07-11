package g3.twitter.view;

import java.util.List;

import javax.swing.AbstractListModel;
import g3.twitter.model.Tweet;

public class TweetListModel extends AbstractListModel{

	private static final long serialVersionUID = 1L;
	
	List<Tweet> tweets;
	
	public TweetListModel(List<Tweet> tweets){
		this.tweets = tweets;
	}
	
	@Override
	public Object getElementAt(int index) {
		return tweets.get(index);
	}

	@Override
	public int getSize() {
		return tweets.size();
	}

}
