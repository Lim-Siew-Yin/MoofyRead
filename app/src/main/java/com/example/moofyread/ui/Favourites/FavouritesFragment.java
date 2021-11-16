package com.example.moofyread.ui.Favourites;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moofyread.DBHelper;
import com.example.moofyread.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FavouritesFragment extends Fragment{
    DBHelper mydb;
    ArrayList<String> doc_id, doc_name, doc_author;

    RecyclerView mRecyclerView;
    FavouritesAdapter mFavouritesAdapter;
    FloatingActionButton mButtonAddFav;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_favourites, container, false);

        mydb = new DBHelper(root.getContext());
        doc_id = new ArrayList<>();
        doc_name = new ArrayList<>();
        doc_author = new ArrayList<>();

        //create recyclerview and floating button
        mRecyclerView = (RecyclerView) root.findViewById(R.id.fav_recycler_view);
        mButtonAddFav = (FloatingActionButton) root.findViewById(R.id.add_button);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        mFavouritesAdapter = new FavouritesAdapter(root.getContext(), doc_id, doc_name, doc_author);
        mRecyclerView.setAdapter(mFavouritesAdapter);
        displayData();

        //click floating button to go to add fav page
        mButtonAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    //display recycleview list
    public void displayData(){
        Cursor cursor = mydb.readAllData();
        if(cursor.getCount() == 0){
            //if no data in db, display toast
            Toast.makeText(mRecyclerView.getContext(), "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                doc_id.add(cursor.getString(0));
                doc_name.add(cursor.getString(1));
                doc_author.add(cursor.getString(2));
            }
        }
    }
}

