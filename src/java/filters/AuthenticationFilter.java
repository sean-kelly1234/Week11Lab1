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

public class AuthenticationFilter implements Filter {
     
    private FilterConfig filterConfig = null;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        // before the servlet
        // authenticate here
        
        HttpServletRequest httprequest = (HttpServletRequest) request;
        HttpSession session = httprequest.getSession();
        
        if (session.getAttribute("username") != null) {
            // continue on to the next filter or servlet
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpresponse = (HttpServletResponse) response;
            httpresponse.sendRedirect("login");
        }
        
        
        // after the servlet
        
        
    }

    @Override
    public void destroy() {        
    }

    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
    }

    
}
