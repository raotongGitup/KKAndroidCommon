package com.keke.roomlib.bean;

public class InitBean {


    /**
     * time : 1551183886
     * expression : {"version":2,"download_url":"http://kkaudio-res.ikeke.ltd/expression/3.zip","md5":"c2ad0b9df6f9cd92d549be4119007e44"}
     * version : {"version_id":"1.0.0","describe":"哈哈","platform_id":2,"download_url":"http://kkaudio-res.ikeke.ltd/test/test.apk","channel_id":600001,"upgrade_type":2}
     * giftanimate : [{"gift_id":52,"animate_resource":"http://kkaudio-res.ikeke.ltd/gift/animation_lw_10001.bin"},{"gift_id":53,"animate_resource":"http://kkaudio-res.ikeke.ltd/album/video/platform/710722b0-39bd-11e9-b68d-0242ac120004.mp4"},{"gift_id":54,"animate_resource":"http://kkaudio-res.ikeke.ltd/gift/animation_lw_10003.bin"},{"gift_id":55,"animate_resource":"http://kkaudio-res.ikeke.ltd/gift/animation_lw_10004.bin"},{"gift_id":56,"animate_resource":"http://kkaudio-res.ikeke.ltd/gift/animation_lw_10005.bin"},{"gift_id":57,"animate_resource":"http://kkaudio-res.ikeke.ltd/gift/animation_lw_10006.bin"},{"gift_id":58,"animate_resource":"http://kkaudio-res.ikeke.ltd/gift/animation_lw_10007.bin"},{"gift_id":59,"animate_resource":"http://kkaudio-res.ikeke.ltd/gift/animation_lw_10008.bin"},{"gift_id":60,"animate_resource":"http://kkaudio-res.ikeke.ltd/gift/animation_lw_10009.bin"},{"gift_id":61,"animate_resource":"http://kkaudio-res.ikeke.ltd/gift/animation_lw_10010.bin"},{"gift_id":62,"animate_resource":"http://kkaudio-res.ikeke.ltd/gift/animation_lw_10011.bin"},{"gift_id":63,"animate_resource":"http://kkaudio-res.ikeke.ltd12313"},{"gift_id":64,"animate_resource":"http://kkaudio-res.ikeke.ltd12313"},{"gift_id":65,"animate_resource":"http://kkaudio-res.ikeke.ltd/album/video/platform/236b6692-39bd-11e9-95c2-0242ac120004.mp4"}]
     * rideanimate : [{"goods_id":17,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/ride/animation_zj_10001.bin"},{"goods_id":18,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/ride/animation_zj_10002.bin"},{"goods_id":19,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/ride/animation_zj_10003.bin"},{"goods_id":20,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/ride/animation_zj_10004.bin"},{"goods_id":21,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/ride/animation_zj_10005.bin"},{"goods_id":34,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/mall/none/platform/d9db2360-3a33-11e9-bf24-0242ac120004.bin"},{"goods_id":35,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/mall/none/platform/f46d799e-3a33-11e9-8152-0242ac120004.bin"},{"goods_id":36,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/mall/none/platform/0580cb28-3a34-11e9-90ca-0242ac120004.bin"}]
     * kk_wechat : heydocsc
     * qq_talk_key_android : CWL6x3xJw5lfvaYA58f3sCOdrnbAeeHA
     * user_protocol_url : https://kkaudio-api.ikeke.ltd/html/user_protocol.html
     * qq_talk_key_ios : 554992b9d3abc2d354c5bd953eb967412c62a4cce2c16f75e66ebbdc43dba0cc
     * kk_qq_group : 949701077
     * room_animation_wealth : 1000
     * kk_mail : support@kekestar.com
     * kk_qq : 918893396
     */


    public boolean isasd;

    private int time;
    private ExpressionBean expression;
    private VersionBean version;
    private String kk_wechat;
    private int audit_switch;
    private int audio_quality;
    private int meet_open;
    private int to_comment;
    private String qq_talk_key_android;
    private String user_protocol_url;
    private String qq_talk_key_ios;
    private String kk_qq_group;
    private long room_animation_wealth;
    private String kk_mail;
    private String kk_qq;
    private String medal_show_url;
    private String chat_room_id;
    private String policy_url;
    private String voice_share_url;
    private String cp_show_url;
    private String room_remind;
    private String friend_intimate_url;
    private String union_url;
    private String account_cancel_url;

    public String getAccount_cancel_url() {
        return account_cancel_url;
    }

    public void setAccount_cancel_url(String account_cancel_url) {
        this.account_cancel_url = account_cancel_url;
    }

    public String getFriend_intimate_url() {
        return friend_intimate_url;
    }

    public void setFriend_intimate_url(String friend_intimate_url) {
        this.friend_intimate_url = friend_intimate_url;
    }

    public String getUnion_url() {
        return union_url;
    }

    public void setUnion_url(String union_url) {
        this.union_url = union_url;
    }

    public int getMeet_open() {
        return meet_open;
    }

    public void setMeet_open(int meet_open) {
        this.meet_open = meet_open;
    }

    public String getMedal_show_url() {
        return medal_show_url;
    }

    public void setMedal_show_url(String medal_show_url) {
        this.medal_show_url = medal_show_url;
    }

    public int getTo_comment() {
        return to_comment;
    }

    public void setTo_comment(int to_comment) {
        this.to_comment = to_comment;
    }

    public String getRoom_remind() {
        return room_remind;
    }

    public void setRoom_remind(String room_remind) {
        this.room_remind = room_remind;
    }

    public String getCp_show_url() {
        return cp_show_url;
    }

    public void setCp_show_url(String cp_show_url) {
        this.cp_show_url = cp_show_url;
    }

    public String getVoice_share_url() {
        return voice_share_url;
    }

    public void setVoice_share_url(String voice_share_url) {
        this.voice_share_url = voice_share_url;
    }

    public String getHammer_open_state() {
        return hammer_open_state;
    }

    public void setHammer_open_state(String hammer_open_state) {
        this.hammer_open_state = hammer_open_state;
    }

    private  String hammer_open_state;

    public String getCustomer_str() {
        return customer_str;
    }

    public void setCustomer_str(String customer_str) {
        this.customer_str = customer_str;
    }

    private  String customer_str;

    public boolean isIsasd() {
        return isasd;
    }

    public void setIsasd(boolean isasd) {
        this.isasd = isasd;
    }

    public String getXwgf_url() {
        return xwgf_url;
    }

    public void setXwgf_url(String xwgf_url) {
        this.xwgf_url = xwgf_url;
    }

    public String getWmgy_url() {
        return wmgy_url;
    }

    public void setWmgy_url(String wmgy_url) {
        this.wmgy_url = wmgy_url;
    }

    private String xwgf_url;//行为规范
    private String wmgy_url;// 文明公约

    public int getAudio_quality() {
        return audio_quality;
    }

    public void setAudio_quality(int audio_quality) {
        this.audio_quality = audio_quality;
    }

    public String getKk_to_diamond() {
        return kk_to_diamond;
    }

    public void setKk_to_diamond(String kk_to_diamond) {
        this.kk_to_diamond = kk_to_diamond;
    }

    private String kk_to_diamond;
    private String diamond_to_pearl;    // 钻石 to 珍珠 比例
    private String pearl_to_celebrity ;    // 名人 to 珍珠 比例
    private String pearl_to_reputation ;  // 名声 to 珍珠 比例
    private String pearl_to_hc ;   // 小屋币 to 珍珠 比例


    public String getDiamond_to_pearl() {
        return diamond_to_pearl;
    }

    public void setDiamond_to_pearl(String diamond_to_pearl) {
        this.diamond_to_pearl = diamond_to_pearl;
    }

    public String getPearl_to_celebrity() {
        return pearl_to_celebrity;
    }

    public void setPearl_to_celebrity(String pearl_to_celebrity) {
        this.pearl_to_celebrity = pearl_to_celebrity;
    }

    public String getPearl_to_reputation() {
        return pearl_to_reputation;
    }

    public void setPearl_to_reputation(String pearl_to_reputation) {
        this.pearl_to_reputation = pearl_to_reputation;
    }

    public String getPearl_to_hc() {
        return pearl_to_hc;
    }

    public void setPearl_to_hc(String pearl_to_hc) {
        this.pearl_to_hc = pearl_to_hc;
    }

    public String getChat_room_id() {
        return chat_room_id;
    }

    public void setChat_room_id(String chat_room_id) {
        this.chat_room_id = chat_room_id;
    }

    public void setPolicy_url(String policy_url) {
        this.policy_url = policy_url;
    }

    public String getPolicy_url() {
        return policy_url;
    }

    /**
     * giftanimate : [{"gift_id":52,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/gift/animation_lw_10001.bin"},{"gift_id":53,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/gift/animation_lw_10002.bin"},{"gift_id":54,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/gift/animation_lw_10003.bin"},{"gift_id":55,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/gift/animation_lw_10004.bin"},{"gift_id":56,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/gift/animation_lw_10005.bin"},{"gift_id":57,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/gift/animation_lw_10006.bin"},{"gift_id":58,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/gift/animation_lw_10007.bin"},{"gift_id":59,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/gift/animation_lw_10008.bin"},{"gift_id":60,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/gift/animation_lw_10009.bin"},{"gift_id":61,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/gift/animation_lw_10010.bin"},{"gift_id":62,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/gift/animation_lw_10011.bin"}]
     * rideanimate : [{"goods_id":17,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/ride/animation_zj_10001.bin"},{"goods_id":18,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/ride/animation_zj_10002.bin"},{"goods_id":19,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/ride/animation_zj_10003.bin"},{"goods_id":20,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/ride/animation_zj_10004.bin"},{"goods_id":21,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/ride/animation_zj_10005.bin"},{"goods_id":34,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/mall/none/platform/d9db2360-3a33-11e9-bf24-0242ac120004.bin"},{"goods_id":35,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/mall/none/platform/f46d799e-3a33-11e9-8152-0242ac120004.bin"},{"goods_id":36,"animate_resource":"http://kkaudio.oss-cn-beijing.aliyuncs.com/mall/none/platform/0580cb28-3a34-11e9-90ca-0242ac120004.bin"}]
     * share_room_title : 房间分享标题
     * share_trend_img : 动态分享图片
     * share_trend_title : 动态分享标题
     * share_room_body : 房间分享内容
     * share_room_url : 房间分享链接
     * share_trend_body : 动态分享内容
     * share_user_body : 用户分享内容
     * share_user_url : 用户分享链接
     * share_room_img : 房间分享图片
     * share_trend_url : 动态分享链接
     * share_user_title : 用户分享标题
     * share_user_img : 用户分享图片
     */

    private String share_room_title;

    public String getShare_room_title() {
        return share_room_title;
    }

    public void setShare_room_title(String share_room_title) {
        this.share_room_title = share_room_title;
    }

    public String getShare_trend_img() {
        return share_trend_img;
    }

    public void setShare_trend_img(String share_trend_img) {
        this.share_trend_img = share_trend_img;
    }

    public String getShare_trend_title() {
        return share_trend_title;
    }

    public void setShare_trend_title(String share_trend_title) {
        this.share_trend_title = share_trend_title;
    }

    public String getShare_room_body() {
        return share_room_body;
    }

    public void setShare_room_body(String share_room_body) {
        this.share_room_body = share_room_body;
    }

    public String getShare_room_url() {
        return share_room_url;
    }

    public void setShare_room_url(String share_room_url) {
        this.share_room_url = share_room_url;
    }

    public String getShare_trend_body() {
        return share_trend_body;
    }

    public void setShare_trend_body(String share_trend_body) {
        this.share_trend_body = share_trend_body;
    }

    public String getShare_user_body() {
        return share_user_body;
    }

    public void setShare_user_body(String share_user_body) {
        this.share_user_body = share_user_body;
    }

    public String getShare_user_url() {
        return share_user_url;
    }

    public void setShare_user_url(String share_user_url) {
        this.share_user_url = share_user_url;
    }

    public String getShare_room_img() {
        return share_room_img;
    }

    public void setShare_room_img(String share_room_img) {
        this.share_room_img = share_room_img;
    }

    public String getShare_trend_url() {
        return share_trend_url;
    }

    public void setShare_trend_url(String share_trend_url) {
        this.share_trend_url = share_trend_url;
    }

    public String getShare_user_title() {
        return share_user_title;
    }

    public void setShare_user_title(String share_user_title) {
        this.share_user_title = share_user_title;
    }

    public String getShare_user_img() {
        return share_user_img;
    }

    public void setShare_user_img(String share_user_img) {
        this.share_user_img = share_user_img;
    }

    private String share_trend_img;
    private String share_trend_title;
    private String share_room_body;
    private String share_room_url;
    private String share_trend_body;
    private String share_user_body;
    private String share_user_url;
    private String share_room_img;
    private String share_trend_url;
    private String share_user_title;
    private String share_user_img;


    public int getAudit_switch() {
        return audit_switch;
    }

    public void setAudit_switch(int audit_switch) {
        this.audit_switch = audit_switch;
    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public ExpressionBean getExpression() {
        return expression;
    }

    public void setExpression(ExpressionBean expression) {
        this.expression = expression;
    }

    public VersionBean getVersion() {
        return version;
    }

    public void setVersion(VersionBean version) {
        this.version = version;
    }

    public String getKk_wechat() {
        return kk_wechat;
    }

    public void setKk_wechat(String kk_wechat) {
        this.kk_wechat = kk_wechat;
    }

    public String getQq_talk_key_android() {
        return qq_talk_key_android;
    }

    public void setQq_talk_key_android(String qq_talk_key_android) {
        this.qq_talk_key_android = qq_talk_key_android;
    }

    public String getUser_protocol_url() {
        return user_protocol_url;
    }

    public void setUser_protocol_url(String user_protocol_url) {
        this.user_protocol_url = user_protocol_url;
    }

    public String getQq_talk_key_ios() {
        return qq_talk_key_ios;
    }

    public void setQq_talk_key_ios(String qq_talk_key_ios) {
        this.qq_talk_key_ios = qq_talk_key_ios;
    }

    public String getKk_qq_group() {
        return kk_qq_group;
    }

    public void setKk_qq_group(String kk_qq_group) {
        this.kk_qq_group = kk_qq_group;
    }


    public long getRoom_animation_wealth() {
        return room_animation_wealth;
    }

    public void setRoom_animation_wealth(long room_animation_wealth) {
        this.room_animation_wealth = room_animation_wealth;
    }

    public String getKk_mail() {
        return kk_mail;
    }

    public void setKk_mail(String kk_mail) {
        this.kk_mail = kk_mail;
    }

    public String getKk_qq() {
        return kk_qq;
    }

    public void setKk_qq(String kk_qq) {
        this.kk_qq = kk_qq;
    }


    private String red_packet_status = "0";

    public String getRed_packet_status() {
        return red_packet_status;
    }

    public void setRed_packet_status(String red_packet_status) {
        this.red_packet_status = red_packet_status;
    }

    public static class ExpressionBean {
        /**
         * version : 2
         * download_url : http://kkaudio-res.ikeke.ltd/expression/3.zip
         * md5 : c2ad0b9df6f9cd92d549be4119007e44
         */

        private int version;
        private String download_url;
        private String md5;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getDownload_url() {
            return download_url;
        }

        public void setDownload_url(String download_url) {
            this.download_url = download_url;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }
    }

    public static class VersionBean {
        /**
         * version_id : 1.0.0
         * describe : 哈哈
         * platform_id : 2
         * download_url : http://kkaudio-res.ikeke.ltd/test/test.apk
         * channel_id : 600001
         * upgrade_type : 2
         */

        private String version_id;
        private String describe;
        private int platform_id;
        private String download_url;
        private int channel_id;
        private int upgrade_type;

        public String getVersion_id() {
            return version_id;
        }

        public void setVersion_id(String version_id) {
            this.version_id = version_id;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public int getPlatform_id() {
            return platform_id;
        }

        public void setPlatform_id(int platform_id) {
            this.platform_id = platform_id;
        }

        public String getDownload_url() {
            return download_url;
        }

        public void setDownload_url(String download_url) {
            this.download_url = download_url;
        }

        public int getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(int channel_id) {
            this.channel_id = channel_id;
        }

        public int getUpgrade_type() {
            return upgrade_type;
        }

        public void setUpgrade_type(int upgrade_type) {
            this.upgrade_type = upgrade_type;
        }
    }


}
