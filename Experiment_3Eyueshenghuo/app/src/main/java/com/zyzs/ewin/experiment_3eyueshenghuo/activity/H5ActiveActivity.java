package com.zyzs.ewin.experiment_3eyueshenghuo.activity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.zyzs.ewin.experiment_3eyueshenghuo.R;
import com.zyzs.ewin.experiment_3eyueshenghuo.base.BaseActivity;
import com.zyzs.ewin.experiment_3eyueshenghuo.utils.LogUtil;
import com.zyzs.ewin.experiment_3eyueshenghuo.webview.JavaScriptInterface;

public class H5ActiveActivity extends BaseActivity {

    private static final String TAG = "H5ActiveActivity";

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5_active);

        onCreatInit();
    }

    @Override
    protected void onInitView() {
        mWebView = ((WebView) findViewById(R.id.webview));
    }

    @Override
    protected void onInitData() {}

    @Override
    protected void onAddEventListener() {
        mWebView.addJavascriptInterface(new JavaScriptInterface(this, mWebView), "ulife");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                LogUtil.d(TAG,"shouldOverrideUrlLoading = " + s);
                return true;
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                //rl_progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                //rl_progress.setVisibility(View.GONE);
            }

        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView webView, String s, String message, JsResult jsResult) {
                //showToast(message);
                jsResult.confirm();
                return true;
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            //扩展浏览器上传文件 4.1.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                //Log.d(TAG, "openFileChooser: 3");
                //openFileChooserImpl(uploadMsg);
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                //Log.d(TAG, "onShowFileChooser: 4");
                //onenFileChooseImpleForAndroid(filePathCallback);
                return true;
            }
        });

        mWebView.loadUrl("https://cloud.3ecloud.cn/view/product/product.html?plid=129&account=18298003954&app=4");
    }
}
