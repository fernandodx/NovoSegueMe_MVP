package com.segueme.seguemepsul.view.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.segueme.seguemepsul.R;

/**
 * Created by FernandoDias on 19/01/17.
 */

public class SiteSegueMeFragment extends BaseFragment {

    public static final String URL_SITE = "URL_SITE";

    private WebView webView;
    private ProgressBar progressBar;
    private String urlSite;


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstateState) {
        setRetainInstance(true);

        View view = layoutInflater.inflate(R.layout.site_segueme_fragment, container, false);

        webView = (WebView) view.findViewById(R.id.webview);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_web);

        webView.getSettings().setJavaScriptEnabled(true);

        setWebViewClient(webView);
        setUrlSite(getArguments().getString(URL_SITE));

        webView.loadUrl(getUrlSite());

        webView.setWebChromeClient(new WebChromeClient(){
            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                Log.d("WEBVIEW", message + " -- Para linha "
                        + lineNumber + " de "
                        + sourceID);
            }
        });

        return view;
    }

    private void setWebViewClient(WebView webView) {

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView webView, String url, Bitmap favicon) {
                super.onPageStarted(webView, url, favicon);

                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView webView, String url) {
                progressBar.setVisibility(View.GONE);
            }

        });
    }




    @Override
    public String getTagFragment() {
        return getClass().getSimpleName();
    }

    public String getUrlSite() {
        return urlSite;
    }

    public void setUrlSite(String urlSite) {
        this.urlSite = urlSite;
    }
}
