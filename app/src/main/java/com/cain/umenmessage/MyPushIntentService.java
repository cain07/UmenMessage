package com.cain.umenmessage;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageService;
import com.umeng.message.entity.UMessage;

import org.android.agoo.common.AgooConstants;
import org.json.JSONObject;

/**
 * Developer defined push intent service.
 * Remember to call {@link com.umeng.message.PushAgent#setPushIntentServiceClass(Class)}.
 *
 * @author 这个是 在 siji项目中加入的
 */
//完全自定义处理类
public class MyPushIntentService extends UmengMessageService {
    private static final String TAG = MyPushIntentService.class.getName();

    //private UserPrefs_ userPrefs_;
    private boolean isIntent;
    private boolean isNeedShowNotify;

    @Override
    public void onMessage(Context context, Intent intent) {
        try {
            isIntent = true;
            isNeedShowNotify = true;
           // userPrefs_ = new UserPrefs_(context);
            String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
            UMessage msg = new UMessage(new JSONObject(message));
            UTrack.getInstance(context).trackMsgClick(msg);
            //后台传过来的参数，开发人员可根据type来启动对应的activity，type只自己定义。
            //type_id是需要将此参数传给对应的activity，需要传什么都行。
           // LogUtils.e(TAG, "onMessage----" + msg.display_type);
            String type = msg.display_type;
            //String clientId = userPrefs_.clientId().get();
            /*if (TextUtils.isEmpty(getRunningActivityName()) || getRunningActivityName().equals(LoginActivity_.class.getName()) || getRunningActivityName().equals(MessageActivity_.class.getName())) {
                isIntent = false;
            }*/
            Intent resultIntent = null;
            if (isIntent) {
              //  resultIntent = new Intent(getApplicationContext(), MessageActivity_.class);
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } else {
                resultIntent = new Intent();
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }

            if (TextUtils.isEmpty("222")) {
                resultIntent = context.getPackageManager().getLaunchIntentForPackage("com.sprucetec.driver");
                //resultIntent = new Intent(getApplicationContext(), LoginActivity_.class);
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            showNotification(context, msg, resultIntent);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    // 通知栏显示当前播放信息，利用通知和 PendingIntent来启动对应的activity
    public void showNotification(Context context, UMessage msg, Intent intent) {
        try {
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            RemoteViews contentViews = new RemoteViews(context.getPackageName(), R.layout.notification_view);
            contentViews.setTextViewText(R.id.notification_title, msg.title);
            contentViews.setTextViewText(R.id.notification_text, msg.text);

           // LogUtils.e(TAG, "showNotification------" + msg.text);
            builder.setContent(contentViews)
                    .setTicker(msg.ticker)
                    .setAutoCancel(true);

           // builder.setSmallIcon(R.mipmap.ic_launcher_tongzhi);
            builder.setDefaults(Notification.DEFAULT_ALL);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(), 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(resultPendingIntent);
            //103 是id id不同 则 通知栏不会消失
            mNotificationManager.notify(103, builder.build());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    private String getRunningActivityName() {
        try {
            ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
            return runningActivity;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return "";
        }
    }

}
