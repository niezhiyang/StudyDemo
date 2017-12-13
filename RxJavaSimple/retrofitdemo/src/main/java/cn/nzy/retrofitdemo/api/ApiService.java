package cn.nzy.retrofitdemo.api;

import cn.nzy.retrofitdemo.bean.MovicBean;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * on 2017/12/12.
 * 类的描述:
 */

public interface ApiService {
    @GET("movie/list.json?offset=0&limit=20")
    Call<ResponseBody> getNetData();
    @GET("movie/list.json?offset=0&limit=20")
    Call<MovicBean> getBeanData();
}
