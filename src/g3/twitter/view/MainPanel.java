package g3.twitter.view;

import g3.twitter.controller.TwitterInterface;
import g3.twitter.exception.PostFailException;
import g3.twitter.exception.SearchFailException;
import g3.twitter.exception.UpdateTimelineFailException;
import g3.twitter.model.Tweet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class MainPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	JPanel tweet;
	JTextArea tweetField;
	JButton tweetButton;

	JTabbedPane tabs;
	
	JScrollPane homeScroll;
	JTable home;
	
	JScrollPane searchScroll;
	JTable search;
		
	TwitterInterface twitter;
	
	Timer timer;
	TimerTask task;

	public MainPanel(TwitterInterface twitter){
		define();
		position();
		this.twitter = twitter;
		setAtualizador(10000);
	}

	public void define(){
		setMinimumSize(new Dimension(600,500));

		tweet = new JPanel();
		tweet.setMaximumSize(new Dimension(600,50));

		tweetField = new JTextArea(3,45);
		tweetField.setDocument(new CustomDocument(140));
		tweetField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tweetField.setLineWrap(true);
		tweetButton = new JButton("Tweet");
		tweetButton.setActionCommand(Options.TWEET.name());
		tweetButton.addActionListener(this);
		tweet.add(tweetField);
		tweet.add(tweetButton);

		tabs = new JTabbedPane();
		tabs.setMaximumSize(new Dimension(600,575));

		home = new JTable();
		home.setRowHeight(40);
		home.setShowGrid(true);
		home.setGridColor(Color.BLACK);
		home.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		home.setTableHeader(null);
		homeScroll = new JScrollPane(home);

		search = new JTable();
		search.setRowHeight(40);
		search.setShowGrid(true);
		search.setGridColor(Color.BLACK);
		search.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		search.setTableHeader(null);
		searchScroll = new JScrollPane(search);

		tabs.addTab( "Timeline" , homeScroll);
		tabs.addTab( "Search" , searchScroll);
		
		timer = new Timer();
	}

	public void position() {
		GroupLayout gl = new GroupLayout(this);
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		horizontalAnalysis(gl);
		verticalAnalysis(gl);
		setLayout(gl);
	}

	public void horizontalAnalysis(GroupLayout gl) {
		ParallelGroup pg = gl.createParallelGroup(Alignment.LEADING);

		pg.addComponent(tweet);
		pg.addComponent(tabs);

		gl.setHorizontalGroup(pg);
	}

	public void verticalAnalysis(GroupLayout gl) {
		SequentialGroup sg = gl.createSequentialGroup();

		sg.addComponent(tweet);
		sg.addComponent(tabs);

		gl.setVerticalGroup(sg);
	}

	public void actionPerformed (ActionEvent e){
		Options opcao = Options.valueOf(e.getActionCommand());
		if(opcao == Options.TWEET){
			String text = tweetField.getText();
			try{
				twitter.tweet(text);
			}catch (PostFailException exception) {				
				JOptionPane.showMessageDialog(null, exception.getMessage());
			}
			loadTimeline();
			tabs.setSelectedIndex(0);
			tweetField.setText("");
			}
	}
	
	protected void setAtualizador(int period){
		task = new TimerTask(){    	
	    	public void run(){
	    		loadTimeline();
	    	}};
	    timer.scheduleAtFixedRate(task, 0, period);
	}
	
	protected void changeTimer(int period){
		if(period == 0){
	    task.cancel();
		}else{
			task.cancel();
			task = new TimerTask(){    	
				public void run(){
					loadTimeline();
				}
			};
		    timer.scheduleAtFixedRate(task, 0, period);
		}
	}
	
	protected void searchResults(String query){
		List<Tweet> results;
		try {
			results = twitter.searchTweets(query);
			TweetTableModel searchModel = new TweetTableModel(results);
			tabs.setSelectedIndex(1);
			search.setModel(searchModel);
		} catch (SearchFailException exception) {
			JOptionPane.showMessageDialog(null, exception.getMessage());
		}
	}
	
	private void loadTimeline(){
		try {
			List<Tweet> timeline = twitter.timeline();
			TweetTableModel tweetsModel = new TweetTableModel(timeline);
			home.setModel(tweetsModel);
		}catch (UpdateTimelineFailException exception){
			JOptionPane.showMessageDialog(null, exception.getMessage());
		}
	}
	
}