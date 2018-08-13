package com.zyzs.ewin.experiment_3eyueshenghuo.webview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/*import com.google.gson.reflect.TypeToken;
import com.taichuan.Config;
import com.taichuan.Constants;
import com.taichuan.entity.ResultList;
import com.taichuan.okhttp.callback.JsonCallback;
import com.taichuan.okhttp.convert.JsonConvert;
import com.taichuan.okhttp.request.BaseRequest;
import com.taichuan.utils.AppManager;
import com.taichuan.utils.AppUtils;
import com.taichuan.utils.SPUtils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.smtt.sdk.WebView;
import com.ydtjgj.pricloud.R;
import com.ydtjgj.pricloud.activity.AccessDoorActivity;
import com.ydtjgj.pricloud.activity.AccessMainActivity;
import com.ydtjgj.pricloud.activity.BindRoomActivity;
import com.ydtjgj.pricloud.activity.LoginActivity;
import com.ydtjgj.pricloud.activity.OrderPayActivity;
import com.ydtjgj.pricloud.activity.PersonActivity;
import com.ydtjgj.pricloud.activity.SmartBraceletListActivity;
import com.ydtjgj.pricloud.activity.SmartDoorListActivity;
import com.ydtjgj.pricloud.activity.SmartVideoHomeActivity;
import com.ydtjgj.pricloud.entity.EquipmentDoor;
import com.ydtjgj.pricloud.entity.PayWayInfo;
import com.ydtjgj.pricloud.entity.WxPrePay;
import com.ydtjgj.pricloud.entity.WxShareInfo;
import com.ydtjgj.pricloud.event.EventConstant;
import com.ydtjgj.pricloud.event.MsgEvent;
import com.ydtjgj.pricloud.http.OkHttpRequest;
import com.ydtjgj.pricloud.smarthome.activity.CtrlHostActivity;
import com.ydtjgj.pricloud.ui.CommonDialog;
import com.ydtjgj.pricloud.ui.LoadingDialog;
import com.ydtjgj.pricloud.ui.PayUtils;
import com.ydtjgj.pricloud.ui.SharePop;
import com.ydtjgj.pricloud.utils.ActivityUtils;
import com.ydtjgj.pricloud.utils.SessionCache;
import com.ydtjgj.pricloud.utils.Utils;
import com.ydtjgj.pricloud.utils.WXShareUtils;
import com.ydtjgj.pricloud.wulian.WulianGatewayActivity;

import org.greenrobot.eventbus.EventBus;
import okhttp3.Call;*/

import com.tencent.smtt.sdk.WebView;

public class JavaScriptInterface {

    private Activity mActivity;
    private WebView mWebView;
    private static final String TAG = "JavaScript";

    public JavaScriptInterface(Activity activity, WebView xwalkview) {
        this.mActivity = activity;
        this.mWebView = xwalkview;
    }

    /**
     * H5中点击返回按钮触发事件
     */
    @JavascriptInterface
    public void callBackMethod() {
        Log.d(TAG, "callBackMethod: ");
        mActivity.finish();
    }

    /*//全地址
    @JavascriptInterface
    public void callLocationMethod(String url, String type) {
        Log.d(TAG, "callLocationMethod: url: " + url + ", type: " + type);
        if ("1".equals(type)) {
            Bundle bundle = new Bundle();
            bundle.putString("url", url);
            bundle.putBoolean("isall", false);
            ActivityUtils.to(mActivity, H5ActiveActivity.class, bundle);
        } else if (url.contains("cloudpark")) {
            showToast(R.string.not_open);
//                ActivityUtils.to(mActivity, CPMainActivity.class);
            return;
        } else if (url.contains("smarthome")) {
            if (SessionCache.get().isZigbee()) {
                ActivityUtils.to(mActivity, WulianGatewayActivity.class);
            } else {
                ActivityUtils.to(mActivity, CtrlHostActivity.class);
            }

            return;
        } else if (url.contains("videoshow")) {
//            ActivityUtils.to(mActivity, SmartHomeActivity.class);

            ActivityUtils.to(mActivity, SmartVideoHomeActivity.class);
            return;
        } else if (url.contains("cloudopendoor")) {
            if (SessionCache.get().isAudited()) {
                Intent intent = new Intent();
                intent.setClass(mActivity, AccessMainActivity.class);
                mActivity.startActivity(intent);
            } else {
//                showToast(R.string.house_room_pending_review);
                showToast(R.string.bind_room_pending_review);
                ActivityUtils.to(mActivity, BindRoomActivity.class);
            }
            return;
        } else if (url.contains("person")) {
            ActivityUtils.to(mActivity, PersonActivity.class);
            return;
        } else if (url.contains("smartdoor")) {
            ActivityUtils.to(mActivity, SmartDoorListActivity.class);
            return;
        } else if (url.contains("smartbracelet")) {
            ActivityUtils.to(mActivity, SmartBraceletListActivity.class);
            return;
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("url", url);
            bundle.putBoolean("isall", false);
            ActivityUtils.to(mActivity, H5ActiveActivity.class, bundle);
        }
    }


    @JavascriptInterface
    public void callOpendoorMethod() {
        Log.d(TAG, "callOpendoorMethod: ");
        if (SessionCache.get().isAudited()) {
            getEquipmentList();
        } else {
//            showToast(R.string.house_room_pending_review);
            showToast(R.string.bind_room_pending_review);
            ActivityUtils.to(mActivity, BindRoomActivity.class);
        }
    }

    private void getEquipmentList() {
        OkHttpRequest.getEquipmentList(this, new JsonCallback<ResultList<EquipmentDoor>>() {
            @Override
            public void onBefore(BaseRequest request) {
                showProgressDialog();
            }

            @Override
            public void onSuccess(ResultList<EquipmentDoor> object, Call call, okhttp3.Response response) {
                if (Utils.isState(object.getResultCode())) {
                    List<EquipmentDoor> list = object.getData();
                    if (Utils.isListNotNull(list)) {
                        // 缓存门口机列表
                        long saveTime = (long) SPUtils.get().get(Constants.EQUIPMENT_TIME, 0L);
                        long nowTime = System.currentTimeMillis();
                        String jsonList = JsonConvert.toJson(list);
                        Log.d(TAG, "onResponse: saveTime: " + saveTime + ", nowTime: " + nowTime + ", result: " + (nowTime - saveTime));
                        if (saveTime == 0 || nowTime - saveTime > Constants.DOOR_CACHE_TIME) {
                            Log.d(TAG, "onResponse: 缓存门口机列表");
                            SPUtils.get().put(Constants.EQUIPMENT_TIME, System.currentTimeMillis());
                            SPUtils.get().putString(Constants.EQUIPMENT, jsonList);
                        }

                        Bundle bundle = new Bundle();
                        bundle.putString(Constants.EQUIPMENT, jsonList);
                        bundle.putBoolean(Constants.IS_UNLOCK, true);
                        ActivityUtils.to(mActivity, AccessDoorActivity.class, bundle);

                    } else {
                        showToast(R.string.door_list_is_null);
                    }
                } else {
                    showToast(object.getMsg());
                }
            }

            @Override
            public void onAfter(ResultList<EquipmentDoor> equipmentDoorResultList, Exception e) {
                hideProgressDialog();
            }
        });
    }

    private void hideProgressDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private LoadingDialog dialog;

    protected void showProgressDialog() {
        if (dialog == null) {
            dialog = new LoadingDialog(mActivity);
        }
        if (dialog.isShowing()) {
            return;
        }
        dialog.show();
    }

    //半地址
    @JavascriptInterface
    public void callGoH5Method(String url) {
        Log.d(TAG, "callGoH5Method: url: " + url);
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putBoolean("isall", true);
        ActivityUtils.to(mActivity, H5ActiveActivity.class, bundle);
    }

    @JavascriptInterface
    public void callGoH5Activity(String url, boolean isFinish) {
        Log.d(TAG, "callGoH5Activity: url2: " + url);
        Bundle bundle = new Bundle();
        bundle.putString("url", Utils.getH5Url(Config.H5BASE_URL + url));
        ActivityUtils.to(mActivity, H5Activity.class, bundle);
        if (isFinish) {
            mActivity.finish();
        }
    }

    //获取系统版本号
    @JavascriptInterface
    public String getAndroidVersionName() {
        return AppUtils.getVersionName(mActivity);
    }

    @JavascriptInterface
    public void callLoginMethod() {
        logout();
    }


    @JavascriptInterface
    public void callGoodsMethod(String goodid, String comdityPlid, String comdityXz) {
        Log.d(TAG, "callGoodsMethod: ");
        Bundle bundle = new Bundle();
        bundle.putSerializable("id", goodid);
        bundle.putSerializable("comdityPlid", comdityPlid);
        bundle.putSerializable("comdityXz", comdityXz);
        ActivityUtils.to(mActivity, H5ActiveGoodsActivity.class, bundle);
    }

    @JavascriptInterface
    public void callCallTelMethod(String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        mActivity.startActivity(intent);
    }

    @JavascriptInterface
    public void callGoodsClassMethod(String fid, String flmc) {
        Log.d(TAG, "callGoodsClassMethod: ");
        Bundle bundle = new Bundle();
        bundle.putString("fid", fid);
        bundle.putString("flmc", flmc);
        Log.d(TAG, "callGoodsClassMethod: " + fid);
        ActivityUtils.to(mActivity, H5GoodsListActivity.class, bundle);
    }


    @JavascriptInterface
    public void callAllIconMethod(String url) {
        Log.d(TAG, "callAllIconMethod: ");
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putBoolean("isall", true);
        ActivityUtils.to(mActivity, H5ActiveActivity.class, bundle);
    }

    @JavascriptInterface
    public void callH5Method(String url) {
        Log.d(TAG, "callH5Method: ");
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        ActivityUtils.to(mActivity, H5ActiveActivity.class, bundle);
    }

    @JavascriptInterface
    public void callGoPayMethod(String orderbh, String orderje, String zffs) {
        Log.d(TAG, "callGoPayMethod: ");
        List<PayWayInfo> paywaylist = new ArrayList<>();
        PayWayInfo payWayInfo = new PayWayInfo();
        payWayInfo.setZid("2");
        payWayInfo.setZmc("支付宝付款");
        paywaylist.add(payWayInfo);
        payWayInfo = new PayWayInfo();
        payWayInfo.setZid("3");
        payWayInfo.setZmc("微信支付");
        paywaylist.add(payWayInfo);
        Bundle bundle = new Bundle();
        bundle.putString("orderNo", orderbh);
        bundle.putString("totalprice", orderje);
        bundle.putString("payprice", orderje);
        bundle.putSerializable("prePay", PayUtils.getWxPrePay(orderje));
        bundle.putSerializable("payway", paywaylist.toArray());
        ActivityUtils.to(mActivity, OrderPayActivity.class, bundle);
    }

    @JavascriptInterface
    public void callGoPay2Method(String orderbh, String orderje, String payprice, String zffs) {
        Log.d(TAG, "callGoPay2Method: ");
        List<PayWayInfo> paywaylist = new ArrayList<>();
        PayWayInfo payWayInfo = new PayWayInfo();
        payWayInfo.setZid("2");
        payWayInfo.setZmc("支付宝付款");
        paywaylist.add(payWayInfo);
        payWayInfo = new PayWayInfo();
        payWayInfo.setZid("3");
        payWayInfo.setZmc("微信支付");
        paywaylist.add(payWayInfo);
        Bundle bundle = new Bundle();
        bundle.putString("orderNo", orderbh);
        bundle.putString("totalprice", orderje);
        bundle.putString("payprice", payprice);
        bundle.putSerializable("prePay", PayUtils.getWxPrePay(payprice));
        bundle.putSerializable("payway", paywaylist.toArray());
        ActivityUtils.to(mActivity, OrderPayActivity.class, bundle);
    }

    @JavascriptInterface
    public void callGoPayAgainUpMethod(String orderbh, String orderje, String payprice, String zffs, String url, String failurl) {
        Log.d(TAG, "callGoPayAgainUpMethod:  orderbh: " + orderbh  + ", orderId: " + getOrderIdFormUrl(url) +
                ", orderje: " + orderje +", payprice: " + payprice  +
                ", url: " + url + ", failurl: " + failurl);
        Bundle bundle = new Bundle();
        bundle.putString("orderNo", orderbh);
        bundle.putString("orderId", getOrderIdFormUrl(url));
        bundle.putString("totalprice", orderje);
        bundle.putString("yprice", payprice);
        bundle.putString("h5url", Config.H5BASE_URL + url);
        ActivityUtils.to(mActivity, OrderPayActivity.class, bundle);
    }

    @JavascriptInterface
    public void callServerPayUpMethod(String orderbh, String orderje, String zffs, String url, String failurl) {
        Log.d(TAG, "callServerPayUpMethod:  orderbh: " + orderbh  + ", orderId: " + getOrderIdFormUrl(url) +
                ", orderje: " + orderje + ", url: " + url + ", failurl: " + failurl);
        Bundle bundle = new Bundle();
        bundle.putString("orderNo", orderbh);
        bundle.putString("orderId", getOrderIdFormUrl(url));
        bundle.putString("totalprice", orderje);
        bundle.putString("h5url", Config.H5BASE_URL + url);
        bundle.putString("failurl", Config.H5BASE_URL + failurl);
        Intent intent = new Intent(mActivity, OrderPayActivity.class);
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }

    @JavascriptInterface
    public void callServerPayUpMethod(String orderbh, String orderje, String zffs, String type, String url, String failurl) {
        Log.d(TAG, "callServerPayUpMethod:orderbh: " + orderbh +
                ", orderId: " + getOrderIdFormUrl(url) + ", orderje: " + orderje +
                ", type: " + type + ", url: " + url + ", failurl: " + failurl);
        Bundle bundle = new Bundle();
        bundle.putString("orderNo", orderbh);
        bundle.putString("orderId", getOrderIdFormUrl(url));
        bundle.putString("totalprice", orderje);
        bundle.putString("type", type);
        bundle.putString("h5url", Config.H5BASE_URL + url);
        bundle.putString("failurl", Config.H5BASE_URL + failurl);
        Intent intent = new Intent(mActivity, OrderPayActivity.class);
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }

    private String getOrderIdFormUrl(String url) {
        if (!TextUtils.isEmpty(url) && url.contains("orderId=")) {
            String[] split1 = url.split("orderId=");
            if (!TextUtils.isEmpty(split1[1])) {
                return split1[1].split("&")[0];
            }
        }
        return "";
    }

    private WxPrePay setOrderPersonInfo(String payprice) {
        WxPrePay wxPrePay = new WxPrePay();
        wxPrePay.setIp("192.168.0.1");
        String price = (Double.parseDouble(payprice) * 100) + "";
        String wprice = price.split("\\.")[0];
        wxPrePay.setTotal_fee(wprice);
        wxPrePay.setTrade_type("APP");
        wxPrePay.setApp("4");
        return wxPrePay;
    }

    @JavascriptInterface
    public void goBacktorefreshhomeview() {
        Log.d(TAG, "goBacktorefreshhomeview: ");
        EventBus.getDefault().post(new MsgEvent(EventConstant.REFRESH_HOME));
        AppManager.getInstance().finishActivityExceptMain();
    }

    @JavascriptInterface
    public void goBackRecharge() {
        Log.d(TAG, "goBackRecharge: ");
        EventBus.getDefault().post(new MsgEvent(EventConstant.FINISH_ORDER_PAY));
        mActivity.finish();
    }

    @JavascriptInterface
    public void callBackMethod() {
        Log.d(TAG, "callBackMethod: ");
        mActivity.finish();
    }

    @JavascriptInterface
    public void callFinishAllExceptMain() {
        EventBus.getDefault().post(new MsgEvent(EventConstant.REFRESH_H5MAIN));
        AppManager.getInstance().finishActivityExceptMain();
    }

    @JavascriptInterface
    public void cleancache() {
        final CommonDialog dialog = new CommonDialog(mActivity);
        dialog.setTitle(mActivity.getString(R.string.clear_cache));
        dialog.setMessage(mActivity.getString(R.string.is_clear_cache));
        dialog.setConfirmClickListener(null, new CommonDialog.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                dialog.dismiss();
                showToast(R.string.already_clean_cache);
                logout();
            }
        });
        dialog.show();

    }

    @JavascriptInterface
    public void callLogoutMethod() {
        logout();
    }

    public void logout() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.IS_LOGOUT, true);
        ActivityUtils.to(mActivity, LoginActivity.class, bundle);
    }

    @JavascriptInterface
    public void callRefreshMineFragment() {
        Log.d(TAG, "callRefreshMineFragment: ");
        EventBus.getDefault().post(new MsgEvent(EventConstant.REFRESH_COMMUNITY));
    }

    private IWXAPI iwxapi;
    @JavascriptInterface
    public void callSharePTMethod(String response) {
        Log.d(TAG, "callSharePTMethod: " + response);
        if (TextUtils.isEmpty(response)) {
            return;
        }
        final WxShareInfo wxShareInfo = JsonConvert.fromJson(response, new TypeToken<WxShareInfo>() {
        }.getType());

        if (iwxapi == null) {
            iwxapi = WXAPIFactory.createWXAPI(mActivity, Config.APP_ID);
        }

        if (iwxapi.isWXAppInstalled() && iwxapi.isWXAppSupportAPI()) {
            SharePop sharePop = new SharePop(mActivity);
            sharePop.showAtLocation(mWebView, Gravity.BOTTOM, 0, 0);
            sharePop.setCallback(new SharePop.ShareCallback() {
                @Override
                public void share(int type) {
                    WXShareUtils wxShare = new WXShareUtils(mActivity, wxShareInfo, iwxapi, type);
                    wxShare.sendWebPage();
                }
            });
        } else {
            showToast(R.string.please_install_weixin_to_share);
        }

    }

    private void showToast(@StringRes int resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    private void showToast(String str) {
        Toast.makeText(mActivity, str, Toast.LENGTH_SHORT).show();
    }*/
}
