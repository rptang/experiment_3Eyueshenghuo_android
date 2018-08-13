package com.zyzs.ewin.experiment_3eyueshenghuo.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.pm.ShortcutInfoCompat;
import android.support.v4.content.pm.ShortcutManagerCompat;
import android.support.v4.graphics.drawable.IconCompat;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.zyzs.ewin.experiment_3eyueshenghuo.R;
import com.zyzs.ewin.experiment_3eyueshenghuo.base.BaseActivity;
import com.zyzs.ewin.experiment_3eyueshenghuo.receiver.MyReceiver;
import com.zyzs.ewin.experiment_3eyueshenghuo.utils.PermissionUtil;
import com.zyzs.ewin.experiment_3eyueshenghuo.utils.ToastUtils;
import java.util.Arrays;
import java.util.List;

public class ShortCutActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "ShortCutActivity";

    private ImageButton btn_back;
    private Button btn_create_shortcut,btn_remove_shortcut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_cut);

        onCreatInit();
    }

    @Override
    protected void onInitView() {
        btn_back = findViewById(R.id.btn_back);
        btn_create_shortcut = findViewById(R.id.btn_create_shortcut);
        btn_remove_shortcut = findViewById(R.id.btn_remove_shortcut);
    }

    @Override
    protected void onInitData() {

    }

    @Override
    protected void onAddEventListener() {

        btn_back.setOnClickListener(this);
        btn_create_shortcut.setOnClickListener(this);
        btn_remove_shortcut.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_create_shortcut:
                //ToastUtils.show(ShortCutActivity.this, "创建快捷方式", 1000);
                //createShortCut();
                //addShortCut(this);
                //addShortCutCompact(this);
                abc(this);
                break;
            case R.id.btn_remove_shortcut:
                break;

        }
    }

    /**
     * 老方法
     */
    private void createShortCut() {
        //创建快捷方式的Intent
        /*Intent addIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        Parcelable icon = Intent.ShortcutIconResource.fromContext(this, R.drawable.ic_main_open); //获取快捷键的图标

        Intent myIntent = new Intent(this, ShortCutActivity.class);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "一键开门");//快捷方式的标题
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);//快捷方式的图标
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, myIntent);//快捷方式的动作
        sendBroadcast(addIntent);//发送广播*/

        /*//1.给intent维护图标和名称
        Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, BitmapFactory.decodeResource(getResources(), R.drawable.ic_main_open));
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME,"ShortCut");
        //2.点击快捷方式进入应用
        Intent shortCutIntent = new Intent();
        shortCutIntent.setAction("android.intent.action.splash");
        shortCutIntent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortCutIntent);
        sendBroadcast(intent);*/

        // intent发送隐式意图,去创建快捷方式
        Intent addIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        //不让重建
        addIntent.putExtra("duplicate", false);
        // 将应用的图标设置为Parceable类型
        Parcelable icon = Intent.ShortcutIconResource.fromContext(this, R.drawable.ic_main_open);
        // 点击图标之后的意图操作
        Intent myIntent = new Intent(Intent.ACTION_MAIN);
        myIntent.setClassName(this, getClass().getName());
        // 设置快捷方式的名称
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "百度影音");
        // 设置快捷方式的图标
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        // 设置快捷方式的意图
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, myIntent);
        // 发送广播
        sendBroadcast(addIntent);
    }

    /**
     * 新方法
     */
    //@RequiresApi(api = Build.VERSION_CODES.O)
    //@RequiresApi(api = Build.VERSION_CODES.M)
    /*private void creatShortcut(){

        ShortcutManager mShortcutManager = getSystemService(ShortcutManager.class);

        if (mShortcutManager.isRequestPinShortcutSupported()) {
            // Assumes there's already a shortcut with the ID "my-shortcut".
            // The shortcut must be enabled.
            ShortcutInfo pinShortcutInfo =
                    new ShortcutInfo.Builder(this, "my-shortcut").build();

            // Create the PendingIntent object only if your app needs to be notified
            // that the user allowed the shortcut to be pinned. Note that, if the
            // pinning operation fails, your app isn't notified. We assume here that the
            // app has implemented a method called createShortcutResultIntent() that
            // returns a broadcast intent.
            Intent pinnedShortcutCallbackIntent =
                    mShortcutManager.createShortcutResultIntent(pinShortcutInfo);

            // Configure the intent so that your app's broadcast receiver gets
            // the callback successfully.
            PendingIntent successCallback = PendingIntent.getBroadcast(this, 0,
                    pinnedShortcutCallbackIntent, 0);

            mShortcutManager.requestPinShortcut(pinShortcutInfo,
                    successCallback.getIntentSender());
        }

    }*/

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addShortCut(Context context) {

        ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(Context.SHORTCUT_SERVICE);
        List<ShortcutInfo> mShortcutInfoList = shortcutManager.getDynamicShortcuts();
        Log.d(TAG,"size = "+mShortcutInfoList.size());
        for (int i = 0; i < mShortcutInfoList.size(); i++) {
            Log.d(TAG,"id = "+mShortcutInfoList.get(i).getId());
            if(mShortcutInfoList.get(i).getId().equalsIgnoreCase("The only id")){
                //shortcutManager.removeDynamicShortcuts(Arrays.asList("The only id"));
                return;
            }
        }

        Log.d(TAG,"boolean = "+shortcutManager.isRequestPinShortcutSupported());
        if (shortcutManager.isRequestPinShortcutSupported()) {
            Intent shortcutInfoIntent = new Intent(context, ShortCutActivity.class);
            shortcutInfoIntent.setAction(Intent.ACTION_VIEW); //action必须设置，不然报错

            ShortcutInfo info = new ShortcutInfo.Builder(context, "The only id")
                    .setIcon(Icon.createWithResource(context, R.drawable.ic_main_open))
                    .setShortLabel("Short Label")
                    .setIntent(shortcutInfoIntent)
                    .build();

            //当添加快捷方式的确认弹框弹出来时，将被回调
            PendingIntent shortcutCallbackIntent = PendingIntent.getBroadcast(context, 0, new Intent(context, MyReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);

            shortcutManager.requestPinShortcut(info, shortcutCallbackIntent.getIntentSender());
            shortcutManager.addDynamicShortcuts(Arrays.asList(info));
        }

    }

    public static void addShortCutCompact(Context context) {
        if (ShortcutManagerCompat.isRequestPinShortcutSupported(context)) {
            Intent shortcutInfoIntent = new Intent(context, ShortCutActivity.class);
            shortcutInfoIntent.setAction(Intent.ACTION_VIEW); //action必须设置，不然报错

            ShortcutInfoCompat info = new ShortcutInfoCompat.Builder(context, "The only id")
                    .setIcon(IconCompat.createWithResource(context, R.drawable.ic_main_open))
                    .setShortLabel("Short Label")
                    .setIntent(shortcutInfoIntent)
                    .build();

            //当添加快捷方式的确认弹框弹出来时，将被回调
            PendingIntent shortcutCallbackIntent = PendingIntent.getBroadcast(context, 0, new Intent(context, MyReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
            ShortcutManagerCompat.requestPinShortcut(context, info, shortcutCallbackIntent.getIntentSender());
        }
    }

    private void abc(Context context){

        Log.d(TAG, "permission = : "+PermissionUtil.lacksPermission(this, Manifest.permission.INSTALL_SHORTCUT));

        if(!PermissionUtil.lacksPermission(this, Manifest.permission.INSTALL_SHORTCUT)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(Context.SHORTCUT_SERVICE);
                List<ShortcutInfo> mShortcutInfoList = shortcutManager.getDynamicShortcuts();
                for (int i = 0; i < mShortcutInfoList.size(); i++) {
                    if(mShortcutInfoList.get(i).getId().equalsIgnoreCase("The only id")){
                        return;
                    }
                }

                if (shortcutManager.isRequestPinShortcutSupported()) {
                    Intent shortcutInfoIntent = new Intent(context, ShortCutActivity.class);
                    shortcutInfoIntent.setAction(Intent.ACTION_VIEW); //action必须设置，不然报错

                    ShortcutInfo info = new ShortcutInfo.Builder(context, "The only id")
                            .setIcon(Icon.createWithResource(context, R.drawable.ic_main_open))
                            .setShortLabel("Short Label")
                            .setIntent(shortcutInfoIntent)
                            .build();
                    shortcutManager.addDynamicShortcuts(Arrays.asList(info));
                }
            }else {
                //创建快捷方式的Intent
                Intent addIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
                Parcelable icon = Intent.ShortcutIconResource.fromContext(this, R.drawable.ic_main_open); //获取快捷键的图标

                Intent myIntent = new Intent(this, ShortCutActivity.class);
                addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "一键开门");//快捷方式的标题
                addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);//快捷方式的图标
                addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, myIntent);//快捷方式的动作
                sendBroadcast(addIntent);//发送广播
            }
        }else {
            ToastUtils.show(ShortCutActivity.this, "创建快捷方式权限未开启", 1000);
        }
    }
}
