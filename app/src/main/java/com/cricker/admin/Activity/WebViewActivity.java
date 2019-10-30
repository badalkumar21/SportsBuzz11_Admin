package com.cricker.admin.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.cricker.admin.R;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WebViewActivity extends AppCompatActivity {

    final private UnityAdsListner unityAdsListner = new UnityAdsListner();
    WebView myWebView;
    private ProgressBar progressBar;
    private boolean isRunning;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

//        Random rnd = new Random();
//        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

        int[] androidColors = getResources().getIntArray(R.array.androidcolors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_view);
        toolbar.setBackgroundColor(randomAndroidColor);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(getResources().getColor(R.color.colorGray));
            window.setStatusBarColor(randomAndroidColor);
        }


        UnitAdsInitialize();

        ScheduledExecutorService unityAdsSchedule = Executors.newSingleThreadScheduledExecutor();
        unityAdsSchedule.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isRunning) {
                            if (UnityAds.isReady()) {
                                UnitAdsShow();

                            } else {

                                UnitAdsInitialize();
                                UnitAdsShow();

                            }
                        }

                    }
                });
            }
        }, 30, 600, TimeUnit.SECONDS);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressBar = (ProgressBar) findViewById(R.id.pro);

        myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        Bundle b = getIntent().getExtras();
        final String id = b.getString("id");
        myWebView.loadUrl(id);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                setTitle("Loading...");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                setTitle(null);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.contains("target")) {
                    view.loadUrl(url);
                } else {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                }

                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        isRunning = true;
    }


    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void UnitAdsInitialize() {

        UnityAds.initialize(this, "2856610", unityAdsListner);
    }

    private void UnitAdsShow() {

        UnityAds.show(this);

    }

    private class UnityAdsListner implements IUnityAdsListener {


        @Override
        public void onUnityAdsReady(String s) {

        }

        @Override
        public void onUnityAdsStart(String s) {

        }

        @Override
        public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {

        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {

        }
    }


}