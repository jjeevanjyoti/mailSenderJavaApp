package com.assignment.springboot.mongo.model;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 



@Document(collection= "employee")
public class Employee {
 
	ArrayList<String> emails=new ArrayList<String>();

	private String[] emaillist;
 
 
    public ArrayList<String> getEmails() {
		return emails;
	}


	public void setEmails(ArrayList<String> emails) {
		this.emails = emails;
	}


	public String[] getList() {
		return emaillist;
	}


	public void setList(String[] emaillist) {
		this.emaillist = emaillist;
	}


	@Override
    public String toString() {
        return "emaillist [emaillist=" +emaillist +"]";
    }
}