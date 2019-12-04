package com.mickey.servletFilters.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class DemoFilter
 */
@WebFilter("/FilterDemoServlet")
public class DemoFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		response.getWriter().println("Starting from filter");

		// pass the request along the filter chain
		chain.doFilter(request, response);
		response.getWriter().println("Ending at filter");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("from filter init() method");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
