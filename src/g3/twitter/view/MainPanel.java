package g3.twitter.view;

import g3.twitter.controller.ITwitter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.Timer;

import twitter4j.Status;
import twitter4j.Tweet;
import twitter4j.TwitterException;

public class MainPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	JPanel tweet;
	JTextArea tweetField;
	JButton tweetButton;

	JTabbedPane tabs;
	
	JTextArea home;
	JTextPane homeContent;
	
	JPanel mentions;
	JTextPane mentionsContent;
		
	ITwitter twitter;

	public MainPanel(ITwitter twitter){
		define();
		position();
		setAtualizador();
		this.twitter = twitter;
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

		home = new JTextArea ();

		mentions = new JPanel();


		tabs.addTab( "Home" , home );
		tabs.addTab( "Mentions" , mentions );
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
			}catch(Exception x){
				int i = 2;
			}
			loadTimeline();
			tweetField.setText("");
			}
	}
	
	private void setAtualizador()
	{
		int delay = 10000; 
		ActionListener taskPerformer = new ActionListener() { 
		  public void actionPerformed(ActionEvent evt) {
			  //loadTimeline();			  		  
		  } 
		}; 
		new Timer(delay, taskPerformer).start();
	}
	
	public void searchResults(List<Tweet> results){
		home.removeAll();
		
		for(Tweet tweet:results)
			//home.add(newTweet(tweet));
		
		tabs.repaint();

	}
	
	private void loadTimeline(){
		home.removeAll();
		
		GroupLayout gl = new GroupLayout(home);
		gl.setHorizontalGroup(gl.createParallelGroup());
		try {
			for(Status status : twitter.tweets())
				home.setText(status.getText());
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tabs.repaint();
	}
	
}