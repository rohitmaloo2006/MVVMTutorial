package maloo.mvvmtutorial.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import maloo.mvvmtutorial.response.TopNewsResponse;
import maloo.mvvmtutorial.retrofit.APIRequest;
import maloo.mvvmtutorial.utils.AppConstants;


public class DataRepository {

    private APIRequest apiRequest;

    @Inject
    DataRepository(APIRequest apiRequest) {
        this.apiRequest = apiRequest;
    }


    private final MutableLiveData<TopNewsResponse> listOfNews = new MutableLiveData<>();

    public MutableLiveData<TopNewsResponse> getNewsArticles(String query) {
        apiRequest.getTopNews(query, AppConstants.API_KEY).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError);
        return listOfNews;
    }

    private void onError(Throwable t) {
        listOfNews.setValue(null);
        t.printStackTrace();
    }

    private void onSuccess(TopNewsResponse response) {
        listOfNews.setValue(response);
    }

}
