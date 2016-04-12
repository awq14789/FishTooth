package cn.yayi365.yayi.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;
import cn.yayi365.yayi.tools.im.IMHelper;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.Environment;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.wxlib.util.SysUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * Created by one_v on 2016-2-25.
 */
public class BaseApplication extends Application {

    private static final String TAG = BaseApplication.class.getName();

    @NonNull
    public static final CountDownLatch latch = new CountDownLatch(1);

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AlibabaSDK.turnOnDebug();
        initOneSDK(this);
    }


    /**
     * 初始化阿里百川相关组件
     */
    private void initOneSDK(final Application app) {
        ///////////////////////初始化IM/////////////////////////////////
        SysUtil.setApplication(this);
        if (SysUtil.isTCMSServiceProcess(this)) {
            return;
        }
        //IM开始初始化
        IMHelper.getInstance(this).initIM();

        /////////////////////////初始化推送///////////////////////////
        AlibabaSDK.asyncInit(app, new InitResultCallback() {
            @Override
            public void onSuccess() {
                initCloudChannel(app);
            }

            @Override
            public void onFailure(int i, String s) {
                Log.e(TAG, "init onesdk failed : " + s);
            }
        });

    }

    private void initCloudChannel(final Application app) {
        final CloudPushService cloudPushService = AlibabaSDK.getService(CloudPushService.class);
        if (cloudPushService != null) {
            cloudPushService.register(app, new CommonCallback() {

                @Override
                public void onSuccess() {
                    Log.e(TAG, "init cloudchannel success--->" + cloudPushService.getDeviceId());
                }

                @Override
                public void onFailed(String errorCode, String errorMessage) {
                    Log.e(TAG, "init cloudchannel fail" + "err:" + errorCode + " - message:" + errorMessage);
                }

            });
        }
    }

}
