package com.keke.roomlib.base;

import com.google.gson.Gson;
import com.keke.roomlib.bean.DiyMallBean;
import com.keke.roomlib.bean.HeartMoShiBean;
import com.keke.roomlib.bean.RoomBean;
import com.keke.roomlib.bean.RoomImMessageBean;
import com.keke.roomlib.bean.RoomMessageBean;
import com.keke.roomlib.bean.UserBean;
import com.keke.roomlib.im.RoogYunIMManager;
import com.keke.roomlib.room.RoomManager;
import com.keke.roomlib.utils.Utlis;
import com.keke.roomlib.zego.VoicesBinderForegroundService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class RoomImManager implements Serializable {


    public static final String ROOM_IM_10001 = "10001";
    public static final String ROOM_IM_10002 = "10002";
    public static final String ROOM_IM_10003 = "10003";
    public static final String ROOM_IM_20004 = "20004";
    public static final String ROOM_IM_20005 = "20005";
    public static final String ROOM_IM_30001 = "30001";
    public static final String ROOM_IM_30002 = "30002";
    public static final String ROOM_IM_30003 = "30003";
    public static final String ROOM_IM_30004 = "30004";
    public static final String ROOM_IM_30005 = "30005";
    public static final String ROOM_IM_30006 = "30006";
    public static final String ROOM_IM_30007 = "30007";
    public static final String ROOM_IM_30008 = "30008";
    public static final String ROOM_IM_30009 = "30009";
    public static final String ROOM_IM_30010 = "30010";
    public static final String ROOM_IM_30011 = "30011";
    public static final String ROOM_IM_30012 = "30012";
    public static final String ROOM_IM_30013 = "30013";
    public static final String ROOM_IM_30014 = "30014";
    public static final String ROOM_IM_30015 = "30015";
    public static final String ROOM_IM_30016 = "30016";
    public static final String ROOM_IM_30017 = "30017";
    public static final String ROOM_IM_30018 = "30018";
    public static final String ROOM_IM_30019 = "30019";
    public static final String ROOM_IM_30020 = "30020";
    public static final String ROOM_IM_30021 = "30021";
    public static final String ROOM_IM_30022 = "30022";
    public static final String ROOM_IM_30023 = "30023";
    public static final String ROOM_IM_30024 = "30024";
    public static final String ROOM_IM_30040 = "30040";
    public static final String ROOM_IM_30041 = "30041";
    public static final String ROOM_IM_30042 = "30042";
    public static final String ROOM_IM_30043 = "30043";
    public static final String ROOM_IM_30044 = "30044";
    public static final String ROOM_IM_30045 = "30045";
    public static final String ROOM_IM_30046 = "30046";
    public static final String ROOM_IM_30047 = "30047";
    public static final String ROOM_IM_30048 = "30048";
    public static final String ROOM_IM_30049 = "30049";
    public static final String ROOM_IM_30050 = "30050";
    public static final String ROOM_IM_30051 = "30051";
    public static final String ROOM_IM_30052 = "30052";
    public static final String ROOM_IM_30053 = "30053";
    public static final String ROOM_IM_30054 = "30054";
    public static final String ROOM_IM_30055 = "30055";
    public static final String ROOM_IM_30056 = "30056";
    public static final String ROOM_IM_30057 = "30057";
    public static final String ROOM_IM_30058 = "30058";
    public static final String ROOM_IM_30061 = "30061";
    public static final String ROOM_IM_30062 = "30062";
    public static final String ROOM_IM_30063 = "30063";
    public static final String ROOM_IM_30064 = "30064";
    public static final String ROOM_IM_30065 = "30065";
    public static final String ROOM_IM_30066 = "30066";
    public static final String ROOM_IM_30067 = "30067";
    public static final String ROOM_IM_30068 = "30068";
    public static final String ROOM_IM_30069 = "30069";
    public static final String ROOM_IM_30070 = "30070";
    public static final String ROOM_IM_30071 = "30071";
    public static final String ROOM_IM_30072 = "30072";
    public static final String ROOM_IM_30073 = "30073";
    public static final String ROOM_IM_30074 = "30074";
    public static final String ROOM_IM_30075 = "30075";
    public static final String ROOM_IM_30076 = "30076";
    public static final String ROOM_IM_30077 = "30077";
    public static final String ROOM_IM_30078 = "30078";
    public static final String ROOM_IM_30079 = "30079";
    public static final String ROOM_IM_30080 = "30080";
    public static final String ROOM_IM_30081 = "30081";
    public static final String ROOM_IM_30082 = "30082";
    public static final String ROOM_IM_30083 = "30083";
    public static final String ROOM_IM_30084 = "30084";
    public static final String ROOM_IM_30085 = "30085";
    public static final String ROOM_IM_11001 = "11001";


    private List<RoomImMessageBean> data = new ArrayList<>();
    public OnRoomIMDoThings doThings;
    private Gson gson = new Gson();

    public RoomImManager(OnRoomIMDoThings doThings) {
        this.doThings = doThings;
    }

    public static final int SEND_MESSAGE = 1;


    public void sendME_10001_text_message(UserBean ue, String t, String roomId,HashMap<String,Object> maps) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_10001 + "");
        map.put("fid", ue.getUser_id());
        map.put("fnn", ue.getNick_name());
        map.put("fimg", ue.gethead());
        map.put("t", t);
        map.put("w", ue.getWaiter() + "");
        map.put("wc", ue.getWealth_balance());
        map.put("uid", ue.getUser_id());
        map.put("fbt", ue.getBirthday() + "");
        map.put("fsex", ue.getGender() + "");
        map.put("uid", ue.getUser_id());
        map.put("cc", ue.getCharm_balance());
        map.put("rc", ue.getReputation_balance());
        map.put("bc", ue.getCelebrity_balance());
        map.put("v", ue.getV());
        map.put("cc", ue.getCharm_balance());
        map.put("l", ue.getLiang() + "");
        map.put("nov", ue.getNovice() + "");
        // TODO: 2019/11/30  判断是否是守护
        Iterator<String> iter = maps.keySet().iterator();
        while (iter.hasNext()){
            map.put(iter.next(),maps.get(iter.next()));

        }
//        if(UserManager.getInstance().getGuardCacheBean()!=null){
//            RoomImMessageBean.Game game=new RoomImMessageBean.Game();
//            if(UserManager.getInstance().getGuardCacheBean().size()==0){
//                 game.setName("00");
//                 game.setType(0);
//                 map.put("gsm",game);
//
//             }else{
//               game.setName(UserManager.getInstance().getGuardCacheBean().get(0).getName());
//               game.setType(UserManager.getInstance().getGuardCacheBean().get(0).getType());
//               map.put("gsm",game);
//
//           }
//
//         }
        // TODO: 2019/11/30 神秘人
//        if (UserManager.getInstance().getUserBean().getMedal().size()>0) {
//            map.put("medal",UserManager.getInstance().getUserBean().getMedal());
//        }

       List<DiyMallBean.DiyItemBean> diyItems = ue.getAttires();
        if (diyItems != null) {
            for (DiyMallBean.DiyItemBean diyItem : diyItems) {
                if (diyItem.getGoods_type() == 3) {
                    map.put("bid", diyItem.getGoods_id() + "");
                    map.put("bres", diyItem.getAnimate_resource());


                }
            }
        }

        map.put("bt", ue.getBirthday() + "");
        map.put("sex", ue.getGender() + "");

        if (RoomManager.getInstance().getmRoomBean() != null) {
            String json = new Gson().toJson(map);
            RoogYunIMManager.getInstance().sendChatTextRoomMess(RoomManager.getInstance().getmRoomBean().getRoom_id(), t, json);

        }
        String s = VoicesBinderForegroundService.mapToJson(map);
        doThings.refChatAdapter(new Gson().fromJson(s, RoomImMessageBean.class));


    }


    public void sendME_11001(HeartMoShiBean.SelectDataBean dataBean, String is_heart, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_11001 + "");
        map.put("uid", dataBean.getUser_id());
        map.put("sid", dataBean.getSelect_user_id());
        map.put("snn", dataBean.getSelect_nickname());
        map.put("unn", dataBean.getNickname());
        map.put("ssex",dataBean.getSelect_gender());
        map.put("usex", dataBean.getGender());

        map.put("is_heart", is_heart);
        RoomManager.getInstance().sendMessage(roomId, map);

        String s = VoicesBinderForegroundService.mapToJson(map);
        doThings.showXingDongPublic(new Gson().fromJson(s, RoomImMessageBean.class));


    }

    public void sendME_10001_pic_message(UserBean ue, String pic, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_10002 + "");
        map.put("fid", ue.getUser_id());
        map.put("fnn", ue.getNick_name());
        map.put("fimg", ue.gethead());
        map.put("pic", pic);
        RoomManager.getInstance().sendMessage(roomId, map);
    }


    public void sendME_30001(String mid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30001 + "");
        map.put("mid", mid);
        RoomManager.getInstance().sendMessage(roomId, map);
    }


    public void sendME_30002(String mid, UserBean bean, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30002 + "");
        map.put("uid", bean.getUser_id());
        map.put("mid", bean.getMid());
        map.put("nn", bean.getNick_name());
        map.put("img", bean.gethead());
        map.put("sex", bean.getGender() + "");
        map.put("bt", bean.getBirthday() + "");
        RoomManager.getInstance().sendMessage(roomId, map);
    }


    public void sendME_30003(String mid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30003 + "");
        map.put("mid", mid);
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30004(String mid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30004 + "");
        map.put("mid", mid);
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30007(String mid, UserBean userBean, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30007 + "");
        map.put("mid", mid);
        map.put("uid", userBean.getUser_id());
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30008(String mid, UserBean bean, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30008 + "");
        map.put("uid", bean.getUser_id());
        map.put("mid", bean.getMid());
        map.put("nn", bean.getNick_name());
        map.put("img", bean.gethead());
        map.put("sex", bean.getGender() + "");
        map.put("bt", bean.getBirthday() + "");
        RoomManager.getInstance().sendMessage(roomId, map);
    }


    public void sendME_30009(String mid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30009 + "");
        map.put("mid", mid);
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30010(String mid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30010 + "");
        map.put("mid", mid);
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30011(String mid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30011 + "");
        map.put("mid", mid);
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30012(String mid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30012 + "");
        map.put("mid", mid);
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30013(String mid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30013 + "");
        map.put("mid", mid);
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30014(String mid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30014 + "");
        map.put("mid", mid);
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30015(String mid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30015 + "");
        map.put("mid", mid);
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30017(String mid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30017 + "");
        map.put("mid", mid);
        RoomManager.getInstance().sendMessage(roomId, map);
    }


    public void sendME_30019(String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30019 + "");
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30020(String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30020 + "");
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30021(String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30021 + "");
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30022(String s, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30022 + "");
        map.put("s", s + "");
        RoomManager.getInstance().sendMessage(roomId, map);
    }


    public void sendME_30040(UserBean bean, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30040 + "");
        map.put("uid", bean.getUser_id());
        map.put("nn", bean.getNick_name());
        map.put("img", bean.gethead());
        map.put("sex", bean.getGender() + "");
        map.put("bt", bean.getBirthday() + "");
        map.put("gt", bean.getGuard_type() + "");
        RoomManager.getInstance().sendMessage(roomId, map);
    }


    public void sendME_30041(String mid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30041 + "");
        map.put("uid", mid);
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30042(String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30042 + "");
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30043(String url, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30043 + "");
        map.put("url", url);
        RoomManager.getInstance().sendMessage(roomId, map);
        RoomImMessageBean bean = new RoomImMessageBean();
        bean.setUrl(url);
        doThings.changeRoomBack(bean);


    }

    public void sendME_30044(String uid, String eid, String bid, String show_message, String bres, String roomId, String mlt, String pro,HashMap<String,Object> maps) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30044 + "");
        map.put("uid", uid);
        map.put("eid", eid);
        map.put("mlt", mlt);
        map.put("pro", pro);
        map.put("sm", show_message);
        map.put("w", UserManager.getInstance().getUserBean().getWaiter() + "");
        map.put("v", UserManager.getInstance().getUserBean().getV());

        map.put("l", UserManager.getInstance().getUserBean().getLiang() + "");
        map.put("fid", UserManager.user_id);
        map.put("nn", UserManager.getInstance().getUserBean().getNick_name());
        map.put("img", UserManager.getInstance().getUserBean().gethead());
        map.put("wc", UserManager.getInstance().getUserBean().getWealth_balance());
        map.put("bt", UserManager.getInstance().getUserBean().getBirthday() + "");
        map.put("sex", UserManager.getInstance().getUserBean().getGender() + "");
        map.put("cc", UserManager.getInstance().getUserBean().getCharm_balance() + "");
        map.put("nov", UserManager.getInstance().getUserBean().getNovice() + "");

        if (bid != null) {
            map.put("bid", bid);
            map.put("bres", bres);
        }

        // TODO: 2019/11/30  判断是否是守护，神秘人
        Iterator<String> iter = maps.keySet().iterator();
        while (iter.hasNext()){
            map.put(iter.next(),maps.get(iter.next()));

        }
        
//        if(UserManager.getInstance().getGuardCacheBean()!=null){
//            RoomImMessageBean.Game game=new RoomImMessageBean.Game();
//            if(UserManager.getInstance().getGuardCacheBean().size()==0){
//                game.setName("");
//                game.setType(0);
//                map.put("gsm",game);
//
//            }else{
//                game.setName(UserManager.getInstance().getGuardCacheBean().get(0).getName());
//                game.setType(UserManager.getInstance().getGuardCacheBean().get(0).getType());
//                map.put("gsm",game);
//
//            }
//
//        }
//
//        if (UserManager.getInstance().getUserBean().getMedal().size()>0) {
//            map.put("medal",UserManager.getInstance().getUserBean().getMedal());
//        }

        
        RoomManager.getInstance().sendMessage(roomId, map);
        String s = VoicesBinderForegroundService.mapToJson(map);
        doThings.expressionAnimation(new Gson().fromJson(s, RoomImMessageBean.class));
    }


    public void sendME_30045(String uid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30045 + "");
        map.put("uid", uid);
        map.put("sot", 1000 + "");
        RoomManager.getInstance().sendMessage(roomId, map);
    }


    public void sendME_30046(String uid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30046 + "");
        map.put("uid", uid);
        RoomManager.getInstance().sendMessage(roomId, map);
    }


    public void sendME_30047(String uid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30047 + "");
        map.put("uid", uid);
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30048(String uid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30048 + "");
        map.put("uid", uid);
        RoomManager.getInstance().sendMessage(roomId, map);
    }

    public void sendME_30049(String uid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30049 + "");
        map.put("uid", uid);
        RoomManager.getInstance().sendMessage(roomId, map);
    }


    public void sendME_30055(String gid, String roomId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", ROOM_IM_30057 + "");
        map.put("mid", "0");
        map.put("gid", gid);
        map.put("gc", "1");
        map.put("uid", "-");
        RoomManager.getInstance().sendMessage(roomId, map);
    }


    public void acceptIM(RoomMessageBean roomMessageBean) {

        String ims = ((String) roomMessageBean.getO());
        RoomImMessageBean bean = gson.fromJson(ims, RoomImMessageBean.class);
        if (!Utlis.isEmp(bean.getFnn())) {
            bean.setUid(bean.getFid());
        }
        data.add(bean);
        changgeLoacRoombean(bean);

        switch (bean.getK()) {
            case ROOM_IM_10001:
                bean.setUid(bean.getFid());
                if (bean.getIs_text_message().equals("1")) {
                    return;
                }

                doThings.refChatAdapter(bean);
                break;
            case ROOM_IM_10002:
                doThings.refChatAdapter(bean);
                break;
            case ROOM_IM_10003:
                doThings.onSystemMessage(bean);
                break;
            case ROOM_IM_20004:
                doThings.onReceveGift(bean);
                break;
            case ROOM_IM_20005:
                doThings.onReceveGiftPearl(bean);
                break;
            case ROOM_IM_30001:
                doThings.zcMOut_zl(bean, true);
                break;
            case ROOM_IM_30002:
                doThings.zcMUp_Out(imMessToUser(bean), true);
                break;
            case ROOM_IM_30003:
                doThings.zcMOut_zl(bean, false);
                break;
            case ROOM_IM_30004:
                doThings.zcMUp_Out(imMessToUser(bean), false);
                break;
            case ROOM_IM_30005:
                doThings.zcMBM(bean, false);
                break;
            case ROOM_IM_30006:
                doThings.zcMBM(bean, true);

                break;
            case ROOM_IM_30007:
                doThings.ptM_Up_Out_zl(bean, true);
                break;
            case ROOM_IM_30008:
                doThings.ptMUp_Out(imMessToUser(bean), true);
                break;
            case ROOM_IM_30009:
                doThings.ptM_Up_Out_zl(bean, false);
                break;
            case ROOM_IM_30076:
                doThings.ptM_Out_zl(bean);
                break;
            case ROOM_IM_30077:
                doThings.showRedStartMess(bean);
                break;
            case ROOM_IM_30078:
                doThings.dZPState(bean);
                break;
            case ROOM_IM_30079:
                doThings.dZPPublish(bean);
                break;
            case ROOM_IM_30080:
                doThings.dZPRefush(bean);
                break;
            case ROOM_IM_30010:
                doThings.ptMUp_Out(imMessToUser(bean), false);
                break;
            case ROOM_IM_30011:
                doThings.ptMJY(bean, true);
                break;
            case ROOM_IM_30012:
                doThings.ptMJY(bean, false);
                break;
            case ROOM_IM_30013:
                doThings.ptMLB(bean, true);
                break;
            case ROOM_IM_30014:
                doThings.ptMLB(bean, false);
                break;
            case ROOM_IM_30015:
                doThings.ptMBM_zl(bean, true);
                break;
            case ROOM_IM_30016:
                doThings.ptMBM(bean, true);
                break;
            case ROOM_IM_30017:
                doThings.ptMBM_zl(bean, false);
                break;
            case ROOM_IM_30018:
                doThings.ptMBM(bean, false);
                break;


            case ROOM_IM_30019:
                doThings.openXinDong(bean);
                break;
            case ROOM_IM_30020:
                doThings.closeXinDong(bean);
                break;
            case ROOM_IM_30021:
                doThings.againXinDong(bean);
            case ROOM_IM_30022:
                doThings.upDaXinDong(bean);
                break;

            case ROOM_IM_30023:
                doThings.addXdSelect(bean);
                break;
            case ROOM_IM_30024:
                doThings.refMeetOpen();
                break;
            case ROOM_IM_30040:
                doThings.addPM(imMessToUser(bean));
                break;
            case ROOM_IM_30041:
                doThings.removePM(imMessToUser(bean));
                break;
            case ROOM_IM_30042:
                doThings.clearPM();
                break;
            case ROOM_IM_30043:
                doThings.changeRoomBack(bean);
                break;
            case ROOM_IM_30044:

                // TODO: 2019/12/2   动画表情通知
//                for (DownExprBean.ResBean resBean : ErBanApp.instance.allExprs.getRes()) {
//                    if (bean.getEid().equals(resBean.getId() + "")) {
//                        if (!resBean.getCheck_server().equals("0") && !roomMessageBean.getSenderUserId().equals("11")) {
//                            return;
//                        }
//                    }
//                }
                doThings.expressionAnimation(bean);
                break;
            case ROOM_IM_30045:
                doThings.userOUt(bean);
                if (bean.getUid().equals(UserManager.user_id)) {
                    // TODO: 2019/12/2  去登录界面重新登录
                   // LauncherActivity.goToMainActivityTYPE(ErBanApp.instance.onResumedAcitivity, "room_out", bean.getSot());
                    RoomManager.getInstance().leaveRoom(1);
                }
                break;
            case ROOM_IM_30046:
                doThings.setHeiMingDang(bean, true);
                break;
            case ROOM_IM_30047:
                doThings.setHeiMingDang(bean, false);

                break;
            case ROOM_IM_30048:
                if (bean.getUid().equals(UserManager.user_id)) {
                    return;
                }
                doThings.addUser(bean);
                break;
            case ROOM_IM_30049:
                doThings.remUser(bean);

                break;
            case ROOM_IM_30050:
                doThings.bianjPIC(bean);

                break;
            case ROOM_IM_30051:
                doThings.bianjName(bean);
                break;
            case ROOM_IM_30052:
                doThings.bianjGonggao(bean);
                break;
            case ROOM_IM_30053:
                doThings.setGuanLiYuan(bean, true);
                break;
            case ROOM_IM_30054:
                doThings.setGuanLiYuan(bean, false);
                break;

            case ROOM_IM_30055:
                doThings.setUserGiftJB(bean, true, true);
                break;
            case ROOM_IM_30057:
                doThings.setUserGiftJB(bean, true, false);
                break;


            case ROOM_IM_30056:

                doThings.setUserGiftJB(bean, false, true);

                break;
            case ROOM_IM_30058:
                doThings.setUserGiftJB(bean, false, false);

                break;


            case ROOM_IM_30070:
                doThings.setUserGiftPearlJB(bean, true, true);
                break;
            case ROOM_IM_30072:
                doThings.setUserGiftPearlJB(bean, true, false);
                break;


            case ROOM_IM_30071:

                doThings.setUserGiftPearlJB(bean, false, true);

                break;
            case ROOM_IM_30073:
                doThings.setUserGiftPearlJB(bean, false, false);

                break;
            case ROOM_IM_30074:
                doThings.changeTopic(bean);
                break;
            case ROOM_IM_30075:
                doThings.shouHuOpen(bean);
                break;

            case ROOM_IM_30061:
                doThings.baoShangMKF(bean);
                break;

            case ROOM_IM_30062:
                doThings.setToushi(bean, true);
                break;
            case ROOM_IM_30063:
                doThings.setToushi(bean, false);
                break;
            case ROOM_IM_30064:
                doThings.roomSetChatPingMu();
                break;
            case ROOM_IM_30065:
                doThings.roomSetPassWord();
                break;
            case ROOM_IM_30066:
                doThings.RoomClose();
                break;
            case ROOM_IM_30067:
                doThings.RoomVolumeClose(bean);
                break;
            case ROOM_IM_30068:
                doThings.hotChange(bean);
                break;
            case ROOM_IM_30069:
                doThings.zaDanMess(bean);
                break;
            case ROOM_IM_30081:
                doThings.changgeRoomActionType();
                break;
            case ROOM_IM_30082:
                doThings.changePKLeRiNum(bean);
                break;
            case ROOM_IM_30083:
                doThings.setPKcfContent(bean);
                break;
            case ROOM_IM_30084:
                doThings.setPKState(bean);
                break;
            case ROOM_IM_30085:
                doThings.setPKPublis(bean);
                break;
            case ROOM_IM_11001:
                doThings.showXingDongPublic(bean);
                break;


        }


    }

    private void changgeLoacRoombean(RoomImMessageBean bean) {
        if (RoomManager.getInstance().getmRoomBean() != null) {

            switch (bean.getK()) {
                case ROOM_IM_20004:

                    Long wealth_balance = new Long(bean.getWc());
                    Long rwc = new Long(bean.getRwc());
                    if (bean.getFid().equals(UserManager.user_id)) {
                        UserManager.getInstance().getUserBean().setWealth_balance(wealth_balance);
                    }
                    RoomBean roomBean = RoomManager.getInstance().getmRoomBean();
                    roomBean.setWealth_value(rwc);

                    break;
                case ROOM_IM_20005:

                    Long bc = new Long(bean.getBc());
                    if (bean.getFid().equals(UserManager.user_id)) {
                        UserManager.getInstance().getUserBean().setCelebrity_balance(bc);
                    }
                    ;

                    break;
                case ROOM_IM_30021:
                    RoomManager.getInstance().getmRoomBean().getRoomTallyInfo().setOpened(new Integer(bean.getOpened()));
                    break;
                case ROOM_IM_30002:
                    RoomManager.getInstance().getmRoomBean().setClose_timestamp(0);
                    break;
                case ROOM_IM_30004:
                    RoomManager.getInstance().getmRoomBean().setClose_timestamp(new Long(bean.getCts()));
                    RoomManager.getInstance().beginDJS();
                    break;
                case ROOM_IM_30024:
                    RoomManager.getInstance().getmRoomBean().setMelee_opened(new Integer(bean.getOpened()));
                    break;
                case ROOM_IM_30043:
                    RoomManager.getInstance().getmRoomBean().setBackground_pic(bean.getUrl());
                    break;
                case ROOM_IM_30046:
                    if (bean.getUid().equals(UserManager.user_id)) {
                        RoomManager.getInstance().getmRoomBean().setIs_block(true);
                    }

                    break;
                case ROOM_IM_30047:
                    if (bean.getUid().equals(UserManager.user_id)) {
                        RoomManager.getInstance().getmRoomBean().setIs_block(false);

                    }
                    break;
                case ROOM_IM_30048:
                    if (!bean.getUid().equals(UserManager.user_id))
                        RoomManager.getInstance().getmRoomBean().setOnline_count(new Integer(bean.getRuc()));
                    break;
                case ROOM_IM_30049:
//                    if (!bean.getUid().equals(UserManager.user_id))
//                        RoomManager.getInstance().getmRoomBean().setOnline_count(RoomManager.getInstance().getmRoomBean().getOnline_count() - 1);
                    RoomManager.getInstance().getmRoomBean().setOnline_count(new Integer(bean.getRuc()));

                    break;

                case ROOM_IM_30050:
                    RoomManager.getInstance().getmRoomBean().setImage(bean.getImg());

                    break;
                case ROOM_IM_30051:
                    RoomManager.getInstance().getmRoomBean().setName(bean.getN());

                    break;
                case ROOM_IM_30052:
                    RoomManager.getInstance().getmRoomBean().setNotice(bean.getN());
                    break;
                case ROOM_IM_30053:
                    if (bean.getUid().equals(UserManager.user_id)) {

                        RoomManager.getInstance().getmRoomBean().setUser_role(1);
                    }
                    break;
                case ROOM_IM_30054:
                    if (bean.getUid().equals(UserManager.user_id)) {

                        RoomManager.getInstance().getmRoomBean().setUser_role(0);
                    }
                    break;
                case ROOM_IM_30055:
                case ROOM_IM_30057:

                    break;


                case ROOM_IM_30056:
                case ROOM_IM_30058:
                    break;
                case ROOM_IM_30064:
                    RoomManager.getInstance().getmRoomBean().setScreen_opened(new Integer(bean.getOpened()));
                    break;
                case ROOM_IM_30065:
                    RoomManager.getInstance().getmRoomBean().setPassword(new Integer(bean.getPwd()));

                    break;
                case ROOM_IM_30067:
                    RoomManager.getInstance().getmRoomBean().setVolume_closed(new Integer(bean.getClosed()));
                    break;
                case ROOM_IM_30068:
                    RoomManager.getInstance().getmRoomBean().setHot(new Double(bean.getHot()));

                    break;
                case ROOM_IM_30075:
                    Long rwcs = new Long(bean.getRwc());
                    RoomManager.getInstance().getmRoomBean().setWealth_value(rwcs);
                    break;
                case ROOM_IM_30078:
                    RoomManager.getInstance().getmRoomBean().setTurntable_opened(new Integer(bean.getState()));
                    break;
                case ROOM_IM_30081:
                    RoomBean roomBean1 = RoomManager.getInstance().getmRoomBean();
                    roomBean1.setHeart(bean.getRoom().getHeart());
                    roomBean1.setMelee(bean.getRoom().getMelee());
                    roomBean1.setTally(bean.getRoom().getTally());
                    roomBean1.setPk(bean.getRoom().getPk());
                    roomBean1.setHeart_opened(bean.getRoom().getHeart_opened());
                    roomBean1.setMelee_opened(bean.getRoom().getMelee_opened());
//                    roomBean1.setTally(bean.getRoom().getTally_opened());
                    roomBean1.setPk_opened(bean.getRoom().getPk_opened());
                    roomBean1.setAction_type(new Integer(bean.getAction_type()));
                    break;


            }
        }
    }


    public static UserBean imMessToUser(RoomImMessageBean bea) {
        UserBean userBean = new UserBean();

        if (!Utlis.isEmp(bea.getMid()))
            userBean.setMid(bea.getMid());
        if (!Utlis.isEmp(bea.getUid()))
            userBean.setUser_id(bea.getUid());
        if (!Utlis.isEmp(bea.getNn()))
            userBean.setNick_name(bea.getNn());
        if (!Utlis.isEmp(bea.getImg()))
            userBean.getImages().add(bea.getImg());
        if (!Utlis.isEmp(bea.getBt()))
            userBean.setBirthday(new Long(bea.getBt()));
        if (!Utlis.isEmp(bea.getFbt()))
            userBean.setBirthday(new Long(bea.getFbt()));
        if (!Utlis.isEmp(bea.getSex()))
            userBean.setGender(new Integer(bea.getSex()));
        if (!Utlis.isEmp(bea.getFsex()))
            userBean.setGender(new Integer(bea.getFsex()));
        if (!Utlis.isEmp(bea.getGt()))
            userBean.setGuard_type(new Integer(bea.getGt()));
        if (!Utlis.isEmp(bea.getWc()))
            userBean.setWealth_balance(new Long(bea.getWc()));
        if (!Utlis.isEmp(bea.getW()))
            userBean.setWaiter(new Integer(bea.getW()));
        if (!Utlis.isEmp(bea.getCc()))
            userBean.setCharm_balance(new Long(bea.getCc()));
        if (!Utlis.isEmp(bea.getCres())) {
            DiyMallBean.DiyItemBean diyItemBean = new DiyMallBean.DiyItemBean();
            diyItemBean.setGoods_type(1);
            diyItemBean.setAnimate_resource(bea.getCres());
            userBean.getAttires().add(diyItemBean);
        }
        if (!Utlis.isEmp(bea.getL())) {
            userBean.setLiang(new Integer(bea.getL()));
        }
        if (!Utlis.isEmp(bea.getNov())) {
            userBean.setNovice(new Integer(bea.getNov()));
        }
        if (!Utlis.isEmp(bea.getRc())) {
            userBean.setReputation_balance(new Long(bea.getRc()));
        }

        if (!Utlis.isEmp(bea.getBc())) {
            userBean.setCelebrity_balance(new Long(bea.getBc()));
        }

        if (bea.getGs() != null){
            List<UserBean.GuardAttributeBean> guardBeanList = new ArrayList<>();
            for (int i = 0; i < bea.getGs().size(); i++) {
                UserBean.GuardAttributeBean guardBean = new UserBean.GuardAttributeBean();
                guardBean.setName(bea.getGs().get(i).getName());
                guardBean.setGuard_img(bea.getGs().get(i).getGuard_img());
                guardBean.setGuard_small_img(bea.getGs().get(i).getGuard_small_img());
                guardBean.setExpire_time(bea.getGs().get(i).getExpire_time());
                guardBean.setRemain_time(bea.getGs().get(i).getRemain_time());
                guardBean.setType(bea.getGs().get(i).getType());
                guardBeanList.add(guardBean);
            }

            userBean.setGuard_attribute(guardBeanList);
        }
        userBean.setMedal(bea.getMedal());
        userBean.setV(bea.getV());
        return userBean;
    }


    public interface OnRoomIMDoThings {

        void onSystemMessage(RoomImMessageBean bean);

        void onReceveGift(RoomImMessageBean bean);

        void onReceveGiftPearl(RoomImMessageBean bean);

        void refChatAdapter(RoomImMessageBean bean);

        void zcMUp_Out(UserBean bean, boolean is);

        void zcMOut_zl(RoomImMessageBean bean, boolean is);

        void ptM_Up_Out_zl(RoomImMessageBean bean, boolean is);
        void ptM_Out_zl(RoomImMessageBean bean);


        void ptMUp_Out(UserBean bea, boolean is);

        void ptMJY(RoomImMessageBean bean, boolean is);

        void ptMLB(RoomImMessageBean bean, boolean is);

        void ptMBM_zl(RoomImMessageBean bean, boolean is);

        void ptMBM(RoomImMessageBean bean, boolean is);

        void zcMBM(RoomImMessageBean bean, boolean is);

        void openXinDong(RoomImMessageBean bean);

        void closeXinDong(RoomImMessageBean bean);

        void againXinDong(RoomImMessageBean bean);

        void upDaXinDong(RoomImMessageBean bean);


        void addPM(UserBean userBean);

        void removePM(UserBean userBean);

        void clearPM();

        void changeRoomBack(RoomImMessageBean bean);

        void expressionAnimation(RoomImMessageBean bean);

        void userOUt(RoomImMessageBean bean);

        void setHeiMingDang(RoomImMessageBean bean, boolean is);

        void setGuanLiYuan(RoomImMessageBean bean, boolean is);

        void bianjPIC(RoomImMessageBean bean);

        void bianjName(RoomImMessageBean bean);

        void bianjGonggao(RoomImMessageBean bean);

        void addUser(RoomImMessageBean bean);

        void remUser(RoomImMessageBean bean);


        void setUserGiftJB(RoomImMessageBean bean, boolean is, boolean is_zc);

        void setUserGiftPearlJB(RoomImMessageBean bean, boolean is, boolean is_zc);


        void baoShangMKF(RoomImMessageBean bean);

        void setToushi(RoomImMessageBean bean, boolean is);

        void RoomClose();

        void RoomVolumeClose(RoomImMessageBean bean);

        void shouHuOpen(RoomImMessageBean bean);

        void changeTopic(RoomImMessageBean bean);

        void roomSetPassWord();

        void roomSetChatPingMu();

        void addXdSelect(RoomImMessageBean bean);

        //倒计时
        void setDaoJiTime(long time);

        void hotChange(RoomImMessageBean bean);
        void refMeetOpen();
        void changgeRoomActionType();

        void zaDanMess(RoomImMessageBean bean);
        void changePKLeRiNum(RoomImMessageBean bean);
        void setPKcfContent(RoomImMessageBean bean);
        void setPKState(RoomImMessageBean bean);
        void setPKPublis(RoomImMessageBean bean);
        void showXingDongPublic(RoomImMessageBean bean);

        void showRedStartMess(RoomImMessageBean bean);
        void dZPState(RoomImMessageBean bean);
        void dZPRefush(RoomImMessageBean bean);
        void dZPPublish(RoomImMessageBean bean);


    }
}
