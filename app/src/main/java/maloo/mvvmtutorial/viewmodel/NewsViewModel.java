package maloo.mvvmtutorial.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import maloo.mvvmtutorial.model.TopNews;
import maloo.mvvmtutorial.repository.DataRepository;
import maloo.mvvmtutorial.response.TopNewsResponse;

@HiltViewModel
public class NewsViewModel extends AndroidViewModel {

    private DataRepository mDataRepository;

    private MutableLiveData<TopNews> mSelectTopNews = new MutableLiveData<>();

    private MutableLiveData<TopNewsResponse> mListOfNews = new MutableLiveData<>();

    @Inject
    public NewsViewModel(@NonNull Application application, DataRepository dataRepository) {
        super(application);
        this.mDataRepository = dataRepository;
    }

    public MutableLiveData<TopNewsResponse> getTopNews(String countryName) {
        mListOfNews = loadNewsData(countryName);
        return mListOfNews;
    }

    private MutableLiveData<TopNewsResponse> loadNewsData(String category) {
        return mDataRepository.getNewsArticles(category);
    }


    public void setSelectedNews(TopNews item) {
        mSelectTopNews.setValue(item);
    }

    public MutableLiveData<TopNews> getSelectedNews() {
        return mSelectTopNews;
    }
}
