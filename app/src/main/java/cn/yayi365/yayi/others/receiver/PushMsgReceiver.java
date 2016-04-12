package cn.yayi365.yayi.others.receiver;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;

import java.util.Map;

/**
 * Created by one_v on 2016/3/22 15:48.
 */
public class PushMsgReceiver extends MessageReceiver {

    private static final String REC_TAG = PushMsgReceiver.class.getName();

    @Override
    protected void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        // TODO 处理推送通知
        if (null != extraMap) {
            for (Map.Entry<String, String> entry : extraMap.entrySet()) {
                Log.e(REC_TAG, "@Get diy param : Key=" + entry.getKey() + " , Value=" + entry.getValue());
            }
        } else {
            Log.e(REC_TAG, "@收到通知 && 自定义消息为空");
        }
        Log.e(REC_TAG, "收到一条推送通知 ： " + title);
    }

    @Override
    protected void onMessage(Context context, CPushMessage cPushMessage) {
        try {

            Log.e(REC_TAG, "收到一条推送消息 ： " + cPushMessage.getTitle());

            // 持久化推送的消息到数据库
//            new MessageDao(context).add(new MessageEntity(cPushMessage.getMessageId().substring(6,16), Integer.valueOf(cPushMessage.getAppId()), cPushMessage.getTitle(), cPushMessage.getContent(), new SimpleDateFormat("HH:mm:ss").format(new Date())));
//
//            // 刷新下消息列表
//            ActivityBox.CPDMainActivity.initMessageView();
        } catch (Exception e) {
            Log.e(REC_TAG, e.toString());
        }
    }

    @Override
    protected void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        Log.e(REC_TAG, "onNotificationOpened ： " + " : " + title + " : " + summary + " : " + extraMap);
    }

    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
        Log.e(REC_TAG, "onNotificationRemoved ： " + messageId);
    }
}
