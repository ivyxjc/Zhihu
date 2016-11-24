package com.jc.zhihu.network;

import com.jc.zhihu.Model.DetailModel;
import com.jc.zhihu.Model.ListModel;

import java.util.List;


import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jc on 11/24/2016.
 */

public class API {

    public static final String BASE_URL="https://zhuanlan.zhihu.com/api/";

    private static final String LIST_API = "columns/";

    private static final String DETAIL_API = "posts/";

    public interface ZhihuService{
        @GET(LIST_API+"{suffix}/posts")
        Observable<List<ListModel>> getList(@Path("suffix") String suffix, @Query("limit") int limit,@Query("offset") int offset);

        @GET(DETAIL_API + "{slug}")
        Observable<DetailModel> getDetail(@Path("slug") int slug);
    }
}
