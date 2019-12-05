package com.keke.roomlib.im;

import android.os.Parcel;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

/**
 * 自定义消息
 */

public abstract class CustomizeMessage extends MessageContent {


    /**
     * 发消息时调用，将自定义消息对象序列化为消息数据:
     * 首先将消息属性封装成json，再将json转换成byte数组
     */

    protected String mContent;

    @Override
    public byte[] encode() {


        try {
            return mContent.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public CustomizeMessage() {

    }
    /**
     * 将收到的消息进行解析，byte -> json,再将json中的内容取出赋值给消息属性
     */

    private static final String TAG = "CustomizeMessage";
    public CustomizeMessage(byte[] data) {
        String jsonString = null;
        try {
            jsonString = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        setmContent(jsonString);
        Log.e(TAG, "CustomizeMessage: "+jsonString );

    }

    public IMbean getmContent() {
        Log.e(TAG, mContent );
        return new Gson().fromJson(mContent,IMbean.class);
    }
    public String getContent() {
        return mContent;
    }


    public void setmContent(String mContent) {
        this.mContent = mContent;
    }
    public void setmContent(Object ob) {
        this.mContent = new Gson().toJson(ob);
    }
}
