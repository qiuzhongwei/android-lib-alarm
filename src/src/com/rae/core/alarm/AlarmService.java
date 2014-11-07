package com.rae.core.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.rae.core.alarm.provider.AlarmProviderFactory;

/**
 * ���ӷ���
 * 
 * @author ChenRui
 * 
 */
public class AlarmService extends Service {
	private static final String	TAG	= "AlarmService";
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
//	@SuppressWarnings("deprecation")
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "���ӷ�������");
		AlarmProviderFactory.refreshAlarmList(getApplicationContext());
//		
//		// ֪ͨ��
//		Notification notification = new Notification();
//		notification.flags = Notification.FLAG_AUTO_CANCEL;
//		notification.tickerText = "�������ѷ����ѿ�����";
//		notification.when = System.currentTimeMillis();
//		notification.icon = getApplicationContext().getApplicationInfo().icon;
//		notification.setLatestEventInfo(getApplicationContext(), "��������", "���ӷ����Ѿ�������", null);
//		
//		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(10021, notification);
		
	}
	
	@Override
	public void onDestroy() {
		Log.e(TAG, "���ӷ��񱻹رգ�ȡ���������ӣ�");
		AlarmProviderFactory.closeAlarmList(this);
		super.onDestroy();
	}
	
	// TODO ���ڼ������ִ�����
	
}
