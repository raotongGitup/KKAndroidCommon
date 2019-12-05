package com.keke.roomlib.bean;

import java.util.List;

public class WealthRankListBean {

    /**
     * user : {"user_id":3,"nickname":"","portrait":"http://kkaudio.oss-cn-beijing.aliyuncs.com/a/a.jpg","wealth_balance":"0"}
     * list : [{"rank_name":"富豪","image":"","min_value":100,"max_value":999,"sub_rank":[{"rank_name":"国王","image":"","min_value":100,"max_value":199},{"rank_name":"国王","image":"","min_value":200,"max_value":399},{"rank_name":"国王","image":"","min_value":400,"max_value":699},{"rank_name":"国王","image":"","min_value":700,"max_value":999}]},{"rank_name":"骑士","image":"","min_value":1000,"max_value":2999,"sub_rank":[{"rank_name":"国王","image":"","min_value":0,"max_value":0},{"rank_name":"国王","image":"","min_value":1000,"max_value":1399}]},{"rank_name":"勋爵","image":"","min_value":3000,"max_value":9999},{"rank_name":"男爵","image":"","min_value":10000,"max_value":29999},{"rank_name":"子爵","image":"","min_value":30000,"max_value":59999},{"rank_name":"伯爵","image":"","min_value":60000,"max_value":109999},{"rank_name":"侯爵","image":"","min_value":110000,"max_value":159999},{"rank_name":"公爵","image":"","min_value":160000,"max_value":279999},{"rank_name":"郡公","image":"","min_value":280000,"max_value":479999},{"rank_name":"国公","image":"","min_value":480000,"max_value":799999},{"rank_name":"王爵","image":"","min_value":800000,"max_value":1279999},{"rank_name":"藩王","image":"","min_value":1280000,"max_value":1999999},{"rank_name":"郡王","image":"","min_value":2000000,"max_value":3199999},{"rank_name":"亲王","image":"","min_value":3200000,"max_value":4599999},{"rank_name":"国王","image":"","min_value":4600000,"max_value":6199999},{"rank_name":"皇帝","image":"","min_value":6200000,"max_value":7999999},{"rank_name":"大帝","image":"","min_value":8000000,"max_value":0}]
     */

    private UserBean user;
    private List<ListBean> list;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }


    public static class ListBean {
        /**
         * rank_name : 富豪
         * image :
         * min_value : 100
         * max_value : 999
         * sub_rank : [{"rank_name":"国王","image":"","min_value":100,"max_value":199},{"rank_name":"国王","image":"","min_value":200,"max_value":399},{"rank_name":"国王","image":"","min_value":400,"max_value":699},{"rank_name":"国王","image":"","min_value":700,"max_value":999}]
         */

        private String rank_name;
        private String image;
        private String level;
        private String join_animation;
        private int min_value;
        private int max_value;
        private List<SubRankBean> sub_rank;

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getJoin_animation() {
            return join_animation;
        }

        public void setJoin_animation(String join_animation) {
            this.join_animation = join_animation;
        }

        public String getRank_name() {
            return rank_name;
        }

        public void setRank_name(String rank_name) {
            this.rank_name = rank_name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getMin_value() {
            return min_value;
        }

        public void setMin_value(int min_value) {
            this.min_value = min_value;
        }

        public int getMax_value() {
            return max_value;
        }

        public void setMax_value(int max_value) {
            this.max_value = max_value;
        }

        public List<SubRankBean> getSub_rank() {
            return sub_rank;
        }

        public void setSub_rank(List<SubRankBean> sub_rank) {
            this.sub_rank = sub_rank;
        }

        public static class SubRankBean {
            /**
             * rank_name : 国王
             * image :
             * min_value : 100
             * max_value : 199
             */

            private String rank_name;
            private String image;
            private String small_image;
            private int min_value;
            private int max_value;
            private String level;

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getSmall_image() {
                return small_image;
            }

            public void setSmall_image(String small_image) {
                this.small_image = small_image;
            }

            public String getRank_name() {
                return rank_name;
            }

            public void setRank_name(String rank_name) {
                this.rank_name = rank_name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getMin_value() {
                return min_value;
            }

            public void setMin_value(int min_value) {
                this.min_value = min_value;
            }

            public int getMax_value() {
                return max_value;
            }

            public void setMax_value(int max_value) {
                this.max_value = max_value;
            }
        }
    }
}
