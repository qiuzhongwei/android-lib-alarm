package com.rae.core.alarm;

import java.util.Comparator;

/**
 * ����ʱ��Ƚ�
 * 
 * @author ChenRui
 * 
 */
class AlarmEntityComparator implements Comparator<AlarmEntity> {
	
	@Override
	public int compare(AlarmEntity lhs, AlarmEntity rhs) {
		// �Ƚ�ʱ�䡣
		// ������l<r;0:l=r;������l>r
		
		long l = AlarmUtils.getTimeInMillis(lhs.getNextTime());
		
		long r = AlarmUtils.getTimeInMillis(rhs.getNextTime());
		
		if (l == r) {
			return 0;
		}
		else if (l < r) {
			return -1;
		}
		else {
			return 1;
		}
		
	}
}