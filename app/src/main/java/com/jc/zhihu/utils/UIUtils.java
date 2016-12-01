//package com.jc.zhihu.utils;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//
//import com.jc.zhihu.App;
//
///**
// * Created by jc on 11/29/2016.
// */
//
//public class UIUtils {
//    public static Context getContext() {
//        return App.getInstance();
//    }
//    public static void startActivity(Class<?> clz) {
//        Intent intent = new Intent(getContext(), clz);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        getContext().startActivity(intent);
//    }
//
//    public static void startActivity(Class<?> clz, Bundle bundle) {
//        Intent intent = new Intent(getContext(), clz);
//        intent.putExtras(bundle);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        getContext().startActivity(intent);
//    }
//}
