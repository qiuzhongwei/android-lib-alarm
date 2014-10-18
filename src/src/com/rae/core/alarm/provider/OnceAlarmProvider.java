package com.rae.core.alarm.provider;

import android.content.Context;

import com.rae.core.alarm.AlarmEntity;

/**
 * һ��������
 * 
 * @author ChenRui
 * 
 */
public class OnceAlarmProvider extends AlarmProvider {

	public OnceAlarmProvider(Context context, AlarmEntity entity) {
		super(context, entity);
	}

	@Override
	public AlarmEntity create() {
		set(mAlarmEntity.getTime());
		return this.mAlarmEntity;
	}

	@Override
	public void update() {
		getDatabase().delete(mAlarmEntity); // �����ݿ���ɾ������
	}

	@Override
	public void skip() {
		cancle(); // ȡ����ǰ����
		getDatabase().delete(mAlarmEntity); // ��ɾ������
	}

	@Override
	public long getNextAlarmTime(long currentTimeAtMillis) {
		return 0; // һ��������û���´�ʱ�䡣
	}
}
