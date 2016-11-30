package com.jc.zhihu.model;

import java.io.Serializable;

/**
 * Created by jc on 11/24/2016.
 */

public class ListModel implements Serializable{
    private String title;
    private String titleImage;
    private String url;

    private int slug;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSlug() {
        return slug;
    }

    public void setSlug(int slug) {
        this.slug = slug;
    }

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
