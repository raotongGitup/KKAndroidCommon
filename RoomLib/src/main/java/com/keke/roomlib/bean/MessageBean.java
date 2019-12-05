package com.keke.roomlib.bean;

public class MessageBean {

    public static final int USER_LOGIN_SUCCES=8;
    public static final int USER_SF_CHANGE=9;


    public static final int IM_XIAO_XI_REFRE=10;
    public static final int DONGTAI_FABU_SUCCES=11;
    public static final int USER_DIY_CHANGE=12;
    public static final int JOIN_OTHER_CHATROOM=13;

    public static final int CLOSE_IM_CONVERSA_ACTIVITY=14;



    public static final int KK_ALL_GIFT=15;


    public static final int ROOM_LEAVE=16;
    public static final int ROOM_LEAVE_CONN=17;
    public static final int ROOM_MUSIC_CHANGE=18;
    public static final int ROOM_CLOSE_ACTIVITY=19;

    public static final int KK_WARNNING=20;
    public static final int USER_60_MES=21;
    public static final int KK_TRANSPARENT=22;
    public static final int KK_MESS_RED_HINT=23;
    public static final int OUT_APP_STROY=24;
    public static final int LOAD_APP_RESOURCE=25;
    public static final int KK_MEET_ROOM=26;




    public static final int SERVICE_LEAVE=27;
    public static final int SERVICE_LEAVE_CONN=28;
    public static final int SERVICE_MUSIC_CHANGE=29;
    public static final int SERVICE_VOICE_EVENT_OTHERS_VOICE=30;
    public static final int SERVICE_JOIN_SUCCES=31;
    public static final int SERVICE_VOICE_EVENT_NET_WORK_QUALITY=32;
    public static final int SERVICE_VOICE_EVENT_REMOTE_AUDIO_STATS=33;
    public static final int SERVICE_VOICE_EVENT_BGM_STOPPED=34;

    public static final int ONETOONE_LEAVE=35;
    public static final int ONETOONE_ADD_ROOM=36;

    public static final int JOIN_OTHER_ONE_ONE=37;

    public static final int REFRESH_RED_LIST = 38;

    public static final int KK_RED_PACKET_STATUS = 39;





    private int mes_code = -1;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;
    private Object bean;

    public int getMes_code() {
        return mes_code;
    }

    public void setMes_code(int mes_code) {
        this.mes_code = mes_code;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
