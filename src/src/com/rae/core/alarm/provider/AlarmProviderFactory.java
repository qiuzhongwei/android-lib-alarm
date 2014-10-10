package com.rae.core.alarm.provider;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.rae.core.alarm.AlarmDataBase;
import com.rae.core.alarm.AlarmEntity;
import com.rae.core.alarm.IDbAlarm;

/**
 * ��ȡ���Ӳ���ʵ��
 * 
 * @author ChenRui
 * 
 */
public final class AlarmProviderFactory {
	private static final String	TAG	= "AlarmProviderFactory";
	
	/**
	 * ��ȡ����ʵ������
	 * 
	 * @param type
	 *            ���ͣ�Ϊ{@link AlarmEntity} getCycle() ���������͡�
	 * @return
	 */
	public static AlarmProvider getProvider(Context context, AlarmEntity entity) {
		String type = entity.getCycle();
		AlarmProvider provider = null;
		
		if (AlarmEntity.TYPE_REPEAT_EVERY_ONE_TIME.equals(type)) { // �ظ�����
			provider = new EveryOneTimeRepeatProvider(context, entity);
		}
		else if (AlarmEntity.TYPE_REPEAT_EVERY_DAY.equals(type)) { // ÿ���ظ�����
			provider = new EveryDayRepeatAlarmProvider(context, entity);
		}
		else if (AlarmEntity.TYPE_REPEAT_EVERY_WEEK.equals(type)) {// ÿ�ܼ��ظ�����
			provider = new EveryWeekRepeatProvider(context, entity);
		}
		else {// ��������
			provider = new OnceAlarmProvider(context, entity);
		}
		return provider;
	}
	
	/**
	 * ˢ�����ݿ��� ����
	 * 
	 * @param context
	 */
	public static List<AlarmEntity> refreshAlarmList(Context context) {
		AlarmDataBase db = new AlarmDataBase(context);
		List<AlarmEntity> lists = db.getAlarms();
		for (AlarmEntity entity : lists) {
			AlarmProvider provider = getProvider(context, entity);
			provider.cancle();// �ص�֮ǰ������
			provider.create(); // ���¿�������
			Log.i(TAG, "���¸���������״̬��\n" + entity.toString());
			provider = null;
		}
		db.close();
		System.gc();
		return lists;
	}
	
	/**
	 * �ر��������ӡ�
	 * 
	 * @param context
	 */
	public static void closeAlarmList(Context context) {
		// �������е�����Ϊ�ر�״̬
		AlarmDataBase db = new AlarmDataBase(context);
		List<AlarmEntity> lists = db.getAlarms();
		for (AlarmEntity entity : lists) {
			
			AlarmProvider provider = AlarmProviderFactory.getProvider(context, entity);
			provider.cancle(); // ȡ����������
			provider = null;
			
			// ����״̬
			entity.setState(AlarmEntity.STATUS_CLOSE);
			db.update(entity);
		}
		db.close();
		System.gc();
	}
	
//	public static AlarmEntity getAlarmEntityById(Context context, int id) {
//		if (id == 0) { return null; }
//		AlarmDataBase db = new AlarmDataBase(context);
//		AlarmEntity result = db.getAlarm(id);
//		db.close();
//		return result;
//	}
	
	public static IDbAlarm getDbAlarm(Context context){
		return new AlarmDataBase(context);
	}
}
