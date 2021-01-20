package gr.uom.smaggregator;

import androidx.appcompat.app.AppCompatActivity;
import twitter4j.Status;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PostDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        Intent intent = getIntent();
        TextView userId = findViewById(R.id.frag_userId);
        TextView postBody = findViewById(R.id.frag_postBody);
        TextView timestamp = findViewById(R.id.frag_date);
       userId.setText(intent.getStringExtra("userId"));
       postBody.setText(intent.getStringExtra("postBody"));
       timestamp.setText(intent.getStringExtra("timestamp"));

    }
}