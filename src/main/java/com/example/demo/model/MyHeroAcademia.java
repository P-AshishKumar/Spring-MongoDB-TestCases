package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Heroes")
public class MyHeroAcademia {

	@Id
	private long id;
	private String name;
	private String quirk;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuirk() {
		return quirk;
	}

	public void setQuirk(String quirk) {
		this.quirk = quirk;
	}

}
