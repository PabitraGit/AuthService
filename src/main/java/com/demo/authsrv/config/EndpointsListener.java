package com.demo.authsrv.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Service
public class EndpointsListener implements ApplicationListener {
	
	private List<Object> listURI = new ArrayList<>();

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent) {
            ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
            applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods().forEach(
            		(key, value) -> listURI.add(key.getPatternsCondition().getPatterns()));
        }
	}
	
	public List<String> getAllURIs(){
		return listURI.stream()
		.map(setStr -> {
			String str = setStr.toString();
			String finalStr = str.substring(1,str.length()-1);
			return String.valueOf(finalStr);
		})
		.collect(Collectors.toList());
	}

}
