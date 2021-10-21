package com.kh.spring.common.mail;

import java.util.HashMap;
import java.util.Map;

public class MailTemplate {

	private String templateName;
	private Map<String, String> componet = new HashMap<>();
	
	public MailTemplate(String templateName) {
		this.templateName = templateName;
	}
	
	public void addMailComponent(String name, String component) {
		componet.put(name, component);
	}
	
	
	
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public void setComponet(Map<String, String> componet) {
		this.componet = componet;
	}

	public String getTemplateName() {
		return templateName;
	}

	public Map<String, String> getMailComp(){
		return componet;
	}
	
}
