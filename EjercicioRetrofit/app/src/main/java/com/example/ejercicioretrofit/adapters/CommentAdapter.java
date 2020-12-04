package com.example.ejercicioretrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ejercicioretrofit.R;
import com.example.ejercicioretrofit.models.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private Context context;
    private List<Comment> comments;

    public CommentAdapter(List<Comment> comments, Context context){
        this.comments = comments;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.comment_row, parent, false);
        return new CommentAdapter.CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.bindData(comments.get(position));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder{
        private TextView commentNameText;
        private TextView commentEmailText;
        private TextView commentBodyText;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            commentNameText = itemView.findViewById(R.id.commentNameText);
            commentEmailText = itemView.findViewById(R.id.commentEmailText);
            commentBodyText = itemView.findViewById(R.id.commentBodyText);
        }

        public void bindData(Comment comment){
            commentNameText.setText(comment.getName());
            commentEmailText.setText(comment.getEmail());
            commentBodyText.setText(comment.getBody());
        }
    }
}
