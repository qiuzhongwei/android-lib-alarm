package com.rae.core.alarm.provider;

import java.util.Calendar;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.rae.core.alarm.AlarmEntity;
import com.rae.core.alarm.AlarmUtils;

/**
 * ÿN���1�Ρ�
 * 
 * @author ChenRui
 * 
 */
public class EveryOneTimeRepeatProvider extends AlarmProvider {

	public EveryOneTimeRepeatProvider(Context context, AlarmEntity entity) {
		super(context, entity);
	}

	@Override
	public AlarmEntity create() {
		long triggerAtMillis;
		if (TextUtils.isEmpty(mAlarmEntity.getNextTime())) { // ��һ�δ���
			triggerAtMillis = oneCreate();
		} else {
			triggerAtMillis = AlarmUtils.getTimeInMillis(mAlarmEntity.getNextTime());
			Log.i(TAG, "ȡ�´�����ʱ�䣡");
		}

		if (triggerAtMillis <= System.currentTimeMillis()) {
			triggerAtMillis = getNextAlarmTime(triggerAtMillis); // �������ʱ�䣬������Ϊ�´�����ʱ�䡣
		}

		setRepeat(triggerAtMillis, getTimeSpan());
		return this.mAlarmEntity;
	}


	private int getTimeSpan() {
		return mAlarmEntity.getTimeSpan();
	}
	
	/**
	 * ��һ�δ���ʱ�����
	 */
	protected long oneCreate() {
		Log.i(TAG, "����Ϊ��һ�δ�����");
		return converTime(System.currentTimeMillis(), mAlarmEntity.getTime());
	}
	
	

	@Override
	public void update() {
		String time = mAlarmEntity.getNextTime(); // �´�����ʱ�� = ��ǰʱ�� + �ظ�����
		calendar.clear();
		calendar.setTime(AlarmUtils.parseDate(time));
		calendar.add(Calendar.MILLISECOND, getTimeSpan());
		mAlarmEntity.setNextTime(calendar.getTimeInMillis()); // �����´�����ʱ�䡣
		mAlarmEntity.setState(AlarmEntity.STATUS_WAITING); // ����״̬Ϊ�ȴ�����״̬��
		Log.i(TAG, "�´�����ʱ�䣺" + mAlarmEntity.getNextTime());
		getDatabase().update(mAlarmEntity); // ����ʵ��
	}

	@Override
	public void skip() {
		cancle(); // ȡ������
		update(); // �����´�����
		create(); // ���¿�ʼ
	}

	/**
	 * ��ȡ�´�����ʱ��ʱ��
	 * 
	 * @param time
	 *            ��ǰʱ�䣬��ʽ��08:00
	 * @return
	 */
	public long getNextAlarmTime(long currentTimeAtMillis) {
		long current = System.currentTimeMillis();
		if (currentTimeAtMillis > current) {
			return currentTimeAtMillis;
		}

		// 1����ȡ���ߵ�ʱ���� / �������
		long span = (current - currentTimeAtMillis) % getTimeSpan();

		// 2 �������������ȡ��ǰʱ�� + �������
		if (span == 0) {
			calendar.setTimeInMillis(current);
			calendar.add(Calendar.MILLISECOND, getTimeSpan());
			currentTimeAtMillis = calendar.getTimeInMillis();
		} else {
			// 3�����������򣺴���ʱ�� + (ȡ��+1)*������ڡ�
			int s = (int) ((current - currentTimeAtMillis) / getTimeSpan()) + 1;
			calendar.setTimeInMillis(currentTimeAtMillis);
			calendar.add(Calendar.MILLISECOND, s * getTimeSpan());
			currentTimeAtMillis = calendar.getTimeInMillis();
		}
		Log.i(TAG, "�´�����ʱ��Ϊ��" + AlarmUtils.getDateByTimeInMillis(currentTimeAtMillis));
		calendar.clear();
		return currentTimeAtMillis;
	}

}
