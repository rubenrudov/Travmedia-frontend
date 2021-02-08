package com.travmedia.handlers;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.travmedia.R;
import com.travmedia.models.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostAdapter
                .ViewHolder(LayoutInflater.from(context)
                        .inflate(
                                R.layout.post_display, parent, false
                        )
                );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int index) {
        Post current = posts.get(index);
        holder.title.setText(current.getTitle());
        holder.publisher.setText(current.getPublisher());
        holder.date.setText(current.getUploadedAt());
        holder.content.setText(current.getContent());
    }

    @Override
    public int getItemCount() {
        return this.posts.size();
    }
    // For showing posts in recycler view

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, publisher, date, content;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            publisher = itemView.findViewById(R.id.publisher);
            date = itemView.findViewById(R.id.dateOfUpload);
            content = itemView.findViewById(R.id.content);
        }
    }
}
