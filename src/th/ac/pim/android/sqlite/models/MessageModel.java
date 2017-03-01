package th.ac.pim.android.sqlite.models;

import java.io.Serializable;

public class MessageModel implements Serializable {

	private int id;
	private String message;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public String toString(){
		return message;
	}
}
