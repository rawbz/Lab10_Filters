/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rmjba
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

   @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                //any code before chain.dofilter will be executed before the sevlet  
        
        //get access to a session 
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        
        String email = (String) session.getAttribute("email");
        
        //check if the email is null, then redirect to the home (not logged in)
        if(email == null){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("login");
            return; //stop the code call - very important
            //always have a return after a redirect / response call 
        }
        
        
        //this will either call upon the next filter in the chain or it will lo0ad the requested sevlet 
        //if you dont have this, the request will stop here 
        chain.doFilter(request, response); 
        
        //any code after the chain will be executed after the servlet 
    }

    @Override
    public void destroy() {}
    

}
