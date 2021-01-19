package gr.uom.smaggregator;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final ArrayList<FBFriend> fbFriends;

    public MyAdapter(ArrayList<FBFriend> fbFriends){
        this.fbFriends = fbFriends;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("demo" , "the ID is : " + fbFriends.get(position).getId());
        //Picasso.get().load("https://graph.facebook/" + fbFriends.get(position).getId()+ "/picture?type=large").into(holder.imageView);

        holder.textView.setText(fbFriends.get(position).getName());


    }

    @Override
    public int getItemCount() {

        return fbFriends.size();
    }

    public void clear(){
        fbFriends.clear();
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewMyName);

            //imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
