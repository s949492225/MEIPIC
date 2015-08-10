package com.syiyi.bean;

/**
 * Created by songlintao on 15/8/5.
 */
public class DirItem {
    private String imgeUrl;
    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DirItem(String imgeUrl) {
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
