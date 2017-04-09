package xyz.ivyxjc.zhihu.network;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import xyz.ivyxjc.zhihu.model.DetailModel;
import xyz.ivyxjc.zhihu.model.ListModel;

/**
 * Created by jc on 11/24/2016.
 */

public class API {

    public static final String BASE_URL="https://zhuanlan.zhihu.com/api/";

    private static final String LIST_API = "columns/";

    private static final String DETAIL_API = "posts/";

    public interface ZhihuService{
        @GET(LIST_API+"{suffix}/posts")
        Observable<ArrayList<ListModel>> getList(@Path("suffix") String suffix, @Query("limit") int limit, @Query("offset") int offset);

        @GET(DETAIL_API + "{slug}")
        Observable<DetailModel> getDetail(@Path("slug") int slug);
    }
}
