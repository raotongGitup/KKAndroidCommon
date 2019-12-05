package com.keke.roomlib.bean;

public class GiftDownBean {

    /**
     * gift_id : 1
     * animate_resource : https://kkaudio.oss-cn-beijing.aliyuncs.com/gift/test1.bin
     */

    private int gift_id;
    private String animate_resource;
    private String animate_resource_md5;

    public String getAnimate_resource_md5() {
        return animate_resource_md5;
    }

    public void setAnimate_resource_md5(String animate_resource_md5) {
        this.animate_resource_md5 = animate_resource_md5;
    }

    public int getGift_id() {
        return gift_id;
    }

    public void setGift_id(int gift_id) {
        this.gift_id = gift_id;
    }

    public String getAnimate_resource() {
        return animate_resource;
    }

    public void setAnimate_resource(String animate_resource) {
        this.animate_resource = animate_resource;
    }
}
