package g3.twitter.main;


import g3.twitter.controller.MockTwitter;
import g3.twitter.model.User;
import g3.twitter.view.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User user = new User("Joaozinho");
		MockTwitter twitter = new MockTwitter(user);
		MainWindow view = new MainWindow(twitter);
	    view.display();
	}
}
