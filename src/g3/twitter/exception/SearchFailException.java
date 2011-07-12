package g3.twitter.exception;

public class SearchFailException extends Exception {

	private static final long serialVersionUID = 1L;

	public SearchFailException() {
		super("N‹o foi poss’vel executar a sua pesquisa");
	}
}
