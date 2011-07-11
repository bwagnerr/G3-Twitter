package g3.twitter.view;

import g3.twitter.controller.ITwitter;

import javax.swing.JFrame;


public class MainWindow extends JFrame{
	

	private static final long serialVersionUID = 1L;
	
	ContentPanel contentPanel;
	
	public MainWindow(ITwitter twitter){
		super("Twitter");
		configure(twitter);
	}
	
	public void configure(ITwitter twitter){
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		contentPanel = new ContentPanel(twitter);
		getContentPane().add(contentPanel);
		setSize(800,600);
		setResizable(false);
	}
	
	public void display(){
		pack();
		this.setVisible(true);
	}
}