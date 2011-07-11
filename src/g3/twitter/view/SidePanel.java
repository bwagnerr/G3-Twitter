package g3.twitter.view;

import g3.twitter.controller.ITwitter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

import twitter4j.TwitterException;

public class SidePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	// Search
	JPanel user;
	JLabel userName;
	JPanel search;
	JLabel searchLabel;
	JTextField searchField;
	JButton searchButton;
	JPanel follow;
	JLabel followLabel;
	JTextField followField;
	JLabel followSucess;
	JButton followButton;
	ContentPanel container;
	ITwitter twitter;

	public SidePanel(ITwitter twitter, ContentPanel container) {
		define(twitter);
		position();
		this.container = container;
		this.twitter = twitter;
	}

	public void define(ITwitter twitter) {
		
		setMinimumSize(new Dimension(200,768));
		setBorder(BorderFactory.createMatteBorder(0,1,0,0,Color.BLACK));

		user = new JPanel();
		user.setMaximumSize(new Dimension(200,100));
		try { userName = new JLabel("@"+twitter.currentUser());	} catch (TwitterException e) {}
		user.add(userName);
		
		search = new JPanel();
		search.setMaximumSize(new Dimension(200,100));
		searchField = new JTextField(9);
		searchField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		searchLabel = new JLabel("Type here to search:");
		searchButton = new JButton("Search");
		searchButton.setActionCommand(Options.SEARCH.name());
		searchButton.addActionListener(this);
		search.add(searchLabel);
		search.add(searchField);
		search.add(searchButton);
		
		follow = new JPanel();
		follow.setMaximumSize(new Dimension(200,100));
		followField = new JTextField(9);
		followField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		followLabel = new JLabel("Type @user to follow:");
		followButton = new JButton("Follow");
		followButton.setActionCommand(Options.FOLLOW.name());
		followButton.addActionListener(this);
		followSucess= new JLabel("");
		follow.add(followLabel);
		follow.add(followField);
		follow.add(followButton);
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

		pg.addComponent(user);
		pg.addComponent(search);
		pg.addComponent(follow);
		pg.addComponent(followSucess);

		gl.setHorizontalGroup(pg);
	}

	public void verticalAnalysis(GroupLayout gl) {
		SequentialGroup sg = gl.createSequentialGroup();

		sg.addComponent(user);
		sg.addComponent(search);
		sg.addComponent(follow);
		sg.addComponent(followSucess);


		gl.setVerticalGroup(sg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Options option = Options.valueOf(e.getActionCommand());
		if(option == Options.SEARCH){
			String query = searchField.getText();
			try {this.container.searchResults(twitter.searchTweets(query));}
			catch (TwitterException e1) {}				
			searchField.setText("");
		}else if(option == Options.FOLLOW){
			String user = followField.getText();
			try {
				twitter.followUser(user);
				followField.setText("");
				followSucess.setText("Successfully Followed");
			}catch(TwitterException e1){
				followSucess.setText("Request already sent");
			}

		}
	}

}