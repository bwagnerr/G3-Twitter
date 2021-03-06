package g3.twitter.view;

import g3.twitter.controller.TwitterInterface;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JPanel;


public class ContentPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	SidePanel sidePanel;
	MainPanel mainPanel;
	
	TwitterInterface twitter;

	public ContentPanel(TwitterInterface twitter){
		this.twitter = twitter;
		define();
		position();		
	}

	public void define(){
		mainPanel = new MainPanel(twitter);
		sidePanel = new SidePanel(twitter,this);		
	}

	public void position(){
		GroupLayout gl = new GroupLayout(this);
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		horizontalAnalysis(gl);
		verticalAnalysis(gl);
		setLayout(gl);
	}
	public void horizontalAnalysis(GroupLayout gl){
		SequentialGroup sg = gl.createSequentialGroup();
		
		sg.addComponent(mainPanel);
		sg.addComponent(sidePanel);
		
		gl.setHorizontalGroup(sg);
	}
	
	public void verticalAnalysis(GroupLayout gl){
		ParallelGroup pg = gl.createParallelGroup(Alignment.LEADING);
		
		pg.addComponent(sidePanel);
		pg.addComponent(mainPanel);
		
		gl.setVerticalGroup(pg);
	}

	public void searchResults(String query){
		mainPanel.searchResults(query);		
	}
	
	public void setTimer(int period){
		mainPanel.changeTimer(period);
	}

}