package com.keke.roomlib.room;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.keke.roomlib.R;
import com.keke.roomlib.api.KKRoom;
import com.keke.roomlib.api.PublicApi;
import com.keke.roomlib.api.ResponseListener;
import com.keke.roomlib.base.DoingThingsListener;
import com.keke.roomlib.base.OneToOneImMessageBean;
import com.keke.roomlib.base.OneToOneListener;
import com.keke.roomlib.base.OneToOneMessageBean;
import com.keke.roomlib.base.UserManager;
import com.keke.roomlib.bean.MessageBean;
import com.keke.roomlib.bean.RoomMessageBean;
import com.keke.roomlib.im.RoogYunIMManager;
import com.keke.roomlib.utils.TimeUtil;
import com.keke.roomlib.zego.AgoraManager;
import com.keke.roomlib.zego.VoicesBinderForegroundService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.rong.imlib.RongIMClient;

import static com.keke.roomlib.base.OneToOneMessageBean.ONE_TO_ONE_JOIN_SUCCES;
import static com.keke.roomlib.base.OneToOneMessageBean.ONE_TO_ONE_LEAVE;
import static com.keke.roomlib.bean.MessageBean.ONETOONE_ADD_ROOM;


public class OneToOneManger {

    private static final String TAG = "OneToOneManger";

    private static OneToOneManger mOneToOneManger;
    private final EventBus eventBus;
    private final Gson gson;
    private String mRoomId;
    private int joinType;
    public boolean is_live = false;


    public static final int OTO_JOIN_INVS = 1;
    public static final int OTO_JOIN_ADD = 2;
    private DoingThingsListener is_loginListener;

    public static OneToOneManger getInstance() {
        if (mOneToOneManger == null) {
            synchronized (OneToOneManger.class) {
                if (mOneToOneManger == null) {
                    mOneToOneManger = new OneToOneManger();
                }
            }
        }
        return mOneToOneManger;
    }

    public String getmRoomId() {
        return mRoomId;
    }

    private OneToOneManger() {
        eventBus = EventBus.getDefault();
        eventBus.register(this);
        gson = new Gson();
    }

    public static final int HANDLER_TIME = 1;
    public long mTime = 0;

    public boolean isLike_both =false;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HANDLER_TIME:
                    if (mTime == 0) {
                        return;
                    }
                    long l = mTime - KKRoom.getInstance().getNowTime();
                    if (mOneToOneListener!=null) {
                        if (l>60) {
                            mOneToOneListener.isRedTime(false);
                        }else
                            mOneToOneListener.isRedTime(true);

                    }
                    String djsTime = TimeUtil.getDJSTime(mTime * 1000);

                    if (djsTime.equals("-1")) {
                        mHandler.removeMessages(HANDLER_TIME);
                        leaveRoom(0);
                        return;
                    } else {
                        sendEmptyMessageDelayed(HANDLER_TIME, 1000);
                        if (mOneToOneListener != null) {
                            mOneToOneListener.showRemainTime(djsTime);
                        }
                    }

                    break;
            }
        }
    };


    private int leaveType;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRoomEven(OneToOneMessageBean oneToOneMessageBean) {

        switch (oneToOneMessageBean.getMes_code()) {
            case ONE_TO_ONE_JOIN_SUCCES:
                switch (joinType) {
                    case OTO_JOIN_ADD:
                    case OTO_JOIN_INVS:
//                        changeUserRole(AgoraManager.ROOM_USER_TYPE_DEF);

                        PublicApi.getMeetWait(mRoomId, new ResponseListener() {
                            @Override
                            public void success(Object o) {
                                mTime = ((long) o);
                                mHandler.sendEmptyMessage(HANDLER_TIME);
                                addMeetRoom();
                            }

                            @Override
                            public void error(String s) {
                                leaveRoom(1);
                            }

                            @Override
                            public void onCodeError(int errorCode) {
                                super.onCodeError(errorCode);
                                leaveRoom(1);
                            }
                        });


                        break;
                }
                break;
            case ONE_TO_ONE_LEAVE:
                userOut();
                break;

        }

    }


    private void addMeetRoom() {
        MessageBean messageBean = new MessageBean();
        messageBean.setMes_code(ONETOONE_ADD_ROOM);
        eventBus.post(messageBean);
    }

    public void joinMeet(Context context,String token){
        joinMeet(context,joinType,mRoomId,token,is_loginListener);
    }


    public void joinMeet(Context context, int joinType, final String room, final String token, final DoingThingsListener is_loginListener) {
        mRoomId = room;
        this.joinType = joinType;
        this.is_loginListener = is_loginListener;
        if (RoomManager.getInstance().isLiveRoom) {
            RoomManager.getInstance().leaveRoom(token,7);

            // TODO: 2019/12/3 loading 接口
            //LauncherActivity.goToLoadingActivity(context);
            return;
        }
        is_live = true;
        if (VoicesBinderForegroundService.isDestory) {
            AgoraManager.getInstance().initRoom(new ResponseListener() {
                @Override
                public void success(Object o) {
                    is_loginListener.dos();
                    AgoraManager.getInstance().JoinRoom(room, AgoraManager.ROOM_ONE_TO_ONE, token, is_loginListener);
                }

                @Override
                public void error(String s) {

                }
            });
        } else {
            is_loginListener.dos();
            AgoraManager.getInstance().JoinRoom(room, AgoraManager.ROOM_ONE_TO_ONE, token, is_loginListener);
        }
    }

    private void userOut() {
        if (!VoicesBinderForegroundService.isDestory && AgoraManager.getInstance().getBinderService() != null) {
            AgoraManager.getInstance().getBinderService().initNOtification("");
        }
        RoogYunIMManager.getInstance().quitChatRoom(mRoomId, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess: quitOneToOne  " + mRoomId);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });


        PublicApi.getMeetCloces(mRoomId, new ResponseListener() {
            @Override
            public void success(Object o) {
                mOneToOneListener = null;
                is_live = false;
                MessageBean messageBean = new MessageBean();
                messageBean.setMes_code(MessageBean.ONETOONE_LEAVE);
                eventBus.post(messageBean);
                if (kuaiqie_id == null) {
                    return;
                }
                if (leaveType == 2) {

                    messageBean = new MessageBean();
                    messageBean.setMes_code(MessageBean.JOIN_OTHER_CHATROOM);
                    messageBean.setBean(kuaiqie_id);
                    eventBus.post(messageBean);
                    kuaiqie_id = null;
                }

            }

            @Override
            public void error(String s) {

            }
        });

    }


    // TODO: 2019/12/3 礼物的请求
//    public void giveGift(RankGiftBean.ListBean.GiftBean giftBean){
//        PublicApi.getMeetGiftGive(mRoomId, giftBean.getGift_id() + "", giftBean.getType() + "", new ResponseListener() {
//            @Override
//            public void success(Object o) {
//
//                if (giftBean.getType()==0) {
//                    UserManager.getInstance().getUserBean().setDiamond_balance(new Long(UserManager.getInstance().getUserBean().getDiamond_balance())-giftBean.getPrice());
//                }else {
//                    UserManager.getInstance().getUserBean().setPearl_balance(new Long(UserManager.getInstance().getUserBean().getPearl_balance())-giftBean.getPrice());
//                }
//
//
//            }
//
//            @Override
//            public void error(String s) {
//                PublicApi.getFinanceCash(  new ResponseListener() {
//                    @Override
//                    public void success(Object o) {
//
//                    }
//
//                    @Override
//                    public void error(String s) {
//
//                    }
//                });
//
//            }
//
//            @Override
//            public void onCodeError(int errorCode) {
//                super.onCodeError(errorCode);
//                PublicApi.getFinanceCash(  new ResponseListener() {
//                    @Override
//                    public void success(Object o) {
//
//                    }
//
//                    @Override
//                    public void error(String s) {
//
//                    }
//                });
//            }
//        });
//
//    }


    // TODO: 2019/12/3  点心的动画
//    public void clickIlikeYou() {
//        if (is_Like) {
//            return;
//        }
//        PublicApi.getMeetLike(mRoomId, new ResponseListener() {
//            @Override
//            public void success(Object o) {
//                is_Like = true;
//                if (mOneToOneListener != null) {
//                    mOneToOneListener.showIlikeYou();
//                }
//            }
//
//            @Override
//            public void error(String s) {
//
//            }
//        });
//
//    }


    public String kuaiqie_id;


    /**
     * @param leaveType 0 正常退出 1非正常退出 2 快切房间
     */
    public void leaveRoom(int leaveType) {
        this.leaveType = leaveType;
        if (AgoraManager.getInstance().getBinderService() == null) {
            return;
        }
        AgoraManager.getInstance().getBinderService().leaveRoom();
    }


    public boolean is_MKF = false;
    public boolean is_JY = false;
    public boolean is_Like = false;
    private OneToOneListener mOneToOneListener;


    public void addRoom(OneToOneListener mOneToOneListener) {
        is_MKF = false;
        is_JY = false;
        is_Like = false;
        isLike_both=false;
        this.mOneToOneListener = mOneToOneListener;
        AgoraManager.getInstance().getBinderService().
                changeUserRole(AgoraManager.ROOM_USER_TYPE_ZB, true);

    }


    public boolean changeMKF() {
        is_MKF = !is_MKF;
        AgoraManager.getInstance().getBinderService().
                changeUserRole(is_MKF ? AgoraManager.ROOM_USER_TYPE_DEF : AgoraManager.ROOM_USER_TYPE_ZB, true);
        AgoraManager.getInstance().getBinderService().setVolume(is_JY ? 0 : 100);
        return is_MKF;
    }

    public boolean changeValue() {
        is_JY = !is_JY;
        AgoraManager.getInstance().getBinderService().setVolume(is_JY ? 0 : 100);
        return is_JY;
    }


    public void acceptIM(RoomMessageBean roomMessageBean) {

        String ims = ((String) roomMessageBean.getO());
        OneToOneImMessageBean bean = gson.fromJson(ims, OneToOneImMessageBean.class);


        switch (bean.getK()) {

            case ONE_TO_ONE_MESSAGE_12002:
                leaveRoom(0);
                break;
            case ONE_TO_ONE_MESSAGE_12003:
                mTime = bean.getTime();
                if (bean.getUser_id().equals(UserManager.user_id)) {
                    mOneToOneListener.showGiftAni(true,bean.getGift_image());
                }else {
                    mOneToOneListener.showGiftAni(false,bean.getGift_image());
                }
                mOneToOneListener.showGiftAniBin(bean.getGift_type()==1,bean.getGift_animate_resource(),bean.getGift_id());


                break;
            case ONE_TO_ONE_MESSAGE_12006:
                if (bean.getLike_val() == 50) {
                    mOneToOneListener.changgeLike(R.drawable.sp_z_ani);
                } else {
                    isLike_both=true;
                    mOneToOneListener.changgeLike(R.drawable.sp_quan_ani);
                    mOneToOneListener.showTruespeople(bean);
                }
                break;

        }
    }


    public static final String ONE_TO_ONE_MESSAGE_12006 = "12006";
    public static final String ONE_TO_ONE_MESSAGE_12003 = "12003";
    public static final String ONE_TO_ONE_MESSAGE_12002 = "12002";


}
