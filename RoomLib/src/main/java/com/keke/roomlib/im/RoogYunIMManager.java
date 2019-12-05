package com.keke.roomlib.im;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.hjq.toast.ToastUtils;
import com.keke.roomlib.R;
import com.keke.roomlib.api.KKRoom;
import com.keke.roomlib.api.PublicApi;
import com.keke.roomlib.api.ResponseListener;
import com.keke.roomlib.base.UserManager;
import com.keke.roomlib.bean.MessageBean;
import com.keke.roomlib.bean.RoomMessageBean;
import com.keke.roomlib.bean.UserBean;
import com.keke.roomlib.im.mess.KKAuidoRoomMessage;
import com.keke.roomlib.im.mess.KKAuidoRoomUserMessage;
import com.keke.roomlib.im.mess.KKMeetRoomMsg;
import com.keke.roomlib.im.mess.KKRedPacketStatusMessage;
import com.keke.roomlib.im.mess.KKTransparentMessage;
import com.keke.roomlib.room.RoomManager;
import com.keke.roomlib.utils.SharedPreferenceUtil;
import com.keke.roomlib.utils.Utlis;
import com.keke.roomlib.zego.VoicesStates;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import io.rong.message.FileMessage;
import io.rong.message.ImageMessage;
import io.rong.message.RichContentMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;

public class RoogYunIMManager {


    private static final String TAG = "RoogYunIMManager";
    private static RoogYunIMManager mRoogYunIMManager;
    private EventBus mEventBus;


    public static RoogYunIMManager getInstance() {
        if (mRoogYunIMManager == null) {
            synchronized (RoogYunIMManager.class) {
                if (mRoogYunIMManager == null) {
                    mRoogYunIMManager = new RoogYunIMManager();
                }
            }
        }
        return mRoogYunIMManager;
    }


    public void init(Context context) {
        mEventBus = EventBus.getDefault();
//        PushConfig config = new PushConfig.Builder()
//                .enableMiPush(ERBanConstant.MI_PUSH_ID, ERBanConstant.MI_PUSH_key)
//                .enableHWPush(true)
//                .enableMeiZuPush(ERBanConstant.MZ_PUSH_ID,ERBanConstant.MZ_PUSH_key)
//                .enableOppoPush(ERBanConstant.OPPO_PUSH_key,ERBanConstant.OPPO_PUSH_SECRE)
//                .build();
//        RongPushClient.setPushConfig(config);
        RongIM.init(context);

        RongIMClient.getInstance().setPushContentShowStatus(true, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                Log.e(TAG, "onSuccess: ");
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });

        RongIMClient.setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
            @Override
            public void onChanged(ConnectionStatus connectionStatus) {
                switch (connectionStatus) {
                    case CONNECTED://连接成功
                        break;
                    case CONNECTING://连接中
                        break;
                    case DISCONNECTED://当前用户主动断开链接
                        break;
                    case SERVER_INVALID:
                        break;
                    case TOKEN_INCORRECT:
                        break;
                    case CONN_USER_BLOCKED:
                        break;
                    case NETWORK_UNAVAILABLE: //
                        ToastUtils.show("网络不可用");
                        break;
                    case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线
                        // TODO: 2019/11/30  用户在比的手机登录



//                        ToastUtils.show(ErBanApp.instance.getString(R.string.zhaiqitashebeidenglu));
//                        UserManager.getInstance().logout(new ResponseListener() {
//                            @Override
//                            public void success(Object o) {
//                            }
//
//                            @Override
//                            public void error(String s) {
//
//                            }
//                        });
                        break;
                }
            }
        });

        registerMessage();
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

            @Override
            public UserInfo getUserInfo(String userId) {
           PublicApi.getUserInfo(userId, new ResponseListener() {
                    @Override
                    public void success(Object o) {
                        UserBean userBean = (UserBean) o;
                        refreshUserInfoCache(userBean);
                    }

                    @Override
                    public void error(String s) {

                    }
                });
                return userId.equals(UserManager.user_id)?new UserInfo(UserManager.getInstance().getUserBean().getUser_id(), UserManager.getInstance().getUserBean().getNick_name(), Uri.parse(UserManager.getInstance().getUserBean().gethead())):null;//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
            }

        }, true);
        setMyExtensionModule();


        // TODO: 2019/11/30  初始换聊天页面的点击时间
//        RongIM.getInstance().setConversationBehaviorListener(new RongIM.ConversationBehaviorListener() {
//            @Override
//            public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
//                if (conversationType == Conversation.ConversationType.PRIVATE) {
//
//                    LauncherActivity.goToUserHomeActivity(context, userInfo.getUserId());
//                    return true;
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
//                return false;
//            }
//
//            @Override
//            public boolean onMessageClick(Context context, View view, Message message) {
//                return false;
//            }
//
//            @Override
//            public boolean onMessageLinkClick(Context context, String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onMessageLongClick(Context context, View view, Message message) {
//                return false;
//            }
//        });

        RongIM.setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
            @Override
            public void onChanged(ConnectionStatus connectionStatus) {

            }
        });


    }


    public RongIM get() {
        return RongIM.getInstance();
    }
    /**
     * 融云初始化自定义消息
     *
     * */
    private void registerMessage() {
//        RongIM.registerMessageType(KKUpdateMessage.class);
//        RongIM.registerMessageType(KKRoomStartMessage.class);
//        RongIM.registerMessageType(KKRechargeMessage.class);
//        RongIM.registerMessageType(KKActivityMessage.class);
//        RongIM.registerMessageType(KKSendMessage.class);
//        RongIM.registerMessageType(KKDynamicMessage.class);
//        RongIM.registerMessageType(KKLookUserMessage.class);
//        RongIM.registerMessageType(KKShareUserMessage.class);
//        RongIM.registerMessageType(KKShareRoomMessage.class);
//        RongIM.registerMessageType(KKShareDynamicMessage.class);
//        RongIM.registerMessageType(KKRankUpdateMessage.class);
//        RongIM.registerMessageType(KKRoleAddMessage.class);
//        RongIM.registerMessageType(KKAllGiftMessage.class);
//        RongIM.registerMessageType(KKCpRingUpMessage.class);
//        RongIM.registerMessageType(KKThrowingHammerAwardMsg.class);
//        RongIM.registerMessageType(KKSysTextMessage.class);
//        RongIM.registerMessageType(KKCpReqMessage.class);
//        RongIM.registerMessageType(KKCpCancelMessage.class);
//        RongIM.registerMessageType(KKSimpleFullServiceMsgNotify.class);
//        RongIM.registerMessageType(KKWarningMessage.class);
        RongIM.registerMessageType(KKAuidoRoomMessage.class);
        RongIM.registerMessageType(KKTransparentMessage.class);
        RongIM.registerMessageType(KKRedPacketStatusMessage.class);
        RongIM.registerMessageType(KKAuidoRoomUserMessage.class);
        RongIM.registerMessageType(KKMeetRoomMsg.class);
//        RongIM.registerMessageType(KKAllRedPacketMessage.class);
//        RongIM.registerMessageType(KKBigRedPacketMessage.class);
//        RongIM.registerMessageType(KKCmsAllRedPacket.class);
//        RongIM.registerMessageType(KKApplyJoinUnionMsgMessage.class);
//        RongIM.registerMessageType(KKAppointJoinUnionMsgMessage.class);
//        RongIM.registerMessageType(KKQuitUnionMsgMessage.class);
//        RongIM.registerMessageType(KKRemoveUnionMsgMessage.class);
//        RongIM.getInstance().registerMessageTemplate(new KKRoomStartMessageItemProvider());
//        RongIM.getInstance().registerMessageTemplate(new KKActivityMessageItemProvider());
//        RongIM.getInstance().registerMessageTemplate(new KKRechargeMessageItemProvider());
//        RongIM.getInstance().registerMessageTemplate(new KKUpdataMessageItemProvider());
//        RongIM.getInstance().registerMessageTemplate(new KKsendMessageItemProvider());
//        RongIM.getInstance().registerMessageTemplate(new KKShareDynamicMessageItemProvider());
//        RongIM.getInstance().registerMessageTemplate(new KKShareRoomMessageItemProvider());
//        RongIM.getInstance().registerMessageTemplate(new KKShareUserMessageItemProvider());
//        RongIM.getInstance().registerMessageTemplate(new KKRoleAddMessageItemProvider());
//        RongIM.getInstance().registerMessageTemplate(new KKRankUpdateMessageItemProvider());
//        RongIM.getInstance().registerMessageTemplate(new KKSysTextMessageItemProvider());
//        RongIM.getInstance().registerMessageTemplate(new KKCpReqMessageItemProvider());
//        RongIM.getInstance().registerMessageTemplate(new KKCpCancelMessageItemProvider());
//
//        RongIM.getInstance().registerMessageTemplate(new KKApplyJoinUnionMsgMessageItemProvider());
//        RongIM.getInstance().registerMessageTemplate(new KKAppointUnionMsgMessageItemProvider());
//        RongIM.getInstance().registerMessageTemplate(new KKQuitUnionMsgMessageItemProvider());
//        RongIM.getInstance().registerMessageTemplate(new KKRechargeMessageItemProvider());









    }


//    public String getMessType(MessageContent messageContent) {
//        if (messageContent instanceof KKRoomStartMessage) {
//            return ErBanApp.instance.getString(R.string.im_fjkaiq);
//        } else if (messageContent instanceof KKSendMessage) {
//            return ErBanApp.instance.getString(R.string.im_lwxx);
//        } else if (messageContent instanceof KKActivityMessage) {
//            return ErBanApp.instance.getString(R.string.im_hdxx);
//        } else if (messageContent instanceof KKRechargeMessage) {
//            return ErBanApp.instance.getString(R.string.im_chongzhi);
//        } else if (messageContent instanceof KKUpdateMessage) {
//            return ErBanApp.instance.getString(R.string.im_banbenxx);
//        } else if (messageContent instanceof KKShareDynamicMessage) {
//            return ErBanApp.instance.getString(R.string.im_dongtaifx);
//        } else if (messageContent instanceof KKShareRoomMessage) {
//            return ErBanApp.instance.getString(R.string.im_fangjfex);
//        } else if (messageContent instanceof KKShareUserMessage) {
//            return ErBanApp.instance.getString(R.string.im_yonghufengx);
//        } else if (messageContent instanceof KKRoleAddMessage) {
//            return ErBanApp.instance.getString(R.string.im_addgly);
//        } else if (messageContent instanceof KKRankUpdateMessage) {
//            return ErBanApp.instance.getString(R.string.im_shengji);
//        } else if (messageContent instanceof KKSysTextMessage) {
//            return ErBanApp.instance.getString(R.string.im_xitongxx);
//        } else if (messageContent instanceof KKCpReqMessage) {
//            return ErBanApp.instance.getString(R.string.im_cpqq);
//        } else if (messageContent instanceof KKCpCancelMessage) {
//            return ErBanApp.instance.getString(R.string.im_cpquxiao);
//        } else {
//            return "";
//        }
//    }

    public void setMyExtensionModule() {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);

               // RongExtensionManager.getInstance().registerExtensionModule(new MyExtensionModule());
            }
        }
    }


    // TODO: 2019/11/30 融云连接成功后调用
    public void setOnReceiveMessageListener() {
        RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
            @Override
            public boolean onReceived(Message message, int i) {
                Log.e(TAG, "onReceived: " );
                mEventBus.post(message);
                switch (message.getConversationType()) {
                    case PRIVATE:
                        Log.e(TAG, "onReceived: "+message.getSentTime() );
                        break;
                    case CHATROOM:
                        Log.e(TAG, "onReceived: chat"+message.getSentTime() );
                        switch (message.getObjectName()) {
                            case "RC:TxtMsg":
                                TextMessage textMessage = (TextMessage) message.getContent();
                                String messs=textMessage.getExtra();

                                RoomMessageBean beanss = new RoomMessageBean();
                                if (RoomManager.getInstance().getmRoomBean()==null) {
                                    return false;
                                }
                                if (!RoomManager.getInstance().getmRoomBean().getRoom_id().equals(message.getTargetId())){
                                    return false;
                                }

                                beanss.setChannelID(RoomManager.getInstance().getmRoomBean().getRoom_id());
                                beanss.setO(messs);
                                beanss.setSenderUserId(message.getSenderUserId());
                                beanss.setEventType(VoicesStates.VOICE_EVENT_MESSAGE_NOTIFY);
                                mEventBus.post(beanss);
                                break;
                            case "kk:audio_room":
                                if (!message.getSenderUserId().equals("11")) {
                                    return true;
                                }
                                KKAuidoRoomMessage chatMessage = (KKAuidoRoomMessage) message.getContent();
                                String mes = chatMessage.getContent();

                                RoomMessageBean bean = new RoomMessageBean();
                                if (RoomManager.getInstance().getmRoomBean()==null) {
                                    return false;
                                }
                                if (!RoomManager.getInstance().getmRoomBean().getRoom_id().equals(message.getTargetId())){
                                    return false;
                                }

                                bean.setChannelID(RoomManager.getInstance().getmRoomBean().getRoom_id());
                                bean.setO(mes);
                                bean.setSenderUserId(message.getSenderUserId());
                                bean.setEventType(VoicesStates.VOICE_EVENT_MESSAGE_NOTIFY);
                                mEventBus.post(bean);
                                break;
                            case  "kk:audio_room_user":
                                KKAuidoRoomUserMessage messageContent = (KKAuidoRoomUserMessage) message.getContent();
                                String mess=messageContent.getContent();



                                RoomMessageBean beans = new RoomMessageBean();
                                if (RoomManager.getInstance().getmRoomBean()==null) {
                                    return false;
                                }
                                if (!RoomManager.getInstance().getmRoomBean().getRoom_id().equals(message.getTargetId())){
                                    return false;
                                }

                                beans.setChannelID(RoomManager.getInstance().getmRoomBean().getRoom_id());
                                beans.setO(mess);
                                beans.setSenderUserId(message.getSenderUserId());
                                beans.setEventType(VoicesStates.VOICE_EVENT_MESSAGE_NOTIFY);
                                mEventBus.post(beans);

                                break;
                            case "kk:all_gift":
                            case "kk:cp_ring_up":
                            case "kk:throwing_hammer_award_msg":
                            case "kk:full_service_msg_notify":
                            case "kk:all_red_packet":
                            case "kk:big_red_packet":
                            case "kk:cms_all_red_packet":
                                long sentTimes = message.getSentTime();

                                if (System.currentTimeMillis() - 1  * 60 * 1000 < sentTimes) {

                                    MessageContent content = message.getContent();
                                    MessageBean meAllGiftBean = new MessageBean();
                                    meAllGiftBean.setMes_code(MessageBean.KK_ALL_GIFT);
                                    meAllGiftBean.setBean(content);
                                    mEventBus.post(meAllGiftBean);
                                }

                                break;
                            case "kk:warning":
                                MessageContent cont = message.getContent();
                                MessageBean meAllWarBean = new MessageBean();
                                meAllWarBean.setMes_code(MessageBean.KK_WARNNING);
                                meAllWarBean.setBean(cont);
                                mEventBus.post(meAllWarBean);
                                break;
                            case "kk:transparent":

                                KKTransparentMessage transparentMessage = (KKTransparentMessage) message.getContent();
                                IMbean iMbean = transparentMessage.getmContent();
                                if ("1".equals(iMbean.getT())) {
                                    KKRoom.initBean.setHammer_open_state("Y");
                                }else {
                                    KKRoom.initBean.setHammer_open_state("N");
                                }
                                MessageBean meAllWarBeans = new MessageBean();
                                meAllWarBeans.setMes_code(MessageBean.KK_TRANSPARENT);
                                mEventBus.post(meAllWarBeans);

                                break;
                            case "kk:redPacketStatus":
                                KKRedPacketStatusMessage redPacketStatusMessage =
                                        (KKRedPacketStatusMessage) message.getContent();
                                KKRoom.initBean.setRed_packet_status(
                                        redPacketStatusMessage.getmContent().getT());
                                MessageBean messageBean = new MessageBean();
                                messageBean.setMes_code(MessageBean.KK_RED_PACKET_STATUS);
                                mEventBus.post(messageBean);
                                break;
                            case "kk:meet_room":


                                // TODO: 2019/11/30  一对一聊天
                                
//                                if (!message.getSenderUserId().equals("11")) {
//                                    return true;
//                                }
//                                KKMeetRoomMsg kkMe = (KKMeetRoomMsg) message.getContent();
//                                String mess2=kkMe.getContent();
//
//
//                                RoomMessageBean bea = new RoomMessageBean();
//
//                                if (Utlis.isEmp(OneToOneManger.getInstance().getmRoomId())) {
//                                    return false;
//                                }
//                                bea.setChannelID(OneToOneManger.getInstance().getmRoomId());
//                                bea.setO(mess2);
//                                bea.setSenderUserId(message.getSenderUserId());
//                                bea.setEventType(VoicesStates.VOICE_EVENT_MESSAGE_NOTIFY);
//                                mEventBus.post(bea);
                                break;

                        }

                        break;


                    case SYSTEM:
                        switch (message.getObjectName()) {
                            case "kk:warning":
                                MessageContent cont = message.getContent();
                                MessageBean meAllWarBean = new MessageBean();
                                meAllWarBean.setMes_code(MessageBean.KK_WARNNING);
                                meAllWarBean.setBean(cont);
                                mEventBus.post(meAllWarBean);
                                break;
                            case "kk:meet_room":

                                KKMeetRoomMsg roomMsg = (KKMeetRoomMsg) message.getContent();
                                IMbean iMbean1 = roomMsg.getmContent();
                                MessageBean me = new MessageBean();
                                me.setBean(iMbean1);
                                me.setMes_code(MessageBean.KK_MEET_ROOM);
                                mEventBus.post(me);

                                break;
                        }

                        default:



                }

                return true;
            }
        });
        RongIM.getInstance().setSendMessageListener(new RongIM.OnSendMessageListener() {
            @Override
            public Message onSend(Message message) {

                if (message.getConversationType()== Conversation.ConversationType.PRIVATE) {

                }

                mEventBus.post(message);
                return message;
            }

            @Override
            public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {

                if (message.getSentStatus() == Message.SentStatus.FAILED) {
                    if (sentMessageErrorCode == RongIM.SentMessageErrorCode.NOT_IN_CHATROOM) {
                        //不在聊天室
                    } else if (sentMessageErrorCode == RongIM.SentMessageErrorCode.NOT_IN_DISCUSSION) {
                        //不在讨论组
                    } else if (sentMessageErrorCode == RongIM.SentMessageErrorCode.NOT_IN_GROUP) {
                        //不在群组
                    } else if (sentMessageErrorCode == RongIM.SentMessageErrorCode.REJECTED_BY_BLACKLIST) {
                        //你在他的黑名单中
                    }
                }

                MessageContent messageContent = message.getContent();

                if (messageContent instanceof TextMessage) {//文本消息
                    TextMessage textMessage = (TextMessage) messageContent;
                    Log.d(TAG, "onSent-TextMessage:" + textMessage.getContent());
                } else if (messageContent instanceof ImageMessage) {//图片消息
                    ImageMessage imageMessage = (ImageMessage) messageContent;
                    Log.d(TAG, "onSent-ImageMessage:" + imageMessage.getRemoteUri());
                } else if (messageContent instanceof VoiceMessage) {//语音消息
                    VoiceMessage voiceMessage = (VoiceMessage) messageContent;
                    Log.d(TAG, "onSent-voiceMessage:" + voiceMessage.getUri().toString());
                } else if (messageContent instanceof RichContentMessage) {//图文消息
                    RichContentMessage richContentMessage = (RichContentMessage) messageContent;
                    Log.d(TAG, "onSent-RichContentMessage:" + richContentMessage.getContent());
                } else {
                    Log.d(TAG, "onSent-其他消息，自己来判断处理");
                }

                return false;
            }
        });
        setCurrentUserInfo();
        RongIM.getInstance().setMessageAttachedUserInfo(true);

        if (KKRoom.initBean.getChat_room_id() == null) {
            return;
        }
        RongIM.getInstance().joinChatRoom(KKRoom.initBean.getChat_room_id(), -1, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess: joinChatRoom" + KKRoom.initBean.getChat_room_id());

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }


    public void  joinChatRoom(final String chatRoomId,  final RongIMClient.OperationCallback callback){


        String room_chat_id = (String) SharedPreferenceUtil.get(KKRoom.getInstance().mContext, "room_chat_id", "");
        if (Utlis.isEmp(room_chat_id)) {
            RongIM.getInstance().joinChatRoom(chatRoomId,-1, callback);
        }else {
            RongIM.getInstance().quitChatRoom(room_chat_id, new RongIMClient.OperationCallback() {
                @Override
                public void onSuccess() {

                    SharedPreferenceUtil.put(KKRoom.getInstance().mContext,"room_chat_id",chatRoomId);
                    RongIM.getInstance().joinChatRoom(chatRoomId,-1, callback);
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    callback.onError(errorCode);
                }
            });
        }

    }


    public void quitChatRoom(final String chatRoomId,  final RongIMClient.OperationCallback callback){
        RongIM.getInstance().quitChatRoom(chatRoomId, callback);
    }


    /**
     * 获取聊天列表
     *
     * @param listResultCallback
     */

    // TODO: 2019/11/30  获取聊天列表(房间理论上不要)
    public void getConversationList(final RongIMClient.ResultCallback<List<Conversation>> listResultCallback) {

        final String sysId[] = {"11", "12", "13", "14","10"};


        RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> conversatio1) {
                final List<Conversation> conversatio = new ArrayList<>();

                a:
                for (String s : sysId) {
                    if (conversatio1 == null) {
                        Conversation con = new Conversation();
                        con.setConversationType(Conversation.ConversationType.SYSTEM);
                        con.setTargetId(s);
                        conversatio.add(con);
                    } else {
                        for (Conversation con : conversatio1) {
                            if (con.getTargetId().equals(s)) {
                                conversatio.add(con);
                                continue a;
                            }
                        }
                        Conversation con = new Conversation();
                        con.setConversationType(Conversation.ConversationType.SYSTEM);
                        con.setTargetId(s);
                        conversatio.add(con);
                    }
                }

                RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
                    @Override
                    public void onSuccess(List<Conversation> conversations) {
                        if (conversations != null) {
                            conversatio.addAll(conversations);
                        }
                        listResultCallback.onSuccess(conversatio);
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        listResultCallback.onError(errorCode);

                    }
                }, Conversation.ConversationType.PRIVATE);

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                listResultCallback.onError(errorCode);
            }


        }, Conversation.ConversationType.SYSTEM);


    }

    public void refreshUserInfoCache(UserBean userBean) {
        RongIM.getInstance().refreshUserInfoCache(new UserInfo(userBean.getUser_id(), userBean.getNick_name(), Uri.parse(userBean.gethead())));
    }

    public void setCurrentUserInfo() {
        if (UserManager.getInstance().getUserBean() == null) {
            return;
        }
        RongIM.getInstance().setCurrentUserInfo(new UserInfo(UserManager.getInstance().getUserBean().getUser_id(), UserManager.getInstance().getUserBean().getNick_name(), Uri.parse(UserManager.getInstance().getUserBean().gethead())));
    }


    public String con_id;


    // TODO: 2019/11/30  跳转到聊天页面（房间理论上不要）
//    public void startConversation(final Context context, final Conversation.ConversationType conversationType, final String targetId, final String title, final FragmentManager fragmentManager) {
//        con_id = targetId;
//
//        if (Conversation.ConversationType.PRIVATE==conversationType) {
//
//           PublicApi.getFriendsMsgAuth(targetId, new ResponseListener() {
//                @Override
//                public void success(Object o) {
//
//                    JSONObject jsonObject = (JSONObject) o;
//                    int auth=0;
//                    try {
//                        JSONObject data = jsonObject.getJSONObject("data");
//                        auth = data.getInt("auth");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    if (auth==1) {
//                        if (context != null && !TextUtils.isEmpty(targetId) && conversationType != null) {
//                            Uri uri = Uri.parse("rong://" + context.getApplicationInfo().processName).buildUpon().appendPath("conversation").appendPath(conversationType.getName().toLowerCase(Locale.US)).appendQueryParameter("targetId", targetId).appendQueryParameter("title", title).build();
//                            Intent intent = new Intent("android.intent.action.VIEW", uri);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            context.startActivity(intent);
//                        } else {
//                            throw new IllegalArgumentException();
//                        }
//                    }else {
//                        if (fragmentManager==null) {
//                            try {
//                                ToastUtils.show(jsonObject.getString("msg"));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            return;
//                        }
//
//                        BaseSystemTitleDialog baseSystemTitleDialog = new BaseSystemTitleDialog();
//                        try {
//                            baseSystemTitleDialog.setCon(jsonObject.getString("msg"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        baseSystemTitleDialog.setzClick(ErBanApp.instance.getString(R.string.quxiao), new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                baseSystemTitleDialog.dismiss();
//                            }
//                        });
//                        baseSystemTitleDialog.setyClick(ErBanApp.instance.getString(R.string.qd), new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                baseSystemTitleDialog.dismiss();
//                            }
//                        });
//                        baseSystemTitleDialog.show(fragmentManager);
//
//                    }
//                }
//
//                @Override
//                public void error(String s) {
//
//                }
//
//                @Override
//                public void onCodeError(int errorCode) {
//                    super.onCodeError(errorCode);
//                    if (errorCode==2048) {
//                        if (fragmentManager==null) {
//                            ToastUtils.show(context.getString(R.string.qingaidekekeyuyingyonghugengju));
//                            return;
//                        }
//                        BaseSystemTitleDialog baseSystemTitleDialog = new BaseSystemTitleDialog();
//                        baseSystemTitleDialog.setCon(context.getString(R.string.qingaidekekeyuyingyonghugengju));
//                        baseSystemTitleDialog.setzClick(ErBanApp.instance.getString(R.string.quxiao), new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                baseSystemTitleDialog.dismiss();
//                            }
//                        });
//                        baseSystemTitleDialog.setyClick(ErBanApp.instance.getString(R.string.qd), new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                LauncherActivity.goToAuthenticationWei(context);
//                                baseSystemTitleDialog.dismiss();
//                            }
//                        });
//                        baseSystemTitleDialog.show(fragmentManager);
//                    }
//
//
//                }
//            });
//        }else {
//            if (context != null && !TextUtils.isEmpty(targetId) && conversationType != null) {
//                Uri uri = Uri.parse("rong://" + context.getApplicationInfo().processName).buildUpon().appendPath("conversation").appendPath(conversationType.getName().toLowerCase(Locale.US)).appendQueryParameter("targetId", targetId).appendQueryParameter("title", title).build();
//                Intent intent = new Intent("android.intent.action.VIEW", uri);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//            } else {
//                throw new IllegalArgumentException();
//            }
//        }
//    }


    public void removeConversation(Conversation.ConversationType conversationType, String targetId, RongIMClient.ResultCallback<Boolean> resultCallback) {
        RongIM.getInstance().removeConversation(conversationType, targetId, resultCallback);
    }


    public void deleteMessages(final int[] messageIds, final RongIMClient.ResultCallback<Boolean> callback) {
        RongIM.getInstance().deleteMessages(messageIds, callback);

    }


    public void sendChatRoomMess(String targetid,String jsonMess){


        Message message = new Message();
        message.setConversationType(Conversation.ConversationType.CHATROOM);
        message.setSentTime(System.currentTimeMillis());
        message.setTargetId(targetid);


        KKAuidoRoomUserMessage kkAuidoRoomMessage = new KKAuidoRoomUserMessage();
        kkAuidoRoomMessage.setmContent(jsonMess);
        message.setObjectName("kk:audio_room_user");
        message.setContent(kkAuidoRoomMessage);

        RongIM.getInstance().sendMessage(message, KKRoom.getInstance().mContext.getString(R.string.home_liaotianshixiaoxi), "chat", new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {
            }

            @Override
            public void onSuccess(Message message) {
                Log.e(TAG, "onSuccess: sendChatRoomMess" );

            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                Log.e(TAG, "onError: sendChatRoomMess"+errorCode );

            }
        });

    }

    public void sendChatTextRoomMess(String targetid,String cont,String jsonMess){


        TextMessage textMessage = TextMessage.obtain(cont);
        textMessage.setExtra(jsonMess);
        Message myMessage = Message.obtain(targetid, Conversation.ConversationType.CHATROOM, textMessage);

        RongIM.getInstance().sendMessage(myMessage, KKRoom.getInstance().mContext.getString(R.string.home_liaotianshixiaoxi), "chat", new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {

            }

            @Override
            public void onSuccess(Message message) {

                Log.e(TAG, "onSuccess: sendChatTextRoomMess" );
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                Log.e(TAG, "onError: sendChatTextRoomMess"+errorCode );
            }
        });

    }


    public void sendShareIma(String targetId, final Uri bitmap){
        ImageMessage imageMessage= ImageMessage.obtain(bitmap,bitmap,true);

        RongIMClient.getInstance().sendImageMessage(Conversation.ConversationType.PRIVATE, targetId, imageMessage , null, "", new RongIMClient.SendImageMessageCallback() {

            @Override
            public void onAttached(Message message) {
                //保存数据库成功
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode code) {
                Log.e(TAG, "onError: "+code );
                //发送失败
            }

            @Override
            public void onSuccess(Message message) {
                //发送成功
                new File(bitmap.toString()).delete();
            }

            @Override
            public void onProgress(Message message, int progress) {
                //发送进度
            }
        });
    }

    public void sendTextMess(int targetId,String message){
        TextMessage textMessage = new TextMessage(message);
        Message message1 = Message.obtain(targetId+"", Conversation.ConversationType.PRIVATE,textMessage);
        RongIM.getInstance().sendMessage(message1, null, null, new IRongCallback.ISendMediaMessageCallback() {
            @Override
            public void onProgress(Message message, int i) {

            }

            @Override
            public void onCanceled(Message message) {

            }

            @Override
            public void onAttached(Message message) {

            }

            @Override
            public void onSuccess(Message message) {

            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {

            }
        });
    }
    /**
     *  im之间用户的分享
     *
     * */

    // TODO: 2019/11/30  用户自己分享的消息
//    public void sendShareMess(String targetid, IMbean ben) {
//
//
//        Message message = new Message();
//        message.setConversationType(Conversation.ConversationType.PRIVATE);
//        message.setSentTime(System.currentTimeMillis());
//        message.setTargetId(targetid);
//
//
//        switch (ben.getK()) {
//
//            case 90001:
//                KKShareRoomMessage kkShareRoomMessage = new KKShareRoomMessage();
//                kkShareRoomMessage.setmContent(ben);
//                message.setObjectName("kk:share_room");
//                message.setContent(kkShareRoomMessage);
//
//                break;
//            case 90002:
//                KKShareUserMessage kkShareUserMessage = new KKShareUserMessage();
//                kkShareUserMessage.setmContent(ben);
//                message.setContent(kkShareUserMessage);
//                message.setObjectName("kk:share_user");
//                break;
//            case 90003:
//                KKShareDynamicMessage kkShareDynamicMessage = new KKShareDynamicMessage();
//                kkShareDynamicMessage.setmContent(ben);
//                message.setObjectName("kk:share_dynamic");
//                message.setContent(kkShareDynamicMessage);
//
//                break;
//
//        }
//
//
//        RongIM.getInstance().sendMessage(message, ErBanApp.instance.getString(R.string.home_shoudaoyitfxxx), "share", new IRongCallback.ISendMessageCallback() {
//            @Override
//            public void onAttached(Message message) {
//            }
//
//            @Override
//            public void onSuccess(Message message) {
//
//            }
//
//            @Override
//            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
//            }
//        });
//    }

    /**
     * 根据不同的messageContent, 在"结果"页面做出相应的显示
     */
    private void handleMessage(MessageContent messageContent) {
        if (messageContent == null)
            return;

        if (messageContent instanceof TextMessage) {
            Log.d(TAG, "文本消息: " + ((TextMessage) messageContent).getContent());
        } else if (messageContent instanceof ImageMessage) {
            Log.d(TAG, "图片消息, Uri --> " + ((ImageMessage) messageContent).getThumUri());
        } else if (messageContent instanceof VoiceMessage) {
            Log.d(TAG, "语音消息, Uri --> " + ((VoiceMessage) messageContent).getUri());
        } else if (messageContent instanceof FileMessage) {
            Log.d(TAG, "文件消息, Uri --> " + ((FileMessage) messageContent).getFileUrl());
        } else if (messageContent instanceof CustomizeMessage) {
//            Log.d(TAG, "自定义消息的内容: " + ((CustomizeMessage) messageContent).getContent());
        }
    }


}
