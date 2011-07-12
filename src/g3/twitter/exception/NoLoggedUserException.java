package g3.twitter.exception;

public class NoLoggedUserException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoLoggedUserException() {
		super("N‹o foi poss’vel logar no twitter com esse usu‡rio");
	}
}
