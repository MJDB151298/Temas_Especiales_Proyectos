package com.example.ejercicioretrofit.adapters;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ejercicioretrofit.R;
import com.example.ejercicioretrofit.fragments.CommentFragment;
import com.example.ejercicioretrofit.models.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private Context context;
    private List<Post> posts;
    private static FragmentActivity activity;

    public PostAdapter(List<Post> posts, Context context, FragmentActivity activity){
        this.posts = posts;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.post_row, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {
        holder.bindData(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView postIdText;
        private TextView postTitleText;
        private TextView postBodyText;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            postIdText = itemView.findViewById(R.id.postIdText);
            postTitleText = itemView.findViewById(R.id.postTitleText);
            postBodyText = itemView.findViewById(R.id.postBodyText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Call comments
                    Bundle bundle = new Bundle();
                    bundle.putInt("POST_ID", Integer.parseInt(postIdText.getText().toString()));

                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    CommentFragment commentFragment = new CommentFragment();
                    commentFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment_container, commentFragment).addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }

        public void bindData(Post post){
            postIdText.setText(Integer.toString(post.getId()));
            postTitleText.setText(post.getTitle());
            postBodyText.setText(post.getBody());
        }
    }
}
