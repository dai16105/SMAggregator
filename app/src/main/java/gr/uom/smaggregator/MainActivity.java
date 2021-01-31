package gr.uom.smaggregator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // TAGS
    public static final String TAG = "Rest Twitter Posts";

    private static final String EMAIL = "email";
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private Context context;
    private PostArrayAdapter postArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView tweetList = findViewById(R.id.postListView);
        Button postSomething = findViewById(R.id.post_button);

        SearchView searchBar = findViewById(R.id.searchView);

        SearchListener searchListener = new SearchListener();
        postArrayAdapter = new PostArrayAdapter(this,
                        R.layout.list_record,
                        new ArrayList<>(),
                        tweetList);

        searchBar.setOnQueryTextListener(searchListener);

        postSomething.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PostActivity.class));
            }
        });

        tweetList.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,"Clicked cell number:" + position);


                // --------------------------> BUG! <-----------------------
                //----------------------------------------------------------------------------

                /* Click listener where you fetch the specific tweet and pass it to another activity
                PostDetails to be rendered.
                OutOfBoundsException, maybe because the tweetlist itself is empty and I don't know how to get
                the array list filled with tweets.

                   Intent intent = new Intent(getBaseContext(),PostDetails.class);

                intent.putExtra("userId",tweet.getUser().getScreenName());
                intent.putExtra("postBody",tweet.getText());

                DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                intent.putExtra("timestamp",dateFormat.format(tweet.getCreatedAt()));


                ----------------------------------------------------------------------
                ----------------------------------------------------------------------
                */
            }
        });
    }



    private class SearchListener implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String query) {
            Log.d(TAG, "Text submitted!");
            if (!query.isEmpty()) {
                hideKeyboard();
                String[] params = new String[]{query};
                Toast.makeText(getApplicationContext(),"Searching...",Toast.LENGTH_SHORT).show();

                new GetTwitterData(postArrayAdapter).execute(params);
                GetTwitterData tweets = new GetTwitterData(postArrayAdapter);
                
                return true;
            } else {
                return false;
            }

        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }



    }

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = MainActivity.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


        //FaceBook
        // --------------------------------------------------------------------------

     /*   context = this.context;


        callbackManager = CallbackManager.Factory.create();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();


        loginButton = findViewById(R.id.facebook_login_button3);
        loginButton.setPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("makis ", loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        LoginManager.getInstance().logInWithReadPermissions(this, Collections.singletonList("public_profile"));
        Log.i("makis ", isLoggedIn + " ola ka ");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

     // ---------------------------------------------------------------------

    }*/



}

