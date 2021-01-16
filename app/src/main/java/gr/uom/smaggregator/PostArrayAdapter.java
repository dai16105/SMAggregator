package gr.uom.smaggregator;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import twitter4j.Status;

public class PostArrayAdapter extends ArrayAdapter<Status> {

    private static int counter = 0;
    private List<Status> postList;
    private final LayoutInflater inflater;
    private final int layoutResource;

    private final ListView postListView;





    public PostArrayAdapter(@NonNull Context context, int resource, @NonNull List<Status> objects, ListView listView) {
        super(context, resource, objects);
        postList = objects;
        layoutResource = resource;
        inflater = LayoutInflater.from(context);
        postListView = listView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        counter++;
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
            Log.w("VIEW_HOLDER", "View Holder Created");
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Status currentPost = postList.get(position);

        viewHolder.userId.setText("@" + currentPost.getUser().getScreenName());
        viewHolder.postBody.setText(currentPost.getText());
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        viewHolder.timestamp.setText(dateFormat.format(currentPost.getCreatedAt()));




        return convertView;
    }


    private class ViewHolder{
        final TextView userId;
        final TextView postBody;
        final TextView timestamp;
        final ImageView icon;


        ViewHolder(View view){
            userId = view.findViewById(R.id.userId);
            postBody = view.findViewById(R.id.postBody);
            timestamp = view.findViewById(R.id.date);
            icon = view.findViewById(R.id.img);
        }
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    public void setPostList(List<Status> postList) {
        this.postList = postList;
        postListView.setAdapter(this);
    }
}
