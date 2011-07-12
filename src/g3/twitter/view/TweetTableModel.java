package g3.twitter.view;

import g3.twitter.model.Tweet;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TweetTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	
	List<Tweet> tweets;
	
	public TweetTableModel(List<Tweet> tweets){
		this.tweets = tweets;
	}
	
	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public int getRowCount() {
		return tweets.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return tweets.get(arg0);
	}

}
