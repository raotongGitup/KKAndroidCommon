package com.keke.roomlib.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class XunZhangBean implements Serializable {

    /**
     * class_name : 成就勋章
     * item : [{"medal_id":1,"medal_name":"勋章名称1","medal_desc":"钻石达标1000","unacquired_state":0,"day":1,"big_img":"big_img","middle_img":"middle_img","small_img":"small_img"}]
     * sub_item : [{"class_name":"财富勋章1","item":[{"medal_id":3,"medal_name":"勋章名称2","medal_desc":"钻石达标1000","unacquired_state":1,"day":0,"big_img":"big_img","middle_img":"middle_img","small_img":"small_img"},{"medal_id":4,"medal_name":"勋章名称8","medal_desc":"钻石达标1000","unacquired_state":1,"day":0,"big_img":"big_img","middle_img":"middle_img","small_img":"small_img"}]},{"class_name":"开心","item":[]},{"class_name":"不开心","item":[]}]
     */

    private String class_name;
    private ArrayList<XunZhangItemBean> item;
    private List<SubItemBean> sub_item;

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public ArrayList<XunZhangItemBean> getItem() {
        return item;
    }

    public void setItem(ArrayList<XunZhangItemBean> item) {
        this.item = item;
    }

    public List<SubItemBean> getSub_item() {
        return sub_item;
    }

    public void setSub_item(List<SubItemBean> sub_item) {
        this.sub_item = sub_item;
    }

    public static class XunZhangItemBean implements Serializable {
        /**
         * medal_id : 1
         * medal_name : 勋章名称1
         * medal_desc : 钻石达标1000
         * unacquired_state : 0
         * day : 1
         * big_img : big_img
         * middle_img : middle_img
         * small_img : small_img
         */

        private String medal_id;
        private String medal_name;
        private String medal_desc;
        private int unacquired_state;
        private long day;
        private int is_enabled;
        private String big_img;
        private String middle_img;
        private String class_id;
        private String acquire_time="";
        private String small_img;
        private String join_animation;

        public String getClass_id() {
            return class_id;
        }

        public void setClass_id(String class_id) {
            this.class_id = class_id;
        }

        public String getJoin_animation() {
            return join_animation;
        }

        public void setJoin_animation(String join_animation) {
            this.join_animation = join_animation;
        }

        public String getAcquire_time() {
            return acquire_time;
        }

        public void setAcquire_time(String acquire_time) {
            this.acquire_time = acquire_time;
        }

        /**
         * big_gray_img : big_gray_img
         * middle_gray_img : middle_gray_img
         * small_gray_img : small_gray_img
         */


        private String big_gray_img;
        private String middle_gray_img;
        private String small_gray_img;


        public String getMedal_id() {
            return medal_id;
        }

        public void setMedal_id(String medal_id) {
            this.medal_id = medal_id;
        }

        public String getMedal_name() {
            return medal_name;
        }

        public void setMedal_name(String medal_name) {
            this.medal_name = medal_name;
        }

        public String getMedal_desc() {
            return medal_desc;
        }

        public void setMedal_desc(String medal_desc) {
            this.medal_desc = medal_desc;
        }

        public int getUnacquired_state() {
            return unacquired_state;
        }

        public void setUnacquired_state(int unacquired_state) {
            this.unacquired_state = unacquired_state;
        }

        public int getIs_enabled() {
            return is_enabled;
        }

        public void setIs_enabled(int is_enabled) {
            this.is_enabled = is_enabled;
        }

        public long getDay() {
            return day;
        }

        public void setDay(long day) {
            this.day = day;
        }

        public String getBig_img() {
            return big_img;
        }

        public void setBig_img(String big_img) {
            this.big_img = big_img;
        }

        public String getMiddle_img() {
            return middle_img;
        }

        public void setMiddle_img(String middle_img) {
            this.middle_img = middle_img;
        }

        public String getSmall_img() {
            return small_img;
        }

        public void setSmall_img(String small_img) {
            this.small_img = small_img;
        }

        public String getBig_gray_img() {
            return big_gray_img;
        }

        public void setBig_gray_img(String big_gray_img) {
            this.big_gray_img = big_gray_img;
        }

        public String getMiddle_gray_img() {
            return middle_gray_img;
        }

        public void setMiddle_gray_img(String middle_gray_img) {
            this.middle_gray_img = middle_gray_img;
        }

        public String getSmall_gray_img() {
            return small_gray_img;
        }

        public void setSmall_gray_img(String small_gray_img) {
            this.small_gray_img = small_gray_img;
        }
    }

    public static class SubItemBean implements Serializable{
        /**
         * class_name : 财富勋章1
         * item : [{"medal_id":3,"medal_name":"勋章名称2","medal_desc":"钻石达标1000","unacquired_state":1,"day":0,"big_img":"big_img","middle_img":"middle_img","small_img":"small_img"},{"medal_id":4,"medal_name":"勋章名称8","medal_desc":"钻石达标1000","unacquired_state":1,"day":0,"big_img":"big_img","middle_img":"middle_img","small_img":"small_img"}]
         */

        private String class_name;
        private List<XunZhangItemBean> item;

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public List<XunZhangItemBean> getItem() {
            return item;
        }

        public void setItem(List<XunZhangItemBean> item) {
            this.item = item;
        }
    }
}
