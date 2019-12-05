package com.keke.roomlib.zego;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.keke.roomlib.BuildConfig;
import com.keke.roomlib.KKUserManager;
import com.keke.roomlib.R;
import com.keke.roomlib.api.KKRoom;
import com.keke.roomlib.api.PublicApi;
import com.keke.roomlib.api.ResponseListener;
import com.keke.roomlib.base.DoingThingsListener;
import com.keke.roomlib.bean.CarDownBean;
import com.keke.roomlib.bean.GiftDownBean;
import com.keke.roomlib.bean.MessageBean;
import com.keke.roomlib.im.RoogYunIMManager;
import com.keke.roomlib.room.RoomManager;
import com.keke.roomlib.utils.SharedPreferenceUtil;
import com.keke.roomlib.utils.Utlis;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import io.agora.rtc.Constants;
import io.agora.rtc.IAudioEffectManager;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.rong.imlib.RongIMClient;

import static com.keke.roomlib.bean.MessageBean.SERVICE_JOIN_SUCCES;
import static com.keke.roomlib.bean.MessageBean.SERVICE_VOICE_EVENT_BGM_STOPPED;
import static com.keke.roomlib.bean.MessageBean.SERVICE_VOICE_EVENT_NET_WORK_QUALITY;
import static com.keke.roomlib.bean.MessageBean.SERVICE_VOICE_EVENT_REMOTE_AUDIO_STATS;
import static io.agora.rtc.Constants.AUDIO_ROUTE_DEFAULT;
import static io.agora.rtc.Constants.AUDIO_ROUTE_EARPIECE;
import static io.agora.rtc.Constants.AUDIO_ROUTE_HEADSET;
import static io.agora.rtc.Constants.AUDIO_ROUTE_HEADSETBLUETOOTH;
import static io.agora.rtc.Constants.AUDIO_ROUTE_HEADSETNOMIC;
import static io.agora.rtc.Constants.AUDIO_ROUTE_LOUDSPEAKER;
import static io.agora.rtc.Constants.AUDIO_ROUTE_SPEAKERPHONE;
import static io.agora.rtc.Constants.CONNECTION_STATE_CONNECTED;
import static io.agora.rtc.Constants.CONNECTION_STATE_CONNECTING;
import static io.agora.rtc.Constants.CONNECTION_STATE_RECONNECTING;
import static io.agora.rtc.Constants.ERR_NOT_READY;
import static io.agora.rtc.Constants.MEDIA_ENGINE_AUDIO_EVENT_MIXING_ERROR;
import static io.agora.rtc.Constants.MEDIA_ENGINE_AUDIO_EVENT_MIXING_PAUSED;
import static io.agora.rtc.Constants.MEDIA_ENGINE_AUDIO_EVENT_MIXING_STOPPED;

public class VoicesBinderForegroundService extends Service {


    public static final String IS_FORCEDISABLE_ACE = "setForceDisableAEC_s";
    private static final String TAG = "VoicesBinderForegroundS";
    public static final int NOTIFICATION_FOREGROUND_ID = 1;// 是用来标记Notifaction，可用于更新，删除Notifition
    private NotificationManager notificationManager;

    // 定义Binder类-当然也可以写成外部类
    private VoicesBinderForegroundBinder voicesBinderForegroundBinder = new VoicesBinderForegroundBinder();

    private ResponseListener listener;
    private EventBus mEventBus;

    public static boolean isDestory = true;
    private List<GiftDownBean> gifts;
    public static List<GiftDownBean> gifts_s;
    private List<GiftDownBean> gifts_pearl;
    public static List<GiftDownBean> gifts_pearl_s;
    private List<CarDownBean> cars;
    public static List<CarDownBean> cars_s;
    private static RtcEngine mRtcEngine;
    private IAudioEffectManager manager;
    private int vol;
    private int voBackMusic;

    private String LOG_TAG = "VoicesBinderForegroundService";


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, TAG + "onCreate");
        isDestory = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "voices";
            String channelName = "直播信息";
            int importance = NotificationManager.IMPORTANCE_LOW;
            createNotificationChannel(channelId, channelName, importance);
        }
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);
        initNOtification("");


    }


    // TODO: 2019/12/3 礼物下载
//    @Subscribe(threadMode = ThreadMode.BACKGROUND)
//    public void baEven(MessageBean messageBean) {
//        switch (messageBean.getMes_code()) {
//            case LOAD_APP_RESOURCE:
//
//
//                // 加载座驾，礼物等
//                // loadGiftlist();
//                // loadCarList();
//                //loadGiftPearllist();
//                break;
//        }
//    }

    public void initYM(ResponseListener listener) {
        this.listener = listener;
        initializeAgoraEngine();
    }

    public void unInitYM() {
    }

    public void JoinRoom(final String room, final String token, final DoingThingsListener is_loginListener) {

        // TODO: 2019/12/2  声网加入频道
        RoogYunIMManager.getInstance().joinChatRoom(room, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                mRtcEngine.joinChannel(token, room, KKUserManager.user_id, new Integer(KKUserManager.user_id));

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                is_loginListener.fail();
            }
        });
    }


    public void leaveRoom() {
//        api.leaveChannelAll();

        mRtcEngine.leaveChannel();
    }

    public void setOutputToSpeaker(boolean is) {
//        api.setOutputToSpeaker(is); //：true——输出到扬声器，false——输出到听筒
        mRtcEngine.setEnableSpeakerphone(is); //：true——输出到扬声器，false——输出到听筒

    }

    public boolean isSpeakerphoneEnabled() {
        return mRtcEngine.isSpeakerphoneEnabled();
    }


    public void changeUserRole(int role, boolean is) {

        final int rol = role == AgoraManager.ROOM_USER_TYPE_DEF ? AgoraManager.ROOM_USER_TYPE_ZB : AgoraManager.ROOM_USER_TYPE_DEF;


        int clientRole = mRtcEngine.setClientRole(role);

        mRtcEngine.adjustRecordingSignalVolume(100);
        setVolume(120);
        KKRoom.getInstance().isPlaingSong = false;
        mRtcEngine.enableAudioVolumeIndication(500, 3, true);
        stopBackgroundMusic();

        if (is) {
            return;
        }

        if (clientRole < 0) {
            RoomManager.getInstance().changeUserRole(rol, true);
        }
        if (AgoraManager.ROOM_USER_TYPE_DEF == role) {

            // TODO: 2019/12/2  用户下麦 
            PublicApi.getRoomOutmicroConfirm(roomid, new ResponseListener() {
                @Override
                public void success(Object o) {

                }

                @Override
                public void error(String s) {
                    RoomManager.getInstance().changeUserRole(rol, true);
                }
            });
        }


        if (AgoraManager.ROOM_USER_TYPE_PTM == role) {


            // TODO: 2019/12/2  用户上麦
            PublicApi.getRoomIntomicroConfirm(roomid, new ResponseListener() {
                @Override
                public void success(Object o) {
                }

                @Override
                public void error(String s) {
                    RoomManager.getInstance().changeUserRole(rol, true);

                }
            });
        }


    }


    public void setAudioProfile(int profile, int scenario) {
        mRtcEngine.setAudioProfile(profile, scenario);
        setForceDisableAEC(false);
    }

    public void setForceDisableAEC(boolean disableAEC) {

        String disab = (String) SharedPreferenceUtil.get(this, VoicesBinderForegroundService.IS_FORCEDISABLE_ACE, "3");


//        声卡模式设置                    disab
//        1、PC模拟器加外置声卡带耳机模式    0
//        2、PC模拟器加外置声卡无耳机模式    1
//        3、手机加外置声卡模式。           2
//        4、无声卡模式                   3

        mRtcEngine.setParameters("{\"che.audio.opensl\":false}");

        switch (disab) {
            case "0":
                mRtcEngine.setParameters("{\"che.audio.enable.aec\":false}");
                mRtcEngine.setParameters("{\"che.audio.enable.ns\":false}");
                mRtcEngine.setParameters("{\"che.audio.enable.agc\":false}");
                break;
            case "1":
            case "2":
                mRtcEngine.setParameters("{\"che.audio.enable.aec\":false}");
                mRtcEngine.setParameters("{\"che.audio.enable.ns\":false}");
                break;
            case "3":
                break;
        }


    }


    public void changeMKF(boolean i) {
//        api.setMicrophoneMute(i);//true——关闭麦克风，false——开启麦克风。


        if (RoomManager.getInstance().getRoomHostInfoBean().getUser() != null && KKUserManager.user_id.equals(RoomManager.getInstance().getRoomHostInfoBean().getUser().getUser_id())) {
            mRtcEngine.adjustRecordingSignalVolume(i ? 0 : 100);//true——关闭麦克风，false——开启麦克风。
            int code = 0;
            if (i) {
                if (!KKRoom.getInstance().isPlaingSong)
                    code = mRtcEngine.setClientRole(AgoraManager.ROOM_USER_TYPE_DEF);
            } else {
                code = mRtcEngine.setClientRole(AgoraManager.ROOM_USER_TYPE_ZB);
            }

            if ((code < 0)) {
                ToastUtils.show(R.string.shibai);
            }
        } else {


            switch (RoomManager.getInstance().getmRoomBean().getRoom_type().getId() + "") {
                case "7":
                    return;
            }
            int clientRole = mRtcEngine.setClientRole(i ? AgoraManager.ROOM_USER_TYPE_DEF : AgoraManager.ROOM_USER_TYPE_ZB);
            if ((clientRole < 0)) {
                ToastUtils.show(getString(R.string.bimaishibai));
            }

        }
    }


    public void sendMessage(String roomID, HashMap<String, Object> map) {
        RoogYunIMManager.getInstance().sendChatRoomMess(roomID, mapToJson(map));
    }

    public static String mapToJson(HashMap<String, Object> map) {
        return new Gson().toJson(map);
    }


//    初始化 通知栏

    PendingIntent activity;

    public void initNOtification(String roomid) {


        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        RemoteViews[] remoteViews = new RemoteViews[2];
        //普通notification用到的视图
        remoteViews[0] = new RemoteViews(getPackageName(), R.layout.normal_notification);
//        显示bigView的notification用到的视图
//        remoteViews[1] = new RemoteViews(getPackageName(), R.layout.big_notification);
        Intent intent = new Intent(this, NotificationBroadcast.class);
        intent.setAction("notification_cancelled");
        intent.putExtra(NotificationBroadcast.CANCEL_NOTICE_TYPE, 0);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent service = PendingIntent.getBroadcast(this, 3, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews[0].setOnClickPendingIntent(R.id.iv, service);
        if (RoomManager.getInstance().getmRoomBean() == null || Utlis.isEmp(roomid)) {
            remoteViews[0].setTextViewText(R.id.tv_name, getString(R.string.app_name));
            remoteViews[0].setTextViewText(R.id.tv_desc, "");
            if (activity != null) {
                activity.cancel();
            }
        } else {
            remoteViews[0].setTextViewText(R.id.tv_name, RoomManager.getInstance().getmRoomBean().getName());
            remoteViews[0].setTextViewText(R.id.tv_desc, RoomManager.getInstance().getmRoomBean().getDesc());

            if (roomid != null) {

                // TODO: 2019/12/2 跳转到房间
//                Intent intents = new Intent(this, VoiceChatActivity.class);
//                intents.putExtra(VoiceChatActivity.ROOM_ID, roomid);
//                intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                activity = PendingIntent.getActivity(this, 4, intents, PendingIntent.FLAG_CANCEL_CURRENT);
//                remoteViews[0].setOnClickPendingIntent(R.id.rll, activity);


            }
        }
        sendCustomViewNotification(remoteViews);
    }


    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }


    public void sendCustomViewNotification(RemoteViews[] remoteViews) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationManager.getNotificationChannel("voices");
            String is_open_notifica = (String) SharedPreferenceUtil.get(this, "is_open_notifica", "0");
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE && is_open_notifica.equals("0")) {
                SharedPreferenceUtil.put(this, "is_open_notifica", "1");
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
                Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();
            }
        }


        Notification notification = new NotificationCompat.Builder(this, "voices")
                .setSmallIcon(KKRoom.getInstance().getIc_launcher())
                .setTicker(getString(R.string.app_name))
                .setOngoing(false)
                .setContent(remoteViews[0])//设置普通notification视图
//                .setCustomBigContentView(remoteViews[1])//设置显示bigView的notification视图
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .build();

        startForeground(NOTIFICATION_FOREGROUND_ID, notification);
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, TAG + "  onBind: ");
        return voicesBinderForegroundBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, TAG + "  onUnbind: ");
        return super.onUnbind(intent);
    }


    @Override
    public void onDestroy() {
        isDestory = true;
        super.onDestroy();
        Log.d(TAG, TAG + "  onDestroy: ");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            // 取消Notification
            if (notificationManager != null)
                notificationManager.cancel(NOTIFICATION_FOREGROUND_ID);
        }

        Log.d(TAG, "DaemonService---->onDestroy，前台service被杀死");
        mRtcEngine.leaveChannel();
        stopForeground(true);
        // 任务完成，终止自己


        stopSelf();

    }


    private static String roomid = "";

    private void initializeAgoraEngine() {
        try {

            if (mRtcEngine == null) {
                mRtcEngine = RtcEngine.create(getBaseContext(), KKRoom.getInstance().AGORA_APPKEY, new IRtcEngineEventHandler() {

                    //                发生警告回调
                    @Override
                    public void onWarning(int warn) {
                        super.onWarning(warn);
                        Log.d(TAG, "onWarning: " + warn);
                    }

                    //                发生错误回调
                    @Override
                    public void onError(int err) {
                        super.onError(err);
                        Log.d(TAG, "onError: " + err);
                        switch (err) {
                            case ERR_NOT_READY:
                                if (listener != null) {
                                    listener.error("声网初始化失败");
                                }
                                listener = null;
                                break;
                        }
                    }

                    //                加入频道回调。
                    @Override
                    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
                        super.onJoinChannelSuccess(channel, uid, elapsed);
                        Log.d(TAG, "onJoinChannelSuccess: " + channel + "  " + elapsed);
                        roomid = channel;
                        MessageBean messageBean = new MessageBean();
                        messageBean.setMes_code(SERVICE_JOIN_SUCCES);
                        mEventBus.post(messageBean);

                    }

                    //                重新加入频道回调
                    @Override
                    public void onRejoinChannelSuccess(String channel, int uid, int elapsed) {
                        super.onRejoinChannelSuccess(channel, uid, elapsed);
                        Log.d(TAG, "onRejoinChannelSuccess: " + channel + "  " + elapsed);
                        MessageBean messageBean = new MessageBean();
                        messageBean.setMes_code(MessageBean.SERVICE_LEAVE_CONN);
                        mEventBus.post(messageBean);
                    }

                    //                离开频道回调
                    @Override
                    public void onLeaveChannel(RtcStats stats) {
                        Log.d(TAG, "onLeaveChannel: " + stats);
                        super.onLeaveChannel(stats);
                        MessageBean messageBean = new MessageBean();
                        messageBean.setMes_code(MessageBean.SERVICE_LEAVE);
                        mEventBus.post(messageBean);

                    }


                    //                用户角色已切换回调。
                    @Override
                    public void onClientRoleChanged(int oldRole, int newRole) {
                        super.onClientRoleChanged(oldRole, newRole);
                        Log.d(TAG, "onClientRoleChanged: " + oldRole + "   " + newRole);


                    }

                    //                远端用户/主播加入当前频道回调。
//                通信模式下，该回调提示有远端用户加入了频道，并返回新加入用户的 ID；如果加入之前，已经有其他用户在频道中了，新加入的用户也会收到这些已有用户加入频道的回调。
//                直播模式下，该回调提示有主播加入了频道，并返回该主播的用户 ID。如果在加入之前，已经有主播在频道中了，新加入的用户也会收到已有主播加入频道的回调。Agora 建议连麦主播不超过 17 人。
                    @Override
                    public void onUserJoined(int uid, int elapsed) {
                        super.onUserJoined(uid, elapsed);
                        Log.d(TAG, "onUserJoined: " + uid);
                    }

                    //                远端用户（通信模式）/主播（直播模式）离开当前频道回调。
                    @Override
                    public void onUserOffline(int uid, int reason) {
                        super.onUserOffline(uid, reason);
                        Log.d(TAG, "onUserOffline: " + uid);

                    }


                    //                网络连接状态已改变回调
                    @Override
                    public void onConnectionStateChanged(int state, int reason) {
                        super.onConnectionStateChanged(state, reason);
                        Log.d(TAG, "onConnectionStateChanged: " + state + "   " + reason);

                        switch (state) {
                            case CONNECTION_STATE_CONNECTING://建立网络连接中
                                break;
                            case CONNECTION_STATE_CONNECTED: //网络已连接
                                break;
                            case CONNECTION_STATE_RECONNECTING: //重新建立网络连接中
                                ToastUtils.show("重新建立网络连接中");
                                break;
                        }
                    }

                    //                网络连接中断，且 SDK 无法在 10 秒内连接服务器回调。
                    @Override
                    public void onConnectionLost() {
                        super.onConnectionLost();
                        Log.d(TAG, "onConnectionLost: ");

                    }

                    @Override
                    public void onApiCallExecuted(int error, String api, String result) {
                        super.onApiCallExecuted(error, api, result);
                        Log.d(TAG, "onApiCallExecuted: " + "    " + api + "    " + result);
                    }

                    //                Token 服务即将过期回调。
                    @Override
                    public void onTokenPrivilegeWillExpire(String token) {
                        super.onTokenPrivilegeWillExpire(token);
                        Log.d(TAG, "onTokenPrivilegeWillExpire: ");
                        // TODO: 2019/12/2  token过期
                        PublicApi.getRoomToken(roomid, new ResponseListener() {
                            @Override
                            public void success(Object o) {
                                mRtcEngine.renewToken(((String) o));

                            }

                            @Override
                            public void error(String s) {

                            }
                        });
                    }

                    //                Token 服务过期回调。
                    @Override
                    public void onRequestToken() {
                        super.onRequestToken();
                        Log.d(TAG, "onRequestToken: ");


                        // TODO: 2019/12/2  房间token过期
                        PublicApi.getRoomToken(roomid, new ResponseListener() {
                            @Override
                            public void success(Object o) {
                                mRtcEngine.renewToken(((String) o));

                            }

                            @Override
                            public void error(String s) {

                            }
                        });
                    }


//                麦克风状态已改变回调。
//                true：麦克风已启用
//                false：麦克风已禁用


//                    @Override
//                    public void onLocalAudioStateChanged(int state, int error) {
//                        super.onLocalAudioStateChanged(state, error);
//                    }

                    @Override
                    public void onMicrophoneEnabled(boolean enabled) {
                        super.onMicrophoneEnabled(enabled);
                        Log.d(TAG, "onMicrophoneEnabled: " + enabled);
                    }


                    //                提示频道内谁正在说话以及说话者音量的回调。
                    @Override
                    public void onAudioVolumeIndication(AudioVolumeInfo[] speakers, int totalVolume) {
                        super.onAudioVolumeIndication(speakers, totalVolume);
                        if (Utlis.isEmp(roomid)) {
                            return;
                        }
                        for (AudioVolumeInfo speaker : speakers) {
                            String uid = speaker.uid + "";
                            if (speaker.uid == 0) {
                                uid = KKUserManager.user_id;
                            }
                            Log.d(TAG, "onAudioVolumeIndication: " + uid + "   " + totalVolume);
                            if (speaker.volume <= 40) {
                                return;
                            }
                            MessageBean messageBean = new MessageBean();
                            messageBean.setMes_code(MessageBean.SERVICE_VOICE_EVENT_OTHERS_VOICE);
                            messageBean.setBean(uid);
                            mEventBus.post(messageBean);

                        }

                    }


                    @Override
                    public void onActiveSpeaker(int uid) {
                        super.onActiveSpeaker(uid);
                        Log.d(TAG, "onActiveSpeaker: " + uid);
                    }


                    //                语音路由已变更回调。
                    @Override
                    public void onAudioRouteChanged(int routing) {
                        super.onAudioRouteChanged(routing);

                        Log.d(TAG, "onAudioRouteChanged: " + routing);


                        switch (routing) {
                            case AUDIO_ROUTE_DEFAULT://使用默认的音频路由。
                                break;
                            case AUDIO_ROUTE_HEADSET://使用耳机为语音路由。
                                break;
                            case AUDIO_ROUTE_EARPIECE://使用听筒为语音路由。
                                break;
                            case AUDIO_ROUTE_HEADSETNOMIC://使用不带麦的耳机为语音路由。
                                break;
                            case AUDIO_ROUTE_SPEAKERPHONE://使用手机的扬声器为语音路由。
                                break;
                            case AUDIO_ROUTE_LOUDSPEAKER://使用外接的扬声器为语音路由。
                                break;
                            case AUDIO_ROUTE_HEADSETBLUETOOTH://使用蓝牙耳机为语音路由。
                                break;
                        }


                    }

                    //                通话前网络上下行 last mile 质量报告回调。
                    @Override
                    public void onNetworkQuality(int uid, int txQuality, int rxQuality) {
                        super.onNetworkQuality(uid, txQuality, rxQuality);
                        Log.d(TAG, "onNetworkQuality: " + uid + "   " + txQuality);

                        if ((uid + "").equals("0")) {

                            MessageBean messageBean = new MessageBean();
                            messageBean.setMes_code(SERVICE_VOICE_EVENT_NET_WORK_QUALITY);
                            messageBean.setBean(rxQuality + "");
                            mEventBus.post(messageBean);

                        }
                    }

                    @Override
                    public void onRemoteAudioStats(RemoteAudioStats stats) {
                        super.onRemoteAudioStats(stats);


                        MessageBean messageBean = new MessageBean();
                        messageBean.setMes_code(SERVICE_VOICE_EVENT_REMOTE_AUDIO_STATS);
                        messageBean.setBean(stats.networkTransportDelay + "");
                        mEventBus.post(messageBean);


                    }

                    //                本地音乐文件播放已结束回调。


                    @Override
                    public void onAudioMixingStateChanged(int state, int errorCode) {
                        super.onAudioMixingStateChanged(state, errorCode);

                        switch (state) {
                            case MEDIA_ENGINE_AUDIO_EVENT_MIXING_PAUSED:
                            case MEDIA_ENGINE_AUDIO_EVENT_MIXING_STOPPED:
                            case MEDIA_ENGINE_AUDIO_EVENT_MIXING_ERROR:

                                MessageBean messageBean = new MessageBean();
                                messageBean.setMes_code(SERVICE_VOICE_EVENT_BGM_STOPPED);
                                mEventBus.post(messageBean);
                                break;
                        }


                    }
                });
            }


//            if (!Utlis.isEmp(ErBanApp.SD_BASE_OUT_PATH)) {
//
//                mRtcEngine.setLogFilter(LOG_FILTER_INFO);
//                String ts = new SimpleDateFormat("yyyyMMdd").format(new Date());
//
//                File file = new File(ERBanConstant.OUTSD_PATH);
//                if (file.exists()) {
//                    File[] files = file.listFiles();
//                    for (File file1 : files) {
//                        if (!file1.getName().equals(ts)) {
//                            file1.delete();
//                        }
//                    }
//                }
//
//                file = new File(ERBanConstant.OUTSD_PATH + "/");
//                if (!file.exists()) {
//                    file.mkdir();
//                }
//
//                String filepath = ERBanConstant.OUTSD_PATH + "/" + ts + ".log";
//                mRtcEngine.setLogFile(filepath);
//
//
//            }
//            mRtcEngine.setLogFile(Environment.getExternalStorageState()+File.separator+"errb")

            mRtcEngine.setAudioProfile(Constants.AUDIO_PROFILE_MUSIC_HIGH_QUALITY, Constants.AUDIO_SCENARIO_CHATROOM_ENTERTAINMENT);
            mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);
            manager = mRtcEngine.getAudioEffectManager();


            if (listener != null) {
                listener.success(null);
            }
            listener = null;


        } catch (Exception e) {
            Log.e(LOG_TAG, Log.getStackTraceString(e));

        }
    }


    private void log(String s) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, s);
        }
    }

    public class VoicesBinderForegroundBinder extends Binder {
        public VoicesBinderForegroundService getService() {
            return VoicesBinderForegroundService.this;
        }
    }


//    private void loadCarList() {
//
//        // TODO: 2019/12/2  商城列表会出问题
//        PublicApi.getDiyGoodList(DIYMallActivity.DIY_MAll, "0", "2", "1000", new ResponseListener() {
//            @Override
//            public void success(Object o) {
//                cars_s = new ArrayList<>();
//                List<DiyMallBean.DiyItemBean> list = ((DiyMallBean) o).getList();
//
//                for (DiyMallBean.DiyItemBean diyItemBean : list) {
//                    CarDownBean carDownBean = new CarDownBean();
//                    carDownBean.setGoods_id(diyItemBean.getGoods_id());
//                    carDownBean.setAnimate_resource(diyItemBean.getAnimate_resource());
//                    carDownBean.setAnimate_resource_md5(diyItemBean.getAnimate_resource_md5());
//                    cars_s.add(carDownBean);
//
//                }
//            }
//
//            @Override
//            public void error(String s) {
//
//            }
//        });
//
//        // TODO: 2019/12/2  加载座驾
//        PublicApi.getCarDown(new ResponseListener() {
//            @Override
//            public void success(Object o) {
//                List<CarDownBean> carDownBeans = (List<CarDownBean>) o;
//                cars = new ArrayList<>();
//                for (CarDownBean carDownBean : carDownBeans) {
//                    if (!FileManager.fileIsExists(ERBanConstant.IMAGER_PATH_ZUOJIA + "/" + MD5Util.md5(carDownBean.getAnimate_resource()) + ".bin") && !carDownBean.getAnimate_resource().endsWith(".svga")) {
//                        cars.add(carDownBean);
//                    }
//                    BaisAniUtil.getInstance().addCar(carDownBean);
//
//                }
//
//
//                if (cars.size() > 0) {
//                    startLoadCar(cars.get(0));
//                }
//
//            }
//
//            @Override
//            public void error(String s) {
//
//            }
//        });
//
//    }


    private int error_car = 0;

//    public void startLoadCar(CarDownBean carDownBean) {
//
//        if (UpdataActivity.is_up) {
//            return;
//        }
//        PublicApi.downloadFile(carDownBean.getAnimate_resource(), ERBanConstant.IMAGER_PATH_ZUOJIA, MD5Util.md5(carDownBean.getAnimate_resource()) + ".bin", carDownBean.getAnimate_resource_md5(), new FileDownLoadObserver<File>() {
//            @Override
//            public void onDownLoadSuccess(File file) {
//                error_car = 0;
//                Log.e(TAG, "onDownLoadSuccess: zj:" + carDownBean.getGoods_id());
//                cars.remove(carDownBean);
//                BaisAniUtil.getInstance().addCar(carDownBean);
//                if (cars.size() > 0) {
//                    startLoadCar(cars.get(0));
//                }
//            }
//
//            @Override
//            public void onDownLoadFail(Throwable throwable) {
//                error_car++;
//                Log.e(TAG, "onDownLoadFail: zj:" + carDownBean.getGoods_id());
//
//                if (error_car == 5) {
//                    return;
//                }
//                if (cars.size() != 0) {
//                    cars.remove(0);
//                }
//                cars.add(carDownBean);
//                if (cars.size() > 0) {
//                    startLoadCar(cars.get(0));
//                }
//            }
//
//            @Override
//            public void onProgress(int progress, long total) {
//
//            }
//        });
//    }


//    public void loadGiftlist() {
//
//
//        PublicApi.getGiftList("1", null, new ResponseListener() {
//            @Override
//            public void success(Object o) {
//
//
//                PublicApi.getGiftDown(new ResponseListener() {
//                    @Override
//                    public void success(Object o) {
//                        List<GiftDownBean> giftDownBeans = ((List<GiftDownBean>) o);
//                        gifts = new ArrayList<>();
//                        for (GiftDownBean giftDownBean : giftDownBeans) {
//                            if (!FileManager.fileIsExists(ERBanConstant.IMAGER_PATH_HOT + "/" + MD5Util.md5(giftDownBean.getAnimate_resource()) + ".bin") && !giftDownBean.getAnimate_resource().endsWith(".svga")) {
//                                gifts.add(giftDownBean);
//                            }
//                        }
//
//                        if (gifts.size() > 0) {
//                            startLoadGift(gifts.get(0));
//                        }
//
//
//                    }
//
//                    @Override
//                    public void error(String s) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void error(String s) {
//
//            }
//        });
//
//
//    }

//    public void loadGiftPearllist() {
//
//
//        PublicApi.getGiftPearlList(new ResponseListener() {
//            @Override
//            public void success(Object o) {
//
//
//                PublicApi.getGiftPearlDown(new ResponseListener() {
//                    @Override
//                    public void success(Object o) {
//                        List<GiftDownBean> giftDownBeans = ((List<GiftDownBean>) o);
//                        gifts_pearl = new ArrayList<>();
//                        for (GiftDownBean giftDownBean : giftDownBeans) {
//                            if (!FileManager.fileIsExists(ERBanConstant.IMAGER_PATH_HOT_PEARL + "/" + MD5Util.md5(giftDownBean.getAnimate_resource()) + ".bin") && !giftDownBean.getAnimate_resource().endsWith(".svga")) {
//                                gifts_pearl.add(giftDownBean);
//                            }
//                        }
//
//                        if (gifts_pearl.size() > 0) {
//                            startLoadGiftPearl(gifts_pearl.get(0));
//                        }
//
//
//                    }
//
//                    @Override
//                    public void error(String s) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void error(String s) {
//
//            }
//        });
//
//
//    }


    public void playBackgroundMusic(String path) {


        int code = mRtcEngine.setClientRole(AgoraManager.ROOM_USER_TYPE_ZB);

        if (code >= 0) {
            mRtcEngine.startAudioMixing(path, false, false, 1);
//        api.setHeadsetMonitorOn(false, true);
//            setBackgroundMusicVolume(voBackMusic);
            MessageBean messageBean = new MessageBean();
            messageBean.setMes_code(MessageBean.ROOM_MUSIC_CHANGE);
            mEventBus.post(messageBean);
        }

    }


    public void stopBackgroundMusic() {
        mRtcEngine.stopAudioMixing();
        MessageBean messageBean = new MessageBean();
        messageBean.setMes_code(MessageBean.ROOM_MUSIC_CHANGE);
        mEventBus.post(messageBean);
    }


    public void setBackgroundMusicVolume(int vo) {
        voBackMusic = vo;
        mRtcEngine.adjustAudioMixingVolume(vo);
    }


    public void setVolume(int vol) {
        this.vol = vol;

        //调节音乐文件的本地播放音量。
        mRtcEngine.adjustAudioMixingPlayoutVolume(vol == 0 ? vol : voBackMusic);
//        调节播放音量。
        mRtcEngine.adjustPlaybackSignalVolume(vol);
    }


    public int getVolume() {
        return vol;
    }


    private int error = 0;


    // TODO: 2019/12/2  礼物的下载
//    public void startLoadGift(GiftDownBean giftDownBean) {
//        if (UpdataActivity.is_up) {
//            return;
//        }
//        PublicApi.downloadFile(giftDownBean.getAnimate_resource(), ERBanConstant.IMAGER_PATH_HOT, MD5Util.md5(giftDownBean.getAnimate_resource()) + ".bin", giftDownBean.getAnimate_resource_md5(), new FileDownLoadObserver<File>() {
//            @Override
//            public void onDownLoadSuccess(File file) {
//                error = 0;
//                Log.e(TAG, "onDownLoadSuccess: " + giftDownBean.getGift_id());
//                gifts.remove(giftDownBean);
//                BaisAniUtil.getInstance().addGift(giftDownBean);
//                if (gifts.size() > 0) {
//                    startLoadGift(gifts.get(0));
//                }
//            }
//
//            @Override
//            public void onDownLoadFail(Throwable throwable) {
//                error++;
//                Log.e(TAG, "onDownLoadFail: lw   " + giftDownBean.getGift_id());
//
//                if (error == 5) {
//                    return;
//                }
//
//                if (gifts.size() <= 0) {
//                    return;
//                }
//                gifts.remove(0);
//                gifts.add(giftDownBean);
//                if (gifts.size() > 0) {
//                    startLoadGift(gifts.get(0));
//                }
//            }
//
//            @Override
//            public void onProgress(int progress, long total) {
//
//            }
//        });
//    }
//
//    public void startLoadGiftPearl(GiftDownBean giftDownBean) {
//        if (UpdataActivity.is_up) {
//            return;
//        }
//        PublicApi.downloadFile(giftDownBean.getAnimate_resource(), ERBanConstant.IMAGER_PATH_HOT_PEARL, MD5Util.md5(giftDownBean.getAnimate_resource()) + ".bin", giftDownBean.getAnimate_resource_md5(), new FileDownLoadObserver<File>() {
//            @Override
//            public void onDownLoadSuccess(File file) {
//                error = 0;
//                Log.e(TAG, "onDownLoadSuccess: " + giftDownBean.getGift_id());
//                gifts_pearl.remove(giftDownBean);
//                BaisAniUtil.getInstance().addGiftPearl(giftDownBean);
//                if (gifts_pearl.size() > 0) {
//                    startLoadGiftPearl(gifts_pearl.get(0));
//                }
//            }
//
//            @Override
//            public void onDownLoadFail(Throwable throwable) {
//                error++;
//                Log.e(TAG, "onDownLoadFail: pearl :" + giftDownBean.getGift_id());
//
//                if (error == 5) {
//                    return;
//                }
//                if (gifts_pearl.size() <= 0) {
//                    return;
//                }
//                gifts_pearl.remove(0);
//                gifts_pearl.add(giftDownBean);
//                if (gifts_pearl.size() > 0) {
//                    startLoadGiftPearl(gifts_pearl.get(0));
//                }
//            }
//
//            @Override
//            public void onProgress(int progress, long total) {
//
//            }
//        });
//    }


}
