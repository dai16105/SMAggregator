package gr.uom.smaggregator;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import static com.facebook.FacebookSdk.getApplicationContext;


public class GetTwitterData extends AsyncTask<String, Void, List<Status>> {
    private PostArrayAdapter adapter;

    public List<twitter4j.Status> Tweets;

    public static final String TAG = "Twitter Query Data";

    public GetTwitterData (PostArrayAdapter adapter){
        this.adapter = adapter;
    }


// -----   Get the 100 most recent tweets from the last 7 days, based on a given Query and download them on a list.
    @Override
    protected List<twitter4j.Status> doInBackground(String... params) {

        Twitter twitter = GetTwitterConfig.getInstance();
        Query query = new Query(params[0]);
        query.setCount(100);
        QueryResult result = null;

        try {
            result = twitter.search(query);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return  result.getTweets();
    }

    // -----   Parse the tweets via the adapter on the list view
    @Override
    protected void onPostExecute(List<twitter4j.Status> statuses) {
        super.onPostExecute(statuses);

         Tweets = statuses;
         Log.d(TAG, "HERE ARE THE RESULTS!!!!!");
         Log.d(TAG, "--------------------------");
        // If there are no tweets found, create a pop-up notifying the user.
        if (Tweets == null || Tweets.isEmpty()) {
             Log.d(TAG, "The list is empty !");
            Toast.makeText(getApplicationContext(),"No posts were found!",Toast.LENGTH_LONG).show();
        }
        for (twitter4j.Status tweet: Tweets) {
            Log.d(TAG, "A tweet from: @\t" + tweet.getUser().getScreenName() + tweet.getText());
        }
        adapter.setPostList(Tweets);
    }
}

