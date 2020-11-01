package com.icss.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns="/AuctionSynSvl")
public class AuctionSynSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuctionSynSvl() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();		
		 try { 		        
		      Thread.sleep(1000);  
		      String name = Thread.currentThread().getName();  
		      long duration = System.currentTimeMillis();
		      String linenum = request.getParameter("lineNum");			  
		  	  out.println(linenum + " "+ name +" "+ duration);
		  	  out.close();
		  } catch (Exception e) {  
		      throw new RuntimeException(e.getMessage(), e);  
		  }  
	}

	

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
