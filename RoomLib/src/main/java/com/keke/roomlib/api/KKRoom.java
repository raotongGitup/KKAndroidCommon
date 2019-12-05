package com.keke.roomlib.api;

import android.content.Context;

import com.keke.roomlib.api.bean.SongInfo;
import com.keke.roomlib.bean.GiftListBean;
import com.keke.roomlib.bean.InitBean;
import com.keke.roomlib.bean.WealthRankListBean;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.MessageContent;

public class KKRoom {

    private static KKRoom kkRoom;

    public Context mContext;
    private int ic_launcher;


    
    public String AGORA_APPKEY;
    public String RONGYUN_APPKEY;
    public List<SongInfo> songInfos;  //音乐列表
    public SongInfo playIngSong;      //正在播放的音乐
    public boolean isPlaingSong = false;
    public int mPlayMusicType = 0;      // 0 顺序播放 1随机播放 -1单曲循环





    public GiftListBean giftListBean;
    public GiftListBean giftPearListBean;
    public GiftListBean giftCBDListBean;
    public List<WealthRankListBean.ListBean> wealRankList;
    public List<WealthRankListBean.ListBean> celeRankList;
    public List<WealthRankListBean.ListBean> charmRankList;
    public List<WealthRankListBean.ListBean> peputRankList;

    private List<Class<? extends MessageContent>> registerMessageType = new ArrayList<>();

    private List<IContainerItemProvider.MessageProvider> MessageProvider = new ArrayList<>();
    /**
     * 服务器的地址
     */
    private long timestampCorrection;


    /**
     * 初始化接口惠普调用
     */
    public static InitBean initBean = new InitBean();

    public static KKRoom getInstance() {
        if (kkRoom == null) {
            synchronized (KKRoom.class) {
                if (kkRoom == null) {
                    kkRoom = new KKRoom();
                }
            }
        }
        return kkRoom;
    }

    private void initSDK(Context context, String AGORA_APPKEY, String RONGYUN_APPKEY) {
        this.mContext = context;
        this.RONGYUN_APPKEY = RONGYUN_APPKEY;
        this.AGORA_APPKEY = AGORA_APPKEY;

    }

    public int getIc_launcher() {
        return ic_launcher;
    }

    public void setIc_launcher(int ic_launcher) {
        this.ic_launcher = ic_launcher;
    }

    /**
     * 初始化sdk
     */
    public static void init(Context context, String AGORA_APPKEY, String RONGYUN_APPKEY) {
        KKRoom.getInstance().initSDK(context, AGORA_APPKEY, RONGYUN_APPKEY);


    }

    public long getTimestampCorrection() {
        return timestampCorrection;
    }

    public void setTimestampCorrection(long timestampCorrection) {
        this.timestampCorrection = timestampCorrection;
    }


    public long getNowTime() {
        return System.currentTimeMillis() / 1000 + getTimestampCorrection();
    }
}
