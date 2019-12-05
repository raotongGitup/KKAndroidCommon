package com.keke.roomlib.base;

public class OneToOneImMessageBean {

    /**
     * k : 12003
     * meet_id : 1111
     * time : 123432432
     */

    private String k;
    private int like_val;
    private String meet_id;
    private long time;
    /**
     * man_user_id : 123
     * woman_user_id : 1234
     * man_user_nickname : 我是男的
     * woman_user_nickname : 我是美女
     * man_user_image : http://www/..jpg
     * woman_user_image : http://www/..jpg
     */

    private String man_user_id;
    private String woman_user_id;
    private String man_user_nickname;
    private String woman_user_nickname;
    private String man_user_image;
    private String woman_user_image;
    /**
     * k : 12003
     * time : 123432432
     * user_id : 111
     * gift_id : 10
     * gift_type : 0
     * gift_name : 哈哈
     * gift_image :
     * gift_animate_resource :
     */

    private String user_id;
    private String gift_id;
    private int gift_type;
    private String gift_name;
    private String gift_image;
    private String gift_animate_resource;


    public int getLike_val() {
        return like_val;
    }

    public void setLike_val(int like_val) {
        this.like_val = like_val;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getMeet_id() {
        return meet_id;
    }

    public void setMeet_id(String meet_id) {
        this.meet_id = meet_id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMan_user_id() {
        return man_user_id;
    }

    public void setMan_user_id(String man_user_id) {
        this.man_user_id = man_user_id;
    }

    public String getWoman_user_id() {
        return woman_user_id;
    }

    public void setWoman_user_id(String woman_user_id) {
        this.woman_user_id = woman_user_id;
    }

    public String getMan_user_nickname() {
        return man_user_nickname;
    }

    public void setMan_user_nickname(String man_user_nickname) {
        this.man_user_nickname = man_user_nickname;
    }

    public String getWoman_user_nickname() {
        return woman_user_nickname;
    }

    public void setWoman_user_nickname(String woman_user_nickname) {
        this.woman_user_nickname = woman_user_nickname;
    }

    public String getMan_user_image() {
        return man_user_image;
    }

    public void setMan_user_image(String man_user_image) {
        this.man_user_image = man_user_image;
    }

    public String getWoman_user_image() {
        return woman_user_image;
    }

    public void setWoman_user_image(String woman_user_image) {
        this.woman_user_image = woman_user_image;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGift_id() {
        return gift_id;
    }

    public void setGift_id(String gift_id) {
        this.gift_id = gift_id;
    }

    public int getGift_type() {
        return gift_type;
    }

    public void setGift_type(int gift_type) {
        this.gift_type = gift_type;
    }

    public String getGift_name() {
        return gift_name;
    }

    public void setGift_name(String gift_name) {
        this.gift_name = gift_name;
    }

    public String getGift_image() {
        return gift_image;
    }

    public void setGift_image(String gift_image) {
        this.gift_image = gift_image;
    }

    public String getGift_animate_resource() {
        return gift_animate_resource;
    }

    public void setGift_animate_resource(String gift_animate_resource) {
        this.gift_animate_resource = gift_animate_resource;
    }
}
