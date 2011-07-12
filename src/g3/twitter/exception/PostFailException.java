package g3.twitter.exception;

public class PostFailException extends Exception {

	private static final long serialVersionUID = 1L;

	public PostFailException() {
		super("N‹o foi poss’vel tweetar");
	}
}
