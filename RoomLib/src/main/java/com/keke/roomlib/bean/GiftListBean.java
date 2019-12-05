package com.keke.roomlib.bean;

import java.util.ArrayList;
import java.util.List;

public class GiftListBean {

    /**
     * list : [{"gift_id":4,"name":"测试礼物4","image":"","animate_resource":"","price":"10"},{"gift_id":5,"name":"测试陆吾5","image":"","animate_resource":"","price":"10"},{"gift_id":3,"name":"测试礼物3","image":"","animate_resource":"","price":"10"},{"gift_id":2,"name":"测试礼物2","image":"","animate_resource":"","price":"20"},{"gift_id":1,"name":"测试礼物","image":"","animate_resource":"","price":"10"},{"gift_id":6,"name":"测试陆吾6","image":"","animate_resource":"","price":"10"}]
     * spec_list : [1314,500,300,200,100,80,60,40,20,10,1]
     * page_index : 0
     * page_count : 6
     * total : 6
     * page_size : 8
     */

    private int page_index;
    private int page_count;
    private int total_count;
    private int total;
    private int page_size;
    private List<ListBean> list=new ArrayList<>();
    private List<String> spec_list=new ArrayList<>();
    private String gift_total;

    public String getGift_total() {
        return gift_total;
    }


    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public void setGift_total(String gift_total) {
        this.gift_total = gift_total;
    }

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<String> getSpec_list() {
        return spec_list;
    }

    public void setSpec_list(List<String> spec_list) {
        this.spec_list = spec_list;
    }

    public static class ListBean {
        /**
         * gift_id : 4
         * name : 测试礼物4
         * image :
         * animate_resource :
         * price : 10
         */

        private int total_num;
        private int gift_id;
        private int is_show;
        private String name;
        private String avail;
        private String image;
        private String animate_resource;
        private String animate_resource_md5;
        private String price;

        private int gift_type;
        private long reckon_time;

        public int getGift_type() {
            return gift_type;
        }

        public void setGift_type(int gift_type) {
            this.gift_type = gift_type;
        }

        public long getReckon_time() {
            return reckon_time;
        }

        public void setReckon_time(long reckon_time) {
            this.reckon_time = reckon_time;
        }

        public String getAnimate_resource_md5() {
            return animate_resource_md5;
        }

        public void setAnimate_resource_md5(String animate_resource_md5) {
            this.animate_resource_md5 = animate_resource_md5;
        }

        public int getIs_show() {
            return is_show;
        }

        public void setIs_show(int is_show) {
            this.is_show = is_show;
        }

        public String getAvail() {
            return avail;
        }

        public void setAvail(String avail) {
            this.avail = avail;
        }

        public int getTotal_num() {
            return total_num;
        }

        public void setTotal_num(int total_num) {
            this.total_num = total_num;
        }

        public int getGift_id() {
            return gift_id;
        }

        public void setGift_id(int gift_id) {
            this.gift_id = gift_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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
    }
}
