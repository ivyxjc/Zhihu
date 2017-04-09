package xyz.ivyxjc.zhihu.utils;

import xyz.ivyxjc.zhihu.R;

/**
 * Created by jc on 11/29/2016.
 */

public class NightModeUtil {
    private static int theme= R.style.AppTheme_DAY;

    public static boolean isNigthMode(){
        return theme==R.style.AppTheme_Night;
    }

    public static void setTheme(int t){
        theme=t;
    }
}
