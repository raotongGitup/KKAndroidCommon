package com.keke.baseretrofit;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import androidx.core.app.ActivityCompat;


/**
 * @author songxudong
 */
public class DeviceUtil {
    private static int height = -1;
    private static int width = -1;
    private static float densityDpi = -1;
    private static float density = -1;
    //<uses-permission android:name="android.permission.READ_PHONE_STATE" />
    private static String deviceId = "";
    //<uses-permission android:name="android.permission.BLUETOOTH" />
    //android.permission.BLUETOOTH_ADMIN
    private static String deviceName = "";
    private static String model = Build.MODEL;
    private static String android_VersionCode = Build.VERSION.SDK;  //Android版本号
    private static String manufacturer = Build.MANUFACTURER;
    private static int appVersionCode = -1;
    private static String appVersionName = "";
    /**
     * 当前设备的 IMEI
     */
    public static String IMEI;

    public static String MD5_KEY = "D93569608DFED17BA63EF17CCC60E93D";

    public static String getDeviceId(Context context) {
        if (TextUtils.isEmpty(deviceId)) {
            getValues(context);
        }
        return deviceId;
    }

    public static int getHeight(Context context) {
        if (height < 0) {
            getValues(context);
        }
        return height;
    }

    public static int getWidth(Context context) {
        if (width < 0) {
            getValues(context);
        }
        return width;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getModel() {
        return model;
    }

    /**
     * 获取Android版本号
     *
     * @return
     */
    public static String getAndroidVersionCode() {
        return android_VersionCode;
    }

    public static String getManufacturer() {
        return manufacturer;
    }

    public static float getDensityDpi(Context context) {
        if (densityDpi < 0) {
            getValues(context);
        }
        return densityDpi;
    }

    public static float getDensity(Context context) {
        if (density < 0) {
            getValues(context);
        }
        return density;
    }

    private static void  getValues(Context context) {

        DisplayMetrics display = new DisplayMetrics();
        display = context.getResources().getDisplayMetrics();
        width = display.widthPixels;
        height = display.heightPixels;
        densityDpi = display.densityDpi;
        density = display.density;
        if (Build.VERSION.SDK_INT > 22) {
            if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)) {

                TelephonyManager tm = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);//
                deviceId = tm.getDeviceId();
            }
        }else {

            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);//
            deviceId = tm.getDeviceId();
        }
        System.out.println("width" + width);
        System.out.println("height" + height);
        System.out.println("densityDpi" + densityDpi);
        System.out.println("density" + density);
        Log.e("lty", "getValues: "+deviceId);
    }

    /**
     * 获取设备名称
     *
     * @return
     */
    public static String getDeviceName() {
        if (TextUtils.isEmpty(deviceName)) {
            BluetoothAdapter myDevice;
            try {
                myDevice = BluetoothAdapter.getDefaultAdapter();
                deviceName = myDevice.getName();
            } catch (Exception e) {

            }
            if (TextUtils.isEmpty(deviceName)) {
                deviceName = "Android";
            }
        }
        return deviceName;
    }

    private static void getAppVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            appVersionCode = info.versionCode;
            appVersionName = info.versionName;
        } catch (Exception e) {

        }


    }

    public static String getAppVersionName(Context context) {
        if (TextUtils.isEmpty(appVersionName)) {
            getAppVersion(context);
        }
        return appVersionName;
    }

    public static int getAppVersionCode(Context context) {
        if (appVersionCode < 0) {
            getAppVersion(context);
        }
        return appVersionCode;
    }

    public static int px_to_dp(Context context, int pxValue) {
        return (int) (pxValue / getDensity(context) + 0.5f);
    }

    public static int dp_to_px(Context context, float dpValue) {
        return (int) (getDensity(context) * dpValue + 0.5f);
    }

    public static boolean existSDCard() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    //1 竖屏 0横屏
    public static boolean ScreenOrient(Activity activity) {
        int orient = activity.getRequestedOrientation();
        if (orient != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE && orient != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            WindowManager windowManager = activity.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            int screenWidth = display.getWidth();
            int screenHeight = display.getHeight();
            orient = screenWidth < screenHeight ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        }

        return orient == 0;
    }

    @SuppressLint("MissingPermission")
    public static String getAppUniqueToken(Context context) {
        try {
            IMEI = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        } catch (Exception e) {
            //LogUtils.loge("Envi", e.toString());
        }
        String result = "";
        String androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
        if (IMEI == null || "".equals(IMEI)) {
            result = result + androidId;
        } else {
            result = result + IMEI;
        }
        result = result + MD5_KEY;
        return result;
    }
}
