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

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Twitter API Keys
    private static final String TWITTER_CONSUMER_KEY = BuildConfig.TWITTER_CONSUMER_KEY;
    private static final String TWITTER_CONSUMER_SECRET_KEY = BuildConfig.TWITTER_CONSUMER_SECRET_KEY;
    private static final String TWITTER_ACCESS_TOKEN = BuildConfig.TWITTER_ACCESS_TOKEN;
    private static final String TWITTER_ACCESS_SECRET_TOKEN = BuildConfig.TWITTER_ACCESS_SECRET_TOKEN;


    // TAGS
    public static final String TAG = "Rest Twitter Posts";

    private static final String EMAIL = "email";
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = this.context;

        //        Twitter twitter = tf.getInstance();

        Log.d(TAG, "OnCreate: Starting Web Service!");
        GetTwitterData twitterData = new GetTwitterData();

        twitterData.execute();
        Log.d(TAG, "Finished Web Service!");


        //FaceBook


        callbackManager = CallbackManager.Factory.create();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();


        loginButton = findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
             Log.i("makis ",loginResult.getAccessToken().getToken());



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
        Log.i("makis ",isLoggedIn + " ola ka ");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


}