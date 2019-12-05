package com.keke.roomlib.base;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.keke.roomlib.api.KKRoom;
import com.keke.roomlib.api.PublicApi;
import com.keke.roomlib.api.ResponseListener;
import com.keke.roomlib.api.bean.EBBaseEntity;
import com.keke.roomlib.bean.UserBean;
import com.keke.roomlib.utils.SharedPreferenceUtil;

/**
 * Created by Administrator on 2018/8/23.
 */

public class UserManager {



    private static UserManager sUserManager;
    private static final String USERINFO_CODE = "userinfo_code";
    private static final String USERINFO = "userinfo";
    private UserBean userBean;
    public static String user_id;
//    public static boolean is_new = false;   //判断是否新用户
//    private ResponseListener listener;
//
    public boolean initUserBean(final boolean isLog, final UserLoginListener listener) {
        String user_code = (String) SharedPreferenceUtil.get(KKRoom.getInstance().mContext, USERINFO_CODE, "");
        if (TextUtils.isEmpty(user_code) || "".equals(user_code)) {
            listener.error(null);
            if (isLog) {
                return true;
            }

            // TODO: 2019/11/30  退出登录
          //  login();
            return true;
        } else {
            PublicApi.getUserInfo(user_code, new ResponseListener() {
                @Override
                public void success(Object o) {

                    resetUserBean((UserBean) o);
                    if (!isLog) {
                        listener.success(o);
                        return;
                    }
                    // TODO: 2019/11/30  退出登录
                  //  login(userBean, listener);
                }

                @Override
                public void error(String s) {
                    listener.error(s);
                    if (isLog) {
                        return;
                    }
                    // TODO: 2019/11/30  退出登录
                  //  login();
                }


                @Override
                public void onCodeError(int errorCode) {
                    super.onCodeError(errorCode);
                    switch (errorCode) {
                        case EBBaseEntity
                                .TOKEN_ERRO_CODE:
                        case EBBaseEntity.Expired_CODE:
                            listener.error("cooke 错误");
                            break;
                    }
                }
            });
            return false;
        }

    }
//
//    public void login() {
//        Log.e(TAG, "login: " );
//        Intent intent = new Intent(ErBanApp.instance, LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        ErBanApp.instance.startActivity(intent);
//    }
    public static UserManager getInstance() {
        if (sUserManager == null) {
            synchronized (UserManager.class) {
                if (sUserManager == null) {
                    sUserManager = new UserManager();
                }
            }
        }
        return sUserManager;
    }
//
//    /**
//     * 登录
//     *
//     * @param user
//     */
//    public void login(UserBean user, UserLoginListener listener) {
//
//        resetUserBean(user);
//        if (userBean.getBirthday() == 0) {
//            is_new = true;
//        } else {
//            is_new = false;
//        }
//
//
//        PublicApi.getMyselefDIy();
//        ErBanApp.instance.initDeviceInfo();
//        ErBanApp.instance.initRankList();
//        AgoraManager.getInstance().initRoom(new ResponseListener() {
//            @Override
//            public void success(Object o) {
//
//
//                if (Utlis.isEmp(ErBanApp.getRong_token())) {
//                    listener.error("Token nulls");
//                    return;
//                }
//                if (ErBanApp.instance.isMainProcess()) {
//                    RongIM.connect(ErBanApp.getRong_token(), new RongIMClient.ConnectCallback() {
//
//                        /**
//                         * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
//                         * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
//                         */
//                        @Override
//                        public void onTokenIncorrect() {
//                            listener.error("Token 过期");
//                        }
//
//                        /**
//                         * 连接融云成功
//                         *
//                         * @param userid 当前 token 对应的用户 id
//                         */
//                        @Override
//                        public void onSuccess(String userid) {
//                            Log.e("roogIM", "--onSuccess" + userid);
//                            RoogYunIMManager.getInstance().setOnReceiveMessageListener();
//                            listener.success(o);
//
//                            MessageBean bean = new MessageBean();
//                            bean.setMes_code(MessageBean.USER_LOGIN_SUCCES);
//                            EventBus.getDefault().post(bean);
//                        }
//
//                        /**
//                         * 连接融云失败
//                         *
//                         * @param errorCode 错误码，可到官网 查看错误码对应的注释
//                         */
//                        @Override
//                        public void onError(RongIMClient.ErrorCode errorCode) {
//                            listener.error(errorCode.getMessage());
//                        }
//                    });
//                }
//
//
//
//            }
//
//            @Override
//            public void error(String s) {
//                listener.error(s);
//            }
//        });
//
//    }
//
//    /**
//     * 退出
//     */
//    private boolean is_logout=false;
//    public void logout(ResponseListener listener) {
//
//        if (RoomManager.getInstance().getmRoomBean()!=null) {
//            is_logout=true;
//            RoomManager.getInstance().leaveRoom(6);
//            return;
//        }
//        ErBanApp.instance.removeUMengAlias();
//        userBean = null;
//        is_new = false;
//        user_id = null;
//        if (ErBanApp.instance.api != null) {
//            ErBanApp.instance.api.clearAll();
//        }
//        // TODO: 2018/12/19    退出zego 退出im
//
//        RoomManager.getInstance().userOut();
//
//        SharedPreferenceUtil.put(ErBanApp.instance, USERINFO, "");
//        SharedPreferenceUtil.put(ErBanApp.instance, USERINFO_CODE, "");
//        AgoraManager.getInstance().unInitRoom();
//        RongIM.getInstance().logout();
//        if (listener ==null) {
//            login();
//            return;
//        }
//
//
//        PublicApi.getLoginOut(new ResponseListener() {
//            @Override
//            public void success(Object o) {
//
//                login();
//                listener.success(o);
//            }
//
//            @Override
//            public void error(String s) {
//                listener.error(s);
//            }
//        });
//    }
//
//
    /**
     * 重置 userbean
     *
     * @param userBean1
     */
    public void resetUserBean(UserBean userBean1) {
        user_id = userBean1.getUser_id();
        userBean = userBean1;
//        PublicApi.getSystemPushUesrInfo(ErBanApp.instance.deviceToken, new ResponseListener() {
//            @Override
//            public void success(Object o) {
//
//            }
//
//            @Override
//            public void error(String s) {
//
//            }
//        });

        // TODO: 2019/11/30  友盟push
       // ErBanApp.instance.addUMengAlias();
        String userinfo = new Gson().toJson(userBean);
        SharedPreferenceUtil.put(KKRoom.getInstance().mContext, USERINFO, userinfo);
        SharedPreferenceUtil.put(KKRoom.getInstance().mContext, USERINFO_CODE, userBean.getUser_id());


        // TODO: 2019/11/30  房间用户雄消息
//        PublicApi.getFinanceCash(new ResponseListener() {
//            @Override
//            public void success(Object o) {
//                String userinfo = new Gson().toJson(userBean);
//                SharedPreferenceUtil.put(KKRoom.getInstance().mContext, USERINFO, userinfo);
//                SharedPreferenceUtil.put(KKRoom.getInstance().mContext, USERINFO_CODE, userBean.getUser_id());
//
//            }
//
//            @Override
//            public void error(String s) {
//
//            }
//        });
    }
//
    /**
     * 获取本地保存 userbean
     */
    public void getLocationUserBean() {
        String userinfo = (String) SharedPreferenceUtil.get(KKRoom.getInstance().mContext, USERINFO, "");
        if (userinfo == null || "".equals(userinfo)) {
            initUserBean(false, new UserLoginListener() {
                @Override
                public void success(Object o) {

                }

                @Override
                public void error(String s) {
                    ToastUtils.show(s);
                }
            });
        } else {
            userBean = new Gson().fromJson(userinfo, UserBean.class);
        }
    }
//
//
//    public UserManager() {
//        EventBus.getDefault().register(this);
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void maEven(MessageBean messageBean){
//
//        switch (messageBean.getMes_code()) {
//            case MessageBean.ROOM_LEAVE:
//                if (is_logout) {
//                    is_logout=false;
//                    Log.e(TAG, "maEven: 3" );
//
//                    UserManager.getInstance().logout(null);
//                }
//                break;
//
//        }
//    }
//
//    private static final String TAG = "UserManager";
//
//
//
//
//
//    /*保存电台房的数据*/
//
//    public static  ArrayList<GuardCacheBean> cacheBea;
//    private LruCache<String,ArrayList<GuardCacheBean>> lruCache=new LruCache<>(1*1024*1024);
//    public ArrayList<GuardCacheBean> getGuardCacheBean(){
//        ArrayList<GuardCacheBean> cast=lruCache.get("SetGuardCacheBean");
//
//        return cast;
//
//    }
//    //保存到内存据中
//    public void SetGuardCacheBean(ArrayList<GuardCacheBean> cacheBean){
//
//        if (cacheBean!=null) {
//            lruCache.put("SetGuardCacheBean",cacheBean);
//        }
//
//
//    }
//
//   public void setGuardCacheclust(){
//       lruCache.remove("SetGuardCacheBean");
//   }
//
//
    public UserBean getUserBean() {
        if (userBean == null) {
            getLocationUserBean();
        }
        return userBean;
    }
//
//    public static void setUserBean(UserBean userBean) {
//        getInstance().resetUserBean(userBean);
//    }
//
//    public static boolean isIs_new() {
//        return is_new;
//    }
//
//
//    public interface UserLoginListener {
//        void success(Object o);
//
//        void error(String s);
//    }
//
//    public String getUserJoinAni(UserBean us){
//        Float wealth = new Float(userBean.getWealth_balance());
//        if (ErBanApp.instance.wealRankList==null){
//            return "";
//        }
//
//
//        for (int i = 0; i < ErBanApp.instance.wealRankList.size(); i++) {
//            WealthRankListBean.ListBean listBean = ErBanApp.instance.wealRankList.get(i);
//
//            if (i==0){
//                if (listBean.getMin_value()>wealth) {
//                    return "";
//                }
//            }
//
//
//            if (listBean.getMax_value() > wealth||(listBean.getMax_value()==0&&i==ErBanApp.instance.wealRankList.size()-1)) {
//                return listBean.getJoin_animation();
//            }
//        }
//
//        return "";
//    }
public interface UserLoginListener {
    void success(Object o);

    void error(String s);
}
}
