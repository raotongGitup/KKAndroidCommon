package com.keke.roomlib.bean;

import java.util.List;

public class WealthDetailListBean {

    /**
     * list : [{"day":"2019-03-23","total":10000}]
     * total : 10000
     */

    private String total;
    private List<ListBean> list;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * day : 2019-03-23
         * total : 10000
         */

        private String day;
        private String total;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }
}
