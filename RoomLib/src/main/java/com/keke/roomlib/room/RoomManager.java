package com.keke.roomlib.room;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.hjq.toast.ToastUtils;
import com.keke.roomlib.KKUserManager;
import com.keke.roomlib.R;
import com.keke.roomlib.ani.AnimatioListener;
import com.keke.roomlib.api.KKRoom;
import com.keke.roomlib.api.PublicApi;
import com.keke.roomlib.api.ResponseListener;
import com.keke.roomlib.api.bean.EBBaseEntity;
import com.keke.roomlib.api.bean.SongInfo;
import com.keke.roomlib.base.DoingThingsListener;
import com.keke.roomlib.base.RoomImManager;
import com.keke.roomlib.base.RoomManagerListener;
import com.keke.roomlib.bean.DiyMallBean;
import com.keke.roomlib.bean.HeartMoShiBean;
import com.keke.roomlib.bean.MessageBean;
import com.keke.roomlib.bean.RoomBean;
import com.keke.roomlib.bean.RoomHostInfoBean;
import com.keke.roomlib.bean.RoomImMessageBean;
import com.keke.roomlib.bean.RoomMessageBean;
import com.keke.roomlib.bean.RoomTallyInfo;
import com.keke.roomlib.bean.UserBean;
import com.keke.roomlib.bean.XunZhangBean;
import com.keke.roomlib.im.RoogYunIMManager;
import com.keke.roomlib.utils.SharedPreferenceUtil;
import com.keke.roomlib.utils.Utlis;
import com.keke.roomlib.zego.AgoraManager;
import com.keke.roomlib.zego.VoicesBinderForegroundService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import io.agora.rtc.Constants;
import io.rong.imlib.RongIMClient;


public class RoomManager implements RoomImManager.OnRoomIMDoThings {
    private static RoomManager mRoomManager;
    private final EventBus eventBus;
    private final RoomImManager mRoomImManager;

    private static final String TAG = "RoomManager";


    public int user_sf = -1;


    public boolean isLiveRoom = false;

    private boolean is_MKF = true;
    public boolean is_Xd_Pub = false;  // 心动是否公布


    public int musicVolume = 50;

    private String liveRoom;
    //private DragView mDragView;

    private RoomBean mRoomBean;
    private RoomHostInfoBean roomHostInfoBean;         //主持麦的信息
    private ArrayList<RoomHostInfoBean> ptMinfos = new ArrayList<>();      //普通麦信息
    // 房间内 自己的状态
    private boolean is_Live = false;
    public static final int LEAVE_ROOM = 10005;
    public static final int REF_ROOM_XDONG = 10006;

    public HashSet<String> usPM;

    public long mXingDongTime = -1;
    private ArrayList<RoomTallyInfo.TallyDataBean> tally_data;


    public static RoomManager getInstance() {
        if (mRoomManager == null) {
            synchronized (RoomManager.class) {
                if (mRoomManager == null) {
                    mRoomManager = new RoomManager();
                }
            }
        }
        return mRoomManager;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case LEAVE_ROOM:

                    if (mRoomBean == null) {
                        return;
                    }
                    if (mRoomBean.getClose_timestamp() == 0) {
                        mRoomBean.getRoomImManager().doThings.setDaoJiTime(-1);
                        return;
                    }
                    long time = mRoomBean.getClose_timestamp() - KKRoom.getInstance().getNowTime();
                    if (time <= 0) {

                        if (roomHostInfoBean.getUser() != null) {
                            return;
                        }
                        mHandler.removeMessages(LEAVE_ROOM);
                        leaveRoom(2);
                    } else {
                        mRoomBean.getRoomImManager().doThings.setDaoJiTime(time);
                        mHandler.removeMessages(LEAVE_ROOM);
                        mHandler.sendEmptyMessageDelayed(LEAVE_ROOM, 1000);
                    }

                    break;

                case REF_ROOM_XDONG:

                    if (mXingDongTime == -1) {
                        return;
                    }

                    long time1 = mXingDongTime - KKRoom.getInstance().getNowTime() * 1000;
                    if (time1 > 0) {
                        mRoomManagerListener.refXinDonMess(time1);
                        sendEmptyMessage(REF_ROOM_XDONG);
                    } else {
                        time1 = 0;
                        mRoomManagerListener.refXinDonMess(time1);

                    }


                    break;
            }
        }
    };


    private RoomManagerListener mRoomManagerListener;
    private String mRoomId;
    private boolean isOpenRoom;
    private boolean isPlaying = false;   //加入房间
    /**
     * @param mRoomManagerListener 房间监听器
     * @param mRoomId              房间id
     */
    public void initChatRoom(final RoomManagerListener mRoomManagerListener, final String mRoomId, final boolean isOpenRoom) {
        mRoomManagerListener.onShowLoading();
        this.mRoomManagerListener = mRoomManagerListener;
        this.mRoomId = mRoomId;
        isPlaying = true;
        this.isOpenRoom = isOpenRoom;
        if (mRoomBean != null && mRoomBean.getRoom_id().equals(mRoomId)) {
            initRoom(true, mRoomBean);
        } else {
            //进入房间
            PublicApi.getRoomJoin(mRoomId, new ResponseListener() {
                @Override
                public void success(Object o) {
                    RoomBean roomBean = ((RoomBean) o);
                    if (roomBean.getHeart_opened() == 1) {
                        roomBean.setMelee_opened(1);
                    }

                    if (roomBean.getCertified() == 1) {
                        // TODO: 2019/12/2 认证后的礼物
                        //ErBanApp.instance.giftListBean = ErBanApp.instance.giftCBDListBean;

                    } else {
                        // TODO: 2019/12/2 未认证后的礼物
                       // ErBanApp.instance.giftListBean = ErBanApp.instance.giftPearListBean;

                    }
                    if (isOpenRoom) {
                        roomBean.setOpened(true);
                    }
                    isPking = true;
                    if (roomBean.isOpened()) {
                        if (roomBean.getCertified() == 1) {

                        }
                        initRoom(false, roomBean);
                    } else {
                        if (roomBean.isAllow_open()) {
                            mRoomManagerListener.goToOpenLineRoomActivity(roomBean);
                        }
                        mRoomManagerListener.finishActi();

                    }
                }

                @Override
                public void error(String s) {
                    ToastUtils.show(s);
                    mRoomManagerListener.onCloseLoading();
                }

                @Override
                public void onCodeError(int errorCode) {
                    super.onCodeError(errorCode);
                    mRoomManagerListener.onCloseLoading();
                    switch (errorCode) {
                        case EBBaseEntity.ROOM_ERRO_CODE:
                            ToastUtils.show("您已被房主踢出房间，不能进入该房间！");
                            break;
                        case EBBaseEntity.ROOM_NEED_PASS_WORD:
                            // TODO: 2019/12/2  房间需要密码


//                          RoomPassWordDialog roomPassWordDialog = new RoomPassWordDialog();
//                            roomPassWordDialog.setListenr(new RoomPassWordDialog.onPassWorldDialogListenr() {
//                                @Override
//                                public void ondiss(RoomBean roomBean) {
//                                    if (roomBean == null) {
//                                        mRoomManagerListener.finishActi();
//                                    } else {
//                                        mRoomManagerListener.onCloseLoading();
//                                        if (isOpenRoom) {
//                                            roomBean.setOpened(true);
//                                        }
//                                        if (roomBean.isOpened()) {
//                                            initRoom(false, roomBean);
//                                        } else {
//                                            if (roomBean.isAllow_open()) {
//                                                mRoomManagerListener.goToOpenLineRoomActivity(roomBean);
//                                            }
//                                            mRoomManagerListener.finishActi();
//
//
//                                        }
//                                    }
//                                }
//                            });
//                            roomPassWordDialog.setRoomId(mRoomId);
//                            roomPassWordDialog.show(manager);

                            return;
                    }

                    mRoomManagerListener.finishActi();

                }
            });
        }

    }

    /**
     * @param is_ini
     * @param roomBean
     */

    public void initRoom(final boolean is_ini, final RoomBean roomBean) {
        mRoomManagerListener.iniRoom(roomBean);
        is_Live = false;
        if (!is_ini) {
            is_MKF = false;
        }

        usPM = new HashSet<>();
        //普通麦上麦列表
        PublicApi.getPaiMaiList(mRoomId, new ResponseListener() {
            @Override
            public void success(Object o) {
                List<UserBean> beans = (List<UserBean>) o;

                if (roomBean.getFree_micro() == 1) {
                    return;
                }
                for (int i = 0; i < beans.size(); i++) {
                    usPM.add(beans.get(i).getUser_id() + "");
                }
                // 刷新 排麦人数
                mRoomManagerListener.reUsPM(usPM);
            }

            @Override
            public void error(String s) {

            }
        });


        //   主持信息   获取支持麦
        PublicApi.getRoomHostInfo(mRoomId, new ResponseListener() {
            @Override
            public void success(Object o) {
                roomHostInfoBean = ((RoomHostInfoBean) o);
                //普通麦列表
                PublicApi.getRoomPTInfo(mRoomId, new ResponseListener() {
                    @Override
                    public void success(Object o) {
                        ArrayList<RoomHostInfoBean> roomHostInfoBeanss = ((ArrayList<RoomHostInfoBean>) o);
                        ptMinfos = new ArrayList<>();

                        switch (roomBean.getRoom_type().getId()) {
                            case 9:

                                for (int i = 0; i < roomHostInfoBeanss.size() && i < 3; i++) {
                                    ptMinfos.add(roomHostInfoBeanss.get(i));
                                }
                                break;
                            case 7:

                                for (int i = 0; i < roomHostInfoBeanss.size() && i < 5; i++) {
                                    ptMinfos.add(roomHostInfoBeanss.get(i));
                                }
                                break;
                            default:

                                for (int i = 0; i < roomHostInfoBeanss.size(); i++) {
                                    ptMinfos.add(roomHostInfoBeanss.get(i));
                                }
                                break;
                        }
                        //判断自己是否是主持
                        if (roomHostInfoBean.getUser() != null && roomHostInfoBean.getUser().getUser_id().equals(KKUserManager.user_id)) {
                            is_Live = true;
                            if (!is_ini) {
                                is_MKF = true;
                            }
                            roomHostInfoBean.setVolume(is_MKF);

                            // TODO: 2019/12/2  判断是否是自己然后对其赋值
                            //roomHostInfoBean.setUser(UserManager.getInstance().getUserBean());

                        }

//                        判断普通麦是否有自己
                        for (RoomHostInfoBean ptMinfo : ptMinfos) {
                            if (ptMinfo.getUser() != null && ptMinfo.getUser().getUser_id().equals(KKUserManager.user_id)) {
                                is_Live = true;

                                if (!is_ini) {
                                    is_MKF = true;
                                    ptMinfo.setVolume(is_MKF);
                                    ptMinfo.setVolume_self(is_MKF);
                                }


                                // TODO: 2019/12/2  判断是否是自己然后对其赋值
                               // ptMinfo.setUser(UserManager.getInstance().getUserBean());
                            }
                        }


                        // 房间im消息注册
                        roomBean.setRoomImManager(mRoomImManager);
                        if (is_ini) {
                            mRoomManagerListener.refRoom(roomBean, ptMinfos, is_Live, is_MKF);
                            mRoomManagerListener.refHost(roomHostInfoBean, ptMinfos, roomBean);
                            mRoomManagerListener.refPTUsers(ptMinfos);
                            mRoomManagerListener.onCloseLoading();
                            refHeartINfo();


                        } else {
                            joinRoom(mRoomId, roomBean.getAgora_token(), new DoingThingsListener() {
                                @Override
                                public void succes() {

                                }

                                @Override
                                public void fail() {
                                    leaveRoom(5);
                                }

                                @Override
                                public void dos() {
                                    setmRoomBean(roomBean);
                                    setAudioProfileType(roomBean.getMode_type());
                                }
                            });
                        }


                    }

                    @Override
                    public void error(String s) {
                        ToastUtils.show(s);
                        mRoomManagerListener.onCloseLoading();
                    }
                });

            }

            @Override
            public void error(String s) {

            }
        });
    }

    private void refHeartINfo() {
        if (mRoomBean.getHeart_opened() == 1) {
            PublicApi.getRoomHeartinfo(mRoomId, new ResponseListener() {
                @Override
                public void success(Object o) {
                    HeartMoShiBean moShiBean = (HeartMoShiBean) o;
                    RoomImMessageBean ben = new RoomImMessageBean();
                    ben.setState(moShiBean.getState() + "");
                    ben.setSelect_time(moShiBean.getSelect_time() + "");
                    openXinDong(ben);

                    xdUserIds = new ArrayList<>();
                    for (HeartMoShiBean.SelectDataBean selectDataBean : moShiBean.getSelect_data()) {
                        xdUserIds.add(selectDataBean.getUser_id());
                    }
                    mRoomManagerListener.refRoomIsSeclect();

//
//                    PublicApi.getRoomHeartinfo(mRoomId, new ResponseListener() {
//                        @Override
//                        public void success(Object o) {
//                            HeartMoShiBean moShiBean = (HeartMoShiBean) o;
//
//                        }
//
//                        @Override
//                        public void error(String s) {
//
//                        }
//                    });
                }

                @Override
                public void error(String s) {

                }
            });

        }


        PublicApi.getRoomTallyinfo(mRoomId, new ResponseListener() {
            @Override
            public void success(Object o) {
                RoomTallyInfo tallyInfo = (RoomTallyInfo) o;
                mRoomBean.setRoomTallyInfo(tallyInfo);
                if (tallyInfo.getOpened() != 0) {
                    tally_data = tallyInfo.getTally_data();
                    mRoomManagerListener.refGiftNum(tallyInfo.getTally_data(), ptMinfos);
                    if (mRoomBean.getHeart_opened() == 1) {
                        if (tallyInfo.getMax_send_tally_data() != null) {
                            mRoomManagerListener.showGuiBIngxi(tallyInfo.getMax_send_tally_data());
                        }
                    }
                    if (mRoomBean.getPk_opened() == 1) {
                        if (tallyInfo.getPk_data() != null) {
                            mRoomManagerListener.refPKLeReNum(tallyInfo.getPk_data().getLeft(), tallyInfo.getPk_data().getRight());
                            mRoomManagerListener.setPKcfContent(tallyInfo.getPk_data().getPunish());
                        } else {
                            mRoomManagerListener.setPKcfContent("");

                        }
                    }

                } else {
                    mRoomManagerListener.setPKcfContent("");
                }
            }

            @Override
            public void error(String s) {

            }
        });


    }

    public List<String> xdUserIds = new ArrayList<>();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mainEven(MessageBean messageBean) {
        switch (messageBean.getMes_code()) {
            case MessageBean.ROOM_CLOSE_ACTIVITY:

                if (((String) messageBean.getBean()).equals(mRoomId)) {
                    mRoomManagerListener.finishActi();
                }
                break;
            case MessageBean.USER_DIY_CHANGE:
                // TODO: 2019/12/2  是自己就设置数据
//                if (roomHostInfoBean.getUser() != null && roomHostInfoBean.getUser().getUser_id().equals(UserManager.user_id)) {
//
//
//
//                    roomHostInfoBean.setUser(UserManager.getInstance().getUserBean());
//                }
//
//                for (RoomHostInfoBean ptMinfo : ptMinfos) {
//                    if (ptMinfo.getUser() != null && ptMinfo.getUser().getUser_id().equals(UserManager.user_id)) {
//                        ptMinfo.setUser(UserManager.getInstance().getUserBean());
//                    }
//                }

                mRoomManagerListener.refHost(roomHostInfoBean, ptMinfos, mRoomBean);
                mRoomManagerListener.refPTUsers(ptMinfos);
                break;


            case MessageBean.ROOM_LEAVE_CONN:
                PublicApi.getUserExst(mRoomBean.getRoom_id(), new ResponseListener() {
                    @Override
                    public void success(Object o) {
                        leaveRoom(3);
                    }

                    @Override
                    public void error(String s) {

                    }

                    @Override
                    public void onCodeError(int errorCode) {
                        super.onCodeError(errorCode);
                        switch (errorCode) {
                            case 0:
                                if (mRoomBean == null) {
                                    return;
                                }
                                PublicApi.getRoomInfo(mRoomBean.getRoom_id(), new ResponseListener() {
                                    @Override
                                    public void success(Object o) {

                                        initRoom(true, ((RoomBean) o));
                                    }

                                    @Override
                                    public void error(String s) {

                                    }
                                });
                                break;
                        }

                    }
                });


                break;


        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRoomEven(RoomMessageBean roomMessageBean) {
        if (mRoomId == null) {
            return;
        }
        if (mRoomId.equals(roomMessageBean.getChannelID())) {

            switch (roomMessageBean.getEventType()) {
                case VOICE_EVENT_JOIN_OK:
                    musicVolume = 30;
                    KKRoom.getInstance().isPlaingSong = false;
                    liveRoom = roomMessageBean.getChannelID();
                    isLiveRoom = true;
                    ToastUtils.show("加入房间成功");
                    changeUserRole(AgoraManager.ROOM_USER_TYPE_DEF, true);
                    mRoomManagerListener.refRoom(mRoomBean, ptMinfos, is_Live, is_MKF);
                    mRoomManagerListener.refHost(roomHostInfoBean, ptMinfos, mRoomBean);
                    mRoomManagerListener.refPTUsers(ptMinfos);
                    refHeartINfo();
                    mRoomManagerListener.onCloseLoading();
                    mRoomManagerListener.showCar();
                    mRoomManagerListener.showGongGao();
                    mRoomManagerListener.joinRoomSucces();


                    RoomImMessageBean roomImMessageBean = new RoomImMessageBean();
                    roomImMessageBean.setUid(KKUserManager.user_id);
                    roomImMessageBean.setK(RoomImManager.ROOM_IM_30048);
                    // TODO: 2019/12/2  自己加入房间复制参数


                    roomImMessageBean.setImg(KKUserManager.getInstance().getUserBean().gethead());
                    roomImMessageBean.setV(KKUserManager.getInstance().getUserBean().getV());
                    roomImMessageBean.setSex(KKUserManager.getInstance().getUserBean().getGender() + "");
                    roomImMessageBean.setBt(KKUserManager.getInstance().getUserBean().getBirthday() + "");
                    roomImMessageBean.setWc(KKUserManager.getInstance().getUserBean().getWealth_balance() + "");
                    roomImMessageBean.setNov(KKUserManager.getInstance().getUserBean().getNovice() + "");
                    // TODO: 2019/12/2  神秘人


                    if (mRoomBean.getUser_prop() != null && mRoomBean.getUser_prop().isHide_info()) {
                        roomImMessageBean.setNn(KKRoom.getInstance().mContext.getString(R.string.shengmiren));
                        roomImMessageBean.setTcolor("FF6AC4");
                        roomImMessageBean.setMedal(new ArrayList<XunZhangBean.XunZhangItemBean>());


                    } else {

                        roomImMessageBean.setMedal(KKUserManager.getInstance().getUserBean().getMedal());
                        List<DiyMallBean.DiyItemBean> diyItems = KKUserManager.getInstance().getUserBean().getAttires();
                        roomImMessageBean.setNn(KKUserManager.getInstance().getUserBean().getNick_name());
                        if (diyItems != null) {
                            for (DiyMallBean.DiyItemBean diyItem : diyItems) {
                                if (diyItem.getGoods_type() == 2) {
                                    roomImMessageBean.setRid(diyItem.getGoods_id() + "");
                                    roomImMessageBean.setRres(diyItem.getAnimate_resource());
                                }
                            }
                        }
                    }
                    mRoomBean.setOnline_count(mRoomBean.getOnline_count() + 1);
                    roomImMessageBean.setRuc(mRoomBean.getOnline_count() + "");
                    addUser(roomImMessageBean);


                    if (isOpenRoom) {

                        PublicApi.getInToZhuChiMai(mRoomId, roomHostInfoBean.getMicrophone_id() + "", new ResponseListener() {
                            @Override
                            public void success(Object o) {
                                is_Live = true;
                                is_MKF = true;
                                roomHostInfoBean.setVolume(is_MKF);
                                roomHostInfoBean.setUser(KKUserManager.getInstance().getUserBean());
                                mRoomManagerListener.refHost(roomHostInfoBean, ptMinfos, mRoomBean);
                                mRoomManagerListener.refRoom(mRoomBean, ptMinfos, is_Live, is_MKF);
                            }

                            @Override
                            public void error(String s) {

                            }
                        });


                    }

                    break;

                case VOICE_EVENT_JOIN_FAILED:
                    ToastUtils.show("加入房间失败" + ((String) roomMessageBean.getO()));
                    mRoomManagerListener.onCloseLoading();
                    break;


                case VOICE_EVENT_OTHERS_VOICE:
                    mRoomManagerListener.onlineUserVoiceOn_Off(roomHostInfoBean, ((String) roomMessageBean.getO()), true);
                    break;

                case VOICE_EVENT_LEAVED_ALL:
                    ToastUtils.show("离开房间");
                    mRoomManagerListener.finishActi();
                    userOut();
                    break;
                case VOICE_EVENT_REMOTE_AUDIO_STATS:
                    mRoomManagerListener.voiceEventnetAudioStats(roomMessageBean);
                    break;
                case VOICE_EVENT_NET_WORK_QUALITY:
                    mRoomManagerListener.voiceEventnetWorkQuality(roomMessageBean);
                    break;
                case VOICE_EVENT_BGM_STOPPED:
                    // TODO: 2019/12/2 音乐的判断
//                    if (KKRoom.getInstance().isPlaingSong && roomHostInfoBean.getUser() != null && roomHostInfoBean.getUser().getUser_id().equals(KKUserManager.user_id)) {
//                        if (KKRoom.getInstance().mPlayMusicType == -1) {
//                            playBackgroundMusic(2);
//                        } else {
//                            playBackgroundMusic(1);
//                        }
//                    }
                    break;

            }
        } else {
            switch (roomMessageBean.getEventType()) {
                case VOICE_EVENT_JOIN_OK:
                    isPlaying = false;
                    break;
            }
        }


    }


    public RoomHostInfoBean getRoomHostInfoBean() {
        return roomHostInfoBean;
    }

    public ArrayList<RoomHostInfoBean> getPtMinfos() {
        return ptMinfos;
    }

    public void setmRoomBean(RoomBean mRoomBean) {
        this.mRoomBean = mRoomBean;
        AgoraManager.getInstance().getBinderService().initNOtification(mRoomBean.getRoom_id());


        // TODO: 2019/11/30  延迟加载球状图片
//        mDragView.post(new Runnable() {
//            @Override
//            public void run() {
//                if (mRoomBean == null) {
//                    return;
//                }
//                GlideUtil.getInstance().loadImage(ErBanApp.instance, iv_back, mRoomBean.getImage());
//                mDragView.setVisibility(View.VISIBLE);
//            }
//        });
    }

    public RoomBean getmRoomBean() {
        return mRoomBean;
    }


    // TODO: 2019/11/30
//    public void setmDragView(DragView mDragView, Activity activity) {
//        this.mDragView = mDragView;
//
//        mDragView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!Utlis.isEmp(liveRoom) && !mDragView.is_move) {
//                    LauncherActivity.goToVoiceChatActivity(activity, liveRoom);
//                }
//            }
//        });
//        iv_back = ((CircleImageView) mDragView.findViewById(R.id.iv_back));
//    }


    public void sendText_message(String mes, HashMap<String, Object> maps) {

        chleckSMR();
        mRoomImManager.sendME_10001_text_message(KKUserManager.getInstance().getUserBean(), mes, mRoomId, maps);
    }

//    public void showChatMoreDialog() {
//        UserBean user = roomHostInfoBean.getUser();
//        RoomChatMoreDialog roomChatMoreDialog = new RoomChatMoreDialog(mRoomBean, user == null ? false : user.getUser_id().equals(UserManager.user_id));
//        roomChatMoreDialog.show(manager);
//    }


    // TODO: 2019/12/2  发送礼物
//    public void showGift(UserBean uss) {
//        List<UserBean> us = new ArrayList<>();
//        if (roomHostInfoBean.getUser() != null) {
//            us.add(roomHostInfoBean.getUser());
//        }
//
//        for (RoomHostInfoBean ptMinfo : ptMinfos) {
//            if (ptMinfo.getUser() != null && !UserManager.user_id.equals(ptMinfo.getUser().getUser_id())) {
//                us.add(ptMinfo.getUser());
//            }
//        }
//
//        if (RoomManager.getInstance().getmRoomBean() != null
//                && RoomManager.getInstance().getmRoomBean().getCertified() == 1) {
//
//            GiftAllDialog giftAllDialog = new GiftAllDialog(us, uss);
//            giftAllDialog.show(manager);
//        } else {
//
//            GiftPearlAllDialog giftPearlAllDialog = new GiftPearlAllDialog(us, uss);
//            giftPearlAllDialog.show(manager);
//        }
//    }


//    自己开关 麦克风

    public void changeMKF() {
        if (roomHostInfoBean == null) {
            return;
        }

        if (getmRoomBean() == null) {
            return;
        }

        if (roomHostInfoBean.getUser() != null && roomHostInfoBean.getUser().getUser_id().equals(KKUserManager.user_id)) {

            PublicApi.getMKFZhuChiMai(getmRoomBean().getRoom_id(), roomHostInfoBean.getMicrophone_id() + "", is_MKF, new ResponseListener() {
                @Override
                public void success(Object o) {

                }

                @Override
                public void error(String s) {

                }
            });


            return;
        } else {
            for (RoomHostInfoBean ptMinfo : ptMinfos) {
                if (ptMinfo.getUser() != null && ptMinfo.getUser().getUser_id().equals(KKUserManager.user_id)) {

                    PublicApi.kongzhiPTM(getmRoomBean().getRoom_id(), ptMinfo.getMicrophone_id() + "", is_MKF, 1, new ResponseListener() {
                        @Override
                        public void success(Object o) {
                        }

                        @Override
                        public void error(String s) {
                        }
                    });
                }
            }
        }
    }


    public RoomManager() {
        eventBus = EventBus.getDefault();
        eventBus.register(this);

        // TODO: 2019/12/2  音乐播放问题
        KKRoom.getInstance().songInfos = new ArrayList<>();
        KKRoom.getInstance().playIngSong = null;

        mRoomImManager = new RoomImManager(this);


    }

    public void changeMKF(boolean isOpen) {
        AgoraManager.getInstance().getBinderService().changeMKF(isOpen);
    }


    public void joinRoom(final String room, final String token, final DoingThingsListener is_loginListener) {
       // mChatMessgs = new ArrayList<>();

        if (VoicesBinderForegroundService.isDestory) {
            AgoraManager.getInstance().initRoom(new ResponseListener() {
                @Override
                public void success(Object o) {
                    is_loginListener.dos();
                    AgoraManager.getInstance().JoinRoom(room, AgoraManager.ROOM_CHAT_ROOM, token, is_loginListener);
                }

                @Override
                public void error(String s) {

                }
            });
        } else {
            is_loginListener.dos();
            AgoraManager.getInstance().JoinRoom(room, AgoraManager.ROOM_CHAT_ROOM, token, is_loginListener);
        }
    }


    private String kuaiqie_roomID;

    public void leaveRoom(String kuaiqie_roomID) {
        this.kuaiqie_roomID = kuaiqie_roomID;

        if (AgoraManager.getInstance().getBinderService() == null) {

            return;
        }
        AgoraManager.getInstance().getBinderService().leaveRoom();
    }

    public void leaveRoom(String kuaiqie_roomID, int leaveType) {
        this.kuaiqie_roomID = kuaiqie_roomID;
        this.leaveType = leaveType;

        if (AgoraManager.getInstance().getBinderService() == null) {

            return;
        }
        AgoraManager.getInstance().getBinderService().leaveRoom();
    }


    private int leaveType = 0;  //0 正常，1踢人，2倒计时,3异常离开,4清理房间,5加入房间途中失败,6用户退出, 7切一对一

    public void leaveRoom(int leaveType) {
        this.leaveType = leaveType;
        if (AgoraManager.getInstance().getBinderService() == null) {
            return;
        }
        AgoraManager.getInstance().getBinderService().leaveRoom();
    }


    public boolean isPlayIngRoom(String roomId, String token, DoingThingsListener is_loginListener) {
//        除了正在播放且是正在播放自己房间 其他重新连接
        if (isLiveRoom && roomId.equals(liveRoom)) {
            return true;
        } else {
            joinRoom(roomId, token, is_loginListener);
            return false;
        }

    }

    public void sendMessage(String roomID, HashMap<String, Object> map) {
        AgoraManager.getInstance().getBinderService().sendMessage(roomID, map);
    }


    public void changeUserRole(int role) {
        changeUserRole(role, false);
    }

    public void changeUserRole(int role, boolean is) {
        AgoraManager.getInstance().getBinderService().changeUserRole(role, is);
        user_sf = role;
    }


    public void setOutputToSpeaker(boolean is) {
        AgoraManager.getInstance().getBinderService().setOutputToSpeaker(is);
    }


    public boolean isSpeakerphoneEnabled() {
        return AgoraManager.getInstance().getBinderService().isSpeakerphoneEnabled();
    }


    public void setForceDisableAEC(boolean disableAEC) {
        AgoraManager.getInstance().getBinderService().setForceDisableAEC(disableAEC);
    }


    public boolean isZB_tr() {

        if (mRoomBean.getHost().getUser_id().equals(KKUserManager.user_id)) {
            return true;
        }

        if (roomHostInfoBean.getUser() == null) {
            return false;
        } else {
            return roomHostInfoBean.getUser().getUser_id().equals(KKUserManager.user_id);
        }
    }

    public boolean canTiRen() {

        if (mRoomBean != null && mRoomBean.getUser_role() > 0) {
            return true;
        }
        return false;
    }


    // TODO: 2019/12/2  查看主持麦信息

//    public void clickHost() {
//        if (roomHostInfoBean != null) {
//            if (roomHostInfoBean.getUser() == null) {
//
//                if (mRoomBean.getUser_role() > 0) {
//
//
//                    PublicApi.getInToZhuChiMai(mRoomId, roomHostInfoBean.getMicrophone_id() + "", new ResponseListener() {
//                        @Override
//                        public void success(Object o) {
//
//                        }
//
//                        @Override
//                        public void error(String s) {
//
//                        }
//                    });
//
//                }
//
//            } else {
//                // 查看 主持信息
//
//                if (roomHostInfoBean.getUser().getUser_id().equals(KKUserManager.user_id)) {
//
//
//                    new UserHomeDialog(roomHostInfoBean.getUser().getUser_id(), mRoomId, roomHostInfoBean.getMicrophone_id() + "", isZB_tr(), showAllUH(UserManager.user_id), roomHostInfoBean.isVolume(), true, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            showGift(roomHostInfoBean.getUser());
//                        }
//                    }).show(manager);
//
//                } else {
//
//
//                    if (UserManager.user_id.equals(mRoomBean.getUser_id() + "")) {
//
//                        new UserHomeDialog(roomHostInfoBean.getUser().getUser_id(), mRoomId, roomHostInfoBean.getMicrophone_id() + "", isZB_tr(), showAllUH(UserManager.user_id), roomHostInfoBean.isVolume(), true, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                showGift(roomHostInfoBean.getUser());
//                            }
//                        }).show(manager);
//                    } else {
//                        new UserHomeDialog(roomHostInfoBean.getUser().getUser_id(), mRoomId, roomHostInfoBean.getMicrophone_id() + "", isZB_tr(), showAllUH(UserManager.user_id), roomHostInfoBean.isVolume(), true, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                showGift(roomHostInfoBean.getUser());
//                            }
//                        }).show(manager);
//                    }
//
//                }
//
//            }
//
//        }
//    }


//    判断是否拥有显示全不 userhomedialog权限

    public boolean showAllUH(String uid) {
        if (uid.equals(mRoomBean.getUser_id() + "")) {
            return true;
        }
        if (roomHostInfoBean.getUser() != null && roomHostInfoBean.getUser().getUser_id().equals(uid)) {
            return true;
        }

        return canTiRen();

    }

    public boolean isOnLine(String uid) {
        if (roomHostInfoBean.getUser() != null && uid.equals(roomHostInfoBean.getUser().getUser_id())) {
            return true;
        }

        for (RoomHostInfoBean ptMinfo : ptMinfos) {
            if (ptMinfo.getUser() != null) {
                if (uid.equals(ptMinfo.getUser().getUser_id())) {
                    return true;
                }
            }
        }
        return false;

    }

    public List<UserBean> getOnLinUser() {
        List<UserBean> userBeans = new ArrayList<>();

        if (roomHostInfoBean.getUser() != null) {
            userBeans.add(roomHostInfoBean.getUser());
        }
        for (RoomHostInfoBean ptMinfo : ptMinfos) {
            if (ptMinfo.getUser() != null) {
                userBeans.add(ptMinfo.getUser());
            }
        }
        return userBeans;


    }

    public boolean isVoa(String uid) {
        if (roomHostInfoBean.getUser() != null && uid.equals(roomHostInfoBean.getUser().getUser_id())) {

            return roomHostInfoBean.isVolume();
        }

        for (RoomHostInfoBean ptMinfo : ptMinfos) {
            if (ptMinfo.getUser() != null) {
                if (ptMinfo.getUser() != null && uid.equals(ptMinfo.getUser().getUser_id())) {
                    return ptMinfo.isVolume();
                }
            }
        }
        return false;
    }


    // TODO: 2019/12/2 背景音乐
    public void playBackgroundMusic(String path, boolean isOpen) {
        int n = 0;
        for (int i = 0; i < KKRoom.getInstance().songInfos.size(); i++) {
            if (path.equals(KKRoom.getInstance().songInfos.get(i).getSongUrl())) {
                n = i;
            }
        }
        if (isOpen) {
            KKRoom.getInstance().isPlaingSong = true;
            KKRoom.getInstance().playIngSong = KKRoom.getInstance().songInfos.get(n);
            AgoraManager.getInstance().getBinderService().playBackgroundMusic(path);
        } else {
            if (KKRoom.getInstance().songInfos.size() > n) {
                KKRoom.getInstance().songInfos.remove(n);
                MessageBean messageBean = new MessageBean();
                messageBean.setMes_code(MessageBean.ROOM_MUSIC_CHANGE);
                eventBus.post(messageBean);
            }
            if (KKRoom.getInstance().playIngSong != null) {
                if (KKRoom.getInstance().playIngSong.getSongUrl().equals(path)) {
                    KKRoom.getInstance().isPlaingSong = false;
                    KKRoom.getInstance().playIngSong = null;
                    AgoraManager.getInstance().getBinderService().stopBackgroundMusic();
                }
            }

        }
    }

    public void refRoom() {
        mRoomManagerListener.refRoom(mRoomBean, ptMinfos, is_Live, is_MKF);
    }


    // TODO: 2019/12/2 排麦的dialog
//    public void showPMdialog() {
//        paiMaiDialog = new PaiMaiDialog(mRoomId, RoomManager.getInstance().isZB(), 0, new PaiMaiDialog.refPaiMnum() {
//            @Override
//            public void ref(int num) {
//
//                if (RoomManager.getInstance().getmRoomBean().getFree_micro() == 1) {
//                    return;
//                }
//                mRoomManagerListener.reUsPM(usPM);
//
//            }
//        }, RoomManager.getInstance().getOnLinUser(), 1);
//        paiMaiDialog.show(manager);
//    }
//
//    public void showPMdialog(FragmentManager fragmentManager) {
//        paiMaiDialog = new PaiMaiDialog(mRoomId, RoomManager.getInstance().isZB(), 0, new PaiMaiDialog.refPaiMnum() {
//            @Override
//            public void ref(int num) {
//
//                if (RoomManager.getInstance().getmRoomBean().getFree_micro() == 1) {
//                    return;
//                }
//                mRoomManagerListener.reUsPM(usPM);
//
//            }
//        }, RoomManager.getInstance().getOnLinUser(), 1);
//        paiMaiDialog.show(fragmentManager);
//    }
//
//    public void showPMdialog(String mid) {
//        paiMaiDialog = new PaiMaiDialog(mRoomId, true, 0, mid + "", new PaiMaiDialog.refPaiMnum() {
//            @Override
//            public void ref(int num) {
//                mRoomManagerListener.reUsPM(usPM);
//            }
//        }, getOnLinUser());
//        paiMaiDialog.show(manager);
//    }


    public void chleckSMR() {
        if (mRoomBean != null && mRoomBean.getUser_prop() != null && mRoomBean.getUser_prop().isHide_info()) {
            PublicApi.getProSuerPackInvalid(new ResponseListener() {
                @Override
                public void success(Object o) {
                    mRoomBean.getUser_prop().setHide_info(false);
                }

                @Override
                public void error(String s) {

                }
            });

        }

    }


    // TODO: 2019/12/2  守护的dialog
//    public void showGuardDialog(int guardid, String mid) {
//
////        PublicApi.getGuardType(id, new ResponseListener() {
////            @Override
////            public void success(Object o) {
////                ArrayList<GuardBean> guardBeanArrayList = (ArrayList<GuardBean>)o;
////                List<IMultiItem> list = new ArrayList<>();
////                for (GuardBean guardBean : guardBeanArrayList) {
////                    list.add(new ItemDialogGuardSelect(guardBean.getName(), guardBean.getDay()+""
////                            , new View.OnClickListener() {
////                        @Override
////                        public void onClick(View v) {
////
////                        }
////                    }));
////                }
////                GuardSelectDialog commonBottomSpaceDialog = new GuardSelectDialog()
////            }
////
////            @Override
////            public void error(String s) {
////
////            }
////        });
//        PublicApi.getRoomUserList(mRoomId + "", guardid, new ResponseListener() {
//            @Override
//            public void success(Object o) {
//                RadioMicDialog radioMicDialog = new RadioMicDialog(mRoomId + "", 1
//                        , guardid, (List<UserBean>) o, mid);
//                radioMicDialog.show(manager);
//            }
//
//            @Override
//            public void error(String s) {
//
//            }
//        });
//    }


    // TODO: 2019/12/2  用户的dialog
//    public void showUserDialog(String user_id, String mic_id, String userid, boolean isVolume) {
//        if (mRoomBean == null) {
//            return;
//        }
//        new UserHomeDialog(user_id, RoomManager.getInstance().getmRoomBean().getRoom_id(), mic_id + "", isZB_tr(), showAllUH(userid), isVolume, isOnLine(user_id), new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                UserBean userBean = null;
//                List<UserBean> us = new ArrayList<>();
//                if (roomHostInfoBean.getUser() != null) {
//                    us.add(roomHostInfoBean.getUser());
//                    if (user_id.equals(roomHostInfoBean.getUser().getUser_id())) {
//                        userBean = roomHostInfoBean.getUser();
//                    }
//                }
//
//                for (RoomHostInfoBean ptMinfo : ptMinfos) {
//                    if (ptMinfo.getUser() != null && !UserManager.user_id.equals(ptMinfo.getUser().getUser_id())) {
//                        us.add(ptMinfo.getUser());
//                        if (user_id.equals(ptMinfo.getUser().getUser_id())) {
//                            userBean = ptMinfo.getUser();
//                        }
//                    }
//                }
//
//
//                if (RoomManager.getInstance().getmRoomBean().getCertified() == 1) {
//
//                    GiftAllDialog giftAllDialog = new GiftAllDialog(us, userBean);
//                    giftAllDialog.show(manager);
//                } else {
//
//                    GiftPearlAllDialog giftPearlAllDialog = new GiftPearlAllDialog(us, userBean);
//                    giftPearlAllDialog.show(manager);
//                }
//
//            }
//        }).show(manager);
//    }

    public void setAudioProfile(int profile, int scenario) {
        AgoraManager.getInstance().getBinderService().setAudioProfile(profile, scenario);

    }


    public void setAudioProfileType(int mode_type) {


        switch (mode_type) {
            case 0:
                setAudioProfile(mRoomBean.getChat_mode_switch().equals("1") ? Constants.AUDIO_PROFILE_MUSIC_STANDARD_STEREO : Constants.AUDIO_PROFILE_MUSIC_HIGH_QUALITY, Constants.AUDIO_SCENARIO_SHOWROOM);
                break;
            case 1:
                setAudioProfile(mRoomBean.getGood_audio_mode_switch().equals("1") ? Constants.AUDIO_PROFILE_MUSIC_STANDARD_STEREO : Constants.AUDIO_PROFILE_MUSIC_HIGH_QUALITY, Constants.AUDIO_SCENARIO_GAME_STREAMING);
                break;
            case 2:
                setAudioProfile(mRoomBean.getGreat_audio_mode_switch().equals("1") ? Constants.AUDIO_PROFILE_MUSIC_HIGH_QUALITY : Constants.AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO, Constants.AUDIO_SCENARIO_GAME_STREAMING);
                break;
            case 3:
                setAudioProfile(mRoomBean.getGame_audio_mode_switch().equals("1") ? Constants.AUDIO_PROFILE_MUSIC_STANDARD_STEREO : Constants.AUDIO_PROFILE_MUSIC_HIGH_QUALITY, Constants.AUDIO_SCENARIO_CHATROOM_GAMING);
                break;
        }
    }


    public void setBackgroundMusicVolume(int musicVol) {
        musicVolume = musicVol;
        AgoraManager.getInstance().getBinderService().setBackgroundMusicVolume(musicVol);

    }


    // TODO: 2019/12/2 背景音乐播放
    public void playBackgroundMusic(int type) {

        if (KKRoom.getInstance().songInfos == null || (KKRoom.getInstance().songInfos != null && KKRoom.getInstance().songInfos.size() == 0)) {
            return;
        }
        int top = -1;
        if (KKRoom.getInstance().playIngSong != null) {

            for (int i = 0; i < KKRoom.getInstance().songInfos.size(); i++) {
                if (KKRoom.getInstance().playIngSong.getSongUrl().equals(KKRoom.getInstance().songInfos.get(i).getSongUrl())) {
                    top = i;
                }
            }
        }

        if (top == -1) {
            if (KKRoom.getInstance().songInfos.size() > 0) {
                KKRoom.getInstance().isPlaingSong = true;
                KKRoom.getInstance().playIngSong = KKRoom.getInstance().songInfos.get(0);
                AgoraManager.getInstance().getBinderService().playBackgroundMusic(KKRoom.getInstance().songInfos.get(0).getSongUrl());
                AgoraManager.getInstance().getBinderService().setBackgroundMusicVolume(musicVolume);
            }
        } else {
            switch (type) {
                case -1:  //上一曲
                    if (KKRoom.getInstance().mPlayMusicType == 1) {
                        top = new Random().nextInt(KKRoom.getInstance().songInfos.size());
                    } else {
                        top = top - 1;
                    }
                    if (top == -1) {
                        top = KKRoom.getInstance().songInfos.size() - 1;
                    }
                   KKRoom.getInstance().isPlaingSong = true;
                   KKRoom.getInstance().playIngSong = KKRoom.getInstance().songInfos.get(top);
                    AgoraManager.getInstance().getBinderService().playBackgroundMusic(KKRoom.getInstance().songInfos.get(top).getSongUrl());

                    break;
                case 0:    //暂停开始

                    if (KKRoom.getInstance().isPlaingSong) {
                       KKRoom.getInstance().isPlaingSong = false;
                        AgoraManager.getInstance().getBinderService().stopBackgroundMusic();
                    } else {
                        if (Utlis.isEmp(KKRoom.getInstance().songInfos.get(top).getSongUrl())) {
                            return;
                        }
                        if (!new File(KKRoom.getInstance().songInfos.get(top).getSongUrl()).exists()) {
                            return;
                        }
                        KKRoom.getInstance().isPlaingSong = true;
                        AgoraManager.getInstance().getBinderService().playBackgroundMusic(KKRoom.getInstance().songInfos.get(top).getSongUrl());

                    }

                    break;
                case 1:   //下一曲

                    if (KKRoom.getInstance().mPlayMusicType == 1) {
                        top = new Random().nextInt(KKRoom.getInstance().songInfos.size());
                    } else {
                        top = top + 1;
                    }
                    if (top >= KKRoom.getInstance().songInfos.size()) {
                        top = 0;
                    }
                    KKRoom.getInstance().isPlaingSong = true;
                    KKRoom.getInstance().playIngSong = KKRoom.getInstance().songInfos.get(top);
                    AgoraManager.getInstance().getBinderService().playBackgroundMusic(KKRoom.getInstance().songInfos.get(top).getSongUrl());


                    break;
                case 2:    //循环播放
                    if (Utlis.isEmp(KKRoom.getInstance().songInfos.get(top).getSongUrl())) {
                        return;
                    }
                    if (!new File(KKRoom.getInstance().songInfos.get(top).getSongUrl()).exists()) {
                        return;
                    }
                    KKRoom.getInstance().isPlaingSong = true;
                    AgoraManager.getInstance().getBinderService().playBackgroundMusic(KKRoom.getInstance().songInfos.get(top).getSongUrl());

                    break;
            }
        }


    }

    public void userOut() {
        isLiveRoom = false;
      //  mChatMessgs = new ArrayList<>();
        if (!VoicesBinderForegroundService.isDestory && AgoraManager.getInstance().getBinderService() != null) {
            AgoraManager.getInstance().getBinderService().initNOtification("");
        }

        if (mRoomBean == null) {
            return;
        }


        String room_id = mRoomBean.getRoom_id();
        RoogYunIMManager.getInstance().quitChatRoom(room_id, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess: quitChatRoom  " + liveRoom);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });

        PublicApi.roomQUit(room_id, new ResponseListener() {
            @Override
            public void success(Object o) {
                if (kuaiqie_roomID == null) {
                    if (leaveType == 5) {
                        return;
                    }
                    MessageBean messageBean = new MessageBean();
                    messageBean.setMes_code(MessageBean.ROOM_LEAVE);
                    messageBean.setBean(leaveType);
                    eventBus.post(messageBean);

                    switch (leaveType) {
                        case 3:
                        case 5:
                            return;
                    }
                    ToastUtils.show("离开房间");
                } else {
//                    mDragView.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            if (leaveType == 7) {
//
//                                MessageBean messageBean = new MessageBean();
//                                messageBean.setMes_code(MessageBean.JOIN_OTHER_ONE_ONE);
//                                messageBean.setBean(kuaiqie_roomID);
//                                eventBus.post(messageBean);
//                            } else {
//
//                                MessageBean messageBean = new MessageBean();
//                                messageBean.setMes_code(MessageBean.JOIN_OTHER_CHATROOM);
//                                messageBean.setBean(kuaiqie_roomID);
//                                eventBus.post(messageBean);
//                            }
//                            kuaiqie_roomID = null;
//
//                        }
//                    }, 1000);
                }
            }

            @Override
            public void error(String s) {

            }
        });
        mRoomBean = null;



        if (leaveType == 6) {
            MessageBean messageBean = new MessageBean();
            messageBean.setMes_code(MessageBean.ROOM_LEAVE);
            messageBean.setBean(leaveType);
            eventBus.post(messageBean);
            return;
        }
    }

//
//    public List<IMultiItem> addMess(IMultiItem item) {
//        synchronized (mChatMessgs) {
//            if (mChatMessgs.size() > 30) {
//                List<IMultiItem> da = new ArrayList<>();
//                for (int i = mChatMessgs.size() - 10; i < mChatMessgs.size(); i++) {
//                    if (i >= 0) {
//                        da.add(mChatMessgs.get(i));
//                    }
//                }
//                mChatMessgs = da;
//            }
//
//            mChatMessgs.add(item);
//
//        }
//
//        return mChatMessgs;
//    }


    public void beginDJS() {
        mHandler.sendEmptyMessage(LEAVE_ROOM);
    }


    public void setVolume(int is_mute) {
        AgoraManager.getInstance().getBinderService().setVolume(is_mute);
    }

    public int getVolume() {
        return AgoraManager.getInstance().getBinderService().getVolume();
    }


    public void musicWorR(boolean is) {
        if (is) {
           KKRoom.getInstance().songInfos = new ArrayList<>();
            String music = (String) SharedPreferenceUtil.get(KKRoom.getInstance().mContext, "music", "");
            if (!Utlis.isEmp(music)) {
                JsonArray asJsonArray = new JsonParser().parse(music).getAsJsonArray();
                Gson gson = new Gson();


                for (JsonElement jsonElement : asJsonArray) {
                    SongInfo songInfo = gson.fromJson(jsonElement, SongInfo.class);
                    if (new File(songInfo.getSongUrl()).exists()) {
                        KKRoom.getInstance().songInfos.add(songInfo);
                    }

                }

            }


        } else {
            String json = new Gson().toJson(KKRoom.getInstance().songInfos);

            SharedPreferenceUtil.put(KKRoom.getInstance().mContext, "music", json);

        }
    }

    public void musicWorcq(boolean is) {
        if (is) {
            if (KKRoom.getInstance().songInfos == null) {
                KKRoom.getInstance().songInfos = new ArrayList<>();
            }
            String music = (String) SharedPreferenceUtil.get(KKRoom.getInstance().mContext, "musics", "");
            if (!Utlis.isEmp(music)) {
                JsonArray asJsonArray = new JsonParser().parse(music).getAsJsonArray();
                Gson gson = new Gson();


                for (JsonElement jsonElement : asJsonArray) {
                    SongInfo songInfo = gson.fromJson(jsonElement, SongInfo.class);
                    if (new File(songInfo.getSongUrl()).exists()) {
                        KKRoom.getInstance().songInfos.add(songInfo);
                    }

                }

            }


        } else {
            String json = new Gson().toJson(KKRoom.getInstance().songInfos);

            SharedPreferenceUtil.put(KKRoom.getInstance().mContext, "musics", json);

        }
    }


    private void goTraverALl(String user_id, boolean b, boolean self) {
        if ((roomHostInfoBean.getUser() != null && roomHostInfoBean.getUser().getUser_id().equals(user_id)) || "-1".equals(user_id)) {
            roomHostInfoBean.setVolume(b);
            roomHostInfoBean.setVolume_self(self);
            mRoomManagerListener.refHost(roomHostInfoBean, ptMinfos, mRoomBean);
            return;
        }

        for (int i = 0; i < ptMinfos.size(); i++) {
            if ((ptMinfos.get(i).getUser() != null && ptMinfos.get(i).getUser().getUser_id().equals(user_id)) || "-1".equals(user_id)) {
                ptMinfos.get(i).setVolume(b);
                ptMinfos.get(i).setVolume_self(self);
            }
        }
        mRoomManagerListener.refPTUsers(ptMinfos);
    }


    @Override
    public void onSystemMessage(RoomImMessageBean bean) {
        mRoomManagerListener.onSystemMessage(bean);
    }

    @Override
    public void onReceveGift(RoomImMessageBean bean) {
        List<RoomImMessageBean.TuBean> tu = bean.getTu();

        for (RoomImMessageBean.TuBean tuBean : tu) {
            if (KKUserManager.user_id.equals(tuBean.getId() + "")) {
                KKUserManager.getInstance().getUserBean().setCharm_balance(new Long(tuBean.getCc()));
            }
        }

        mRoomManagerListener.receveGift(bean, tu);
        mRoomManagerListener.showPlayAni(bean);
    }

    @Override
    public void onReceveGiftPearl(RoomImMessageBean bean) {
        List<RoomImMessageBean.TuBean> tu = bean.getTu();

        for (RoomImMessageBean.TuBean tuBean : tu) {
            if (KKUserManager.user_id.equals(tuBean.getId() + "")) {
                KKUserManager.getInstance().getUserBean().setReputation_balance(new Long(tuBean.getRc()));
            }
        }

        mRoomManagerListener.receveGift(bean, tu);
        mRoomManagerListener.showPlayAni(bean);
    }


    @Override
    public void refChatAdapter(RoomImMessageBean bean) {
        mRoomManagerListener.refChatAdapter(bean);

    }

    @Override
    public void zcMUp_Out(UserBean bean, boolean is) {

        if (is) {
            bean.setMid(roomHostInfoBean.getMicrophone_id() + "");
            roomHostInfoBean.setUser(bean);
            roomHostInfoBean.setVolume(true);
            roomHostInfoBean.setVolume_self(true);
        } else {
            roomHostInfoBean.setUser(null);
            roomHostInfoBean.setVolume(true);
            roomHostInfoBean.setVolume_self(true);
        }
        mRoomManagerListener.refRoom(mRoomBean, ptMinfos, is_Live, is_MKF);
        mRoomManagerListener.refHost(roomHostInfoBean, ptMinfos, mRoomBean);
        if (mRoomBean.getHeart_opened() == 1) {
            mRoomManagerListener.refRoomIsSeclect();
        }

    }

    @Override
    public void zcMOut_zl(RoomImMessageBean bean, boolean is) {
        if (is) {
            // TODO: 2019/12/2  电台放的数据存储
           //KKUserManager.getInstance().cacheBea = null;
            if (bean.getUid().equals(KKUserManager.user_id)) {
                is_Live = true;
                is_MKF = true;
               // ErBanApp.instance.isPlaingSong = false;
                changeUserRole(AgoraManager.ROOM_USER_TYPE_ZB);
                zcMUp_Out(KKUserManager.getInstance().getUserBean(), true);
                chleckSMR();

            }


        } else {
            if (isZB() && bean.getMid().equals(roomHostInfoBean.getMicrophone_id() + "")) {
                is_Live = false;
                is_MKF = false;
                changeUserRole(AgoraManager.ROOM_USER_TYPE_DEF);
                zcMUp_Out(KKUserManager.getInstance().getUserBean(), false);
            }
            // TODO: 2019/12/2 守护问题删除
            //KKUserManager.getInstance().setGuardCacheclust();
        }


    }
    @Override
    public void ptM_Up_Out_zl(RoomImMessageBean bean, boolean is) {

        for (int i = 0; i < ptMinfos.size(); i++) {
            if (bean.getMid().equals(ptMinfos.get(i).getMicrophone_id() + "")) {
                if (is) {

                    if (bean.getUid().equals(KKUserManager.user_id)) {


                        KKUserManager.getInstance().getUserBean().setMid(ptMinfos.get(i).getMicrophone_id() + "");
                        is_MKF = true;
                        is_Live = true;
                        ptMinfos.get(i).setUser(KKUserManager.getInstance().getUserBean());
                        ptMinfos.get(i).setVolume(is_MKF);
                        ptMinfos.get(i).setVolume_self(is_MKF);

                        switch (mRoomBean.getRoom_type().getId() + "") {
                            case "7":
                                PublicApi.getRoomIntomicroConfirm(mRoomId, new ResponseListener() {
                                    @Override
                                    public void success(Object o) {
                                    }

                                    @Override
                                    public void error(String s) {

                                    }
                                });
                                continue;

                        }


                        changeUserRole(AgoraManager.ROOM_USER_TYPE_PTM);
                        chleckSMR();

                        // TODO: 2019/12/2  开始互动（咱不知道用在那）

//                        if (bean.getT().equals("1")) {
//                            BaisTitleDialog baisTitleDialog = new BaisTitleDialog();
//                            baisTitleDialog.setTvContent(ErBanApp.instance.getString(R.string.shifoukaishihudong));
//                            baisTitleDialog.setNoOnclickListener(ErBanApp.instance.getString(R.string.kaishishuohua), true, new BaisTitleDialog.onNoOnclickListener() {
//                                @Override
//                                public void onNoClick() {
//                                    changeMKF();
//                                    baisTitleDialog.dismiss();
//                                }
//                            });
//                            baisTitleDialog.setYesOnclickListener(ErBanApp.instance.getString(R.string.xiamai), true, new BaisTitleDialog.onYesOnclickListener() {
//                                @Override
//                                public void onYesClick() {
//
//                                    PublicApi.getPTOUT(UserManager.user_id, mRoomId, bean.getMid() + "", new ResponseListener() {
//                                        @Override
//                                        public void success(Object o) {
//
//                                        }
//
//                                        @Override
//                                        public void error(String s) {
//
//                                        }
//                                    });
//
//                                    baisTitleDialog.dismiss();
//                                }
//                            });
//                            baisTitleDialog.show(manager);
//                        }

                    }


                } else {
                    if (bean.getUid().equals(KKUserManager.user_id)) {
                        is_MKF = true;
                        is_Live = false;
                        changeUserRole(AgoraManager.ROOM_USER_TYPE_DEF);

                        ptMinfos.get(i).setVolume(is_MKF);
                        ptMinfos.get(i).setVolume_self(is_MKF);
                        ptMinfos.get(i).setUser(null);
                    }
                }
            }
        }

        mRoomManagerListener.refRoom(mRoomBean, ptMinfos, is_Live, is_MKF);
        mRoomManagerListener.refPTUsers(ptMinfos);
    }

    @Override
    public void ptM_Out_zl(RoomImMessageBean bean) {


        for (String s : bean.getUids()) {
            if (KKUserManager.user_id.equals(s)) {

                for (int i = 0; i < ptMinfos.size(); i++) {
                    if (ptMinfos.get(i).getUser() != null && ptMinfos.get(i).getUser().getUser_id().equals(KKUserManager.user_id)) {
                        is_MKF = true;
                        is_Live = false;
                        changeUserRole(AgoraManager.ROOM_USER_TYPE_DEF);

                        ptMinfos.get(i).setVolume(is_MKF);
                        ptMinfos.get(i).setVolume_self(is_MKF);
                        ptMinfos.get(i).setUser(null);
                    }


                }
            }
        }


        mRoomManagerListener.refRoom(mRoomBean, ptMinfos, is_Live, is_MKF);
        mRoomManagerListener.refPTUsers(ptMinfos);

    }

    @Override
    public void ptMUp_Out(UserBean bea, boolean is) {

//        Log.e("@@@@", "ptMUp_Out: "+bea.getMid()+"  "+is );
        for (int i = 0; i < ptMinfos.size(); i++) {
            if (bea.getMid().equals(ptMinfos.get(i).getMicrophone_id() + "")) {
                if (is) {
                    ptMinfos.get(i).setUser(bea);
                    ptMinfos.get(i).setVolume(true);
                    ptMinfos.get(i).setVolume_self(true);
                } else {
                    ptMinfos.get(i).setUser(null);
                    ptMinfos.get(i).setVolume(true);
                    ptMinfos.get(i).setVolume_self(true);
                }
            }
        }

        mRoomManagerListener.dZPRefushOne();
        mRoomManagerListener.refPTUsers(ptMinfos);
        mRoomManagerListener.refGiftNum(tally_data, ptMinfos);
        if (mRoomBean.getHeart_opened() == 1) {
            mRoomManagerListener.refRoomIsSeclect();
        }

    }

    @Override
    public void ptMJY(RoomImMessageBean bean, boolean is) {
        for (int i = 0; i < ptMinfos.size(); i++) {
            if (bean.getMid().equals(ptMinfos.get(i).getMicrophone_id() + "")) {
                if (is) {
                    ptMinfos.get(i).setLock(true);
                } else {
                    ptMinfos.get(i).setLock(false);
                }
            }
        }
        mRoomManagerListener.refPTUsers(ptMinfos);
    }

    @Override
    public void ptMLB(RoomImMessageBean bean, boolean is) {
        for (int i = 0; i < ptMinfos.size(); i++) {
            if (bean.getMid().equals(ptMinfos.get(i).getMicrophone_id() + "")) {
                if (is) {
                    ptMinfos.get(i).setBoss(true);
                } else {
                    ptMinfos.get(i).setBoss(false);
                }
            }
        }
        mRoomManagerListener.refPTUsers(ptMinfos);
    }

    @Override
    public void ptMBM_zl(RoomImMessageBean bean, boolean is) {//true——关闭麦克风，false——开启麦克风。


        if (bean.getMid().equals("-1") && is) {
            for (RoomHostInfoBean ptMinfo : ptMinfos) {
                if ((ptMinfo.getUser() != null && ptMinfo.getUser().getUser_id().equals(KKUserManager.user_id))) {
                    changeMKF(is);

                }
            }


        } else {
            for (int i = 0; i < ptMinfos.size(); i++) {
                if ((bean.getMid().equals(ptMinfos.get(i).getMicrophone_id() + "") || bean.getMid().equals("-1")) && (ptMinfos.get(i).getUser() != null && ptMinfos.get(i).getUser().getUser_id().equals(KKUserManager.user_id))) {

                    RoomHostInfoBean roomHostInfoBean = ptMinfos.get(i);


                    boolean volume = ptMinfos.get(i).isVolume();
                    boolean volume_self = ptMinfos.get(i).isVolume_self();

                    if (!is) {
                        if (bean.getVself() == 1) {
                            volume_self = true;
                        } else {
                            volume = true;
                        }


                    } else {
                        if (bean.getVself() == 1) {
                            volume_self = false;
                        } else {
                            volume = false;
                        }
                    }

                    if (volume && volume_self) {
                        is = false;
                    } else {
                        is = true;
                    }


                    changeMKF(is);


                }
            }
        }
    }

    @Override
    public void ptMBM(RoomImMessageBean bean, boolean is) {


        switch (mRoomBean.getRoom_type().getId() + "") {
            case "7":
                return;
        }


        if (bean.getMid().equals("-1")) {
            mRoomBean.setVolume_closed(is ? 1 : 0);
            if (is) {
                for (RoomHostInfoBean ptMinfo : ptMinfos) {
                    if ((ptMinfo.getUser() != null && ptMinfo.getUser().getUser_id().equals(KKUserManager.user_id))) {
                        is_MKF = !is;
                        mRoomManagerListener.ptmivMkfChange(is_MKF, false);
                    }

                }
                mRoomManagerListener.refPTUsers(ptMinfos);
                return;
            }
        }
        for (RoomHostInfoBean ptMinfo : ptMinfos) {
            if (bean.getMid().equals(ptMinfo.getMicrophone_id() + "") || (bean.getMid().equals("-1"))) {

                if (!is) {
                    if (bean.getVself() == 1) {
                        ptMinfo.setVolume_self(true);
                    } else {
                        ptMinfo.setVolume(true);
                    }


                } else {
                    if (bean.getVself() == 1) {
                        ptMinfo.setVolume_self(false);
                    } else {
                        ptMinfo.setVolume(false);
                    }
                }

                if ((ptMinfo.getUser() != null && ptMinfo.getUser().getUser_id().equals(KKUserManager.user_id))) {

                    if ((ptMinfo.isVolume() && ptMinfo.isVolume_self())) {
                        is = false;
                    } else {
                        is = true;
                    }
                    is_MKF = !is;
                    mRoomManagerListener.ptmivMkfChange(is_MKF, ptMinfo.isVolume());
                }

            }


        }
        mRoomManagerListener.refPTUsers(ptMinfos);


    }

    @Override
    public void zcMBM(RoomImMessageBean bean, boolean is) {
        UserBean user = roomHostInfoBean.getUser();

        if (user != null) {

            if (isZB()) {

                changeMKF(!is);
                is_MKF = is;

                mRoomManagerListener.zcmivMkfChange(is_MKF);

            }

            goTraverALl(user.getUser_id(), is, true);
        }

    }

    //判断是否主持
    public boolean isZB() {
        if (roomHostInfoBean.getUser() == null) {
            return false;
        } else {
            return roomHostInfoBean.getUser().getUser_id().equals(KKUserManager.user_id);
        }
    }


    @Override
    public void openXinDong(RoomImMessageBean bean) {
        Integer integer = new Integer(bean.getState());
        mRoomManagerListener.refXinDon(integer - 1);

        if (bean.getState().equals("3")) {
            is_Xd_Pub = true;
        } else {
            is_Xd_Pub = false;
        }
        switch (bean.getState()) {
            case "2":
                mXingDongTime = new Long(bean.getSelect_time()) * 1000 + 5 * 60 * 1000;
                mHandler.sendEmptyMessage(REF_ROOM_XDONG);
                break;
            case "1":
                select_datas = new ArrayList<>();
                tally_data = new ArrayList<>();
                mRoomManagerListener.clearGiftNum(ptMinfos);
                xdUserIds = new ArrayList<>();
                mRoomManagerListener.refRoomIsSeclect();
                mRoomManagerListener.showGuiBIngxi(null);
            default:
                mXingDongTime = -1;
                mRoomManagerListener.refXinDonMess(5 * 60 * 1000);
                break;
        }

    }

    @Override
    public void closeXinDong(RoomImMessageBean bean) {


//        ArrayList<HeartMoShiBean.SelectDataBean> select_data = new ArrayList<>();
        ArrayList<HeartMoShiBean.SelectDataBean> select_datas = bean.getSelect_data();

//        c:
//        for (int i = 0; i < select_datas.size() - 1; i++) {
//            HeartMoShiBean.SelectDataBean selectData = select_datas.get(i);
//            for (int i1 = i + 1; i1 < select_datas.size(); i1++) {
//                HeartMoShiBean.SelectDataBean select_datum = select_datas.get(i1);
//                if (selectData.getUser_id().equals(select_datum.getSelect_user_id()) && selectData.getSelect_user_id().equals(select_datum.getUser_id())) {
//                    select_data.add(selectData);
//                    continue c;
//                }
//            }
//        }

        this.select_datas = select_datas;
        mRoomManagerListener.showXDChose(select_datas, ptMinfos);
    }

    public ArrayList<HeartMoShiBean.SelectDataBean> select_datas;

    @Override
    public void againXinDong(RoomImMessageBean bean) {
        tally_data = new ArrayList<>();
        mRoomManagerListener.clearGiftNum(ptMinfos);
        if (mRoomBean.getRoomTallyInfo().getOpened() == 0) {
            mRoomManagerListener.closeGiftNum(ptMinfos);

        }

    }

    @Override
    public void upDaXinDong(RoomImMessageBean bean) {

        a:
        for (RoomTallyInfo.TallyDataBean tallyDataBean : bean.getTally_data()) {
            for (RoomTallyInfo.TallyDataBean tally_datum : tally_data) {
                if (tally_datum.getUser_id().equals(tallyDataBean.getUser_id())) {
                    tally_datum.setVal(tallyDataBean.getVal());
                }
            }
            tally_data.add(tallyDataBean);
        }


        if (tally_data != null && tally_data.size() > 0) {
            mRoomManagerListener.refGiftNum(tally_data, ptMinfos);
        }
        mRoomManagerListener.showGuiBIngxi(bean.getMax_send_tally_data());


    }

   // PaiMaiDialog paiMaiDialog;


    // TODO: 2019/12/2  添加排麦通知
    @Override
    public void addPM(UserBean userBean) {

        if (mRoomBean.getFree_micro() == 1) {
            return;
        }
        if (usPM == null) {
            usPM = new HashSet<>();
        }
        usPM.add(userBean.getUser_id() + "");
        mRoomManagerListener.reUsPM(usPM);

//        if (paiMaiDialog != null) {
//            paiMaiDialog.addUS(userBean);
//        }

    }

    @Override
    public void removePM(UserBean userBean) {
        if (usPM == null) {
            usPM = new HashSet<>();
        }
        usPM.remove(userBean.getUser_id() + "");
        mRoomManagerListener.reUsPM(usPM);

//        if (paiMaiDialog != null) {
//            paiMaiDialog.removeUs(userBean);
//        }
    }

    @Override
    public void clearPM() {

        usPM = new HashSet<>();
        mRoomManagerListener.reUsPM(usPM);
//        if (paiMaiDialog != null) {
//            paiMaiDialog.clear();
//        }
    }

    @Override
    public void changeRoomBack(RoomImMessageBean bean) {
        mRoomManagerListener.changeRoomBack(bean);
    }

    @Override
    public void expressionAnimation(final RoomImMessageBean bean) {

        AnimatioListener animatioListener = null;
        if (!isOnLine(bean.getUid()) || (isOnLine(bean.getUid()) && "0".equals(bean.getMlt()) && "1".equals(bean.getPro()))) {

            bean.setFnn(bean.getNn());
            bean.setFimg(bean.getImg());
            if (bean.getSm().equals("1") || Utlis.isEmp(bean.getSm())) {
                refChatAdapter(bean);
            }
        } else {
            animatioListener = new AnimatioListener() {
                @Override
                public void onAniOver() {
                    super.onAniOver();
                    if (!"0".equals(bean.getMlt())) {
                        bean.setFnn(bean.getNn());
                        bean.setFimg(bean.getImg());
                        if (bean.getSm().equals("1") || Utlis.isEmp(bean.getSm())) {
                            refChatAdapter(bean);
                        }
                    }
                }
            };
        }
        mRoomManagerListener.expressionAnimation(roomHostInfoBean, ptMinfos, animatioListener, bean);
    }

    @Override
    public void userOUt(RoomImMessageBean bean) {

    }

    @Override
    public void setHeiMingDang(RoomImMessageBean bean, boolean is) {
        if (bean.getUid().equals(KKUserManager.user_id)) {
            if (is) {
                mRoomBean.setIs_block(true);
            } else {
                mRoomBean.setIs_block(false);
            }
            mRoomManagerListener.refRoom(mRoomBean, ptMinfos, is_Live, is_MKF);
        }
    }

    @Override
    public void setGuanLiYuan(RoomImMessageBean bean, boolean is) {
        if (bean.getUid().equals(KKUserManager.user_id)) {
            if (is) {
                mRoomBean.setUser_role(1);
            } else {
                mRoomBean.setUser_role(0);
            }
            mRoomManagerListener.refRoom(mRoomBean, ptMinfos, is_Live, is_MKF);

        }
    }

    @Override
    public void bianjPIC(RoomImMessageBean bean) {
        mRoomBean.setImage(bean.getImg());

    }

    @Override
    public void bianjName(RoomImMessageBean bean) {

        mRoomBean.setName(bean.getN());
        mRoomManagerListener.refRoom(mRoomBean, ptMinfos, is_Live, is_MKF);
    }

    @Override
    public void bianjGonggao(RoomImMessageBean bean) {
        mRoomBean.setNotice(bean.getN());
    }

    @Override
    public void addUser(RoomImMessageBean bean) {
        mRoomManagerListener.addUser(bean);
    }

    @Override
    public void remUser(RoomImMessageBean bean) {
        mRoomManagerListener.remUser(bean);
    }

    @Override
    public void setUserGiftJB(RoomImMessageBean bean, boolean is, boolean is_zc) {

        RoomHostInfoBean.JBGiftBean jbGiftBean;
        if (is) {
            jbGiftBean = new RoomHostInfoBean.JBGiftBean();
            jbGiftBean.setId(bean.getGid());
            jbGiftBean.setNum(bean.getGc());
            jbGiftBean.setUser_id(bean.getUid());
            jbGiftBean.setUser_image(bean.getImg());
            jbGiftBean.setUser_nickname(bean.getNn());

        } else {
            jbGiftBean = null;
        }

        if (is_zc) {

            if (RoomManager.getInstance().roomHostInfoBean != null && bean.getMid().equals(RoomManager.getInstance().roomHostInfoBean.getMicrophone_id() + "")) {
                RoomManager.getInstance().roomHostInfoBean.setGift(jbGiftBean);
                mRoomManagerListener.refHost(roomHostInfoBean, ptMinfos, mRoomBean);
                return;
            }
        } else {

            for (RoomHostInfoBean ptMinfo : RoomManager.getInstance().ptMinfos) {
                if (ptMinfo != null && bean.getMid().equals(ptMinfo.getMicrophone_id() + "")) {
                    ptMinfo.setGift(jbGiftBean);
                    mRoomManagerListener.refPTUsers(RoomManager.getInstance().ptMinfos);
                    return;
                }

            }
        }

    }

    @Override
    public void setUserGiftPearlJB(RoomImMessageBean bean, boolean is, boolean is_zc) {

        RoomHostInfoBean.JBGiftBean jbGiftBean;
        if (is) {
            jbGiftBean = new RoomHostInfoBean.JBGiftBean();
            jbGiftBean.setId(bean.getGid());
            jbGiftBean.setNum(bean.getGc());
            jbGiftBean.setUser_id(bean.getUid());
            jbGiftBean.setUser_image(bean.getImg());
            jbGiftBean.setUser_nickname(bean.getNn());

        } else {
            jbGiftBean = null;
        }

        if (is_zc) {

            if (RoomManager.getInstance().roomHostInfoBean != null && bean.getMid().equals(RoomManager.getInstance().roomHostInfoBean.getMicrophone_id() + "")) {
                RoomManager.getInstance().roomHostInfoBean.setPeal_gift(jbGiftBean);
                mRoomManagerListener.refHost(roomHostInfoBean, ptMinfos, mRoomBean);
                return;
            }
        } else {

            for (RoomHostInfoBean ptMinfo : RoomManager.getInstance().ptMinfos) {
                if (ptMinfo != null && bean.getMid().equals(ptMinfo.getMicrophone_id() + "")) {
                    ptMinfo.setPeal_gift(jbGiftBean);
                    mRoomManagerListener.refPTUsers(RoomManager.getInstance().ptMinfos);
                    return;
                }

            }
        }
    }


    // TODO: 2019/12/2 普通抱麦上麦指令
    @Override
    public void baoShangMKF(RoomImMessageBean bean) {
        if (!bean.getUid().equals(KKUserManager.user_id)) {
            return;
        }

        // TODO: 2019/12/3  普通麦抱上麦指令
        
//        BaisTitleDialog titleDialog = new BaisTitleDialog();
//        titleDialog.setTvContent(ErBanApp.instance.getString(R.string.zhuchirenyaoqingnishangm));
//        titleDialog.setYesOnclickListener(ErBanApp.instance.getString(R.string.jj), true, new BaisTitleDialog.onYesOnclickListener() {
//            @Override
//            public void onYesClick() {
//
//                titleDialog.dismiss();
//
//
//            }
//        });
//        titleDialog.setNoOnclickListener(ErBanApp.instance.getString(R.string.jies), true, new BaisTitleDialog.onNoOnclickListener() {
//            @Override
//            public void onNoClick() {
//                PublicApi.getRoomMicroInto(mRoomBean.getRoom_id(), bean.getMid(), new ResponseListener() {
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
//                titleDialog.dismiss();
//            }
//        });
//        titleDialog.show(manager);
    }

    @Override
    public void setToushi(RoomImMessageBean bean, boolean is) {


        if (roomHostInfoBean.getUser() != null && bean.getUid().equals(roomHostInfoBean.getUser().getUser_id())) {
            UserBean user = roomHostInfoBean.getUser();

            List<DiyMallBean.DiyItemBean> attires = user.getAttires();

            boolean a = true;
            DiyMallBean.DiyItemBean att = null;
            for (DiyMallBean.DiyItemBean attire : attires) {
                if (attire.getGoods_type() == 1) {
                    if (is) {
                        attire.setGoods_id(new Integer(bean.getCid()));
                        attire.setAnimate_resource(bean.getCres());
                        a = false;
                    } else {
                        att = attire;
                    }
                }

            }

            if (is && a) {
                DiyMallBean.DiyItemBean itemBean = new DiyMallBean.DiyItemBean();
                itemBean.setGoods_id(new Integer(bean.getCid()));
                itemBean.setAnimate_resource(bean.getCres());
                itemBean.setGoods_type(1);
                attires.add(itemBean);
            }

            if (!is && att != null) {
                attires.remove(att);
            }
            mRoomManagerListener.refHost(roomHostInfoBean, ptMinfos, mRoomBean);
        }


        for (RoomHostInfoBean ptMinfo : ptMinfos) {

            if (ptMinfo.getUser() != null && bean.getUid().equals(ptMinfo.getUser().getUser_id())) {
                UserBean user = ptMinfo.getUser();

                List<DiyMallBean.DiyItemBean> attires = user.getAttires();

                boolean a = true;
                DiyMallBean.DiyItemBean att = null;
                for (DiyMallBean.DiyItemBean attire : attires) {
                    if (attire.getGoods_type() == 1) {
                        if (is) {
                            attire.setGoods_id(new Integer(bean.getCid()));
                            attire.setAnimate_resource(bean.getCres());
                            a = false;
                        } else {
                            att = attire;
                        }
                    }

                }

                if (is && a) {
                    DiyMallBean.DiyItemBean itemBean = new DiyMallBean.DiyItemBean();
                    itemBean.setGoods_id(new Integer(bean.getCid()));
                    itemBean.setAnimate_resource(bean.getCres());
                    itemBean.setGoods_type(1);
                    attires.add(itemBean);
                }

                if (!is && att != null) {
                    attires.remove(att);
                }

                mRoomManagerListener.refPTUsers(ptMinfos);
            }

        }

    }

    @Override
    public void RoomClose() {
        RoomManager.getInstance().leaveRoom(4);
    }

    @Override
    public void RoomVolumeClose(RoomImMessageBean bean) {
        mRoomManagerListener.refPTUsers(RoomManager.getInstance().ptMinfos);

    }

    @Override
    public void shouHuOpen(RoomImMessageBean bean) {
        mRoomManagerListener.shouHuOpen(bean);
    }

    @Override
    public void changeTopic(RoomImMessageBean bean) {
        mRoomBean.setTopic(bean.getTopic());
        mRoomManagerListener.changeTopic(bean);
    }

    @Override
    public void roomSetPassWord() {
        mRoomManagerListener.refRoom(mRoomBean, ptMinfos, is_Live, is_MKF);
    }

    @Override
    public void roomSetChatPingMu() {
        mRoomManagerListener.refRoom(mRoomBean, ptMinfos, is_Live, is_MKF);

    }

    @Override
    public void addXdSelect(RoomImMessageBean bean) {
        if (xdUserIds != null) {
            xdUserIds.add(bean.getUser_id());
            mRoomManagerListener.refRoomIsSeclect();
        }
    }

    @Override
    public void setDaoJiTime(long time) {

        if (time == -1) {
            mRoomManagerListener.refHost(RoomManager.getInstance().roomHostInfoBean, ptMinfos, mRoomBean);
        } else {
            if (RoomManager.getInstance().roomHostInfoBean.getUser() != null) {
                return;
            }
            mRoomManagerListener.setDaoJiTime(time);
        }
    }

    @Override
    public void hotChange(RoomImMessageBean bean) {
        mRoomManagerListener.hotChange(bean);
    }

    @Override
    public void refMeetOpen() {
        mRoomManagerListener.refPTUsers(ptMinfos);
    }

    @Override
    public void changgeRoomActionType() {
        mRoomManagerListener.changgeRoomActionType();
    }

    @Override
    public void zaDanMess(RoomImMessageBean bean) {
        mRoomManagerListener.zaDanMess(bean);

    }

    @Override
    public void changePKLeRiNum(RoomImMessageBean bean) {
        mRoomManagerListener.refPKLeReNum(bean.getRes().getLeft(), bean.getRes().getRight());
    }

    @Override
    public void setPKcfContent(RoomImMessageBean bean) {
        mRoomManagerListener.setPKcfContent(bean.getContent());
    }


    public boolean isPking = true;

    @Override
    public void setPKState(RoomImMessageBean bean) {
        mRoomManagerListener.setPKState(bean);
        isPking = true;
    }

    @Override
    public void setPKPublis(RoomImMessageBean bean) {
        mRoomManagerListener.setPKPublis(bean);
        isPking = false;
    }

    @Override
    public void showRedStartMess(RoomImMessageBean bean) {
        mRoomManagerListener.showRedStartMess(bean);
    }

    @Override
    public void dZPState(RoomImMessageBean bean) {
        mRoomManagerListener.dZPState(bean);
    }

    @Override
    public void dZPRefush(RoomImMessageBean bean) {
        mRoomManagerListener.dZPRefush(bean);

    }

    @Override
    public void dZPPublish(RoomImMessageBean bean) {
        mRoomManagerListener.dZPPublish(bean);

    }

    @Override
    public void showXingDongPublic(RoomImMessageBean bean) {
        mRoomManagerListener.showXingDongPublic(bean, ptMinfos);

    }
}
