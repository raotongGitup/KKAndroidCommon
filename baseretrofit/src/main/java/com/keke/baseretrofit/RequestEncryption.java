package com.keke.baseretrofit;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.core.app.ActivityCompat;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RequestEncryption {
    /**
     * post请求包裹参数
     */

    public static RequestBody getPOSTbody(HashMap<String, String> map) {
        JSONObject jsonObject = new JSONObject(map);
        return RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),
                toBiao(map));
    }

    public static String toBiao(HashMap<String, String> map) {
        Set<String> strings = map.keySet();
        String s = "";
        for (String string : strings) {
            s = s + string + "=" + map.get(string) + "&";
        }
        return s.substring(0, s.length() - 1);
    }

    /**
     * 请求的加密
     */

    public static HashMap<String, String> initMap(HashMap<String, String> map, String baseUrl, long timestampCorrection) {
        return initMap(false, map, baseUrl, timestampCorrection);
    }

    public static HashMap<String, String> initMap(boolean is, HashMap<String, String> map, String baseUrl, long timestampCorrection) {

        String _t_ = String.valueOf(System.currentTimeMillis() / 1000 + timestampCorrection);
        map.put("guid", getGuid());
        String _s_ = UrlGenerator.generateSignature(is ? "POST" : "GET", baseUrl, map, _t_);

        map.put("_t_", _t_);
        map.put("_s_", _s_);


        HashMap<String, String> maps = new HashMap<>();

        for (String key : map.keySet()) {
            try {
                if (is) {
                    maps.put(key, URLEncoder.encode(map.get(key), "utf-8"));
                } else {
                    maps.put(key, map.get(key));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {

            }
        }


        return maps;
    }


    /**
     * 获取GUID值
     *
     * @return
     */

    private static String mGuid = "";
    private static String MD5_KEY = "879f6c6e1afb557bb7d77220a511099b";

    public static String getGuid() {
        if (TextUtils.isEmpty(mGuid) || (!Utlis.isEmp(mGuid) && mGuid.length() > 32))
            return getGuid(RetrofitConfig.getInstance().context);
        return mGuid;
    }

    public static String getGuid(Context context) {
        if (TextUtils.isEmpty(mGuid) || (!Utlis.isEmp(mGuid) && mGuid.length() > 32)) {//查找sharedPreference 如果没有 生成新的并保存
            SharedPreferences sp = context.getSharedPreferences("GUID", Context.MODE_PRIVATE);
            mGuid = sp.getString("guid", "");
            if (TextUtils.isEmpty(mGuid) || (!Utlis.isEmp(mGuid) && mGuid.length() > 32)) {
                mGuid = MD5Util.md5(DeviceIdUtil.getDeviceId(context));
                sp.edit().putString("guid", mGuid).commit();
            }
        }
        return mGuid;
    }


    @SuppressLint("NewApi")
    private static String getAppUniqueToken(Context context) {
        String IMEI = "";
        String Pseudo_UniqueId = "";
        String macId = "";
        String blueToothId = "";
        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                IMEI = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            macId = ((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Pseudo_UniqueId = "35" + //we make this look like a valid IMEI
                    Build.BOARD.length() % 10 +
                    Build.BRAND.length() % 10 +
                    Build.DEVICE.length() % 10 +
                    Build.DISPLAY.length() % 10 +
                    Build.HOST.length() % 10 +
                    Build.ID.length() % 10 +
                    Build.MANUFACTURER.length() % 10 +
                    Build.MODEL.length() % 10 +
                    Build.PRODUCT.length() % 10 +
                    Build.TAGS.length() % 10 +
                    Build.TYPE.length() % 10 +
                    Build.USER.length() % 10; //13 digits
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            blueToothId = BluetoothAdapter.getDefaultAdapter().getAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        if (!Utlis.isEmp(RetrofitConfig.getInstance().getMd5Key())) {
            MD5_KEY = RetrofitConfig.getInstance().getMd5Key();
        }
        sb.append(IMEI)
                .append(Pseudo_UniqueId)
                .append(macId)
                .append(blueToothId)
                .append(MD5_KEY);

        return MD5Util.md5(sb.toString());
    }
}
