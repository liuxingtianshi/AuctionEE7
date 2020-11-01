package com.icss.listener;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletResponse;


public class AuctionListener implements ServletContextListener{
	private static final BlockingQueue<AsyncContext> queue = new LinkedBlockingQueue<AsyncContext>();  

	  private volatile Thread thread;  
	  
	  public static void add(AsyncContext c) {
	    queue.add(c);  
	  }  
	  
	  @Override  
	  public void contextInitialized(ServletContextEvent servletContextEvent) {  
	    thread = new Thread(new Runnable() {  
	      @Override  
	      public void run() {  
	        while (true) {  	                   
	            AsyncContext acontext = null;  
	            while (queue.peek() != null) {  
	              try {  
	            	acontext = (AsyncContext)queue.poll();
	                ServletResponse response = acontext.getResponse();  
	                PrintWriter out = response.getWriter();	               		        
    		        Thread.sleep(1000);  
    		        String name = Thread.currentThread().getName();  
    		        long duration = System.currentTimeMillis();
    		      	  
    		  	    out.println(acontext.getRequest().getAttribute("linenum") + " "+ name +" "+ duration);
    		  	    out.close();
	      		   
	              } catch (Exception e) {  
	                throw new RuntimeException(e.getMessage(), e);  
	              } finally {  
	            	  if(acontext != null)
	                       acontext.complete();  
	              }  
	            }  	         
	        }  
	      }  
	    });  
	    thread.start();  
	  }  
	  
	  @Override  
	  public void contextDestroyed(ServletContextEvent servletContextEvent) {  
	    thread.interrupt();  
	  }  
}
