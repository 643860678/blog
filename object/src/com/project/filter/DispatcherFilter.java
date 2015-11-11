package com.project.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartResolver;

import com.project.security.utils.XssRequestWapper;

public class DispatcherFilter implements Filter {

	private Log logger;
	private MultipartResolver multipartResolver;

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain paramFilterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		request = checkMultipart(httpServletRequest);
		paramFilterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

	private HttpServletRequest checkMultipart(HttpServletRequest request)
			throws MultipartException {
		if ((this.multipartResolver != null)
				&& (this.multipartResolver.isMultipart(request))) {
			if (this.logger.isInfoEnabled()) {
				this.logger.debug("Request is a MultipartHttpServletRequest");
			}
			if ((request instanceof HttpServletRequest)) {
				if (this.logger.isDebugEnabled()) {
					this.logger
							.debug("Resolve HttpServletRequest to a MultipartHttpServletRequest");
				}
				return this.multipartResolver.resolveMultipart(request);
			}
		} else {
			request = new XssRequestWapper(request);
		}
		return request;
	}
}
