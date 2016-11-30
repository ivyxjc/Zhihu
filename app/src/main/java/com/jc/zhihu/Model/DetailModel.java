package com.jc.zhihu.model;

import java.io.Serializable;

/**
 * Created by jc on 11/24/2016.
 */

public class DetailModel implements Serializable {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
