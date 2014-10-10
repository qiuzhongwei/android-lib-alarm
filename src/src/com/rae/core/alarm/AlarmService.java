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
	private static final String TAG = "AlarmService";

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "���ӷ�������");
		AlarmProviderFactory.refreshAlarmList(getApplicationContext());

	}

	@Override
	public void onDestroy() {
		Log.e(TAG, "���ӷ��񱻹رգ�ȡ���������ӣ�");
		AlarmProviderFactory.closeAlarmList(this);
		super.onDestroy();
	}

	// TODO ���ڼ������ִ�����

}
