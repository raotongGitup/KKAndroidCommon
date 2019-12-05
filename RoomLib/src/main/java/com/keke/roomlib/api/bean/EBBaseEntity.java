package com.keke.roomlib.api.bean;

public class EBBaseEntity<T> {


    public static final int SUCCESS_CODE = 0;//成功的code
    public static final int FAIL_CODE = -1;//失败的code
    public static final int Expired_CODE = 4;//登陆态已过期
    public static final int TOKEN_ERRO_CODE = 5;//登陆态已过期
    public static final int USER_CSCU = 1;//参数错误
    public static final int USER_YCZ = 2002;//用户已经存在
    public static final int USER_YZMCW = 2003;//验证码错误

    public static final int USER_MMGSCWS = 2004;//密码格式错误
    public static final int USER_MMCW = 2005;//密码错误
    public static final int USER_PBCZ = 2006;//手机不存在
    public static final int USER_YHBCZ = 2007;//用户不存在
    public static final int USER_NCYCZ = 2009;//昵称已存在
    public static final int USER_MMGSCW = 2011;//密码格式错误
    public static final int USER_SJHFDQ = 2012;//手机号非当前登陆账户

    public static final int USER_WZC = 2013;//未注册
    public static final int USER_YZCDQYH = 2014;//已注册&当前登陆账户
    public static final int USER_YZCFDQYH = 2015;//已注册&非当前登陆账户
    public static final int USER_YZCWDL = 2016;//已注册&未登陆
    public static final int USER_YIZHUXIAO = 2053;//已注册&未登陆
    public static final int SYSTEM_INIT_UP = 3001;//版本不存在

    public static final int ROOM_ERRO_CODE=1013;//提出房间
    public static final int ROOM_NEED_PASS_WORD=1019;//房间需要密码
    public static final int USER_CP_NOT_HAVE=7004;//CP不存在
    public static final int CP_STATUS_HIDE =7006;//CP已隐藏





    public static final int ROOM_NOT_OPEN=1016;//提出房间




    private int code;
    private String msg;
    private T data;


    public boolean isSuccess() {
        return getStatus() == SUCCESS_CODE;
    }


    public int getStatus() {
        return code;
    }

    public void setStatus(int status) {
        this.code = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}