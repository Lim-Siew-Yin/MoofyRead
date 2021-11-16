package com.example.moofyread.ui.TheSun;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.moofyread.R;
import com.example.moofyread.WebViewController;

public class TheSunFragment extends Fragment {
    //declare buttons
    private Button btn;
    private Button btn2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_thesun, container, false);
        //create web view and load thesun url
        WebView webView = root.findViewById(R.id.webviewthesun);
        webView.loadUrl("https://www.thesundaily.my/");
        webView.setWebViewClient(new WebViewController());

        //set web view to receive error alert
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
                //stop loading the page
                try {
                    webView.stopLoading();
                } catch (Exception e) {
                }
                //enable user to back to previous page
                if (webView.canGoBack()) {
                    webView.goBack();
                }
                //alert dialog to inform user check internet connection
                webView.loadUrl("about:blank");
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle(getString(R.string.error));
                alertDialog.setMessage(getString(R.string.internet));
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                        startActivity(getActivity().getIntent());
                    }
                });

                alertDialog.show();
                super.onReceivedError(webView, errorCode, description, failingUrl);
            }
        });



        //click button go to thestar
        Button btn = root.findViewById(R.id.btnthestar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_thesun_to_nav_thestar);
            }
        });

        //click button go to tst
        Button btn2 = root.findViewById(R.id.btntst);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_thesun_to_nav_thestraitstimes);
            }
        });

        return root;
    }
}