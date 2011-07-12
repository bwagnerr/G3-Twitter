package g3.twitter.model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Tweet {

	private String author;
	private String message;
	private Date timestamp;
	
	public Tweet(String author, String message, Date timestamp){
		this.author = author;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	public String author(){
		return author;
	}
	
	public String message(){
		return message;
	}
	
	public String timestamp(){
		SimpleDateFormat tweetFormat = new SimpleDateFormat("HH:mm - dd/MMM/yy");
		String dateString = new String( tweetFormat.format( timestamp ) );
		return dateString;
	}
	
	@Override
	public String toString(){
		return "\n"+author+":\n"+message+"\nPosted at: "+timestamp()+"\n"; 
	}
}
