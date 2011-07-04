package g3.twitter.model;

public class Tweet {
	private String message;
	private User user;
	
	public boolean post (String message, User user){
		boolean posted = false;
		if(message.length()>0 && message.length()<141){
			this.user = user;
			this.message = message;
			posted = true;
		}
		return posted;
	}
	
	public String show(){
		return message;
	}

	public User author(){
		return user;
	}
}
