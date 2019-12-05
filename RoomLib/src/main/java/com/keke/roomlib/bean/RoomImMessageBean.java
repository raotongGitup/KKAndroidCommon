package com.keke.roomlib.bean;



import java.util.ArrayList;
import java.util.List;

public class RoomImMessageBean {


    /**
     * k : 30082
     * res : {"left":133700,"right":131580}
     */

    private ResBean res;
    /**
     * user : {"left":[],"right":[{"nickname":"左耳FM.小宝贝儿","user_id":646,"thumbnails":"http://thirdwx.qlogo.cn/mmopen/vi_32/EaRhA6Z2th7icuK4SSss1qDj91r21LNicjx2kiaqicxVn29ic3icf3x7lBvXBWxFqTf1Fl2JUlKM4QFXcJ5bpsJa7mwA/132"}]}
     */

    private UserpkBean pk_user;

    public ArrayList<String> getSelect() {
        return select;
    }

    public void setSelect(ArrayList<String> select) {
        this.select = select;
    }

    /**
     {
     "k": 20001,      //信令Code
     "fid": 10000,    //发送人ID
     "fnn": "昵称",    //发送人昵称
     "fimg": "",      //发送人头像URL
     "tid": 10000,    //接受人ID
     "tnn": "昵称",    //接受人昵称
     "timg": "",      //接受人头像url
     "gpic":"",       //礼物图片
     "gid":101010,    //礼物ID
     "gn":"小兔子",    //礼物名称
     "gc":10          //礼物数量
     }
     {
     "k": 30002,      //信令Code
     "uid": 30002,    //用户ID
     "mid": 30002,    //主持麦ID
     "nn": "昵称",     //昵称
     "img":"",        //头像url
     "sex": 0,        //性别
     "bt":"20181110"  //出生日期
     }
     */




    private String k;
    private ArrayList<HeartMoShiBean.SelectDataBean> select_data;
    private ArrayList<RoomTallyInfo.TallyDataBean> tally_data;
    /**
     * k : 30075
     * user_id : 10
     * guard_user_id : 11
     * guard_nickname : xxxx
     * guard_type_id : 10
     * guard_name : xxxx
     */

    private String user_id;
    private String guard_user_id;
    private String guard_nickname;
    private int guard_type_id;
    private String guard_name;
    private String topic;

    private ArrayList<String> select;
    /**
     * k : 11001
     * uid : 30002
     * sid : 30002
     * is_heart : 0
     */

    private String sid;
    private String is_heart;
    private RoomTypeBean room;

    public RoomTypeBean getRoom() {
        return room;
    }

    public void setRoom(RoomTypeBean room) {
        this.room = room;
    }

    /**
     * max_send_tally_data : {"user_id":1,"nickname":"xxxx","image":"http://xxxx.xxx.com","val":0}
     */

    private MaxSendTallyDataBean max_send_tally_data;

    /**
     * k : 30022
     * tally_data : {"host_micro":0,"micro":[{"micro":0,"val":0}]}
     */


    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    private String t;
    private String l;
    private String fid;
    private String fnn;
    private String fimg;
    private String tid;
    private String sot;
    private String tnn;
    private String timg;
    private String gpic;
    private String gid;
    private String gn;
    private String gc;
    private String w;
    private String rc;
    private String bc;
    private String select_time;
    private String state;
    private String usex;
    private String ssex;
    private String action_type;

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    private List<UserBean.GuardAttributeBean> gs;

    public List<UserBean.GuardAttributeBean> getGs() {
        return gs;
    }

    public void setGs(List<UserBean.GuardAttributeBean> gs) {
        this.gs = gs;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public ArrayList<RoomTallyInfo.TallyDataBean> getTally_data() {
        return tally_data;
    }

    public void setTally_data(ArrayList<RoomTallyInfo.TallyDataBean> tally_data) {
        this.tally_data = tally_data;
    }

    public String getSelect_time() {
        return select_time;
    }

    public void setSelect_time(String select_time) {
        this.select_time = select_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getBc() {
        return bc;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getL() {
        return l;
    }

    public String getSot() {
        return sot;
    }

    public void setSot(String sot) {
        this.sot = sot;
    }

    public ArrayList<HeartMoShiBean.SelectDataBean> getSelect_data() {
        return select_data;
    }

    public void setSelect_data(ArrayList<HeartMoShiBean.SelectDataBean> select_data) {
        this.select_data = select_data;
    }

    /**
     * uid : 30002
     * mid : 30002
     * nn : 昵称
     * img :
     * sex : 0
     * bt : 20181110
     */

//    private ArrayList<DzpUser> user;




    private String uid;
    private String mid;
    private String nn;
    private String n;
    private String img;
    private String sex;
    private String fsex;
    private String s;
    private String bt;
    private String fbt;
    private String pic;
    private String gt;
    private String eid;
    private String url;
    private String wc;
    private String rwc;
    private String mlt;
    private String ruc;
    private ArrayList<String> uids;
    private ArrayList<XunZhangBean.XunZhangItemBean> medal=new ArrayList<>();

    public ArrayList<XunZhangBean.XunZhangItemBean> getMedal() {
        return medal;
    }

    public void setMedal(ArrayList<XunZhangBean.XunZhangItemBean> medal) {
        this.medal = medal;
    }

    private String nickname;
    private String content;
    private String image;
    private String animate_resource;
    private String is_text_message="0";
    private String type;


    public ArrayList<String> getUids() {
        return uids;
    }

    public void setUids(ArrayList<String> uids) {
        this.uids = uids;
    }

    public String getIs_text_message() {
        return is_text_message;
    }

    public void setIs_text_message(String is_text_message) {
        this.is_text_message = is_text_message;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getWc() {
        return wc;
    }

    public void setWc(String wc) {
        this.wc = wc;
    }

    public String getRwc() {
        return rwc;
    }

    public void setRwc(String rwc) {
        this.rwc = rwc;
    }

    private String bid;
    private String bres;
    private String cid;
    private String cres;
    private String rid;
    private String rres;
    private String at;
    private String cts;
    private String req;
    private String pro;
    private String opened;
    private String pwd;
    private String cc;
    private String sm="1000";
    private String closed;
    private String hot;
    private String nov;
    private String unn;
    private String snn;
    private String tcolor;



    public String getUnn() {
        return unn;
    }

    public void setUnn(String unn) {
        this.unn = unn;
    }

    public String getSnn() {
        return snn;
    }

    public void setSnn(String snn) {
        this.snn = snn;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getOpened() {
        return opened;
    }

    public void setOpened(String opened) {
        this.opened = opened;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    private int vself=1;

    public String getNov() {
        return nov;
    }

    public String getTcolor() {
        return tcolor;
    }

    public void setTcolor(String tcolor) {
        this.tcolor = tcolor;
    }

    public void setNov(String nov) {
        this.nov = nov;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public int getVself() {
        return vself;
    }

    public void setVself(int vself) {
        this.vself = vself;
    }

    public String getCts() {
        return cts;
    }

    public void setCts(String cts) {
        this.cts = cts;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getRres() {
        return rres;
    }

    public void setRres(String rres) {
        this.rres = rres;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBres() {
        return bres;
    }

    public void setBres(String bres) {
        this.bres = bres;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCres() {
        return cres;
    }

    public void setCres(String cres) {
        this.cres = cres;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    private List<TuBean> tu=new ArrayList<>();

    public List<TuBean> getTu() {
        return tu;
    }

    public void setTu(List<TuBean> tu) {
        this.tu = tu;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getMlt() {
        return mlt;
    }

    public void setMlt(String mlt) {
        this.mlt = mlt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFnn() {
        return fnn;
    }

    public void setFnn(String fnn) {
        this.fnn = fnn;
    }

    public String getFimg() {
        return fimg;
    }

    public void setFimg(String fimg) {
        this.fimg = fimg;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTnn() {
        return tnn;
    }

    public void setTnn(String tnn) {
        this.tnn = tnn;
    }

    public String getTimg() {
        return timg;
    }

    public void setTimg(String timg) {
        this.timg = timg;
    }

    public String getGpic() {
        return gpic;
    }

    public void setGpic(String gpic) {
        this.gpic = gpic;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGn() {
        return gn;
    }

    public void setGn(String gn) {
        this.gn = gn;
    }

    public String getGc() {
        return gc;
    }

    public void setGc(String gc) {
        this.gc = gc;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getNn() {
        return nn;
    }

    public void setNn(String nn) {
        this.nn = nn;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }


    public String getFsex() {
        return fsex;
    }

    public void setFsex(String fsex) {
        this.fsex = fsex;
    }

    public String getFbt() {
        return fbt;
    }

    public void setFbt(String fbt) {
        this.fbt = fbt;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGuard_user_id() {
        return guard_user_id;
    }

    public void setGuard_user_id(String guard_user_id) {
        this.guard_user_id = guard_user_id;
    }

    public String getGuard_nickname() {
        return guard_nickname;
    }

    public void setGuard_nickname(String guard_nickname) {
        this.guard_nickname = guard_nickname;
    }

    public int getGuard_type_id() {
        return guard_type_id;
    }

    public void setGuard_type_id(int guard_type_id) {
        this.guard_type_id = guard_type_id;
    }

    public String getGuard_name() {
        return guard_name;
    }

    public void setGuard_name(String guard_name) {
        this.guard_name = guard_name;
    }


    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getIs_heart() {
        return is_heart;
    }

    public void setIs_heart(String is_heart) {
        this.is_heart = is_heart;
    }

    public MaxSendTallyDataBean getMax_send_tally_data() {
        return max_send_tally_data;
    }

    public void setMax_send_tally_data(MaxSendTallyDataBean max_send_tally_data) {
        this.max_send_tally_data = max_send_tally_data;
    }

    public ResBean getRes() {
        return res;
    }

    public void setRes(ResBean res) {
        this.res = res;
    }


    public UserpkBean getPk_user() {
        return pk_user;
    }

    public void setPk_user(UserpkBean pk_user) {
        this.pk_user = pk_user;
    }

    public static class ResBean {
        /**
         * left : 133700
         * right : 131580
         */

        private String left;
        private String right;

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

    public static class UserpkBean {
        private List<RightBean> left;
        private List<RightBean> right;

        public List<RightBean> getLeft() {
            return left;
        }

        public void setLeft(List<RightBean> left) {
            this.left = left;
        }

        public List<RightBean> getRight() {
            return right;
        }

        public void setRight(List<RightBean> right) {
            this.right = right;
        }

        public static class RightBean {
            /**
             * nickname : 左耳FM.小宝贝儿
             * user_id : 646
             * thumbnails : http://thirdwx.qlogo.cn/mmopen/vi_32/EaRhA6Z2th7icuK4SSss1qDj91r21LNicjx2kiaqicxVn29ic3icf3x7lBvXBWxFqTf1Fl2JUlKM4QFXcJ5bpsJa7mwA/132
             */

            private String nickname;
            private String user_id;
            private String thumbnails;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getThumbnails() {
                return thumbnails;
            }

            public void setThumbnails(String thumbnails) {
                this.thumbnails = thumbnails;
            }
        }
    }

    public  class MaxSendTallyDataBean {
        /**
         * user_id : 1
         * nickname : xxxx
         * image : http://xxxx.xxx.com
         * val : 0
         */

        private String user_id;
        private String nickname;
        private String image;
        private int val;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    private int minute;

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public class TuBean {


        /**
         * id : 22
         * nn : xxx
         */

        private int id;
        private String nn;
        private String cc;
        private String rc;

        public String getRc() {
            return rc;
        }

        public void setRc(String rc) {
            this.rc = rc;
        }

        public String getCc() {
            return cc;
        }

        public void setCc(String cc) {
            this.cc = cc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNn() {
            return nn;
        }

        public void setNn(String nn) {
            this.nn = nn;
        }
    }

    private int v;
    private String joinAni;

    public String getJoinAni() {
        return joinAni;
    }

    public void setJoinAni(String joinAni) {
        this.joinAni = joinAni;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    private Game gsm;

    public Game getGsm() {
        return gsm;
    }

    public void setGsm(Game gsm) {
        this.gsm = gsm;
    }


    public class RoomTypeBean {

        /**
         * room_id : 3348
         * melee : 1
         * tally : 1
         * heart : 1
         * pk : 0
         * melee_opened : 0
         * tally_opened : 0
         * heart_opened : 1
         * pk_opened : 1
         */

        private int room_id;
        private int melee;
        private int tally;
        private int heart;
        private int melee_opened;
        private int tally_opened;
        private int heart_opened;
        private int pk;
        private int pk_opened;

        public int getRoom_id() {
            return room_id;
        }

        public void setRoom_id(int room_id) {
            this.room_id = room_id;
        }

        public int getMelee() {
            return melee;
        }

        public void setMelee(int melee) {
            this.melee = melee;
        }

        public int getTally() {
            return tally;
        }

        public void setTally(int tally) {
            this.tally = tally;
        }

        public int getHeart() {
            return heart;
        }

        public void setHeart(int heart) {
            this.heart = heart;
        }

        public int getPk() {
            return pk;
        }

        public void setPk(int pk) {
            this.pk = pk;
        }

        public int getMelee_opened() {
            return melee_opened;
        }

        public void setMelee_opened(int melee_opened) {
            this.melee_opened = melee_opened;
        }

        public int getTally_opened() {
            return tally_opened;
        }

        public void setTally_opened(int tally_opened) {
            this.tally_opened = tally_opened;
        }

        public int getHeart_opened() {
            return heart_opened;
        }

        public void setHeart_opened(int heart_opened) {
            this.heart_opened = heart_opened;
        }

        public int getPk_opened() {
            return pk_opened;
        }

        public void setPk_opened(int pk_opened) {
            this.pk_opened = pk_opened;
        }
    }


  public static class Game{
        private  String name;

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

      private int type=-1;

    }

    public  class DzpUser{


        /**
         * nickname : 七夕测试2
         * gender : 1
         * user_id : 412
         * thumbnails : http://kkaudio-res.ikeke.ltd/album/image/412/bfc5e944-bff6-11e9-86cd-0242ac120004.jpg?x-oss-process=style/thumbnails
         */

        private String nickname;
        private String gender;
        private String user_id;
        private String thumbnails;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getThumbnails() {
            return thumbnails;
        }

        public void setThumbnails(String thumbnails) {
            this.thumbnails = thumbnails;
        }
    }
}
