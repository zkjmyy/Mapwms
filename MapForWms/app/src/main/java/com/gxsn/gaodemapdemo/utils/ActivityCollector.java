package com.gxsn.gaodemapdemo.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName ActivityCollector
 * @Description 方便随时退出程序
 * @author gpJiao
 * @date 2016年2月2日 下午3:10:39
 */
public class ActivityCollector {
	
	public static List<Activity> activities = new ArrayList<Activity>();
	
	public static void addActivity(Activity activity){
		activities.add(activity);
	}
	
	public static void removeActivity(Activity activity){
		activities.remove(activity);
	}
	
	public static void finishAll(){
		for(Activity activity : activities){
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}

}
