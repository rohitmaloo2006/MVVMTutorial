package maloo.mvvmtutorial.retrofit;

import io.reactivex.rxjava3.core.Single;
import maloo.mvvmtutorial.response.TopNewsResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIRequest {

    @GET("v2/top-headlines")
    Single<TopNewsResponse> getTopNews(@Query("country") String country, @Query("apikey") String apikey);

}
