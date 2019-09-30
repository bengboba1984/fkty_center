package ra.com.common;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class PopupAuthenticator extends Authenticator {
	String username, password;
	 
    public PopupAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }
 
    public PasswordAuthentication getPasswordAuthentication() { 
 
        return new PasswordAuthentication(username, password);  
        }
}
