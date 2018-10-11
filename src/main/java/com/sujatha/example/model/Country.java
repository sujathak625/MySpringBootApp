package com.sujatha.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Country")
public class Country {
	String name;
	String code;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
