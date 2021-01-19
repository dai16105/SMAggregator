package gr.uom.smaggregator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "Facebook";

    CallbackManager callbackManager;
    TextView textView1;
    TextView textViewLogin;
    ImageView imageViewMyPic;
    LoginButton loginButton;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    RecyclerView.LayoutManager layoutManager;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loginButton = findViewById(R.id.facebook_login_button);
        textView1 = findViewById(R.id.textView);
        textViewLogin = findViewById(R.id.textViewMyName);
        imageViewMyPic = findViewById(R.id.imageView);
        recyclerView = findViewById(R.id.rv_friend_list);

        textView1.setVisibility(View.INVISIBLE);

        imageViewMyPic.setVisibility(View.INVISIBLE);

        callbackManager = CallbackManager.Factory.create();

        loginButton.setPermissions(Arrays.asList("user_friends, public_profile"));


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Demo", "We succeded");

                textViewLogin.setVisibility(View.VISIBLE);
                imageViewMyPic.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancel() {
                Log.d("Demo", "We got cancelled");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d("Demo", "error " + exception);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);


        GraphRequest graphRequestFriends = GraphRequest.newMyFriendsRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONArrayCallback() {
            @Override
            public void onCompleted(JSONArray objects, GraphResponse response) {
                Log.d("Demo", "The action completed");
                Log.d("demo" , "asd" + objects.toString());
                ArrayList<FBFriend> fbFriends = new ArrayList<>();

                for (int i = 0; i < objects.length();i++){
                    try {
                        JSONObject object = objects.getJSONObject(i);
                        fbFriends.add(new FBFriend(object.getString("id"), object.getString("name")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


                layoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                myAdapter = new MyAdapter(fbFriends);
                recyclerView.setAdapter(myAdapter);


            }
        });

        graphRequestFriends.executeAsync();


        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                try {
                    String name = object.getString("name");
                    String id = object.getString("id");
                    textViewLogin.setText(name);
                    Picasso.get().load("https://graph.facebook/" + id + "/picture?type=large").into(imageViewMyPic);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        graphRequest.executeAsync();

    }




    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null) {
                myAdapter.clear();

                textViewLogin.setVisibility(View.INVISIBLE);
                imageViewMyPic.setVisibility(View.INVISIBLE);
                textView1.setVisibility(View.INVISIBLE);


                LoginManager.getInstance().logOut();
            }

        }
    };


    @Override
    protected void onDestroy () {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }
}


