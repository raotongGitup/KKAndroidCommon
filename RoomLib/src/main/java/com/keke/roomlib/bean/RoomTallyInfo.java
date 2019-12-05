package com.keke.roomlib.bean;



import java.util.ArrayList;

public class RoomTallyInfo {

    /**
     * opened : 1
     * tally_data : {"host_micro":0,"micro":[{"micro":0,"val":0},{"micro":1,"val":0},{"micro":2,"val":0},{"micro":3,"val":0},{"micro":4,"val":0},{"micro":5,"val":0},{"micro":6,"val":0},{"micro":7,"val":0}]}
     */

    private int opened;
    private ArrayList<TallyDataBean> tally_data;
    private RoomImMessageBean.MaxSendTallyDataBean max_send_tally_data;
    /**
     * pk_data : {"left":0,"right":0}
     */

    private PkDataBean pk_data;

    public RoomImMessageBean.MaxSendTallyDataBean getMax_send_tally_data() {
        return max_send_tally_data;
    }

    public void setMax_send_tally_data(RoomImMessageBean.MaxSendTallyDataBean max_send_tally_data) {
        this.max_send_tally_data = max_send_tally_data;
    }

    public int getOpened() {
        return opened;
    }

    public void setOpened(int opened) {
        this.opened = opened;
    }

    public ArrayList<TallyDataBean> getTally_data() {
        return tally_data;
    }

    public void setTally_data(ArrayList<TallyDataBean> tally_data) {
        this.tally_data = tally_data;
    }

    public PkDataBean getPk_data() {
        return pk_data;
    }

    public void setPk_data(PkDataBean pk_data) {
        this.pk_data = pk_data;
    }

    public static class TallyDataBean {

        /**
         * user_id : 1
         * val : 0
         */

        private String user_id;
        private long val;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public long getVal() {
            return val;
        }

        public void setVal(long val) {
            this.val = val;
        }
    }


    public static class PkDataBean {
        /**
         * left : 0
         * right : 0
         */

        private String left;
        private String right;
        private String punish;

        public String getPunish() {
            return punish;
        }

        public void setPunish(String punish) {
            this.punish = punish;
        }

        public String getLeft() {
            return left;
        }

        public void setLeft(String left) {
            this.left = left;
        }

        public String getRight() {
            return right;
        }

        public void setRight(String right) {
            this.right = right;
        }
    }
}
