package com.study.common.util;

import java.text.DecimalFormat;

public class StudyFileUtil {
	public static String fancySize(long size) {
		//꿀팁 포맹정하기
		DecimalFormat df = new DecimalFormat("#,###.00");
		if (size < 1024) {
			return ""+size+"B";
		}else if (size < 1024*1024) {
			return ""+df.format(size/1024.0)+"KB";
		}else if (size < 1024*1024*1024) {
			return ""+df.format(size/1024.0*1024.0)+"MB";
		}
		return ""+df.format(size/1024.0*1024.0*1024.0)+"GB";
	}
}
