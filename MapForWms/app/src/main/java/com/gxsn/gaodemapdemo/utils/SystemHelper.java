package com.gxsn.gaodemapdemo.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.gxsn.gaodemapdemo.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Enumeration;


/**
 * @ClassName: SystemHelper
 * @Description: 获取系统信息的工具类
 * @author WangJJ
 * @date 2015年11月27日 下午2:17:47
 */
public class SystemHelper {
	private static final String TAG = "SystemHelper";

	private SystemHelper() {
	}

//	/**
//	 * 提示友好的异常信息
//	 * @param ctx
//	 * @param t
//	 */
//	public static void makeExceptionToast(Context ctx, Throwable t) {
//		if (t instanceof ConnectTimeoutException) {
//			Toast.makeText(ctx, R.string.network_not_connected,
//					Toast.LENGTH_SHORT).show();
//		} else if (t instanceof NetworkErrorException) {
//			Toast.makeText(ctx, R.string.network_not_connected,
//					Toast.LENGTH_SHORT).show();
//		} else if (t instanceof SocketTimeoutException) {
//			Toast.makeText(ctx, R.string.socket_exception, Toast.LENGTH_SHORT)
//					.show();
//		} else if (t instanceof FileNotFoundException) {
//			Toast.makeText(ctx, R.string.file_not_found_exception,
//					Toast.LENGTH_SHORT).show();
//		} else if (t instanceof IOException) {
//			Toast.makeText(ctx, R.string.io_exception, Toast.LENGTH_SHORT)
//					.show();
//		} else if (t instanceof JSONException) {
//			Toast.makeText(ctx, R.string.data_parser_exception,
//					Toast.LENGTH_SHORT).show();
//		} else {
//			Toast.makeText(ctx, R.string.unknown_exception, Toast.LENGTH_SHORT)
//					.show();
//		}
//	}

	/**
	 * 保存异常日志
	 * 
	 * @param ctx
	 * @param e
	 */
	@SuppressWarnings("deprecation")
	public static void saveErrorLog(Context ctx, Exception e) {
		String errorlog = ctx.getPackageName() + "_errorlog.txt";
		String savePath = "";
		String logFilePath = "";
		PrintWriter pw = null;

		// 判断是否挂载了SD卡
		String storageState = Environment.getExternalStorageState();
		if (storageState.equals(Environment.MEDIA_MOUNTED)) {
			savePath = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
			File file = new File(savePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			logFilePath = savePath + errorlog;

			File logFile = new File(logFilePath);

			try {
				if (!logFile.exists()) {
					logFile.createNewFile();
				}

				pw = new PrintWriter(new FileWriter(logFile, true));// 追加模式

				pw.println("---------" + (new Date().toLocaleString())
						+ "-----------");
				e.printStackTrace(pw);
			} catch (Exception ee) {
				ee.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
			}
		}
	}

	/**
	 * 创建本应用的桌面快捷方式<br/>
	 * 注意：需要添加权限com.android.launcher.permission.INSTALL_SHORTCUT
	 * @param context
	 * @param clazz
	 *            指定快捷方式要启动的Activity类型信息对象
	 */
	public static void createShortcut(Context context, Class<?> clazz) {
		Intent shortcut = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");

		// 快捷方式的名称
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
				context.getString(R.string.app_name));
		shortcut.putExtra("duplicate", false); // 不允许重复创建

		Intent localIntent2 = new Intent(context, clazz);
		localIntent2.setAction(Intent.ACTION_MAIN);
		localIntent2.addCategory(Intent.CATEGORY_LAUNCHER);

		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, localIntent2);// 指定快捷方式要启动的Activity类型

//		// 快捷方式的图标
//		ShortcutIconResource iconResource = ShortcutIconResource
//				.fromContext(context, R.drawable.background_tab);
//		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);

		context.sendBroadcast(shortcut);
	}

	/**
	 * 检查是否已经创建了桌面快捷方式<br/>
	 * 注意：需要添加权限com.android.launcher.permission.READ_SETTINGS
	 * 
	 * @param context
	 * @return
	 */
	public static boolean hasShortCut(Context context) {
		String url = "";
		if (android.os.Build.VERSION.SDK_INT < 8) {
			url = "content://com.android.launcher.settings/favorites?notify=true";
		} else {
			url = "content://com.android.launcher2.settings/favorites?notify=true";
		}
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(Uri.parse(url), null, "title=?",
				new String[] { context.getString(R.string.app_name) }, null);

		if (cursor != null && cursor.moveToFirst()) {
			cursor.close();
			return true;
		}

		return false;
	}

	/**
	 * 判断是否为平板
	 * 
	 * @param context
	 * @return 平板返回true，否则返回false
	 */
	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	/**
	 * 判断是否为横屏
	 * 
	 * @param context
	 * @return 横屏返回true，竖屏返回false
	 */
	public static boolean isLandscape(Context context) {
		int o = context.getResources().getConfiguration().orientation;
		if (o == Configuration.ORIENTATION_LANDSCAPE) {
			return true;
		} else if (o == Configuration.ORIENTATION_PORTRAIT) {
			return false;
		}
		return false;
	}

	/**
	 * 获取当前机器的屏幕信息对象<br/>
	 * 另外：通过android.os.Build类可以获取当前系统的相关信息
	 * @param context
	 * @return
	 */
	public static DisplayMetrics getScreenInfo(Context context) {
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(dm);

		// int w = dm.widthPixels;//寬度（像素）
		// int h = dm.heightPixels; //高度（像素）
		// float d = dm.density; //密度（0.75 / 1.0 / 1.5）
		// int densityDpi = dm.densityDpi; // 屏幕密度DPI（120 / 160 / 240）
		return dm;
	}

	/**
	 * 获取屏幕密度,如0.75/1.0/1.5
	 * 
	 * @return 密度float值
	 */
	public static float getDensity(Context context) {
		DisplayMetrics dm = getScreenInfo(context);
		return dm.density;
	}

	/**
	 * 获取屏幕密度,如160/240/320/480
	 * 
	 * @return 密度dpi值
	 */
	public static int getDensityDpi(Context context) {
		DisplayMetrics dm = getScreenInfo(context);
		return dm.densityDpi;
	}

	/**
	 * 获取屏幕宽度像素值
	 * 
	 * @return
	 */
	public static int getWidthPixels(Context context) {
		DisplayMetrics dm = getScreenInfo(context);
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕高度像素值
	 * 
	 * @return
	 */
	public static int getHeightPixels(Context context) {
		DisplayMetrics dm = getScreenInfo(context);
		return dm.heightPixels;
	}

	/**
	 * 像素转密度Dpi
	 * 
	 * @param context
	 * @param px
	 *            像素值
	 * @return 密度Dpi值
	 */
	public static int px2dip(Context context, float px) {
		return (int) (0.5F + px / getDensity(context));
	}

	/**
	 * 密度Dpi转像素
	 * 
	 * @param context
	 * @param dip
	 *            密度Dpi值
	 * @return 像素值
	 */
	public static int dip2px(Context context, float dip) {
		return (int) (0.5F + dip * getDensity(context));
	}

	/**
	 * 将px值转换为sp值
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值
	 * 
	 * @param context
	 * @param spValue
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 获取手机号<br/>
	 * 注意：需要添加权限android.permission.READ_PHONE_STATE。另外,有很多手机的系统不允许获取当前手机号
	 * 
	 * @param context
	 * @return
	 */
	public static String getMobileNumber(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getLine1Number();
	}

	/**
	 * 检测当前的网络连接是否可用<br/>
	 * 注意：需要添加权限android.permission.ACCESS_NETWORK_STATE
	 * 
	 * @param context
	 * @return
	 * 
	 * */
	public static boolean isConnected(Context context) {
		boolean flag = false;
		try {
			ConnectivityManager connManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (null != connManager) {
				NetworkInfo info = connManager.getActiveNetworkInfo();
				if (null != info && info.isAvailable()) {
					flag = true;
				}
			}
		} catch (Exception e) {
			Log.e(TAG, "Exception", e);
		}
		return flag;
	}

	/**
	 * 检测当前网络连接的类型<br/>
	 * 注意：需要添加权限android.permission.ACCESS_NETWORK_STATE
	 * 
	 * @param context
	 * @return 返回0代表GPRS网络; 返回1,代表WIFI网络; 返回-1代表网络不可用
	 */
	public static int getNetworkType(Context context) {
		int code = -1;
		try {
			ConnectivityManager connManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (null != connManager) {
				State state = connManager.getNetworkInfo(
						ConnectivityManager.TYPE_WIFI).getState();
				if (State.CONNECTED == state) {
					code = ConnectivityManager.TYPE_WIFI;
				} else {
					if (connManager
							.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null) {
						state = connManager.getNetworkInfo(
								ConnectivityManager.TYPE_MOBILE).getState();
						if (State.CONNECTED == state) {
							code = ConnectivityManager.TYPE_MOBILE;
						}
					}
				}
			}
		} catch (Exception e) {
			Log.e(TAG, "Exception", e);
		}
		return code;
	}

	/**
	 * 返回当前程序版本代码,如:1<br/>
	 * 注：这个值是AndroidMainfest.xml中manifest元素的android:versionCode属性值
	 * 
	 * @param context
	 * @return 当前程序版本代码
	 */
	public static int getAppVersionCode(Context context) {
		int versionCode = -1;
		try {
			PackageManager pm = context.getPackageManager();

			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionCode = pi.versionCode;

		} catch (Exception e) {
			Log.e(TAG, "Exception", e);
		}
		return versionCode;
	}

	/**
	 * 返回当前程序版本名,如:1.0<br/>
	 * 注：这个值是AndroidMainfest.xml中manifest元素的android:versionName属性值
	 * 
	 * @param context
	 * @return 当前程序版本名
	 */
	public static String getAppVersionName(Context context) {
		String versionName = "";
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;

		} catch (Exception e) {
			Log.e(TAG, "Exception", e);
		}
		return versionName;
	}

	/**
	 * 安装指定的APK文件，主要用于本应用程序的更新
	 * 
	 * @param context
	 * @param apkFilePath
	 *            apk文件的全路径名
	 */
	public static void installAPK(Context context, String apkFilePath) {
		File apkfile = new File(apkFilePath);
		if (apkfile.exists()) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(apkfile),
					"application/vnd.android.package-archive");
			context.startActivity(intent);
		}
	}

	/**
	 * @Title: getLocalMacAddress
	 * @Description: 得到本机mac地址
	 * @return: String
	 */
	public static String getLocalMacAddress(Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("WifiPreferenceIpAddress", ex.toString());
		}
		return null;
	}
	
	
	/**
     * 关闭软键盘
     */
    public static void hideSoftKeyboard(Context ctx) {
        InputMethodManager inputMethodManager = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputMethodManager != null ) {
            View localView = ((Activity)ctx).getCurrentFocus();
            if(localView != null && localView.getWindowToken() != null ) {
                IBinder windowToken = localView.getWindowToken();
                inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
            }
        }
    }
    
    /**
    *打开软键盘
    */
   public static void toggleSoftInput(Context ctx) {
       // Display the soft keyboard
       InputMethodManager inputMethodManager = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
       if (inputMethodManager != null) {
           View localView = ((Activity)ctx).getCurrentFocus();
           if (localView != null && localView.getWindowToken() != null) {
               inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
           }
       }
   }
   
   /**
    * @Title: doViewFilePrevieIntent
    * @Description: 查看文件，根据后缀自动选择
    * @return: void
    */
   
//   public static void doViewFilePrevieIntent(Context context ,String path) {
//       try {
//           Intent intent = new Intent();
//           intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//           intent.setAction(Intent.ACTION_VIEW);
//           String type = MimeTypesTools.getMimeType(context, path);
//           File file = new File(path);
//           intent.setDataAndType(Uri.fromFile(file), type);
//           context.startActivity(intent);
//       } catch (Exception e) {
//           e.printStackTrace();
//       }
//   }
   
   /**
	 * 计算语音文件的时间长度
	 * 
	 * @param file
	 * @return
	 */
	public static int calculateVoiceTime(String file) {
		File _file = new File(file);
		if (!_file.exists()) {
			return 0;
		}
		// 650个字节就是1s
		int duration = (int) Math.ceil(_file.length() / 650);
		if (duration > 60) {
			return 60;
		}
		if (duration < 1) {
			return 1;
		}
		return duration;
	}

	private static MessageDigest md = null;

	public static String md5(final String c) {
		if (md == null) {
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}

		if (md != null) {
			md.update(c.getBytes());
			return byte2hex(md.digest());
		}
		return "";
	}

	public static String byte2hex(byte b[]) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xff);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = (new StringBuffer(String.valueOf(hs))).toString();
		}

		return hs.toUpperCase();
	}
	
	/**
	 * 将字符串转换成整型，如果为空则返回默认值
	 * 
	 * @param str
	 *            字符串
	 * @param def
	 *            默认值
	 * @return
	 */
	public static int getInt(String str, int def) {
		try {
			if (str == null) {
				return def;
			}
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return def;
	}
	
	static MediaPlayer mediaPlayer = null;
	public static void playNotifycationMusic(Context context, String voicePath)
			throws IOException {
		// paly music ...
		AssetFileDescriptor fileDescriptor = context.getAssets().openFd(
				voicePath);
		if (mediaPlayer == null) {
			mediaPlayer = new MediaPlayer();
		}
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
		}
		mediaPlayer.reset();
		mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),
				fileDescriptor.getStartOffset(), fileDescriptor.getLength());
		mediaPlayer.prepare();
		mediaPlayer.setLooping(false);
		mediaPlayer.start();
	}

}
