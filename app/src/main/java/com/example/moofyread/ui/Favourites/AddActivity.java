package com.example.moofyread.ui.Favourites;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moofyread.DBHelper;
import com.example.moofyread.R;

public class AddActivity extends AppCompatActivity {

    public EditText mDocName, mDocAuthor;
    public Button mButtonAdd;
    DBHelper myDB;

    boolean insertResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //assign textboxs to variable
        mDocName = findViewById(R.id.input_doc_name);
        mDocAuthor = findViewById(R.id.input_doc_author);
        mButtonAdd = findViewById(R.id.add_fav_button);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if field is empty
                if (mDocName.getText().toString().isEmpty()) {
                    mDocName.setError("Document name is required");
                }else {

                    try{
                        //insert data to db
                        myDB = new DBHelper(AddActivity.this);
                        insertResult = myDB.insertFav(mDocName.getText().toString(),
                                mDocAuthor.getText().toString());

                        //toast message to indicate data insert success
                        Toast.makeText(AddActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();

                    } catch (Exception e){
                        //toast message to indicate data insert failed
                        //e.printStackTrace();
                        Toast.makeText(AddActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }


}