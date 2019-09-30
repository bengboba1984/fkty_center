package ra.com.scheduler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimerTask;

import javax.servlet.ServletContext;


public class CollectorLedJob extends TimerTask {

	//private static boolean isRunning = false; 
	private ServletContext context = null; 
	
	public CollectorLedJob(ServletContext context) {
		this.context = context; 
	}

//	public CollectorGVFJob() {
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public void run() { 
		boolean flag=false;
		Calendar cal = Calendar.getInstance();         
       // if (!isRunning)  {                     
               // isRunning = true;                 
                context.log("Start CollectorLedJob !"); 
               
                
                context.log("End CollectorLedJob !"); 
	}

	private boolean isPortUsing(int port) throws UnknownHostException {
		boolean flag=false;
		ServerSocket s;
		try {
			s=new ServerSocket(port);
			s.close();
			flag=true;
		} catch (IOException e) {
			flag=false;
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return flag;
	}
	
	
}