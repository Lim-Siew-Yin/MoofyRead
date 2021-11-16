package com.example.moofyread.ui.Favourites;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moofyread.R;

import java.util.ArrayList;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.MyViewHolder> {

    private Context context;
    private ArrayList doc_id, doc_name, doc_author;

    public FavouritesAdapter(Context context, ArrayList doc_id, ArrayList doc_name, ArrayList doc_author){
        this.context = context;
        this.doc_id = doc_id;
        this.doc_name = doc_name;
        this.doc_author = doc_author;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_row_favourites, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesAdapter.MyViewHolder holder, int position) {
        holder.mDoc_name.setText(String.valueOf(doc_name.get(position)));
        holder.mDoc_author.setText(String.valueOf(doc_author.get(position)));
    }

    @Override
    public int getItemCount() {
        return doc_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mDoc_name;
        TextView mDoc_author;
        View rootView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            mDoc_name = itemView.findViewById(R.id.doc_name);
            mDoc_author = itemView.findViewById(R.id.doc_author);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(rootView.getContext(), AddActivity.class);
                    rootView.getContext().startActivity(intent);
                }
            });

        }

    }
}
