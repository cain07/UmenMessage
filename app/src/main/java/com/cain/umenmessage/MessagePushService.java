package com.cain.umenmessage;

import com.umeng.message.UmengBaseIntentService;

/**
 * Created by lishuxun on 16/1/10.
 */
public class MessagePushService extends UmengBaseIntentService {

  /*  @Override
    protected void onMessage(Context context, Intent intent) {
        super.onMessage(context, intent);
        try {
            boolean isIntent = true;
            boolean isNeedShowNotify = true;
            String message = intent.getStringExtra(BaseConstants.MESSAGE_BODY);
            UserPrefs_ userPrefs_ = new UserPrefs_(context);
            final UMessage msg = new UMessage(new JSONObject(message));
            LogUtils.e("推送消息是---------->:" + message);
            UTrack.getInstance(context).trackMsgClick(msg);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setSmallIcon(R.drawable.icon_launcher);
            builder.setDefaults(Notification.DEFAULT_ALL);
            String text = "";
            String title = "";
            if (msg.display_type.equals("custom")) {
                isNeedShowNotify = true;
                isIntent = false;
                text = msg.custom;
                title = "CRM新消息";
                builder.setTicker("您有一条新消息");
            } else {
                text = msg.text;
                title = msg.title;
                builder.setTicker(msg.ticker);

                if (msg.extra != null) {
                    if (msg.extra.containsKey("pushMsgId")) {
                        isNeedShowNotify = true;
                        isIntent = true;
                        final String pushMsgId = msg.extra.get("pushMsgId");
                        if (StringUtils.isBlank(MainApp.DEVICE_TOKEN)) {
                            PushAgent mPushAgent = PushAgent.getInstance(this);
                            mPushAgent.enable();
                            MainApp.DEVICE_TOKEN = UmengRegistrar.getRegistrationId(this);
                        }
                        if (!StringUtils.isBlank(pushMsgId) && !StringUtils.isBlank(MainApp.DEVICE_TOKEN)) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        ICollectService_ service = new ICollectService_(MessagePushService.this);
                                        ReportArrivalParam param = new ReportArrivalParam();
                                        param.setDeviceToken(MainApp.DEVICE_TOKEN);
                                        param.setPushMsgId(pushMsgId);
                                        service.reportArrival(param);
                                    } catch (Exception e) {
                                        LogUtils.e("响应消息到达异常：" + e.getMessage());
                                    }
                                }
                            }).start();
                        }
                    }

                    if (msg.extra.containsKey("msg_type") && !StringUtils.isBlank(msg.extra.get("msg_type"))) {
                        isIntent = false;

                        if (userPrefs_.clazzName().get()) {
                            isNeedShowNotify = false;
                            Intent msgIntent = new Intent("receiver.MessageBroadcastReceiver");
                            msgIntent.putExtra("type", msg.extra.get("msg_type"));
                            msgIntent.putExtra("count", Integer.parseInt(msg.extra.get("count")));
                            sendBroadcast(msgIntent);
                        } else {
                            isNeedShowNotify = true;
                        }

                    }
                }

            }
            RemoteViews contentViews = new RemoteViews(getPackageName(), R.layout.custom_notification);
            contentViews.setTextViewText(R.id.titleNo, title);
            contentViews.setTextViewText(R.id.textNo, text);
            contentViews.setImageViewResource(R.id.imageNo, R.drawable.icon_launcher);
            GregorianCalendar ca = new GregorianCalendar();
            System.out.println(ca.get(GregorianCalendar.AM_PM));
            contentViews.setTextViewText(R.id.time, "0".equals(ca.get(GregorianCalendar.AM_PM)) ? "上午" : "下午" + new SimpleDateFormat("hh:mm").format(new Date()));
            contentViews.setInt(R.id.titleNo, "setTextColor", NotificationUtils.isDarkNotificationTheme(this) == true ? Color.WHITE : Color.BLACK);
            contentViews.setInt(R.id.textNo, "setTextColor", NotificationUtils.isDarkNotificationTheme(this) == true ? Color.WHITE : Color.BLACK);
            contentViews.setInt(R.id.time, "setTextColor", NotificationUtils.isDarkNotificationTheme(this) == true ? Color.WHITE : Color.BLACK);
            builder.setContent(contentViews);


            if (isIntent) {
                Intent resultIntent = new Intent(getApplicationContext(), MessageWebActivity_.class);
                resultIntent.putExtra("url", BuildConfig.API_BASE_URI + "/message/index");
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(), 101, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(resultPendingIntent);
            } else {
                Intent resultIntent = new Intent();
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(), 101, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(resultPendingIntent);
            }
            builder.setAutoCancel(true);
            if (isNeedShowNotify) {
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                int msg_id = userPrefs_.msg_id().get();
                mNotificationManager.notify(msg_id, builder.build());
                userPrefs_.msg_id().put(msg_id + 1);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }*/

}
