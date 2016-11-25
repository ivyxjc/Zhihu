package com.jc.zhihu.model;

/**
 * Created by jc on 11/24/2016.
 */

public class ListModel {
    private String title;
    private String titleImage;
    private String url;

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailUrl() {
        return url;
    }

    public void setDetailUrl(String url) {
        this.url = url;
    }
}
