package com.keke.roomlib.bean;

import java.io.Serializable;

public class RoomHostInfoBean {

    /**
     * microphone_id : 0
     * volume : true
     * user : {"user_id":"","nickname":"","image":"","gender":"","birthday":""}
     */


    private int microphone_id;
    private boolean volume=true;
    private boolean volume_self=true;
    private UserBean user;
    /**
     * boss : false
     * lock : false
     * gender : 0
     * wealth : 0
     * guard : 0
     */

    private boolean boss;
    private boolean lock;
    private int gender;
    private int wealth;
    private int guard;
    /**
     * gift : {"id":10,"num":1,"user_id":10,"user_nickname":"昵称","user_image":"头像"}
     */

    private JBGiftBean gift;
    private JBGiftBean pearl_gift;

    public JBGiftBean getPeal_gift() {
        return pearl_gift;
    }

    public void setPeal_gift(JBGiftBean peal_gift) {
        this.pearl_gift = peal_gift;
    }

    public boolean isVolume_self() {
        return volume_self;
    }

    public void setVolume_self(boolean volume_self) {
        this.volume_self = volume_self;
    }

    public int getMicrophone_id() {
        return microphone_id;
    }

    public void setMicrophone_id(int microphone_id) {
        this.microphone_id = microphone_id;
    }

    public boolean isVolume() {
        return volume;
    }

    public void setVolume(boolean volume) {
        this.volume = volume;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }


    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getWealth() {
        return wealth;
    }

    public void setWealth(int wealth) {
        this.wealth = wealth;
    }

    public int getGuard() {
        return guard;
    }

    public void setGuard(int guard) {
        this.guard = guard;
    }

    public JBGiftBean getGift() {
        return gift;
    }

    public void setGift(JBGiftBean gift) {
        this.gift = gift;
    }

    public static class JBGiftBean implements Serializable {
        /**
         * id : 10
         * num : 1
         * user_id : 10
         * user_nickname : 昵称
         * user_image : 头像
         */

        private String id;
        private String num;
        private String user_id;
        private String user_nickname;
        private String user_image;




        private String gift_jb_name;
        private String gift_jb_image;


        public String getGift_jb_name() {
            return gift_jb_name;
        }

        public void setGift_jb_name(String gift_jb_name) {
            this.gift_jb_name = gift_jb_name;
        }

        public String getGift_jb_image() {
            return gift_jb_image;
        }

        public void setGift_jb_image(String gift_jb_image) {
            this.gift_jb_image = gift_jb_image;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }

        public String getUser_image() {
            return user_image;
        }

        public void setUser_image(String user_image) {
            this.user_image = user_image;
        }
    }
}
