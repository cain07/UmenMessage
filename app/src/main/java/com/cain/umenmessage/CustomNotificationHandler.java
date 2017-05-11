package com.cain.umenmessage;

import android.content.Context;
import android.content.Intent;

import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.common.UmLog;
import com.umeng.message.entity.UMessage;

import java.util.HashMap;
import java.util.Map;

//使用自定义的NotificationHandler，来结合友盟统计处理消息通知
//参考http://bbs.umeng.com/thread-11112-1-1.html
public class CustomNotificationHandler extends UmengNotificationClickHandler {
	
	private static final String TAG = CustomNotificationHandler.class.getName();
	
	@Override
	public void dealWithCustomAction(Context context, UMessage msg) {
		UmLog.d(TAG, "dealWithCustomAction");
		super.dealWithCustomAction(context, msg);
		Intent intent = new Intent(context, MyActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	


}
