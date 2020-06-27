package br.unicamp.ft.d166336_m202618.trashtime.ui.tranding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import br.unicamp.ft.d166336_m202618.trashtime.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrandingFragment extends Fragment {

    View view;
    WebView webView;
    ImageView imageView;

    public TrandingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();

        webView.loadUrl("https://docs.google.com/spreadsheets/d/e/2PACX-1vRvnH0cBV6mIkzil9x1cvsibd1Vr6xzNWFPx6JuQNXOVbPuZzW04KUhLb8OPZFO7P9XN2T4HIV6rd9F/pubhtml?gid=1900616793&single=true");

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                webView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_tranding, container, false);
        }

        webView = view.findViewById(R.id.tranding_webview);

        imageView = view.findViewById(R.id.tranding_loading);

        return view;
    }
}