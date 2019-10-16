package ra.com.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ra.com.common.email.MailUtil;

/**
 * Application Lifecycle Listener implementation class SchedulerListener
 *
 */
public class SchedulerListener implements ServletContextListener {
	Timer timer; 
  
    /**
     * Default constructor. 
     */
    public SchedulerListener() {
        // TODO Auto-generated constructor stub
    }  
 
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
        timer=new Timer(true);
//        event.getServletContext().log("SchedulerListener: Create Timer!");
        
//        timer.schedule(new CollectorLedJob(event.getServletContext()),0 ,3*60*1000);//姣�3鍒嗛挓sync涓�娆�
//        event.getServletContext().log("SchedulerListener: Add Schedule (CollectorLedJob)");
   }  
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)  { 
    	timer.cancel(); 
        event.getServletContext().log("SchedulerListener:Timer Destroyed!"); 
    }
	
    private Date addDay(Date date, int num) {
    	  Calendar startDT = Calendar.getInstance();
    	  startDT.setTime(date);
    	  startDT.add(Calendar.DAY_OF_MONTH, num);
    	  return startDT.getTime();
    	 }

}
