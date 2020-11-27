package com.fm.modules.models;

import androidx.annotation.LongDef;

public class Image {
	
	private Long id;
	private String name;
	private String contentType;
	private byte[] content;
		
	public Image() {
	}
	
	public Image(Long id, String name, String contentType, byte[] content) {
		super();
		this.id = id;
		this.name = name;
		this.contentType = contentType;
		this.content = content;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	
}
