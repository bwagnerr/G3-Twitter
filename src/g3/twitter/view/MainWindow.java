package g3.twitter.view;

import java.awt.Dimension;

import g3.twitter.controller.TwitterInterface;

import javax.swing.JFrame;


public class MainWindow extends JFrame{
	

	private static final long serialVersionUID = 1L;
	
	ContentPanel contentPanel;
	
	public MainWindow(TwitterInterface twitter){
		super("Twitter");
		configure(twitter);
	}
	
	public void configure(TwitterInterface twitter){
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		contentPanel = new ContentPanel(twitter);
		getContentPane().add(contentPanel);
		setSize(800,600);
		setMaximumSize(new Dimension(800,600));
		setResizable(false);
	}
	
	public void display(){
		pack();
		this.setVisible(true);
	}
}