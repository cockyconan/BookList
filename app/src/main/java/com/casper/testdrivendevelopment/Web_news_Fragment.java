package com.casper.testdrivendevelopment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class Web_news_Fragment extends Fragment {
    private WebView MyWebView;


    public Web_news_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_web_news_, container, false);
        MyWebView=(WebView)view.findViewById(R.id.web_view_news);
        MyWebView.setWebViewClient(new WebViewClient());
        MyWebView.setWebChromeClient(new WebChromeClient());
        MyWebView.getSettings().setJavaScriptEnabled(true);
        MyWebView.loadUrl("http://www.bilibili.com/");
        return view;
    }

}
