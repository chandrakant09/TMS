package com.lei.utility.constants;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public enum FileSourceConstants {
	UPLOAD("UPLOAD","UPLOAD/"),
	REPORT("REPORT","REPORT/");
	
	private String source;
	private String path;
	
	private FileSourceConstants(String source,String path) {
		this.source=source;
		this.path = path;
	}
	
	public String getSource() {
		return source;
	}
	public String getPath() {
		return path;
	}
}
