package com.keke.roomlib.zego;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.hjq.toast.ToastUtils;
import com.keke.roomlib.api.KKRoom;
import com.keke.roomlib.api.PublicApi;
import com.keke.roomlib.api.ResponseListener;
import com.keke.roomlib.base.DoingThingsListener;
import com.keke.roomlib.base.OneToOneMessageBean;
import com.keke.roomlib.bean.MessageBean;
import com.keke.roomlib.bean.RoomMessageBean;
import com.keke.roomlib.room.OneToOneManger;
import com.keke.roomlib.room.RoomManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.agora.rtc.Constants;

import static com.keke.roomlib.base.OneToOneMessageBean.ONE_TO_ONE_JOIN_SUCCES;
import static com.keke.roomlib.base.OneToOneMessageBean.ONE_TO_ONE_LEAVE;
import static com.keke.roomlib.bean.MessageBean.SERVICE_JOIN_SUCCES;
import static com.keke.roomlib.bean.MessageBean.SERVICE_LEAVE;
import static com.keke.roomlib.bean.MessageBean.SERVICE_LEAVE_CONN;
import static com.keke.roomlib.bean.MessageBean.SERVICE_MUSIC_CHANGE;
import static com.keke.roomlib.bean.MessageBean.SERVICE_VOICE_EVENT_BGM_STOPPED;
import static com.keke.roomlib.bean.MessageBean.SERVICE_VOICE_EVENT_NET_WORK_QUALITY;
import static com.keke.roomlib.bean.MessageBean.SERVICE_VOICE_EVENT_OTHERS_VOICE;
import static com.keke.roomlib.bean.MessageBean.SERVICE_VOICE_EVENT_REMOTE_AUDIO_STATS;


public class AgoraManager {


    public static final int ROOM_CHAT_ROOM = 1;
    public static final int ROOM_ONE_TO_ONE = 2;


    public static final int ROOM_USER_TYPE_DEF = Constants.CLIENT_ROLE_AUDIENCE;
    public static final int ROOM_USER_TYPE_ZB = Constants.CLIENT_ROLE_BROADCASTER;
    public static final int ROOM_USER_TYPE_PTM = Constants.CLIENT_ROLE_BROADCASTER;


    private static AgoraManager mAgoraManager;
    private final EventBus eventBus;
    private int mRoomType;
    private String room = "";

    public static AgoraManager getInstance() {
        if (mAgoraManager == null) {
            synchronized (AgoraManager.class) {
                if (mAgoraManager == null) {
                    mAgoraManager = new AgoraManager();
                }
            }
        }
        return mAgoraManager;
    }


    private AgoraManager() {
        eventBus = EventBus.getDefault();
        eventBus.register(this);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRoomEven(RoomMessageBean roomMessageBean) {
        if (roomMessageBean.getChannelID().equals(room)) {
            switch (roomMessageBean.getEventType()) {
                case VOICE_EVENT_MESSAGE_NOTIFY:
                    switch (mRoomType) {
                        case ROOM_CHAT_ROOM:
                            if (RoomManager.getInstance().getmRoomBean() != null && RoomManager.getInstance().getmRoomBean().getRoomImManager() != null) {
                                try {
                                    RoomManager.getInstance().getmRoomBean().getRoomImManager().acceptIM(roomMessageBean);
                                } catch (NullPointerException e) {
                                    Log.e("@@", "onRoomEven: " + e.getMessage());
                                }
                            }
                            break;
                        case ROOM_ONE_TO_ONE:

                            // TODO: 2019/12/2  一对一一
                            try {
                                OneToOneManger.getInstance().acceptIM(roomMessageBean);
                            } catch (NullPointerException e) {
                                Log.e("@@", "onOneToOneEven: " + e.getMessage());
                            }
                            break;
                    }
                    break;
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void baEven(MessageBean messageBean) {
        switch (mRoomType) {
            case ROOM_CHAT_ROOM:
                switch (messageBean.getMes_code()) {
                    case SERVICE_JOIN_SUCCES:

                        PublicApi.getRoomjoinConfirm(room, new ResponseListener() {
                            @Override
                            public void success(Object o) {
                                RoomMessageBean mes = RoomMessageBean.getNew(room, VoicesStates.VOICE_EVENT_JOIN_OK);
                                eventBus.post(mes);
                            }

                            @Override
                            public void error(String s) {
                                ToastUtils.show(s);
                                RoomManager.getInstance().leaveRoom(5);
                            }

                            @Override
                            public void onCodeError(int errorCode) {
                                super.onCodeError(errorCode);
                                RoomManager.getInstance().leaveRoom(5);
                            }
                        });
                        break;
                    case SERVICE_LEAVE:

                        RoomMessageBean mes = RoomMessageBean.getNew(room == null ? "" : room, VoicesStates.VOICE_EVENT_LEAVED_ALL);
                        eventBus.post(mes);
                        if (room == null) {
                            return;
                        }
                        PublicApi.getRoomQuitConfirm(room, new ResponseListener() {
                            @Override
                            public void success(Object o) {

                            }

                            @Override
                            public void error(String s) {

                            }
                        });
                        break;
                    case SERVICE_LEAVE_CONN:
                        MessageBean message = new MessageBean();
                        message.setMes_code(MessageBean.ROOM_LEAVE_CONN);
                        eventBus.post(message);
                        break;
                    case SERVICE_MUSIC_CHANGE:
                        break;
                    case SERVICE_VOICE_EVENT_OTHERS_VOICE:
                        RoomMessageBean mess = RoomMessageBean.getNew(room, VoicesStates.VOICE_EVENT_OTHERS_VOICE);
                        mess.setO(messageBean.getBean());
                        eventBus.post(mess);
                        break;

                    case SERVICE_VOICE_EVENT_NET_WORK_QUALITY:
                        RoomMessageBean mes1 = RoomMessageBean.getNew(room, VoicesStates.VOICE_EVENT_NET_WORK_QUALITY);
                        mes1.setO(messageBean.getBean());
                        eventBus.post(mes1);
                        break;
                    case SERVICE_VOICE_EVENT_REMOTE_AUDIO_STATS:

                        RoomMessageBean smes = RoomMessageBean.getNew(room, VoicesStates.VOICE_EVENT_REMOTE_AUDIO_STATS);
                        smes.setO(messageBean.getBean());
                        eventBus.post(smes);
                        break;
                    case SERVICE_VOICE_EVENT_BGM_STOPPED:
                        RoomMessageBean messs = RoomMessageBean.getNew(room, VoicesStates.VOICE_EVENT_BGM_STOPPED);
                        eventBus.post(messs);

                        break;
                }
                break;
            case ROOM_ONE_TO_ONE:
                OneToOneMessageBean bean = new OneToOneMessageBean();

                switch (messageBean.getMes_code()) {
                    case SERVICE_JOIN_SUCCES:
                        bean.setMes_code(ONE_TO_ONE_JOIN_SUCCES);
                        eventBus.post(bean);
                        break;
                    case SERVICE_LEAVE:
                        bean.setMes_code(ONE_TO_ONE_LEAVE);
                        eventBus.post(bean);
                        break;
                    case SERVICE_LEAVE_CONN:
                        break;
                    case SERVICE_MUSIC_CHANGE:
                        break;
                    case SERVICE_VOICE_EVENT_OTHERS_VOICE:
                        break;

                    case SERVICE_VOICE_EVENT_NET_WORK_QUALITY:
                        break;
                    case SERVICE_VOICE_EVENT_REMOTE_AUDIO_STATS:
                        break;
                    case SERVICE_VOICE_EVENT_BGM_STOPPED:
                        break;
                }
                break;
        }


    }


    private VoicesBinderForegroundService binderService;
    private ServiceConnection conn;


    public void initRoom(final ResponseListener listener) {
        boolean is = false;
        final boolean s;
        if (binderService != null) {
            is = true;
            listener.success(null);
        }
        s = is;
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                VoicesBinderForegroundService.VoicesBinderForegroundBinder binderForegroundBinder = (VoicesBinderForegroundService.VoicesBinderForegroundBinder) service;
                binderService = binderForegroundBinder.getService();
                binderService.initYM(new ResponseListener() {
                    @Override
                    public void success(Object o) {
                        if (!s) {
                            listener.success(null);
                        }

                    }

                    @Override
                    public void error(String s) {
                        listener.error(s);
                    }
                });

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        // TODO: 2019/12/2  开启服务
        KKRoom.getInstance().mContext.bindService(new Intent(KKRoom.getInstance().mContext, VoicesBinderForegroundService.class), conn, Context.BIND_AUTO_CREATE);
    }


    // TODO: 2019/12/3  退出声网
    public void unInitRoom() {
        if (binderService == null) {
            return;
        }
        binderService.unInitYM();
        try {

            KKRoom.getInstance().mContext.unbindService(conn);
        } catch (IllegalArgumentException e) {

        }

        binderService = null;
    }


    public VoicesBinderForegroundService getBinderService() {
        return binderService;
    }

    public void stopSe() {
        try {

            KKRoom.getInstance().mContext.unbindService(conn);
        } catch (IllegalArgumentException e) {
            System.exit(0);
        }

        binderService = null;
        RoomMessageBean event = new RoomMessageBean();
        event.setEventType(VoicesStates.VOICE_EVENT_LEAVED_ALL);
        event.setmRoomType(mRoomType);
        if (room != null) {
            event.setChannelID(room);
        }
        eventBus.post(event);
    }


    public void JoinRoom(String room, int mRoomType, String token, DoingThingsListener is_loginListener) {
        this.mRoomType = mRoomType;
        this.room = room;
        binderService.JoinRoom(room, token, is_loginListener);
    }


}
