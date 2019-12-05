package com.keke.roomlib.bean;





import com.keke.roomlib.base.UserManager;
import com.keke.roomlib.utils.Utlis;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserBean extends BaseUserBean implements Serializable {
    /**
     * user_id :
     * phone :
     * user_uid :
     * nick_name :
     * brith_stamp :
     * affective_state :
     * sign :
     * home :
     * occupation :
     * school :
     * images : [{"url":"","pic_id":""}]
     * fans_count : 10
     * follow_count : 10
     */


    private int fans_count;
    private int follow_count;
    private int browses_count;
    private int visitors_count;
    private int is_abnormal;
    private String code;
    /**
     * liang : 0
     * super : 0
     * official : 0
     * waiter : 0
     */

    private int liang;
    private int official;
    private int waiter;
    private ArrayList<XunZhangBean.XunZhangItemBean> medal=new ArrayList<>();
    /**
     * user_id : 14
     * birthday : 1
     * affective_state : 1
     * guid :
     * channel_id :
     * charm_balance : 102937212
     * wealth_balance : 3590061
     * pearl_balance : 999963720
     * celebrity_balance : 1110002964
     * hc_balance : 100019188
     * is_cp : 1
     * cp_user : {"user_id":13,"user_uid":"13","nickname":"暴打小朋友","birthday":0,"affective_state":0,"gender":1,"sign":"","occupation":"","school":"","active_certify":0,"code":"","guid":"","channel_id":"","liang":0,"super":0,"official":0,"waiter":0,"home":"","images":["http://kkaudio-res.ikeke.ltd/chat/chat13.png"],"novice":0}
     * cp_intimate : 1
     * cp_autograph : 暴打小 朋友
     * cp_img :
     */

    private int is_cp;
    private UserBean cp_user;
    private int cp_intimate;
    private String cp_autograph;
    private String cp_img;
    private JieZhiLIstBean cp_ring;
    private List<GuardAttributeBean> guard_attribute;
    /**
     * user_prop : {"hide_info":true}
     */

    private UserPropBean user_prop;

    public JieZhiLIstBean getCp_ring() {
        return cp_ring;
    }

    public void setCp_ring(JieZhiLIstBean cp_ring) {
        this.cp_ring = cp_ring;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getFollows_count() {
        return follows_count;
    }

    public void setFollows_count(int follows_count) {
        this.follows_count = follows_count;
    }

    public int getBrowses_count() {
        return browses_count;
    }

    public void setBrowses_count(int browses_count) {
        this.browses_count = browses_count;
    }

    public int getVisitors_count() {
        return visitors_count;
    }

    public void setVisitors_count(int visitors_count) {
        this.visitors_count = visitors_count;
    }

    private int follows_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String user_id;
    private String phone;
    private String mid;
    private String portrait;
    private String user_uid;
    private String nickname;
    private String affective_state;
    private String sign = "";
    private String home;
    private String voice;
    private String voice_time;
    private int gender;
    private int active_certify = 0;
    private int guard_type;
    private long birthday;
    private long diamond_balance;
    private long charm_balance = -1;
    private long wealth_balance;
    private long room_wealth_balance;
    private long pearl_balance;
    private long reputation_balance;
    private long celebrity_balance;
    private long hc_balance;
    private long total_value;
    private String token;
    private int expire_time;
    private int novice;
    private int room_is_certified;
    private boolean is_follow;
    private boolean room_block;
    private String occupation;
    private String cp_user_id;
    private String today_kk_value = "0";
    private RoomTypeBean room_type;


    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getVoice_time() {
        return voice_time;
    }

    public void setVoice_time(String voice_time) {
        this.voice_time = voice_time;
    }

    private List<DiyMallBean.DiyItemBean> attires = new ArrayList<>();
    /**
     * user_id : 23
     * birthday : 788918400
     * affective_state : 0
     * diamond_balance : 0
     * charm_balance : 0
     * wealth_balance : 0
     * total_diamond_value : 44078
     */

    private long total_wealth_value;   //富豪榜   房间排行榜
    private long devote_diamond_total;  //房间内的富豪榜
    private long total_charm_value;     //魅力榜   房间内的魅力
    private String kk_balance = "0";     //壳壳币



    public int getNovice() {
        return novice;
    }

    public String getCp_user_id() {
        return cp_user_id;
    }

    public void setCp_user_id(String cp_user_id) {
        this.cp_user_id = cp_user_id;
    }

    public void setNovice(int novice) {
        this.novice = novice;
    }

    public String getToday_kk_value() {
        return today_kk_value;
    }

    public void setToday_kk_value(String today_kk_value) {
        this.today_kk_value = today_kk_value;
    }

    public String getRoom_wealth_balance() {
        return new BigDecimal(room_wealth_balance).toPlainString();
    }

    public String getTotal_value() {
        return new BigDecimal(total_value).toPlainString();
    }

    public void setTotal_value(long total_value) {
        this.total_value = total_value;
    }

    public void setRoom_wealth_balance(long room_wealth_balance) {
        this.room_wealth_balance = room_wealth_balance;
    }

    public int getRoom_is_certified() {
        return room_is_certified;
    }

    public void setRoom_is_certified(int room_is_certified) {
        this.room_is_certified = room_is_certified;
    }

    public int getIs_abnormal() {
        return is_abnormal;
    }

    public void setIs_abnormal(int is_abnormal) {
        this.is_abnormal = is_abnormal;
    }

    public ArrayList<XunZhangBean.XunZhangItemBean> getMedal() {
        return medal;
    }

    public void setMedal(ArrayList<XunZhangBean.XunZhangItemBean> medal) {
        this.medal = medal;
    }

    public boolean isRoom_block() {
        return room_block;
    }

    public void setRoom_block(boolean room_block) {
        this.room_block = room_block;
    }

    public boolean isIs_follow() {
        return is_follow;
    }

    public void setIs_follow(boolean is_follow) {
        this.is_follow = is_follow;
    }

    public String getKk_balance() {
        return kk_balance;
    }

    public void setKk_balance(String kk_balance) {
        this.kk_balance = kk_balance;
    }

    public int getActive_certify() {
        return active_certify;
    }

    public void setActive_certify(int active_certify) {
        this.active_certify = active_certify;
    }

    public List<DiyMallBean.DiyItemBean> getAttires() {
        return attires;
    }

    public void setAttires(List<DiyMallBean.DiyItemBean> attires) {
        this.attires = attires;
    }

    public String getRank_name() {
        return rank_name;
    }

    public void setRank_name(String rank_name) {
        this.rank_name = rank_name;
    }

    public String getRank_image() {
        return rank_image;
    }

    public RoomTypeBean getRoom_type() {
        return room_type;
    }

    public void setRoom_type(RoomTypeBean room_type) {
        this.room_type = room_type;
    }


    // TODO: 2019/11/30  用户图像获取

    public String getPortrait() {
//        return portrait == null ? gethead() : portrait;
        return gethead();
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public void setRank_image(String rank_image) {
        this.rank_image = rank_image;
    }

    private String rank_name;
    private String rank_image;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String province;
    private String city;

    private String openid;
    private String school;
    private String rong_token;
    private List<String> images = new ArrayList<>();
    private List<String> thumbnails = new ArrayList<>();


    public List<String> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<String> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getRong_token() {
        return rong_token;
    }

    public void setRong_token(String rong_token) {
        this.rong_token = rong_token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(int expire_time) {
        this.expire_time = expire_time;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }


    public int getGuard_type() {
        return guard_type;
    }

    public void setGuard_type(int guard_type) {
        this.guard_type = guard_type;
    }

    public String getDiamond_balance() {
        return new BigDecimal(diamond_balance).toPlainString();
    }

    public void setDiamond_balance(long diamond_balance) {
        this.diamond_balance = diamond_balance;
    }

    public String getPearl_balance() {
        return new BigDecimal(pearl_balance).toPlainString();
    }

    public void setPearl_balance(long pearl_balance) {
        this.pearl_balance = pearl_balance;
    }

    public String getReputation_balance() {
        return new BigDecimal(reputation_balance).toPlainString();
    }

    public void setReputation_balance(long reputation_balance) {
        this.reputation_balance = reputation_balance;
    }

    public String getCelebrity_balance() {
        return new BigDecimal(celebrity_balance).toPlainString();
    }

    public void setCelebrity_balance(long celebrity_balance) {
        this.celebrity_balance = celebrity_balance;
    }

    public String getHc_balance() {
        return new BigDecimal(hc_balance).toPlainString();
    }

    public void setHc_balance(long hc_balance) {
        this.hc_balance = hc_balance;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getCharm_balance() {
        return new BigDecimal(charm_balance).toPlainString();
    }

    public void setCharm_balance(long charm_balance) {
        this.charm_balance = charm_balance;
    }

    public String getWealth_balance() {
        return new BigDecimal(wealth_balance).toPlainString();
    }

    public void setWealth_balance(long wealth_balance) {
        this.wealth_balance = wealth_balance;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(String user_uid) {
        this.user_uid = user_uid;
    }

    public String getNick_name() {
        return nickname;
    }

    public void setNick_name(String nick_name) {
        this.nickname = nick_name;
    }

    public String getAffective_state() {
        return affective_state;
    }

    public void setAffective_state(String affective_state) {
        this.affective_state = affective_state;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }


    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getFans_count() {
        return fans_count;
    }

    public void setFans_count(int fans_count) {
        this.fans_count = fans_count;
    }

    public int getFollow_count() {
        return follow_count;
    }

    public void setFollow_count(int follow_count) {
        this.follow_count = follow_count;
    }


    // TODO: 2019/11/30  用户图像获取
    public String gethead() {
        if (UserManager.user_id == null || user_id == null){
            return null;
        }

        if (UserManager.user_id.equals(user_id)) {
            return images.size() > 0 ? (images.get(0) == null ? "" : images.get(0)) : "";
        } else {
            if (thumbnails.size() > 0) {
                return thumbnails.size() > 0 ? (thumbnails.get(0) == null ? "" : thumbnails.get(0)) : "";

            }
            return images.size() > 0 ? (images.get(0) == null ? "" : images.get(0)) : "";

        }


    }


    private String getImageStrings() {
        String imgas = "";
        for (String image : images) {
            imgas = imgas + image + ",";
        }
        return imgas.substring(0, imgas.length() - 1);
    }

    public long getTotal_wealth_value() {
        return total_wealth_value;
    }

    public void setTotal_wealth_value(long total_wealth_value) {
        this.total_wealth_value = total_wealth_value;
    }

    public long getDevote_diamond_total() {
        return devote_diamond_total;
    }

    public void setDevote_diamond_total(long devote_diamond_total) {
        this.devote_diamond_total = devote_diamond_total;
    }

    public long getTotal_charm_value() {
        return total_charm_value;
    }

    public void setTotal_charm_value(long total_charm_value) {
        this.total_charm_value = total_charm_value;
    }

    public int getLiang() {
        return liang;
    }

    public void setLiang(int liang) {
        this.liang = liang;
    }

    public int getOfficial() {
        return official;
    }

    public void setOfficial(int official) {
        this.official = official;
    }

    public int getWaiter() {
        return waiter;
    }

    public void setWaiter(int waiter) {
        this.waiter = waiter;
    }

    /**
     * user_id : 249
     * user_uid : 120144
     * nickname : 748096
     * birthday : 788918400
     * affective_state : 0
     * gender : 1
     * sign :
     * occupation :
     * school :
     * active_certify : 0
     * code : 55L8
     * guid : a246f5847861467a810bcc4456eb1574
     * channel_id : 101
     * liang : 0
     * super : 0
     * official : 0
     * waiter : 0
     * home :
     * novice : 1
     * images : ["http://kkaudio-res.ikeke.ltd/album/image/249/a8acf436-86ae-11e9-88a6-0242ac120005.jpg"]
     * forbid_time : 1559700091
     */

    private String forbid_time;


    public String getForbid_time() {
        return forbid_time;
    }

    public void setForbid_time(String forbid_time) {
        this.forbid_time = forbid_time;
    }

    public int getIs_cp() {
        return is_cp;
    }

    public void setIs_cp(int is_cp) {
        this.is_cp = is_cp;
    }

    public UserBean getCp_user() {
        return cp_user;
    }

    public void setCp_user(UserBean cp_user) {
        this.cp_user = cp_user;
    }

    public int getCp_intimate() {
        return cp_intimate;
    }

    public void setCp_intimate(int cp_intimate) {
        this.cp_intimate = cp_intimate;
    }

    public String getCp_autograph() {
        return cp_autograph;
    }

    public void setCp_autograph(String cp_autograph) {
        this.cp_autograph = cp_autograph;
    }

    public String getCp_img() {
        return cp_img;
    }

    public void setCp_img(String cp_img) {
        this.cp_img = cp_img;
    }

    public List<GuardAttributeBean> getGuard_attribute() {
        return guard_attribute;
    }

    public void setGuard_attribute(List<GuardAttributeBean> guard_attribute) {
        this.guard_attribute = guard_attribute;
    }

    public UserPropBean getUser_prop() {
        return user_prop;
    }

    public void setUser_prop(UserPropBean user_prop) {
        this.user_prop = user_prop;
    }

    public static class GuardAttributeBean implements Serializable{
        /**
         * remain_time : 16天
         * expire_time : 1565167065
         * name : 黄金守护位
         * type : 3
         * guard_img :
         * guard_small_img :
         */

        private String remain_time;
        private long expire_time;
        private String name;
        private int type;
        private String guard_img;
        private String guard_small_img;

        public String getRemain_time() {
            return remain_time;
        }

        public void setRemain_time(String remain_time) {
            this.remain_time = remain_time;
        }

        public long getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(long expire_time) {
            this.expire_time = expire_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getGuard_img() {
            return guard_img;
        }

        public void setGuard_img(String guard_img) {
            this.guard_img = guard_img;
        }

        public String getGuard_small_img() {
            return guard_small_img;
        }

        public void setGuard_small_img(String guard_small_img) {
            this.guard_small_img = guard_small_img;
        }
    }

    private int room_id;

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public static class UserPropBean implements Serializable {
        /**
         * hide_info : true
         */

        private boolean hide_info;

        public boolean isHide_info() {
            return hide_info;
        }

        public void setHide_info(boolean hide_info) {
            this.hide_info = hide_info;
        }
    }

    public class RoomTypeBean implements Serializable {

        /**
         * id : 0
         * type_name : 普通
         * img_home_url : http://kkaudio.oss-cn-beijing.aliyuncs.com
         * img_room_url : http://kkaudio.oss-cn-beijing.aliyuncs.com/room/type_imgs/0-room.png
         * img_roomedit_url : http://kkaudio.oss-cn-beijing.aliyuncs.com/room/type_imgs/0-edit.png
         */

        private int id;
        private String type_name;
        private String img_home_url;
        private String img_room_url;
        private String img_roomedit_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getImg_home_url() {
            return img_home_url;
        }

        public void setImg_home_url(String img_home_url) {
            this.img_home_url = img_home_url;
        }

        public String getImg_room_url() {
            return img_room_url;
        }

        public void setImg_room_url(String img_room_url) {
            this.img_room_url = img_room_url;
        }

        public String getImg_roomedit_url() {
            return img_roomedit_url;
        }

        public void setImg_roomedit_url(String img_roomedit_url) {
            this.img_roomedit_url = img_roomedit_url;
        }
    }

    private String visitor_timestamp;
    private String browse_timestamp;

    public String getVisitor_timestamp() {
        return visitor_timestamp;
    }

    public void setVisitor_timestamp(String visitor_timestamp) {
        this.visitor_timestamp = visitor_timestamp;
    }

    public String getBrowse_timestamp() {
        return browse_timestamp;
    }

    public void setBrowse_timestamp(String browse_timestamp) {
        this.browse_timestamp = browse_timestamp;
    }
    private int v;
    private String v_desc;
    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public String getV_desc() {
        return v_desc;
    }

    public void setV_desc(String v_desc) {
        this.v_desc = v_desc;
    }

    private int cp_status;

    public int getCp_status() {
        return cp_status;
    }

    public void setCp_status(int cp_status) {
        this.cp_status = cp_status;
    }

    private int intimate;

    public int getIntimate() {
        return intimate;
    }

    public void setIntimate(int intimate) {
        this.intimate = intimate;
    }

    public static UserBean blackMessToUser(UserBean blackListBean) {
        UserBean userBean = new UserBean();
        if (!Utlis.isEmp(String.valueOf(blackListBean.getBirthday())))
            userBean.setBirthday(blackListBean.getBirthday());
        if (!Utlis.isEmp(String.valueOf(blackListBean.getGender())))
            userBean.setGender(blackListBean.getGender());
        return userBean;
    }

    private GoodsBean mall_user_goods;

    public GoodsBean getMall_user_goods() {
        return mall_user_goods;
    }

    public void setMall_user_goods(GoodsBean mall_user_goods) {
        this.mall_user_goods = mall_user_goods;
    }

    public class GoodsBean{
        private VisitingCardBean visiting_card;

        public VisitingCardBean getVisiting_card() {
            return visiting_card;
        }

        public void setVisiting_card(VisitingCardBean visiting_card) {
            this.visiting_card = visiting_card;
        }
    }
}
