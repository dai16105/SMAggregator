package gr.uom.smaggregator;

import android.os.AsyncTask;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class PostTweet extends AsyncTask<String, Void, Status> {

    private final String query;
    // Create a tweet, posting the given query.
    public PostTweet (String query){
        this.query = query;
    }

    Twitter twitter = GetTwitterConfig.getInstance();
    @Override
    protected twitter4j.Status doInBackground(String... strings) {

        twitter4j.Status tweet = null;
        try {
            tweet = twitter.updateStatus(query);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return tweet;
    }
}
