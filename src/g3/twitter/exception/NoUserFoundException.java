package g3.twitter.exception;

public class NoUserFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoUserFoundException() {
		super("N‹o foi poss’vel seguir este usu‡rio");
	}
}
