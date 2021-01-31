package gr.uom.smaggregator;

import androidx.appcompat.app.AppCompatActivity;
import twitter4j.Twitter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        EditText queryField = findViewById(R.id.post_query);
        Button button = findViewById(R.id.button3);


        // Create post button listener. When clicked post the tweet written on the queryField textfield.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = queryField.getText().toString();
                new PostTweet(query).execute();
                Toast.makeText(getApplicationContext(),"Posted tweet!",Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }
}