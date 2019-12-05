package com.keke.roomlib.bean;

import java.util.ArrayList;

public class HeartMoShiBean {

    /**
     * state : 3
     * select_time : 1560000000
     * select_data : [{"micro":0,"smicro":5},{"micro":1,"smicro":null},{"micro":2,"smicro":null},{"micro":3,"smicro":null},{"micro":4,"smicro":null},{"micro":5,"smicro":1},{"micro":6,"smicro":null},{"micro":7,"smicro":null}]
     */

    private int state;
    private long select_time;
    private ArrayList<SelectDataBean> select_data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public long getSelect_time() {
        return select_time;
    }

    public void setSelect_time(long select_time) {
        this.select_time = select_time;
    }

    public ArrayList<SelectDataBean> getSelect_data() {
        return select_data;
    }

    public void setSelect_data(ArrayList<SelectDataBean> select_data) {
        this.select_data = select_data;
    }

    public static class SelectDataBean {

        /**
         * user_id : 0
         * select_user_id : 5
         */

        private String user_id;
        private String nickname;
        private String gender;
        private String select_nickname;
        private String select_gender;
        private String select_user_id;
        private boolean is_select=false;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getSelect_nickname() {
            return select_nickname;
        }

        public void setSelect_nickname(String select_nickname) {
            this.select_nickname = select_nickname;
        }

        public String getSelect_gender() {
            return select_gender;
        }

        public void setSelect_gender(String select_gender) {
            this.select_gender = select_gender;
        }

        public boolean isIs_select() {
            return is_select;
        }

        public void setIs_select(boolean is_select) {
            this.is_select = is_select;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getSelect_user_id() {
            return select_user_id;
        }

        public void setSelect_user_id(String select_user_id) {
            this.select_user_id = select_user_id;
        }
    }
}
