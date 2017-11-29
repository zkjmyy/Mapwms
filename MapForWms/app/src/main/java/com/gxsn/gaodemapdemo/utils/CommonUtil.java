package com.gxsn.gaodemapdemo.utils;

public class CommonUtil {
//	/**
//	 * 在主线程执行一段任务
//	 * @param r
//	 */
//	public static void runOnUIThread(Runnable r) {
//		LiangZhuApplication.getmHandler().post(r);
//	}
//	/**
//	 * 移除子View
//	 * @param child
//	 */
//	public static void removeSelfFromParent(View child){
//		if(child!=null){
//			ViewParent parent = child.getParent();
//			if(parent!=null && parent instanceof ViewGroup){
//				ViewGroup group = (ViewGroup) parent;
//				group.removeView(child);//移除子View
//			}
//		}
//	}
//
//
//	/**
//	 * 得到共有的sp
//	 * @param name
//	 * @return
//     */
//	public static String getCommonSP(String name){
//		String string = PreUtils.getString(UiUtils.getContext(), name, "");
//		return string;
//	}
//
//	public static int getCommSPint(String key){
//		int anInt = PreUtils.getInt(UiUtils.getContext(), key, -1);
//		return anInt;
//	}
//
//
//
//	public static Drawable getDrawable(int id){
//		return LiangZhuApplication.mContext.getResources().getDrawable(id);
//	}
//
//	public static String getString(int id){
//		return LiangZhuApplication.mContext.getResources().getString(id);
//	}
//	public static String[] getStringArray(int id){
//		return LiangZhuApplication.mContext.getResources().getStringArray(id);
//	}
//
//	public static int getColor(int id){
//		return LiangZhuApplication.mContext.getResources().getColor(id);
//	}
//	/**
//	 * 获取dp资源，并且会自动将dp值转为px值
//	 * @param id
//	 * @return
//	 */
//	public static int getDimens(int id){
//		return LiangZhuApplication.mContext.getResources().getDimensionPixelSize(id);
//	}
}
