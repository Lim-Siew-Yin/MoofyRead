package com.example.moofyread.ui.Settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.moofyread.R;


public class SettingsFragment extends Fragment {
    private EditText edittext;
    private TextView textview;
    private Button btnsave;
    private Button btnchange;
    private String text;

    private static final String SHARED_PREFS = "preferences";
    private static final String TEXT = "text";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        edittext = (EditText) root.findViewById(R.id.textName);
        textview = (TextView) root.findViewById(R.id.showname);
        btnsave = (Button) root.findViewById(R.id.btnsave);
        btnchange = (Button) root.findViewById(R.id.btnchange);

        btnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textview.setText(" "+ edittext.getText().toString());
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        loadData();
        updateViews();
        return root;
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, textview.getText().toString());
        editor.apply();

        Toast.makeText(getActivity(), getString(R.string.message), Toast.LENGTH_SHORT).show();

    }

    private void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
    }

    public void updateViews() {
        textview.setText(text);
    }

}


