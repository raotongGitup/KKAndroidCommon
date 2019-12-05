package com.keke.baseretrofit;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.GradientDrawable;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.hjq.toast.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;


class Utlis {


   public static void copyToClipboard(Context context, String content) {
       ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
       cmb.setPrimaryClip(ClipData.newPlainText(null, content));
       ToastUtils.show("复制成功");
   }

   public static boolean isEmp(String s) {
       if (s == null || "".equals(s) || (s != null && s.length() == 0)) {
           return true;
       } else {
           return false;
       }
   }


   /**
    * 至少包含大小写字母及数字中的两种   8~16位同时包含数字和字母
    * 是否包含
    */
   public static boolean isLetterDigit(String str) {
       boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
       boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
       for (int i = 0; i < str.length(); i++) {
           if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
               isDigit = true;
           } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
               isLetter = true;
           }
       }
       String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
       boolean isRight = isDigit && isLetter && str.matches(regex);
       return isRight;
   }


   public static String getAppMetaData(Context ctx, String key) {
       if (ctx == null || TextUtils.isEmpty(key)) {
           return null;
       }
       String resultData = null;
       try {
           PackageManager packageManager = ctx.getPackageManager();
           if (packageManager != null) {
               ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
               if (applicationInfo != null) {
                   if (applicationInfo.metaData != null) {
                       resultData = applicationInfo.metaData.getString(key);
                   }
               }

           }
       } catch (PackageManager.NameNotFoundException e) {
           e.printStackTrace();
       }

       return resultData;
   }


   /**
    * 当前设备的 IMEI
    */
   public static String MD5_KEY = "D93569608DFED17BA63EF17CCC60E93D";


   public static String getAppUniqueToken(Context context) {
       if (!Utlis.isEmp(RetrofitConfig.getInstance().getEQM_MD5_KEY())) {
           MD5_KEY = RetrofitConfig.getInstance().getEQM_MD5_KEY();
       }


       String result = "";
       String androidId = "" + Settings.Secure.getString(context.getContentResolver(),
               Settings.Secure.ANDROID_ID);
       String imei = getIMEI(context);
       if (imei == null || "".equals(imei)) {
           result = result + androidId;
       } else {
           result = result + imei;
       }
       result = result + MD5_KEY;
       return result;
   }

   public final static int REQUEST_READ_PHONE_STATE = 1;

   public static String getIMEI(Context context) {
       String deviceId = "";
       try {
           TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
           if (null != tm) {
               if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                   ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
               } else {
                   if (tm.getDeviceId() != null) {
                       deviceId = tm.getDeviceId();
                   } else {
                       deviceId = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                   }
               }
               Log.d("deviceId--->", deviceId);
           }
       } catch (Exception ex) {
           ex.printStackTrace();
       }
       return deviceId;
   }

   public static String getLeVersionName(Context context) {
       String versionName = null;
       try {
           versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
       } catch (PackageManager.NameNotFoundException e) {
           e.printStackTrace();
       }
       return versionName;
   }


   /**
    * 获取手机wifi的Ip地址~
    */
   public static String getIPAddress(Context context) {
       WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
       WifiInfo wifiInfo = wifiManager.getConnectionInfo();
       int ipAddress = wifiInfo.getIpAddress();
       // 格式化IP address，例如：格式化前：1828825280，格式化后：192.168.1.109
       return String.format("%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));
   }


   /**
    * CP排⾏榜 kkaudio://coupling/rank
    * 派对列表 kkaudio://room/list?business=0&type=10 business: 0.⼩屋、 1.cbd type：派对类型id
    * 留声机 kkaudio://trend/audio
    * ⽤户等级排⾏榜 kkaudio://user/grade/rank?ntype=1&ctype=1 ntype: 0.富豪 1.魅⼒ 2.名⼈ 3.声望 ctype: 0.⽇ 1.周 2.⽉
    * 礼物排⾏榜 kkaudio://gift/rank
    * 派对排⾏榜 kkaudio://room/rank?ctype=1 ctype: 0.⽇ 1.周 2.⽉
    * 动态⻚⾯ kkaudio://template/view?name=123 name: ⻚⾯别名
    * 派对⻚⾯ kkaudio://room/info?id=123 id:派对Id
    * ⽤户⻚⾯ kkaudio://user/info?id=123 id:⽤户ID
    * 打开⽹⻚ kkaudio://web?url=http%3a%2f%2fwww.kkauido.ltd&type=0 url:需要urlencode type: 0.内跳 2.外跳
    * 动态详情 kkaudio://trend/info?id=123 id:动态ID
    * 声鉴 kkaudio://voice/diff
    */

   /**
    * url转json
    *
    * @param paramStr
    * @return
    */
   public static String getJsonStrByQueryUrl(String paramStr) {
       //String paramStr = "a=a1&b=b1&c=c1";
       String[] params = paramStr.split("&");
       JSONObject obj = new JSONObject();
       for (int i = 0; i < params.length; i++) {
           String[] param = params[i].split("=");
           if (param.length >= 2) {
               String key = param[0];
               String value = param[1];
               for (int j = 2; j < param.length; j++) {
                   value += "=" + param[j];
               }
               try {
                   obj.put(key, value);
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       }
       return obj.toString();
   }

   public static void setCornerRadii(GradientDrawable drawable,
                                     float r0, float r1, float r2, float r3) {
       drawable.setCornerRadii(new float[]{r0, r0, r1, r1,
               r2, r2, r3, r3});
   }

   public static String matcherSearchText(int color, String string, String keyWord) {
       SpannableStringBuilder builder = new SpannableStringBuilder(string);
       int indexOf = string.indexOf(keyWord);
       if (indexOf != -1) {
           builder.setSpan(new ForegroundColorSpan(color), indexOf, indexOf + keyWord.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
       }
       return builder.toString();
   }

}
