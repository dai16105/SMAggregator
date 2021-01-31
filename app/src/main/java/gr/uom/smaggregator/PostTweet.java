package gr.uom.smaggregator;

import android.os.AsyncTask;

import java.util.List;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class PostTweet extends AsyncTask<StatusUpdate, Void, Status> {

    private final twitter4j.StatusUpdate status;

    public PostTweet (twitter4j.StatusUpdate status){
        this.status = status;
    }

    Twitter twitter = getTwitterConfig.getInstance();

    @Override
    protected twitter4j.Status doInBackground(twitter4j.StatusUpdate... params) {

        twitter4j.Status tweet = null;
        try {
            tweet = twitter.updateStatus(status);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return tweet;
    }
}
