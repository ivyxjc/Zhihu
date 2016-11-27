package com.jc.zhihu.utils;

/**
 * Created by jc on 11/26/2016.
 */

public class HtmlUtil {
    public static String getHtml(String content){
        String css;
        css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu.css\" type=\"text/css\">";

        String html="<!DOCTYPE html>\n" +
                "<html lang=en xmlns=http://www.w3.org/1999/xhtml>\n" +
                "<head>\n" +
                "  <meta charset=utf-8>\n" +
                "</head>\n" +
                css +"\n"+
                "<body>\n" +
                content +"\n"+
                "</body>\n" +
                "</html>";
        return html;
    }

    public static String getMimeType(){
        return "text/html";
    }

    public static String getCoding(){
            return "utf-8";
    }
}
