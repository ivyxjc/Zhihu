package com.jc.zhihu.utils;

import com.jc.zhihu.Constant;

/**
 * Created by ivyxjc on on 2016/11/30.
 * This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
 */

public class DataUtil {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Constant.TABLE_NAME + "IF NOT EXISTS"+" (" +
                    "id" + " INTEGER PRIMARY KEY," +
                    "zhuanlan_title"+" String"+
                    "article_title"+" String"+
                    "article_content"+" String"+")";

}
