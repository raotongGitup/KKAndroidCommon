package com.keke.roomlib.bean;


import com.keke.roomlib.zego.VoicesStates;

public class RoomMessageBean {

    private int mes_code=-1;
    private String channelID;
    private String senderUserId="";
    private int mRoomType=-1;
    private VoicesStates eventType;
    private Object o;

    public void setmRoomType(int mRoomType) {
        this.mRoomType = mRoomType;
    }

    public String getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public int getMes_code() {
        return mes_code;
    }

    public void setMes_code(int mes_code) {
        this.mes_code = mes_code;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }


    public VoicesStates getEventType() {
        return eventType;
    }

    public void setEventType(VoicesStates eventType) {
        this.eventType = eventType;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }
    public static RoomMessageBean getNew(String channel, VoicesStates eventType){
        RoomMessageBean roomMessageBean = new RoomMessageBean();
        roomMessageBean.setChannelID(channel);
        roomMessageBean.setEventType(eventType);
        return roomMessageBean;
    }
}
