package ra.com.common;



import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.*;

import com.sun.mail.util.MailSSLSocketFactory;

import java.security.GeneralSecurityException;
import java.util.Date;



public class MailUtil {
	
	public static void sendMail(String[] to,String subject,String messageText){
	InternetAddress[] address = null; 
 
    Authenticator auth = (Authenticator) new PopupAuthenticator("service@sen-pro.com","yuanzheng@2009");
 
    String mailserver   = "smtp.qiye.163.com";
    String From         = "service@sen-pro.com"; 
    //String Subject      = "Subject";
    //String messageText  = "û�����";
 if(to==null||"".equals(to)){
	 to[0]="service@sen-pro.com";
 }
    boolean sessionDebug = false;
     
try {
 System.out.println("@@@start send email");
  // �趨��Ҫ�õ�Mail ����������ʹ�õĴ���Э��
  java.util.Properties props = System.getProperties();
  props.put("mail.host",mailserver);
  props.put("mail.transport.protocol","smtp");
  props.put("mail.smtp.auth", "true");
  MailSSLSocketFactory sf = new MailSSLSocketFactory();  
  sf.setTrustAllHosts(true);
  props.put("mail.smtp.ssl.enable", "true");  
  props.put("mail.smtp.ssl.socketFactory", sf); 
  props.put("mail.smtp.port","465");
  // �����µ�Session ����
  javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props,auth);
  mailSession.setDebug(sessionDebug);
  Message msg = new MimeMessage(mailSession);
   
  // �趨�����ʼ��ķ�����
  msg.setFrom(new InternetAddress(From));
   
  // �趨�����ʼ��������˵�����
//  address = InternetAddress.parse(to,false);
//  msg.setRecipients(Message.RecipientType.TO, address);
   
  
  InternetAddress[] sendTo = new InternetAddress[to.length]; 
  for (int i = 0; i < to.length; i++) 
     { 
	  if(to[i]==null||"".equals(to[i])){
   	   to[i] = "service@sen-pro.com";
      }
       System.out.println("���͵�:" + to[i]); 
       sendTo[i] = new InternetAddress(to[i]); 
     } 

  msg.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO, sendTo); 
  
  
  // �趨���е����� 
  msg.setSubject(subject);
  // �趨���ŵ�ʱ��
  msg.setSentDate(new Date());
   
  // �趨�����ŵ�MIME Type
  msg.setText(messageText);
 
  // ����
  Transport.send(msg);
 
     
}
    catch (MessagingException mex) {
 
      mex.printStackTrace();
    } catch (GeneralSecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}