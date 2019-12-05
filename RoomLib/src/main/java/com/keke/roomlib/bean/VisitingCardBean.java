package com.keke.roomlib.bean;

import java.io.Serializable;

public class VisitingCardBean implements Serializable {
    private int goods_id;
    private int goods_type;
    private int end_timestamp;
    private String goods_name;
    private String small_img;
    private String big_img;
    private String animate_resource_md5;
    private String animate_resource;
    private String animate_resource_small;

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(int goods_type) {
        this.goods_type = goods_type;
    }

    public int getEnd_timestamp() {
        return end_timestamp;
    }

    public void setEnd_timestamp(int end_timestamp) {
        this.end_timestamp = end_timestamp;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
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

    public String getAnimate_resource_md5() {
        return animate_resource_md5;
    }

    public void setAnimate_resource_md5(String animate_resource_md5) {
        this.animate_resource_md5 = animate_resource_md5;
    }

    public String getAnimate_resource() {
        return animate_resource;
    }

    public void setAnimate_resource(String animate_resource) {
        this.animate_resource = animate_resource;
    }

    public String getAnimate_resource_small() {
        return animate_resource_small;
    }

    public void setAnimate_resource_small(String animate_resource_small) {
        this.animate_resource_small = animate_resource_small;
    }
}
