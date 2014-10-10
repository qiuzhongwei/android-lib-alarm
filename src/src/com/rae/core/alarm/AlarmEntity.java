package com.rae.core.alarm;

import android.media.RingtoneManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * ����ʵ��
 * 
 * @author ChenRui
 * 
 */
public class AlarmEntity implements Parcelable {
	/**
	 * һ��
	 */
	public final static int TIME_OF_DAY = 86400000;
	/**
	 * һ��
	 */
	public final static int TIME_OF_WEEK = 604800000;

	/**
	 * ������������״̬
	 */
	public static final int STATUS_RUNING = 0;
	/**
	 * ���Ӵ��ڴ��״̬��һ������ڳ��򱻹رջ��߷���δ�������¡�
	 */
	public static final int STATUS_MISS = -1;

	/**
	 * ���Ӵ��ڹر�״̬��һ�������ڷ���رջ����û��Զ�ֹͣ
	 */
	public static final int STATUS_CLOSE = -2;

	/**
	 * ���Ӵ��ڵȴ���һ������״̬��һ���������Ӹ�����һ������ʱ��ʱ��
	 */
	public static final int STATUS_WAITING = 1;

	public static final String TYPE_ONCE = "TYPE_ONCE"; // һ������
	public static final String TYPE_REPEAT_EVERY_ONE_TIME = "TYPE_REPEAT_EVERY_ONE_TIME"; // ÿN����ظ�1��
	public static final String TYPE_REPEAT_EVERY_DAY = "TYPE_REPEAT_EVERY_DAY"; // ÿ���ظ�
	public static final String TYPE_REPEAT_EVERY_WEEK = "TYPE_REPEAT_EVERY_WEEK"; // ÿ�ܼ��ظ�

	public static Creator<AlarmEntity> CREATOR = new Creator<AlarmEntity>() {

		@Override
		public AlarmEntity[] newArray(int size) {
			return new AlarmEntity[size];
		}

		@Override
		public AlarmEntity createFromParcel(Parcel source) {
			return new AlarmEntity(source);
		}
	};

	private int id; // ����Id
	private String title; // ���ѱ���
	private String content; // ��������
	private String time; // ����ʱ�䣺ʱ����
	private String nextTime; // �´�����ʱ�䡣
	private String cycle; // ���ڣ�һ�Ρ��ظ���
	private String ring; // ����
	private String images; // ͼƬ��
	private String sound; // ����
	private int state; // ����״̬���������رա�ɾ��
	private String otherParam; // ����������key,value ����ʽ��
	private int timeSpan; // ������磺1��1�Ρ�3��1�Ρ���λΪ�����롣
	private int[] weeks; // �ظ����ڡ�������������Ϊ���ܼ��ظ�����Ч��

	public AlarmEntity(String cycle, String title, String time) {
		setCycle(cycle);
		setTitle(title);
		setTime(time);
	}

	public AlarmEntity(Parcel source) {
		id = source.readInt();
		title = source.readString();
		content = source.readString();
		time = source.readString();
		nextTime = source.readString();
		cycle = source.readString();
		ring = source.readString();
		images = source.readString();
		sound = source.readString();
		state = source.readInt();
		timeSpan = source.readInt();
		otherParam = source.readString();
		source.readIntArray(weeks);
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(title);
		dest.writeString(content);
		dest.writeString(time);
		dest.writeString(nextTime);
		dest.writeString(cycle);
		dest.writeString(ring);
		dest.writeString(images);
		dest.writeString(sound);
		dest.writeInt(state);
		dest.writeInt(timeSpan);
		dest.writeString(otherParam);
		dest.writeIntArray(weeks);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getNextTime() {
		return nextTime;
	}

	public void setNextTime(String nextTime) {
		this.nextTime = nextTime;
	}

	public void setNextTime(long time) {
		this.nextTime = AlarmUtils.getDateByTimeInMillis(time);
	}

	public String getCycle() {
		return cycle;
	}

	/**
	 * �����������ͣ��ο���̬�����е�TYPE_??
	 * 
	 * @param cycle
	 */
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getRing() {
		if (TextUtils.isEmpty(ring)) {
			// ��ȡϵͳ�Դ�������
			ring = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM).toString();
		}
		return ring;
	}

	public void setRing(String ring) {
		this.ring = ring;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getOtherParam() {
		return otherParam;
	}

	public void setOtherParam(String otherParam) {
		this.otherParam = otherParam;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getTimeSpan() {
		if (timeSpan == 0) {
			timeSpan = TIME_OF_DAY; // Ĭ��Ϊ1��
		}
		return timeSpan;
	}

	public void setTimeSpan(int timeSpan) {
		this.timeSpan = timeSpan;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append("|");
		sb.append(title);
		sb.append("|");
		sb.append(content);
		sb.append("|");
		sb.append(time);
		sb.append("|");
		sb.append(nextTime);
		sb.append("|");
		sb.append(cycle);
		sb.append("|");
		sb.append(ring);
		sb.append("|");
		sb.append(images);
		sb.append("|");
		sb.append(sound);
		sb.append("|");
		sb.append(state);
		sb.append("|");
		sb.append(otherParam);
		sb.append("|");
		return sb.toString();
	}

	public int[] getWeeks() {
		return weeks;
	}

	public void setWeeks(int[] weeks) {
		this.weeks = weeks;
	}

}
