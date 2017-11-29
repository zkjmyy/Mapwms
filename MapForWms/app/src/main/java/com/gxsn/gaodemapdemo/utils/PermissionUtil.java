package com.gxsn.gaodemapdemo.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by zkj on 2017/06/27
 * GreatWall
 */

public class PermissionUtil {
        // 判断权限集合
        public static boolean lacksPermissions(Context context, String... permissions) {
            for (String permission : permissions) {
                if (lacksPermission(context,permission)) {
                    return true;
                }
            }
            return false;
        }

        // 判断是否缺少权限
        private static boolean lacksPermission(Context context, String permission) {
            return ContextCompat.checkSelfPermission(context, permission) ==
                    PackageManager.PERMISSION_DENIED;
        }

}
