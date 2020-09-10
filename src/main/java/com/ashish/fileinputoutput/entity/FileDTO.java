package com.ashish.fileinputoutput.entity;

import java.util.Map;

public class FileDTO {

	private String name;
	private String data;
	private String path;
	private String modifiedDate;
//	private Map<String,String> fileDetails;
//	private Map<String,String> fileModifiedDate;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	/*
	 * public Map<String, String> getFileDetails() { return fileDetails; } public
	 * void setFileDetails(Map<String, String> fileDetails) { this.fileDetails =
	 * fileDetails; } public Map<String, String> getFileModifiedDate() { return
	 * fileModifiedDate; } public void setFileModifiedDate(Map<String, String>
	 * fileModifiedDate) { this.fileModifiedDate = fileModifiedDate; }
	 */
	
	
	
	
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	

	public FileDTO(String name, String data, String path, String modifiedDate) {
		super();
		this.name = name;
		this.data = data;
		this.path = path;
		this.modifiedDate = modifiedDate;
	}
	public FileDTO() {
		
	}
	@Override
	public String toString() {
		return "FileDTO [name=" + name + ", data=" + data + ", path=" + path + ", modifiedDate=" + modifiedDate + "]";
	}
	
	
	
}
