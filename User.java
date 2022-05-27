package homework_one;
import java.util.Scanner;
import java.io.Serializable; 

public class User extends course implements java.io.Serializable{
	//private static final long serialVersionUID = 1275848076531388221L;
	public String userName;
	public String password;
	public String firstName;
	public String lastName;
    
	public User() {	
	}
	
	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public User(String userName, String password, String firstName, String lastName) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	// getters and setters
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassWord() {
		return password;
	}
	
	public void setPassWord(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
    
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
    
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	


}
