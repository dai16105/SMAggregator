package gr.uom.smaggregator;

import androidx.appcompat.app.AppCompatActivity;
import twitter4j.MediaEntity;
import twitter4j.Status;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PostDetails extends AppCompatActivity {

    // Inflate the layout with the data of the specific post
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        Intent intent = getIntent();
        TextView userId = findViewById(R.id.frag_userId);
        TextView postBody = findViewById(R.id.frag_postBody);
        TextView timestamp = findViewById(R.id.frag_date);
        ImageView img = findViewById(R.id.frag_body_img);
       userId.setText(intent.getStringExtra("userId"));
       postBody.setText(intent.getStringExtra("postBody"));
       timestamp.setText(intent.getStringExtra("timestamp"));

        Picasso.get().load(intent.getStringExtra("imgUrl")).into(img);

    }
}