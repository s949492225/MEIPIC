package com.syiyi.bean;

/**
 * Created by songlintao on 15/8/6.
 */
public class PageItem {
    private String url;
    private String title;

    public PageItem(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
