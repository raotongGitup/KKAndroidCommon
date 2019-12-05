package com.keke.roomlib.bean;

import java.util.ArrayList;
import java.util.List;

public class UserHomeBean  {

    /**
     * user : {"user_id":"","phone":"","user_uid":"","nickname":"","birthday":"","affective_state":"","sign":"","home":"","gender":0,"occupation":"","school":"","images":[{"url":""}],"fans_count":10,"follow_count":10}
     * is_follow : true
     * room_list : [{"room_id":"","room_uid":"","room_type":1,"myRoom_type":1,"name":"","image":""}]
     * follow_list : [{"user_id":"","nickname":"","image":"","gender":"","birthday":""}]
     * guard_list : [{"user_id":"","nickname":"","image":"","gender":"","birthday":""}]
     */

    private UserBean user;
    private boolean is_follow;
    private boolean is_block;
    private boolean is_cp;
    private boolean follow_status;
    private int cp_intimate;
    private int medal_user_private_status;
    private String forbid="0";
    private List<RoomBean> room_list=new ArrayList<>();
    private List<UserBean> follow_list=new ArrayList<>();
    private List<UserBean> guard_list=new ArrayList<>();
    private List<DiyMallBean.DiyItemBean> attires = new ArrayList<>();
    private List<DiyMallBean.DiyItemBean> user_rides = new ArrayList<>();
//    private ArrayList<XunZhangBean.XunZhangItemBean> medal=new ArrayList<>();
//
//    public ArrayList<XunZhangBean.XunZhangItemBean> getMedal() {
//        return medal;
//    }

    public int getMedal_user_private_status() {
        return medal_user_private_status;
    }

    public void setMedal_user_private_status(int medal_user_private_status) {
        this.medal_user_private_status = medal_user_private_status;
    }

//    public void setMedal(ArrayList<XunZhangBean.XunZhangItemBean> medal) {
//        this.medal = medal;
//    }

    public String getForbid() {
        return forbid;
    }

    public List<DiyMallBean.DiyItemBean> getUser_rides() {
        return user_rides;
    }


    public void setForbid(String forbid) {
        this.forbid = forbid;
    }

    public void setUser_rides(List<DiyMallBean.DiyItemBean> user_rides) {
        this.user_rides = user_rides;
    }

    public List<DiyMallBean.DiyItemBean> getAttires() {
        return attires;
    }

    public void setAttires(List<DiyMallBean.DiyItemBean> attires) {
        this.attires = attires;
    }

    public UserBean getUser() {
        return user;
    }

    public boolean isIs_block() {
        return is_block;
    }

    public void setIs_block(boolean is_block) {
        this.is_block = is_block;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public boolean isIs_follow() {
        return is_follow;
    }

    public void setIs_follow(boolean is_follow) {
        this.is_follow = is_follow;
    }

    public List<RoomBean> getRoom_list() {
        return room_list;
    }

    public void setRoom_list(List<RoomBean> room_list) {
        this.room_list = room_list;
    }

    public List<UserBean> getFollow_list() {
        return follow_list;
    }

    public void setFollow_list(List<UserBean> follow_list) {
        this.follow_list = follow_list;
    }

    public List<UserBean> getGuard_list() {
        return guard_list;
    }

    public void setGuard_list(List<UserBean> guard_list) {
        this.guard_list = guard_list;
    }

    public boolean isFollow_status() {
        return follow_status;
    }

    public void setFollow_status(boolean follow_status) {
        this.follow_status = follow_status;
    }

    public boolean isIs_cp() {
        return is_cp;
    }

    public void setIs_cp(boolean is_cp) {
        this.is_cp = is_cp;
    }

    public int getCp_intimate() {
        return cp_intimate;
    }

    public void setCp_intimate(int cp_intimate) {
        this.cp_intimate = cp_intimate;
    }
}
