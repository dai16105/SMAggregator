package gr.uom.smaggregator;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import static gr.uom.smaggregator.BuildConfig.TWITTER_ACCESS_SECRET_TOKEN;
import static gr.uom.smaggregator.BuildConfig.TWITTER_ACCESS_TOKEN;
import static gr.uom.smaggregator.BuildConfig.TWITTER_CONSUMER_KEY;
import static gr.uom.smaggregator.BuildConfig.TWITTER_CONSUMER_SECRET_KEY;



public class GetTwitterData extends AsyncTask<String, Void, String> {



    private Twitter twitter;

    public static final String TAG = "Twitter Query Data";
    // Get Auth credentials in an interface
    public Configuration getConfiguration() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET_KEY)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_SECRET_TOKEN);
        return cb.build();
    }
    // Get the Twitter user credentials
    public Twitter getTwitter() {
        if (twitter == null) {
            twitter = new TwitterFactory(getConfiguration()).getInstance();
        }
        return twitter;
    }


//    public static void getMostTrendingTweets(Twitter twitter){
//
//    }



    @Override
    protected String doInBackground(String... strings) {

        Twitter twitter = getTwitter();
        try{
            Query query = new Query("#wow");
            QueryResult result = twitter.search(query);
            int c=0;
            for (twitter4j.Status status : result.getTweets()) {
                Log.d(TAG,"Status@\t" + status.getUser().getScreenName() + "\t:\t" + status.getText());
                c+=1;
            }
            Log.d(TAG,"SIZE=== "+c);
        }catch(Exception e){
            Log.e(TAG,"Something happened....",e);
        }
        return "RESULT!!!!!!!!!";
    }
}
