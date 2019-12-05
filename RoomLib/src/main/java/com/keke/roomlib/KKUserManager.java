package com.keke.roomlib;

import com.hjq.toast.ToastUtils;
import com.keke.roomlib.api.ResponseListener;
import com.keke.roomlib.bean.UserBean;

/**
 * 用户数据存数据
 *
 * */
public class KKUserManager {
    private static KKUserManager sUserManager;
    private static final String USERINFO_CODE = "userinfo_code";
    private static final String USERINFO = "userinfo";
    private UserBean userBean;
    public static String user_id;
    public static boolean is_new = false;   //判断是否新用户
    private ResponseListener listener;

    public static KKUserManager getInstance() {
        if (sUserManager == null) {
            synchronized (KKUserManager.class) {
                if (sUserManager == null) {
                    sUserManager = new KKUserManager();
                }
            }
        }
        return sUserManager;
    }

    public UserBean getUserBean() {

        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        if(userBean==null){
            ToastUtils.show("请重新登录");
        }
        KKUserManager.user_id=userBean.getUser_id();
        this.userBean = userBean;
    }
}
