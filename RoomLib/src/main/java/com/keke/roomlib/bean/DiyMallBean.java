package com.keke.roomlib.bean;

import java.io.Serializable;
import java.util.List;

public class DiyMallBean {

    /**
     * list : [{"goods_id":1,"goods_name":"测试测试","goods_type":1,"small_img":"http://kkaudio.oss-cn-beijing.aliyuncs.com/a.jpg","big_img":"","animate_resource":"","price":"12","sku_id":1,"is_new":1,"time_limit":30,"sku_list":[{"sku_id":2,"time_limit":15,"price":"9"},{"sku_id":1,"time_limit":30,"price":"12"}]},{"goods_id":4,"goods_name":"测试4","goods_type":1,"small_img":"","big_img":"","animate_resource":"","price":"12","sku_id":7,"is_new":1,"time_limit":20,"sku_list":[{"sku_id":8,"time_limit":10,"price":"5"},{"sku_id":7,"time_limit":20,"price":"12"}]}]
     * page_index : 0
     * page_count : 2
     * page_size : 10
     */

    private int page_index;
    private int page_count;
    private int page_size;
    private List<DiyItemBean> list;

    public int getPage_index() {
        return page_index;
    }

    public void setPage_index(int page_index) {
        this.page_index = page_index;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public List<DiyItemBean> getList() {
        return list;
    }

    public void setList(List<DiyItemBean> list) {
        this.list = list;
    }

    public static class DiyItemBean implements Serializable {
        /**
         * goods_id : 1
         * goods_name : 测试测试
         * goods_type : 1
         * small_img : http://kkaudio.oss-cn-beijing.aliyuncs.com/a.jpg
         * big_img :
         * animate_resource :
         * price : 12
         * sku_id : 1
         * is_new : 1
         * time_limit : 30
         * sku_list : [{"sku_id":2,"time_limit":15,"price":"9"},{"sku_id":1,"time_limit":30,"price":"12"}]
         */


        private int goods_id;
        private String goods_name;
        private int goods_type;
        private String small_img;
        private String big_img;
        private String animate_resource;
        private String price;
        private String animate_resource_md5;
        private String animate_resource_small;
        private String msg;
        private int sku_id;
        private int prop_id;
        private int is_new;

        public String getAnimate_resource_small() {
            return animate_resource_small;
        }

        public void setAnimate_resource_small(String animate_resource_small) {
            this.animate_resource_small = animate_resource_small;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getProp_id() {
            return prop_id;
        }

        public void setProp_id(int prop_id) {
            this.prop_id = prop_id;
        }

        public String getAnimate_resource_md5() {
            return animate_resource_md5;
        }

        public void setAnimate_resource_md5(String animate_resource_md5) {
            this.animate_resource_md5 = animate_resource_md5;
        }

        public int getIs_use() {
            return is_use;
        }

        public void setIs_use(int is_use) {
            this.is_use = is_use;
        }

        private int is_use;
        private int time_limit;
        private List<SkuListBean> sku_list;
        /**
         * id : 2
         * end_timestamp : 1547793949
         */

        private int id;
        private String end_timestamp;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public int getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(int goods_type) {
            this.goods_type = goods_type;
        }

        public String getSmall_img() {
            return small_img;
        }

        public void setSmall_img(String small_img) {
            this.small_img = small_img;
        }

        public String getBig_img() {
            return big_img;
        }

        public void setBig_img(String big_img) {
            this.big_img = big_img;
        }

        public String getAnimate_resource() {
            return animate_resource;
        }

        public void setAnimate_resource(String animate_resource) {
            this.animate_resource = animate_resource;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getSku_id() {
            return sku_id;
        }

        public void setSku_id(int sku_id) {
            this.sku_id = sku_id;
        }

        public int getIs_new() {
            return is_new;
        }

        public void setIs_new(int is_new) {
            this.is_new = is_new;
        }

        public int getTime_limit() {
            return time_limit;
        }

        public void setTime_limit(int time_limit) {
            this.time_limit = time_limit;
        }

        public List<SkuListBean> getSku_list() {
            return sku_list;
        }

        public void setSku_list(List<SkuListBean> sku_list) {
            this.sku_list = sku_list;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        public String getEnd_timestamp() {
            return end_timestamp;
        }

        public void setEnd_timestamp(String end_timestamp) {
            this.end_timestamp = end_timestamp;
        }

        public static class SkuListBean implements Serializable{
            /**
             * sku_id : 2
             * time_limit : 15
             * price : 9
             */

            private int sku_id;
            private int time_limit;
            private String price;

            public int getSku_id() {
                return sku_id;
            }

            public void setSku_id(int sku_id) {
                this.sku_id = sku_id;
            }

            public int getTime_limit() {
                return time_limit;
            }

            public void setTime_limit(int time_limit) {
                this.time_limit = time_limit;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
