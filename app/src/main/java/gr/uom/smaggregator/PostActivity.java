package gr.uom.smaggregator;

import androidx.appcompat.app.AppCompatActivity;
import twitter4j.AsyncTwitter;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.api.TweetsResources;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        EditText queryField = findViewById(R.id.post_query);
        Button button = findViewById(R.id.button3);

        Twitter twitter = getTwitterConfig.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = queryField.getText().toString();
                new PostTweet(query).execute();

            }
        });
    }
}