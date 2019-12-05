package com.keke.roomlib.im;

public class IMbean  {


    /**
     * k : 40001
     * rid : 10
     * rn : xx的房间
     * img : http://xxxx.jpg
     */

    private int k;
    private int rid;
    private String rn;
    private String img;
    private String sign;
    private String role_id;
    private String agora_token;
    /**
     * price : 100
     * amount : 100
     */

    private int price;
    private int amount;
    /**
     * gid : 101010
     * gc : 10
     * gn : 哈哈
     * res : aaa.jpg
     * suid : 10
     * snn : 昵称
     * simg : aaa.jpg
     * ruid : 10
     * rnn : 昵称
     * rimg : aaa.jpg
     */

    private int gid;
    private int gc=1;
    private String gn;
    private String res;
    private int suid;
    private String snn;
    private String simg;
    private int ruid;
    private String rnn;
    private String rimg;
    private String gbimg;
    /**
     * n : 今天天气真好
     * url : http://www.jpg
     * outside : 1
     */


    private String n;
    private String url;
    private int outside;
    /**
     * cid : 10
     * cont : json字符串
     * cv : http://www/a.jpg
     * ct : 1
     * uid : 10
     * nn : 昵称
     */


    private int cid;
    private String cont;
    private String cv;
    private String nickname;
    private String content;
    private String image;
    private String meet_id;
    private String animate_resource;
    private int type;
    private int ct;
    private int uid;
    private int sex;
    private long bron;
    private String nn;
    /**
     * num : 999
     */

    private int gnum;

    public String getMeet_id() {
        return meet_id;
    }

    public void setMeet_id(String meet_id) {
        this.meet_id = meet_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    private String t;
    /**
     * cpn : haha2
     * cpimg : http://
     * rlevel : 2
     * rgimg : http://
     */

    private String cpn;
    private String cpimg;
    private int rlevel;
    private String rgimg;
    private String text;





    private String unid;
    private String uname;
    private String aname;
    private String timestemp;
    private String auuid;
    private String status;
    private String rolename;
    private String auditname;
    private String auid;

    public String getAuid() {
        return auid;
    }

    public void setAuid(String auid) {
        this.auid = auid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getAuditname() {
        return auditname;
    }

    public void setAuditname(String auditname) {
        this.auditname = auditname;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getTimestemp() {
        return new Long(timestemp)*1000+"";
    }

    public void setTimestemp(String timestemp) {
        this.timestemp = timestemp;
    }

    public String getAuuid() {
        return auuid;
    }

    public void setAuuid(String auuid) {
        this.auuid = auuid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getAgora_token() {
        return agora_token;
    }

    public void setAgora_token(String agora_token) {
        this.agora_token = agora_token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getBron() {
        return bron;
    }

    public void setBron(long bron) {
        this.bron = bron;
    }

    public String getGbimg() {
        return gbimg;
    }

    public void setGbimg(String gbimg) {
        this.gbimg = gbimg;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getGc() {
        return gc;
    }

    public void setGc(int gc) {
        this.gc = gc;
    }

    public String getGn() {
        return gn;
    }

    public void setGn(String gn) {
        this.gn = gn;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public int getSuid() {
        return suid;
    }

    public void setSuid(int suid) {
        this.suid = suid;
    }

    public String getSnn() {
        return snn;
    }

    public void setSnn(String snn) {
        this.snn = snn;
    }

    public String getSimg() {
        return simg;
    }

    public void setSimg(String simg) {
        this.simg = simg;
    }

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getRnn() {
        return rnn;
    }

    public void setRnn(String rnn) {
        this.rnn = rnn;
    }

    public String getRimg() {
        return rimg;
    }

    public void setRimg(String rimg) {
        this.rimg = rimg;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOutside() {
        return outside;
    }

    public void setOutside(int outside) {
        this.outside = outside;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getCv() {

        if (cv==null) {
            return "";
        }
        String[] split = cv.split(",");
        if (split!=null) {
            if (split.length>0) {
              return split[0];
            }
        }
        return "";
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public int getCt() {
        return ct;
    }

    public void setCt(int ct) {
        this.ct = ct;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNn() {
        return nn;
    }

    public void setNn(String nn) {
        this.nn = nn;
    }

    public int getGnum() {
        return gnum;
    }

    public void setGnum(int gnum) {
        this.gnum = gnum;
    }

    public String getCpn() {
        return cpn;
    }

    public void setCpn(String cpn) {
        this.cpn = cpn;
    }

    public String getCpimg() {
        return cpimg;
    }

    public void setCpimg(String cpimg) {
        this.cpimg = cpimg;
    }

    public int getRlevel() {
        return rlevel;
    }

    public void setRlevel(int rlevel) {
        this.rlevel = rlevel;
    }

    public String getRgimg() {
        return rgimg;
    }

    public void setRgimg(String rgimg) {
        this.rgimg = rgimg;
    }

    private String voice;

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }
}
