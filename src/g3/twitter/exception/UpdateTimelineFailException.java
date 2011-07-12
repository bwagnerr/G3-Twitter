package g3.twitter.exception;

public class UpdateTimelineFailException extends Exception {

	private static final long serialVersionUID = 1L;

	public UpdateTimelineFailException() {
		super("N‹o foi poss’vel atualizar a sua timeline");
	}
}
