package gr.uom.smaggregator;

import androidx.appcompat.app.AppCompatActivity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Twitter API Keys
   private static final String TWITTER_CONSUMER_KEY= BuildConfig.TWITTER_CONSUMER_KEY;
   private static final String TWITTER_CONSUMER_SECRET_KEY = BuildConfig.TWITTER_CONSUMER_SECRET_KEY;
   private static final String TWITTER_ACCESS_TOKEN = BuildConfig.TWITTER_ACCESS_TOKEN;
   private static final String TWITTER_ACCESS_SECRET_TOKEN = BuildConfig.TWITTER_ACCESS_SECRET_TOKEN;

   // TAGS
    public static final String TAG = "Rest Twitter Posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView tweetList = findViewById(R.id.postListView);

        PostArrayAdapter postArrayAdapter =
                new PostArrayAdapter(this,
                        R.layout.list_record,
                        new ArrayList<Status>(),
                        tweetList
                );


        GetTwitterData twitterData = new GetTwitterData(postArrayAdapter);
        twitterData.execute();






    }




}