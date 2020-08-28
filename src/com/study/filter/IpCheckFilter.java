package com.study.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;


public class IpCheckFilter implements Filter{
	
	private Map<String, String> denyIPMap;
	
	@SuppressWarnings("unchecked")
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 초기화
		denyIPMap = new HashedMap();
		//거부 아이피
		denyIPMap.put("192.168.20.42", "Critical");
		denyIPMap.put("192.168.20.38", "Normal");
		denyIPMap.put("192.168.20.39", "Critical");
		denyIPMap.put("192.168.20.29", "Critical");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;

		for (Map.Entry<String, String> dIP : denyIPMap.entrySet()) {
			if (req.getRemoteAddr().equals(dIP.getKey())) {
				
				PrintWriter out = response.getWriter();
				resp.setContentType("text/html; charset=utf-8");
				out.print(dIP.getValue() + "!!! 거부된 아이피 입니다 -->" + dIP.getKey());
				return;
				
			}else {
				chain.doFilter(request, response);
			}
		}
		
		

	
	
	}

}
