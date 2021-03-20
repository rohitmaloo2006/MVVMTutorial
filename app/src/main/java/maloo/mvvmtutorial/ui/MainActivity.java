package maloo.mvvmtutorial.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import maloo.mvvmtutorial.R;
import maloo.mvvmtutorial.model.TopNews;
import maloo.mvvmtutorial.repository.DataRepository;
import maloo.mvvmtutorial.utils.AppConstants;
import maloo.mvvmtutorial.viewmodel.NewsViewModel;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  newsViewModel = new ViewModelProvider(MainActivity.this).get(NewsViewModel.class);
    }
}