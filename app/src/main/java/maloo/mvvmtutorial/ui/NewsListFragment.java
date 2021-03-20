package maloo.mvvmtutorial.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import maloo.mvvmtutorial.R;
import maloo.mvvmtutorial.model.TopNews;
import maloo.mvvmtutorial.utils.AppConstants;
import maloo.mvvmtutorial.viewmodel.NewsViewModel;

@AndroidEntryPoint
public class NewsListFragment extends Fragment {

    private static final String TAG = "NewsListFragment";
    private NewsViewModel mNewsViewModel;
    private LinearLayoutManager mLayoutManager;
    private NewsListAdapter mNewsListAdapter;
    private RecyclerView mNewsRecyclerView;

    public interface ItemClickListener {
        void onItemClicked(TopNews item, int pos);
    }

    private ItemClickListener itemClickListener = new ItemClickListener() {
        @Override
        public void onItemClicked(TopNews topNews, int pos) {
            mNewsViewModel.setSelectedNews(topNews);
        }
    };

    public NewsListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        initializationViews(view);
        fetchNewsAndUpdateView();
        return view;
    }

    private void fetchNewsAndUpdateView() {
        mNewsViewModel.getTopNews(AppConstants.COUNTRY_NAME).observe(getViewLifecycleOwner(), news -> {
            Log.d(TAG, " fragment " + news.getTotalResults());
            List<TopNews> newsList = new ArrayList<>();
            newsList.addAll(news.getArticles());
            mNewsListAdapter.updateNewsList(newsList);
            mNewsListAdapter.notifyDataSetChanged();
        });

    }

    private void initializationViews(View view) {
        mNewsRecyclerView = view.findViewById(R.id.news_list_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mNewsRecyclerView.setLayoutManager(mLayoutManager);
        mNewsRecyclerView.setHasFixedSize(true);
        mNewsViewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);
        mNewsListAdapter = new NewsListAdapter(requireActivity(), itemClickListener);
        mNewsRecyclerView.setAdapter(mNewsListAdapter);
    }
}