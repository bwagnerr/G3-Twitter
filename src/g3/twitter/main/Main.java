package g3.twitter.main;


import g3.twitter.controller.*;
import g3.twitter.view.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TwitterInterface twitter = new RealTwitter();
		MainWindow view = new MainWindow(twitter);
	    view.display();
	}
}