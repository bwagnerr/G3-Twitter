package g3.twitter.view;

import g3.twitter.controller.ITwitter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

public class SidePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	// Search
	JPanel user;
	JTextPane userName;
	JPanel search;
	JTextField searchField;
	JButton searchButton;
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
		setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.BLACK));

		user = new JPanel();
		user.setMaximumSize(new Dimension(200,100));
		userName = new JTextPane();
		userName.setEditable(false);
		userName.setContentType("text/html");
		user.add(userName);
		
		search = new JPanel();
		search.setMaximumSize(new Dimension(200,100));
		searchField = new JTextField(9);
		searchField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		searchButton = new JButton("Search");
		searchButton.setActionCommand(Options.SEARCH.name());
		searchButton.addActionListener(this);
		search.add(searchField);
		search.add(searchButton);
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

		pg.addComponent(search);
		pg.addComponent(user);

		gl.setHorizontalGroup(pg);
	}

	public void verticalAnalysis(GroupLayout gl) {
		SequentialGroup sg = gl.createSequentialGroup();

		sg.addComponent(user);
		sg.addComponent(search);

		gl.setVerticalGroup(sg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Options option = Options.valueOf(e.getActionCommand());
		if(option == Options.SEARCH){
			String query = searchField.getText();
			//this.container.searchResults(twitter.searchTweets(query));						
		}
	}

}