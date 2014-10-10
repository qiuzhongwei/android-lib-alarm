package com.rae.core.alarm;

import java.util.List;

/**
 * �����������ݿ�ӿ�
 * 
 * @author ChenRui
 * 
 */
public interface IDbAlarm {
	/**
	 * ��������£�����������ӡ�
	 * 
	 * @param entity
	 * @return
	 */
	int addOrUpdate(AlarmEntity entity);
	
	/**
	 * ɾ��һ������
	 * 
	 * @param entity
	 * @return
	 */
	boolean delete(AlarmEntity entity);
	
	/**
	 * ����һ������
	 * 
	 * @param entity
	 * @return
	 */
	boolean update(AlarmEntity entity);
	
	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	List<AlarmEntity> getAlarms();
	
	/**
	 * ����������ȡһ�����ӡ�
	 * 
	 * @param id
	 * @return
	 */
	AlarmEntity getAlarm(int id);
	
}
