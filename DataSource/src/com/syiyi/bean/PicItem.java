package com.syiyi.bean;

public class PicItem {
	private String imgeUrl;
	private String title;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PicItem(String imgeUrl) {
		super();
		this.imgeUrl = imgeUrl;
	}

	public String getImgeUrl() {
		return imgeUrl;
	}

	public void setImgeUrl(String imgeUrl) {
		this.imgeUrl = imgeUrl;
	}
	
}
