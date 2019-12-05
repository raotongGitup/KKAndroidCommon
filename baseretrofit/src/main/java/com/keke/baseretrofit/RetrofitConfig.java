package com.keke.baseretrofit;


import android.content.Context;

import java.util.HashMap;

import okhttp3.RequestBody;

/**
 * 文件配置
 */
public class RetrofitConfig {
    private static RetrofitConfig retrofitConfig;
    public Context context;
    private String Guid_MD5_KEY = "";
    private String it_origin = "";
    private String secret_key = "";

    private String EQM_MD5_KEY = "";

    public static RetrofitConfig getInstance() {
        if (retrofitConfig == null) {
            synchronized (RetrofitConfig.class) {
                if (retrofitConfig == null) {
                    retrofitConfig = new RetrofitConfig();
                }
            }
        }
        return retrofitConfig;


    }

    public RequestBody getPOSTbody(HashMap<String, String> map) {
        return RequestEncryption.getPOSTbody(map);
    }

    public HashMap<String, String> initMap(HashMap<String, String> map, String baseUrl, long timestampCorrection) {
        return initMap(false, map, baseUrl, timestampCorrection);
    }

    public HashMap<String, String> initMap(boolean is, HashMap<String, String> map, String baseUrl, long timestampCorrection) {

        return RequestEncryption.initMap(is, map, baseUrl, timestampCorrection);
    }
    /**
     * 初始化接口
     */
    public void initRetrofit(Context context) {
        this.context = context;


    }

    /**
     * 传入获取GUID值的MD5
     */
    public String getMd5Key() {
        return Guid_MD5_KEY;
    }

    public String getEQM_MD5_KEY() {
        return EQM_MD5_KEY;
    }

    public void setEQM_MD5_KEY(String EQM_MD5_KEY) {
        this.EQM_MD5_KEY = EQM_MD5_KEY;
    }

    public void setMd5Key(String md5Key) {
        Guid_MD5_KEY = md5Key;
    }

    public String getIt_origin() {
        return it_origin;
    }

    public void setIt_origin(String it_origin) {
        this.it_origin = it_origin;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }


}
