package com.rae.core.alarm.provider;

import java.util.Calendar;

import android.content.Context;

import com.rae.core.alarm.AlarmEntity;

/**
 * ÿ���ظ�
 * 
 * @author ChenRui
 * 
 */
public class EveryMonthRepeatProvider extends EveryOneTimeRepeatProvider {
	
	public EveryMonthRepeatProvider(Context context, AlarmEntity entity) {
		super(context, entity);
	}

	@Override
	protected long oneCreate() {
		return getNextAlarmTime(System.currentTimeMillis());
	}
	
	
	@Override
	public void update() {
		cancle();
		mAlarmEntity.setNextTime(getNextAlarmTime(System.currentTimeMillis()));
		create();
	}
	
	@Override
	public long getNextAlarmTime(long currentTimeAtMillis) {
		calendar.setTimeInMillis(currentTimeAtMillis);
		calendar.add(Calendar.MONTH, 1); //����һ����
		return calendar.getTimeInMillis();
	}
	
}
