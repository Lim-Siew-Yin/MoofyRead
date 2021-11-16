package com.example.moofyread.ui.TheStar;

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


public class TheStarFragment extends Fragment {
    //declare buttons
    private Button btn2;
    private Button btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_thestar, container, false);
        WebView webView = root.findViewById(R.id.webviewthestar);
        webView.loadUrl("https://www.thestar.com/");
        webView.setWebViewClient(new WebViewController());

        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
                try {
                    webView.stopLoading();
                } catch (Exception e) { }

                if (webView.canGoBack()) {
                    webView.goBack();
                }

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




        Button btn = root.findViewById(R.id.btnthesun);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_thestar_to_nav_thesun);

            }

        });

        Button btn2 = root.findViewById(R.id.btntst);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_thestar_to_nav_thestraitstimes);

            }
        });

        return root;

    }
}
