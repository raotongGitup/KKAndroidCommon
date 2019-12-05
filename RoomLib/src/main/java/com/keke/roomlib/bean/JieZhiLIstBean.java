package com.keke.roomlib.bean;

public class JieZhiLIstBean {

    /**
     * ring_id : 8
     * name : 地老天荒
     * image : http://kkaudio-res.ikeke.ltd
     * level : 8
     * gray_image : http://kkaudio-res.ikeke.ltd
     * is_enabled : 0
     */

    private int ring_id;
    private String name;
    private String image;
    private int level;
    private String gray_image;
    private String ring_record_id;
    private int is_enabled;
    private int min_value;
    private int max_value;
    private long expire_time;


    public long getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(long expire_time) {
        this.expire_time = expire_time;
    }

    public String getRing_record_id() {
        return ring_record_id;
    }

    public void setRing_record_id(String ring_record_id) {
        this.ring_record_id = ring_record_id;
    }

    public int getRing_id() {
        return ring_id;
    }

    public void setRing_id(int ring_id) {
        this.ring_id = ring_id;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getGray_image() {
        return gray_image;
    }

    public void setGray_image(String gray_image) {
        this.gray_image = gray_image;
    }

    public int getIs_enabled() {
        return is_enabled;
    }

    public void setIs_enabled(int is_enabled) {
        this.is_enabled = is_enabled;
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
