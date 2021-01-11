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

import android.os.Bundle;
import android.util.Log;

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



//        Twitter twitter = tf.getInstance();

        Log.d(TAG, "OnCreate: Starting Web Service!");
        GetTwitterData twitterData = new GetTwitterData();

        twitterData.execute();
        Log.d(TAG, "Finished Web Service!");





    }




}