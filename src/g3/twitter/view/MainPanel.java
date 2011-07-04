package g3.twitter.view;

import g3.twitter.controller.Twitter;
import g3.twitter.model.Tweet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.Timer;

public class MainPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	JPanel tweet;
	JTextArea tweetField;
	JButton tweetButton;

	JTabbedPane tabs;
	
	JPanel home;
	JTextPane homeContent;
	
	JPanel mentions;
	JTextPane mentionsContent;
		
	Twitter twitter;

	public MainPanel(Twitter twitter){
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

		home = new JPanel();

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
			twitter.tweet(text);
			loadTimeline();
			tweetField.setText("");
			}
	}
	
	private void setAtualizador()
	{
		int delay = 10000; 
		ActionListener taskPerformer = new ActionListener() { 
		  public void actionPerformed(ActionEvent evt) {
			  loadTimeline();			  		  
		  } 
		}; 
		new Timer(delay, taskPerformer).start();
	}
	
	public void searchResults(List<Tweet> results){
		home.removeAll();
		
		for(Tweet tweet:results)
			home.add(newTweet(tweet));
		
		tabs.repaint();

	}
	
	private void loadTimeline(){
		home.removeAll();
		List<Tweet> timeline = twitter.tweets();
		
		GroupLayout gl = new GroupLayout(home);
		gl.setHorizontalGroup(gl.createParallelGroup());
		for(Tweet tweet:timeline)
			home.add(newTweet(tweet));

		tabs.repaint();
	}

	public JPanel newTweet(Tweet tweet){
		
		JPanel newTweet = new JPanel();
		
		JLabel user = new JLabel();
		user.setText(tweet.author().name()+": ");
		
		JTextPane entry = new JTextPane();
		entry.setContentType("text/html");
		entry.setText("<html><b>"+tweet.show()+"</b></html>");
		entry.setEditable(false);
		
		newTweet.add(user);
		newTweet.add(entry);
		return newTweet;
	}
	
}