package xyz.ivyxjc.zhihu.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by ivyxjc on on 2016/11/25.
 * This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
 */

public class ImageLoadUtil {
    public static void load(Context context, ImageView imageView,String url){
        RequestManager requestManager= Glide.with(context);
        requestManager
                .load(url)
                //硬盘存储策略
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //是否启用内存缓存 默认为true
                .skipMemoryCache(false)
                .centerCrop()
                .into(imageView);
    }
}
