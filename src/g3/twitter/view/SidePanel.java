package g3.twitter.view;

import g3.twitter.controller.TwitterInterface;
import g3.twitter.exception.NoLoggedUserException;
import g3.twitter.exception.NoUserFoundException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	JButton followButton;
	ContentPanel container;
	TwitterInterface twitter;
	JButton changeTimer;

	public SidePanel(TwitterInterface twitter, ContentPanel container) {
		define(twitter);
		position();
		this.container = container;
		this.twitter = twitter;
	}

	public void define(TwitterInterface twitter) {
		
		setMinimumSize(new Dimension(200,768));
		setBorder(BorderFactory.createMatteBorder(0,1,0,0,Color.BLACK));

		user = new JPanel();
		user.setMaximumSize(new Dimension(200,100));
		try {
			userName = new JLabel("@"+twitter.currentUser());
			user.add(userName);
		}catch (NoLoggedUserException exception) {
			JOptionPane.showMessageDialog(null, exception.getMessage());
		};
		
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
		follow.add(followLabel);
		follow.add(followField);
		follow.add(followButton);
		
		changeTimer = new JButton("Atualização Automática...");
		changeTimer.setActionCommand(Options.TIMER.name());
		changeTimer.addActionListener(this);


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
		pg.addComponent(changeTimer);

		gl.setHorizontalGroup(pg);
	}

	public void verticalAnalysis(GroupLayout gl) {
		SequentialGroup sg = gl.createSequentialGroup();

		sg.addComponent(user);
		sg.addComponent(search);
		sg.addComponent(follow);
		sg.addGap(375);
		sg.addComponent(changeTimer);

		gl.setVerticalGroup(sg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Options option = Options.valueOf(e.getActionCommand());
		if(option == Options.SEARCH){
			
			String query = searchField.getText();
			this.container.searchResults(query);
			searchField.setText("");
			
		}else if(option == Options.FOLLOW){
			
			String user = followField.getText();
			
			try {
				twitter.followUser(user);
				followField.setText("");
				JOptionPane.showMessageDialog(null, "Pedido para seguir enviado");
			}catch(NoUserFoundException exception){
				JOptionPane.showMessageDialog(null, exception.getMessage());
			}
		}else if(option == Options.TIMER){
			int dialog = Integer.parseInt(JOptionPane.showInputDialog(null,"Insira a frequência de atualização desejada:"));
			this.container.setTimer(dialog*1000);
		}
	}

}