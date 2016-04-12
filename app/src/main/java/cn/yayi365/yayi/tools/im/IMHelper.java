package cn.yayi365.yayi.tools.im;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMCore;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.contact.IYWContactOperateNotifyListener;
import com.alibaba.mobileim.conversation.IYWConversationListener;
import com.alibaba.mobileim.conversation.IYWConversationService;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWCustomMessageBody;
import com.alibaba.mobileim.conversation.YWMessage;
import com.alibaba.mobileim.login.IYWConnectionListener;
import com.alibaba.mobileim.login.YWLoginCode;
import com.alibaba.wxlib.util.SysUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yayi365.yayi.config.Constant;

/**
 * Created by one_v on 2016/3/18 15:32.
 */
public class IMHelper {

    private static IMHelper instance;

    private Context mContext;

    private YWIMKit mIMKit;

    private YWIMCore mIMCore;




    private IMHelper() {
    }

    public static IMHelper getInstance(Context mContext) {
        if (null == instance) {
            instance = new IMHelper();
        }
        instance.setContext(mContext);
        return instance;
    }

    /**
     * 必须在application中调用
     */
    public void initIM() {
        if (SysUtil.isMainProcess(mContext)) {
            //SDK初始化
            YWAPI.init((Application) mContext, Constant.ALI_APP_KEY);
            //后期将使用Override的方式进行集中配置，请参照YWSDKGlobalConfigSample
            YWAPI.enableSDKLogOutput(true);
            mIMKit = YWAPI.getIMKitInstance( Constant.ALI_DEFAULT_USER_ID, Constant.ALI_APP_KEY);
        }
    }

    public YWIMKit getIMKit() {
        return mIMKit;
    }

    public YWIMCore getIMCore() {
        if (null == mIMCore && null != mIMKit) {
            mIMCore = mIMKit.getIMCore();
        }
        return mIMCore;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public void login(String userId, String password) {
        login(userId, password, null);
    }

    public void login(String userId, String password, IWxCallback callback) {
        //        mIMKit = YWAPI.getIMKitInstance(userId, Constant.ALI_APP_KEY);
        //        mIMKit.setEnableNotification(true);
        if (!TextUtils.isEmpty(YWAPI.getCurrentUser()) && !YWAPI.getCurrentUser().contains(Constant.ALI_DEFAULT_USER_ID)) {
            logout();
        }

        IYWLoginService login = mIMKit.getLoginService();
        YWLoginParam param = YWLoginParam.createLoginParam(userId, password);
        login.login(param, callback);
    }


    public void logout() {
        if (null == mIMKit) {
            return;
        }

        final IYWLoginService mLoginService = mIMKit.getLoginService();

        mLoginService.logout(new IWxCallback() {
            @Override
            public void onSuccess(Object... objects) {
                //                HashMap<String, Object> kitMap = YWAPI.getMultiAccountIMKitMap();
                //                for (String key : kitMap.keySet()) {
                //                    if (key.contains(Constant.ALI_DEFAULT_USER_ID)) {
                //                        mIMKit = YWAPI.getIMKitInstance(key);
                //                        login(Constant.ALI_DEFAULT_USER_ID, "");
                //                    }
                //                }
            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i) {

            }
        });

    }

    public void startTalkToUser(String to) {
        if (null == mIMKit || TextUtils.isEmpty(to)) {
            return;
        }
        Intent intent = mIMKit.getChattingActivityIntent(to, Constant.ALI_APP_KEY);
        mContext.startActivity(intent);
    }

    public void openTalkList() {
        if (null == mIMKit) {
            return;
        }
        Intent intent = mIMKit.getConversationActivityIntent();
        mContext.startActivity(intent);
    }

    //////////////////////////////////////////////////////////////////////////
    private void addConnectionListener() {
        if (mIMKit == null) {
            return;
        }

        mIMCore = mIMKit.getIMCore();

    }

    /**
     * 添加新消息到达监听，该监听应该在登录之前调用以保证登录后可以及时收到消息
     */
    private void addPushMessageListener() {
        if (mIMKit == null) {
            return;
        }

        IYWConversationService conversationService = mIMKit.getConversationService();
        //添加单聊消息监听，先删除再添加，以免多次添加该监听

        //        //添加群聊消息监听，先删除再添加，以免多次添加该监听
        //        conversationService.removeTribePushListener(mTribeListener);
        //        conversationService.addTribePushListener(mTribeListener);
    }

}
