package com.keke.roomlib.bean;

import java.io.Serializable;

public class RoomTypeBean implements Serializable {

    /**
     * type_id : 0
     * type_name : 普通
     * img_home_url :
     * img_room_url : /room/type_imgs/0-room.png
     * img_roomedit_url : /room/type_imgs/0-edit.png
     */

    private int id;
    private String type_name;
    private String img_home_url;
    private String img_room_url;
    private String img_roomedit_url;
    private boolean allow_setting=false;
    public String getImg_cbd_url() {
        return img_cbd_url;
    }

    public void setImg_cbd_url(String img_cbd_url) {
        this.img_cbd_url = img_cbd_url;
    }

    private  String img_cbd_url;

    public boolean isAllow_setting() {
        return allow_setting;
    }

    public void setAllow_setting(boolean allow_setting) {
        this.allow_setting = allow_setting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getImg_home_url() {
        return img_home_url;
    }

    public void setImg_home_url(String img_home_url) {
        this.img_home_url = img_home_url;
    }

    public String getImg_room_url() {
        return img_room_url;
    }

    public void setImg_room_url(String img_room_url) {
        this.img_room_url = img_room_url;
    }

    public String getImg_roomedit_url() {
        return img_roomedit_url;
    }

    public void setImg_roomedit_url(String img_roomedit_url) {
        this.img_roomedit_url = img_roomedit_url;
    }
}
