package com.keke.roomlib.base;


import com.keke.roomlib.ani.AnimatioListener;
import com.keke.roomlib.bean.HeartMoShiBean;
import com.keke.roomlib.bean.RoomBean;
import com.keke.roomlib.bean.RoomHostInfoBean;
import com.keke.roomlib.bean.RoomImMessageBean;
import com.keke.roomlib.bean.RoomMessageBean;
import com.keke.roomlib.bean.RoomTallyInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface RoomManagerListener {


    void onShowLoading();

    void onCloseLoading();

    void finishActi();

    void goToOpenLineRoomActivity(RoomBean bean);

    void iniRoom(RoomBean roomBean);
    void refRoom(RoomBean roomBean, ArrayList<RoomHostInfoBean> ptMinfos, boolean is_Live, boolean is_MKF);


    void reOnlin();
    void showCar();
    void refXinDon(int type);
    void refXinDonMess(long time);
    void refIsSelectXind();
    void clearGiftNum(ArrayList<RoomHostInfoBean> ptMinfos);
    void closeGiftNum(ArrayList<RoomHostInfoBean> ptMinfos);
    void refGiftNum(List<RoomTallyInfo.TallyDataBean> micro, ArrayList<RoomHostInfoBean> ptMinfos);


    void reUsPM(HashSet<String> beans);
    void onlineUserVoiceOn_Off(RoomHostInfoBean roomHostInfoBean, String usid, boolean isSpeak);
    void refPTUsers(List<RoomHostInfoBean> beans);
    void refHost(RoomHostInfoBean roomHostInfoBean, ArrayList<RoomHostInfoBean> ptMinfos, RoomBean roomBean);
    void voiceEventnetWorkQuality(RoomMessageBean roomMessageBean);
    void voiceEventnetAudioStats(RoomMessageBean roomMessageBean);

    void showXDChose(ArrayList<HeartMoShiBean.SelectDataBean> dataBeans, ArrayList<RoomHostInfoBean> ptMinfos);
    void showGuiBIngxi(RoomImMessageBean.MaxSendTallyDataBean bean);

    void ptmivMkfChange(boolean is_MKF, boolean isVolume);
    void zcmivMkfChange(boolean is_MKF);
    void onSystemMessage(RoomImMessageBean bean);
    void receveGift(RoomImMessageBean bean, List<RoomImMessageBean.TuBean> tu);
    void showPlayAni(RoomImMessageBean bean);
    void refChatAdapter(RoomImMessageBean bean);
    void showGongGao();

    // TODO: 2019/12/2  没有调用处
  //  void addGpMessage(IMultiItem item);
    void joinRoomSucces();
    void changeRoomBack(RoomImMessageBean bean);
    void expressionAnimation(RoomHostInfoBean roomHostInfoBean, ArrayList<RoomHostInfoBean> ptMinfos, AnimatioListener animatioListener, RoomImMessageBean bean);
    void addUser(RoomImMessageBean bean);
    void remUser(RoomImMessageBean bean);
     void setDaoJiTime(long time);
    void hotChange(RoomImMessageBean bean);
    void zaDanMess(RoomImMessageBean bean);
    void showXingDongPublic(RoomImMessageBean bean, ArrayList<RoomHostInfoBean> ptMinfos);


    void shouHuOpen(RoomImMessageBean bean);
    void refRoomIsSeclect();

    void changeTopic(RoomImMessageBean bean);
    void showRedStartMess(RoomImMessageBean bean);

    void dZPState(RoomImMessageBean bean);
    void dZPRefush(RoomImMessageBean bean);
    void dZPRefushOne();
    void changgeRoomActionType();
    void dZPPublish(RoomImMessageBean bean);
    void refPKLeReNum(String left, String right);

    void setPKcfContent(String bean);
    void setPKState(RoomImMessageBean bean);
    void setPKPublis(RoomImMessageBean bean);


}
