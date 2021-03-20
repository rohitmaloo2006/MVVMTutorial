package maloo.mvvmtutorial.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import maloo.mvvmtutorial.R;
import maloo.mvvmtutorial.model.TopNews;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    private List<TopNews> mTopNews;
    private final Context mContext;
    private NewsListFragment.ItemClickListener mItemClickLister;

    public NewsListAdapter(Context context, NewsListFragment.ItemClickListener itemClickListener) {
        mContext = context;
        mItemClickLister = itemClickListener;
        mTopNews = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View contactView = inflater.inflate(R.layout.news_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TopNews topNews = mTopNews.get(position);
        TextView newsText = holder.newsTextView;
        newsText.setText(topNews.getTitle());
        ImageView newsImage = holder.newsImageView;
        RequestOptions defaultoption = new RequestOptions().error(R.drawable.ic_launcher_background);
        Glide.with(mContext).setDefaultRequestOptions(defaultoption).load(topNews.getUrlToImage()).into(newsImage);
        holder.newsImageView.setOnClickListener((view) -> {
            mItemClickLister.onItemClicked(topNews, position);
            Navigation.findNavController(view).navigate(R.id.newsDetailFragment);
        });

    }

    @Override
    public int getItemCount() {
        return mTopNews.size();
    }

    public void updateNewsList(List<TopNews> newsList) {
        mTopNews = newsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView newsTextView;
        private final ImageView newsImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            newsTextView = itemView.findViewById(R.id.contact_name);
            newsImageView = itemView.findViewById(R.id.message_button);
        }
    }
}
