package com.keke.roomlib.im.mess;

import android.os.Parcel;
import android.os.Parcelable;


import com.keke.roomlib.im.CustomizeMessage;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;

@MessageTag(value = "kk:redPacketStatus", flag =  MessageTag.NONE| MessageTag.STATUS)
public class KKRedPacketStatusMessage extends CustomizeMessage {
    /**
     * 发消息时调用，将自定义消息对象序列化为消息数据:
     * 首先将消息属性封装成json，再将json转换成byte数组
     */
    public KKRedPacketStatusMessage() {
    }

    public KKRedPacketStatusMessage(byte[] data) {
        super(data);
    }
    /**
     * @param in 通过初始化传入的Parcel，为消息属性赋值
     */
    public KKRedPacketStatusMessage(Parcel in) {
        setmContent(ParcelUtils.readFromParcel(in));
    }

    /**
     * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理
     */
    public static final Parcelable.Creator<KKRedPacketStatusMessage> CREATOR = new Parcelable.Creator<KKRedPacketStatusMessage>() {
        @Override
        public KKRedPacketStatusMessage createFromParcel(Parcel source) {
            return new KKRedPacketStatusMessage(source);
        }

        @Override
        public KKRedPacketStatusMessage[] newArray(int size) {
            return new KKRedPacketStatusMessage[size];
        }
    };

    /**
     * 描述了包含在Parcelable对象排列信息中的特殊对象的类型
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 将类的数据写入到外部提供的Parcel中
     * @param dest 对象被写入的Parcel
     * @param flags 对象如何被写入的附加标志
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelUtils.writeToParcel(dest, mContent);
    }
    public static KKRedPacketStatusMessage obtain(String content) {
        KKRedPacketStatusMessage KKDynamicMessage = new KKRedPacketStatusMessage();
        KKDynamicMessage.mContent = content;
        return KKDynamicMessage;
    }
}
