package com.keke.roomlib.bean;



import com.keke.roomlib.base.RoomImManager;

import java.io.Serializable;
import java.util.ArrayList;

public class RoomBean implements Serializable {

    /**
     * room_id :
     * room_uid :
     * name :
     * notice :
     * image :
     * room_type :
     * wealth_value : 1100
     * host : {"user_id":"","nickname":"","image":"","gender":"","birthday":"","wealth_value":""}
     * user_role : 1
     * is_collect : YES
     * room_action_type : 0
     * online_count : 10
     */

    private String room_id;
    private String room_uid;
    private String name;
    private String notice;
    private String image;
    private String agora_token;
    private String desc="";
    /**
     * liang : 0
     * tag_id : 6
     * tag_name : 小品
     * tag_color : #0FBFFF
     */

    private int liang;
    private int tag_id;
    private int turntable;
    private int has_union;
    private int turntable_opened;
    private String tag_name;
    private String tag_color;

    private int pk;
    private int pk_opened;

    public int getHas_union() {
        return has_union;
    }

    public void setHas_union(int has_union) {
        this.has_union = has_union;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public int getPk_opened() {
        return pk_opened;
    }

    public void setPk_opened(int pk_opened) {
        this.pk_opened = pk_opened;
    }

    public double getHot() {
        return hot;
    }

    public void setHot(double hot) {
        this.hot = hot;
    }

    private double hot;
    private String background_pic;
    private String youme_token;
    private RoomTypeBean room_type;
    private long wealth_value;
    private long close_timestamp=0;
    private UserBean host;
    private int user_role;
    private int free_micro;
    private int mode_type;
    private int heart=0;
    private boolean is_collect;
    private boolean is_block;
    private boolean opened;
    private boolean allow_open;
    private int room_action_type;
    private int online_count;
    private int role_type;
    private int screen_opened;
    private int password;
    private int volume_closed;
    private int accompany_tag;
    private String chat_mode_switch="0";
    private String good_audio_mode_switch="0";
    private String great_audio_mode_switch="0";
    private String game_audio_mode_switch="0";

    // TODO: 2019/11/30  房间
    private RoomImManager roomImManager;
    private int business_type;
    private int room_image_examine_status;
    private int room_tag_id;
    private int melee;
    private int tally;
    private int heart_opened;
    private int melee_opened;
    private String room_image_examine;
    private String topic;
    private String union_id;
    private ArrayList<TagBean> room_tag_list;
    private RoomTallyInfo roomTallyInfo;
    private UserBean.UserPropBean user_prop;

    public UserBean.UserPropBean getUser_prop() {
        return user_prop;
    }

    public void setUser_prop(UserBean.UserPropBean user_prop) {
        this.user_prop = user_prop;
    }

    public String getGame_audio_mode_switch() {
        return game_audio_mode_switch;
    }

    public void setGame_audio_mode_switch(String game_audio_mode_switch) {
        this.game_audio_mode_switch = game_audio_mode_switch;
    }

    public int getHeart_opened() {
        return heart_opened;
    }

    public void setHeart_opened(int heart_opened) {
        this.heart_opened = heart_opened;
    }

    public RoomTallyInfo getRoomTallyInfo() {
        return roomTallyInfo;
    }

    public void setRoomTallyInfo(RoomTallyInfo roomTallyInfo) {
        this.roomTallyInfo = roomTallyInfo;
    }

    public int getTurntable() {
        return turntable;
    }

    public void setTurntable(int turntable) {
        this.turntable = turntable;
    }

    public int getTurntable_opened() {
        return turntable_opened;
    }

    public void setTurntable_opened(int turntable_opened) {
        this.turntable_opened = turntable_opened;
    }

    public int getMelee() {
        return melee;
    }

    public void setMelee(int melee) {
        this.melee = melee;
    }

    public int getTally() {
        return tally;
    }

    public void setTally(int tally) {
        this.tally = tally;
    }

    public int getMelee_opened() {
        return melee_opened;
    }

    public void setMelee_opened(int melee_opened) {
        this.melee_opened = melee_opened;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getRoom_image_examine_status() {
        return room_image_examine_status;
    }

    public void setRoom_image_examine_status(int room_image_examine_status) {
        this.room_image_examine_status = room_image_examine_status;
    }

    public String getUnion_id() {
        return union_id;
    }

    public void setUnion_id(String union_id) {
        this.union_id = union_id;
    }

    public int getRoom_tag_id() {
        return room_tag_id;
    }

    public void setRoom_tag_id(int room_tag_id) {
        this.room_tag_id = room_tag_id;
    }

    public String getRoom_image_examine() {
        return room_image_examine;
    }

    public void setRoom_image_examine(String room_image_examine) {
        this.room_image_examine = room_image_examine;
    }

    public ArrayList<TagBean> getRoom_tag_list() {
        return room_tag_list;
    }

    public void setRoom_tag_list(ArrayList<TagBean> room_tag_list) {
        this.room_tag_list = room_tag_list;
    }

    public int getBusiness_type() {
        return business_type;
    }

    public void setBusiness_type(int business_type) {
        this.business_type = business_type;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public int getAccompany_tag() {
        return accompany_tag;
    }

    public void setAccompany_tag(int accompany_tag) {
        this.accompany_tag = accompany_tag;
    }

    public String getChat_mode_switch() {
        return chat_mode_switch;
    }

    public void setChat_mode_switch(String chat_mode_switch) {
        this.chat_mode_switch = chat_mode_switch;
    }

    public String getGood_audio_mode_switch() {
        return good_audio_mode_switch;
    }

    public void setGood_audio_mode_switch(String good_audio_mode_switch) {
        this.good_audio_mode_switch = good_audio_mode_switch;
    }

    public String getGreat_audio_mode_switch() {
        return great_audio_mode_switch;
    }

    public void setGreat_audio_mode_switch(String great_audio_mode_switch) {
        this.great_audio_mode_switch = great_audio_mode_switch;
    }

    public int getVolume_closed() {
        return volume_closed;
    }

    public void setVolume_closed(int volume_closed) {
        this.volume_closed = volume_closed;
    }

    public int getScreen_opened() {
        return screen_opened;
    }

    public void setScreen_opened(int screen_opened) {
        this.screen_opened = screen_opened;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getFree_micro() {
        return free_micro;
    }

    public void setFree_micro(int free_micro) {
        this.free_micro = free_micro;
    }

    public String getYoume_token() {
        return youme_token;
    }

    public void setYoume_token(String youme_token) {
        this.youme_token = youme_token;
    }

    public long getClose_timestamp() {
        return close_timestamp;
    }

    public void setClose_timestamp(long close_timestamp) {
        this.close_timestamp = close_timestamp;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public boolean isAllow_open() {
        return allow_open;
    }

    public void setAllow_open(boolean allow_open) {
        this.allow_open = allow_open;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCertified() {
        return certified;
    }

    public void setCertified(int certified) {
        this.certified = certified;
    }

    private  int  certified;

    public int getMode_type() {
        return mode_type;
    }

    public void setMode_type(int mode_type) {
        this.mode_type = mode_type;
    }

    public double getRank_value() {
        return rank_value;
    }

    public void setRank_value(double rank_value) {
        this.rank_value = rank_value;
    }

    private  double rank_value;

    public int getRole_type() {
        return role_type;
    }

    public void setRole_type(int role_type) {
        this.role_type = role_type;
    }

    /**
     * room_id : 32
     * action_type : 0
     * room_tag : nulls
     * user_id : 10
     * create_timestamp : 0
     */


    private int action_type;
    private String room_tag;
    private int user_id;
    private int create_timestamp;
    private Long total_wealth_value;

    public Long getTotal_wealth_value() {
        return total_wealth_value;
    }

    public void setTotal_wealth_value(Long total_wealth_value) {
        this.total_wealth_value = total_wealth_value;
    }

    public Long getTotal_charm_value() {
        return total_charm_value;
    }

    public void setTotal_charm_value(Long total_charm_value) {
        this.total_charm_value = total_charm_value;
    }

    public Long getDevote_diamond_total() {
        return devote_diamond_total;
    }

    public void setDevote_diamond_total(Long devote_diamond_total) {
        this.devote_diamond_total = devote_diamond_total;
    }

    private Long total_charm_value;
    private Long devote_diamond_total;  //房间内的富豪榜


    // TODO: 2019/11/30  房间manager
    public RoomImManager getRoomImManager() {
        return roomImManager;
    }

    public void setRoomImManager(RoomImManager roomImManager) {
        this.roomImManager = roomImManager;
    }

    public String getBackground_pic() {
        return background_pic;
    }

    public void setBackground_pic(String background_pic) {
        this.background_pic = background_pic;
    }

    public boolean isIs_block() {
        return is_block;
    }

    public void setIs_block(boolean is_block) {
        this.is_block = is_block;
    }

    public RoomTypeBean getRoom_type() {
        return room_type;
    }

    public void setRoom_type(RoomTypeBean room_type) {
        this.room_type = room_type;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom_uid() {
        return room_uid;
    }

    public void setRoom_uid(String room_uid) {
        this.room_uid = room_uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getWealth_value() {
        return wealth_value;
    }

    public void setWealth_value(long wealth_value) {
        this.wealth_value = wealth_value;
    }

    public UserBean getHost() {
        return host;
    }

    public void setHost(UserBean host) {
        this.host = host;
    }

    public int getUser_role() {
        return user_role;
    }

    public void setUser_role(int user_role) {
        this.user_role = user_role;
    }

    public boolean isIs_collect() {
        return is_collect;
    }

    public void setIs_collect(boolean is_collect) {
        this.is_collect = is_collect;
    }

    public String getAgora_token() {
        return agora_token;
    }

    public void setAgora_token(String agora_token) {
        this.agora_token = agora_token;
    }

    public int getRoom_action_type() {
        return room_action_type;
    }

    public void setRoom_action_type(int room_action_type) {
        this.room_action_type = room_action_type;
    }

    public int getOnline_count() {
        return online_count;
    }

    public void setOnline_count(int online_count) {
        this.online_count = online_count;
    }



    public int getAction_type() {
        return action_type;
    }

    public void setAction_type(int action_type) {
        this.action_type = action_type;
    }

    public String getRoom_tag() {
        return room_tag;
    }

    public void setRoom_tag(String room_tag) {
        this.room_tag = room_tag;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCreate_timestamp() {
        return create_timestamp;
    }

    public void setCreate_timestamp(int create_timestamp) {
        this.create_timestamp = create_timestamp;
    }


    public int getLiang() {
        return liang;
    }

    public void setLiang(int liang) {
        this.liang = liang;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getTag_color() {
        return tag_color;
    }

    public void setTag_color(String tag_color) {
        this.tag_color = tag_color;
    }
}
