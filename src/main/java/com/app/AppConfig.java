package com.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

	@Autowired
	RequestInterceptor requestInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor);
	}

	@Component
	public class RequestInterceptor extends HandlerInterceptorAdapter {

		/**
		 * This is not a good practice to use sysout. Always integrate any
		 * logger with your application. We will discuss about integrating
		 * logger with spring boot application in some later article
		 */
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object)
				throws Exception {
			System.out.println("____________________________________________");
			String requestURI = request.getRequestURI();
			System.out.println("preHandle  intercept : RequestURI::" + requestURI);
			return true;
		}

		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
				ModelAndView model) throws Exception {
			System.out.println("postHandle intercept :" + " completed by @RestController");
		}

		@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
				Exception e) throws Exception {
			System.out.println("afterCompletion intercept : Request Completed.");
			if (e != null)
				System.out.println("exception  :" + e);
			System.out.println("________________________________________");
		}
	}
}