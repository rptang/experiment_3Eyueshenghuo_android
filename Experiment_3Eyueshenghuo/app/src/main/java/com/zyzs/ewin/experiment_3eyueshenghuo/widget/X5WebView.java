package com.zyzs.ewin.experiment_3eyueshenghuo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * @des 腾讯TBS浏览服务x5内核WebView
 */
public class X5WebView extends WebView {

    private static final String TAG = "X5WebView";
    
    private WebViewClient client = new WebViewClient() {
        // 防止加载网页时调起系统浏览器
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "shouldOverrideUrlLoading: ");
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            Log.d(TAG, "onPageFinished: ");
            super.onPageFinished(webView, s);
        }
    };

    public X5WebView(Context arg0) {
        super(arg0);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);

        setWebViewClient(client);
        // this.setWebChromeClient(chromeClient);
        // WebStorage webStorage = WebStorage.getInstance();
        initWebViewSettings();
    }

    private void initWebViewSettings() {
        WebSettings webSetting = getSettings();
        Log.d(TAG, "initWebViewSettings: ");
        webSetting.setJavaScriptEnabled(true);//支持js
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSetting.setAllowFileAccess(true);//设置可以访问文件
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);//支持内容重新布局
        webSetting.setSupportZoom(false);//支持缩放，默认为true。是下面那个的前提。
        webSetting.setBuiltInZoomControls(false);//设置内置的缩放控件。 这个取决于setSupportZoom(), 若setSupportZoom(false)，则该WebView不可缩放，这个不管设置什么都不能缩放。
        webSetting.setUseWideViewPort(true);//将图片调整到适合webview的大小
        webSetting.setSupportMultipleWindows(true); //多窗口
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE); //webview中缓存
//        webSetting.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
//        webSetting.setLoadsImagesAutomatically(true); //支持自动加载图片
//        webSetting.setDefaultTextEncodingName("utf-8"); //设置编码格式
//        webSetting.setPluginState(WebSettings.PluginState.OFF); //设置是否支持flash插件
//        webSetting.setDefaultFontSize(20); //设置默认字体大小

    }

}
