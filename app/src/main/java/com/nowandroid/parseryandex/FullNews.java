package com.nowandroid.parseryandex;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class FullNews extends Fragment {

    public WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        webView = (WebView) getActivity().findViewById(R.id.webView);


        return inflater.inflate(R.layout.fragment_full, null);
    }
}
