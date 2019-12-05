package com.keke.roomlib.api;

/**
 * Created by Administrator on 2018/7/16.
 */

public class ApiConstant {


    /**
     * 初始化
     */
    public static final String APP_INITIAL = "system/init";
    /**
     * 第三方登录
     */
    public static final String USER_LOGIN = "user/login";
    /**
     * 应用内绑定手机
     */
    public static final String USER_BIND_PHONE = "user/bind_phone";
    /**
     * 第三方绑定
     */
    public static final String USER_THIRD_MOVE = "user/third_move";
    public static final String USER_VERIFY_GET_TOKEN = "user/verify/get_token";

    /**
     * 房间背景图片列表
     */
    public static final String USER_BACKGROUND_LIST = "room/background_list";
    /**
     * 房间背景图片列表
     */
    public static final String ROOM_BACKGROUND = "room/background";

    /**
     * 房间类型接口
     */
    public static final String ROOM_TYPE_LIST = "room/type_list";

    /**
     * 设置房间类型
     */
    public static final String ROOM_SET_TYPE = "room/set_type";

    /**
     * 手机密码登录
     */
    public static final String USER_PASSWORD_LOGIN = "user/password/login";
    /**
     * 退出登录
     */
    public static final String USER_LOGIN_OUT = "user/login_out";

    /**
     * 意見反饋
     */
    public static final String USER_EDIT_FEEDBACK = "user/feedback";


    /**
     * 手机注册接口
     */
    public static final String USER_PHONE_REGISTER = "user/phone/register";


    /**
     * 设置密码
     */
    public static final String USER_SET_PASSWORD = "user/set_password";

    /**
     * 密码效驗
     */
    public static final String USER_CHECK_PASSWORD = "user/check_password";
    /**
     * 密码效驗
     */
    public static final String USER_SET_PASSWORD_BY_PASSWORD = "user/set_password_by_password";

    /**
     * 手机注册验证码
     */
    public static final String USER_SMS_CODE = "user/sms/code";
    /**
     * 壳壳币兑换
     */
    public static final String USER_FINANCE_EXCHANGE = "finance/exchange";
    /**
     * 验证接口
     */
    public static final String USER_PHONE_CHECK = "user/phone_check";
    /**
     * 手机号验证接口
     */
    public static final String USER_CHECK_PLAT_PHONE = "user/check_plat_phone";
    /**
     * 第三方手机绑定
     */
    public static final String USER_THIRDPART_BIND_PHONE = "user/thirdpart_bind_phone";

    /**
     * 获取用户
     */
    public static final String USER_INFO = "user/info";
    /**
     * 获取用户详细信息
     */
    public static final String USER_INFO_DETAIL = "user/info_detail";
    /**
     * 获取cp详细信息
     */
    public static final String USER_COUPLING_INFO = "user/coupling_info";
    /**
     * 获取用户详细信息
     */
    public static final String USER_ROOM_USER_INFO = "user/room_user_info";
    /**
     * 编辑资料
     */
    public static final String USER_EDIT = "user/edit";
    /**
     * 上传图片
     */
    public static final String USER_PUT_ALBUM_URL = "system/put_file_url";
    public static final String COUPLING_GETRING = "coupling/get_ring";

    /**
     * 关注用户
     */
    public static final String FRIENDS_FOLLOW = "friends/follow";
    /**
     * 关注用户列表
     */
    public static final String FRIENDS_FOLLOW_LIST = "friends/follow_list";
    /**
     * 粉丝列表
     */
    public static final String FRIENDS_FANS_LIST = "friends/fans_list";
    /**
     * 房间黑名单列表
     */
    public static final String ROOM_BLACK_LIST = "room/block_list";
    /**
     * 房间黑名单设置
     */
    public static final String ROOM_BLACK = "room/block";
    /**
     * 房间管理员列表
     */
    public static final String ROOM_ROLE_LIST = "room/role_list";
    /**
     * 房间管理员设置
     */
    public static final String ROOM_ROLE = "room/role";
    public static final String MEDAL_USER_PROIVATE = "medal/user/private";

    /**
     * 举报
     */
    public static final String FRIENDS_REPORT = "friends/report";
    public static final String ROOM_REPORT = "room/report";
    /**
     * 拉黑用户
     */
    public static final String FRIENDS_BLOCK = "friends/block";

    /**
     * 账号绑定初始化接口
     */
    public static final String USER_ACCOUNT_INIT = "user/account_init";


    /**
     * 拉黑用户
     */
    public static final String FRIENDS_BLOCK_LIST = "friends/blocks_list";

    /**
     * 拉黑用户
     */
    public static final String COUPLING_REPLACE_RING = "coupling/replace_ring";

    /**
     * 浏览足迹
     */
    public static final String FRIENDS_BROWSE_LIST = "friends/browse_list";

    /**
     * 访客
     */
    public static final String FRIENDS_VISITOR_LIST = "friends/visitor_list";

    /**
     * 访客
     */
    public static final String HAMMER_WINNING_LIST = "hammer/winning_list";

    /**
     * 账户信息
     */
    public static final String FINANCE_CASH = "finance/cash";


    /**
     * 钻石充值规则
     */
    public static final String FINANCE_PLAY_RULES = "finance/pay/rules";


    /**
     * 钻石充值规则
     */
    public static final String FINANCE_PEAL_PLAY_RULES = "finance/pearl/pay/rules";


    /**
     * 钻石兑换珍珠
     */
    public static final String FINANCE_DIAMOND_EXCHANGE_PEARl = "finance/diamond/exchange_pearl";


    /**
     * 钻石充值/支付
     */
    public static final String FINANCE_PAY_DO = "finance/pay/do";


    /**
     * 房间开启（重开）心动
     */
    public static final String ROOM_HEART_OPEN = "room/heart/open";

    /**
     * 房间开启（重开）计数
     */
    public static final String ROOM_TALLY_OPEN = "room/tally/open";

    /**
     * 房间关闭（重开）计数
     */
    public static final String ROOM_TALLY_CLOSE = "room/tally/close";

    /**
     * 房间开启（重开）团战
     */
    public static final String ROOM_MELEE_OPEN = "room/melee/open";

    /**
     * 房间关闭（重开）团战
     */
    public static final String ROOM_MELEE_CLOSE = "room/melee/close";


    /**
     * 房间关闭
     */
    public static final String ROOM_HEART_CLOSE = "room/heart/close";


    /**
     * 房间开始选择心动
     */
    public static final String ROOM_HEART_SELECT = "room/heart/select";

    /**
     * 守护-获取守护排行列表（被守护人）
     */
    public static final String GUARD_GET_RANK = "guard/get_rank";

    /**
     * 房间开始选择心动
     */
    public static final String ROOM_HEART_PUBLISH = "room/heart/publish";

    /**
     * 房间开始选择心动
     */
    public static final String ROOM_HEART_INFO = "room/heart/info";

    /**
     * 房间心动互选
     */
    public static final String ROOM_HEART_MICRO_SELECT = "room/heart/micro/select";

    /**
     * 房间开始选择心动
     */
    public static final String ROOM_TALLY_INFO = "room/tally/info";

    /**
     * 设置话题
     */
    public static final String ROOM_TOPIC_SET = "room/topic/set";


    /**
     * 钻石明细
     */
    public static final String FINANCE_DIAMOND_DETAIL_LIST = "finance/diamond_detail_list";

    /**
     * 金豆明细
     */
    public static final String FINANCE_PEARL_DETAIL_LIST = "finance/pearl/detail_list";


    /**
     * 魅力值明细
     */
    public static final String FINANCE_CHARM_DETAIL_LIST = "finance/charm_detail_list";


    /**
     * 壳壳币明细
     */
    public static final String FINANCE_KK_DETAIL_LIST = "finance/kk_detail_list";


    //          房间

    /**
     * 进入房间
     */

    public static final String ROOM_JOIN = "room/join";

    /**
     * 进入房间
     */

    public static final String VOICE_AUDIO_DESCRIBE = "voice/audio_describe";


    /**
     * 房间用户列表
     */

    public static final String ROOM_USER_LIST = "room/user_list";
    /**
     * 进入房间
     */

    public static final String ROOM_QUit = "room/quit";


    /**
     * 获取主持买
     */

    public static final String ROOM_HOSTMIC_INFO = "room/hostmicrophone/info";


    /**
     * 上麦主持买
     */

    public static final String ROOM_HOSTMIC_INTO = "room/hostmicrophone/into";


    /**
     * 下麦主持买
     */

    public static final String ROOM_HOSTMIC_OUT = "room/hostmicrophone/out";


    /**
     * 麦主持买 开关麦克风
     */

    public static final String ROOM_HOSTMIC_MKF = "room/hostmicrophone/";


    /**
     * 普通买上麦列表
     */

    public static final String ROOM_MICROPHONE_APPLY_LIST = "room/microphone/apply_list";


    /**
     * 普通买上麦申请
     */

    public static final String ROOM_MICROPHONE_APPLY = "room/microphone/apply";


    /**
     * 普通买上麦申请
     */

    public static final String GIFT_ROOM_DAY_LOG = "gift/room_day_log";
    public static final String GIFT_PEARL_ROOM_DAY_LOG = "gift_pearl/get_room_day_log";


    /**
     * 普通买上麦申请
     */

    public static final String GIFT_GET_RSNK = "gift/get_rank";


    /**
     * 普通买下麦
     */

    public static final String ROOM_MICROPHONE_OUT = "room/microphone/out";


    /**
     * 普通买跳 麦
     */

    public static final String ROOM_MICROPHONE_JUMP = "room/microphone/jump";


    /**
     * 普通买上麦答应
     */

    public static final String ROOM_MICROPHONE_ACCEPT = "room/microphone/accept";


    /**
     * 普通买上麦拒绝
     */

    public static final String ROOM_MICROPHONE_REJECT = "room/microphone/reject";

    /**
     * 普通买上麦取消
     */

    public static final String ROOM_MICROPHONE_CABCLEL = "room/microphone/cancel";


    /**
     * 普通买列表
     */

    public static final String ROOM_MICROPHONE_LIST = "room/microphone/list";


    /**
     * 普通买包上麦
     */

    public static final String ROOM_MICROPHONE_INTO_REQ = "room/microphone/into_req";


    /**
     * 普通买包下麦
     */

    public static final String ROOM_MICROPHONE_OUT_REQ = "room/microphone/out_req";

    /**
     * 主持买包下麦
     */

    public static final String ROOM_HOSTMICROPHONE_OUT_REQ = "room/hostmicrophone/out_req";


    /**
     * 普通买包下麦
     */

    public static final String ROOM_MICROPHONE_APPLY_CLEAN = "room/microphone/apply_clean";


    /**
     * 设置老板位
     */

    public static final String ROOM_MICROPHONE_BOSS = "room/microphone/";

    /**
     * 设置老板位
     */

    public static final String ROOM_MICROPHONE_LOCK = "room/microphone/";

    /**
     * 设置老板位
     */

    public static final String VOICE_AGE_GENDER_ABALYSIS = "/voice/age_gender/analysis";

    /**
     * 收藏房间
     */

    public static final String ROOM_COLLECT = "room/collect";

    /**
     * 房间踢人
     */

    public static final String ROOM_SHOTOFF = "room/shotoff";

    /**
     * 房间编辑
     */

    public static final String ROOM_EDIT = "room/edit";

    /**
     * 房间开启
     */

    public static final String ROOM_OPEN = "room/open";

    /**
     * 下麦
     */

    public static final String ROOM_OUTMICRO_CONFIRM = "room/outmicro/confirm";

    /**
     * 上麦
     */

    public static final String ROOM_INTOMICRO_CONFIRM = "room/intomicro/confirm";


    /**
     * 出去
     */

    public static final String ROOM_QUIT_CONFIRM = "room/quit/confirm";


    /**
     * 进房间
     */

    public static final String ROOM_JOIN_CONFIRM = "room/join/confirm";


    /**
     * 房间密码
     */

    public static final String ROOM_PASSWORD = "room/password";

    /**
     * 房间密码
     */

    public static final String ROOM_SCREEN_OPEN = "room/screen_open";

    /**
     * 表情包版本
     */

    public static final String SYSTEM_EXPRESSION = "system/expression";

    /**
     * 礼物预加载
     */

    public static final String GIFT_ANIMATE_RESOURCES = "gift/animate_resources";

    /**
     * 礼物预加载
     */

    public static final String GIFT_PEARL_ANIMATE_RESOURCES = "gift_pearl/get_animate_resources";


    /**
     * 商城列表
     */

    public static final String MALL_GOOD_LIST = "mall/goods_list";

    /**
     * 商城列表
     */

    public static final String MALL_USER_GOOD_LIST = "mall/user_goods_list";
    /**
     * 装扮接口
     */

    public static final String MALL_DIY_ATTICE = "mall/diy_attire";


    /**
     * 取消装扮接口
     */

    public static final String MALL_DIY_CANCEL = "mall/diy_cancel";

    /**
     * 礼物列表
     */

    public static final String GIFT_GIFT_LIST = "gift/gift_list";


    /**
     * 礼物列表
     */

    public static final String GIFT_PEARL_LIST = "gift_pearl/list";


    /**
     * 获取勋章列表
     */

    public static final String MEDAL_GET_LIST = "medal/get_list";


    /**
     * 勋章-佩戴
     */

    public static final String MEDAL_WEAR = "medal/wear";

    /**
     * 取消勋章-佩戴
     */

    public static final String MEDAL_CANCEL_WEAR = "medal/cancel_wear";


    /**
     * 赠送礼物
     */

    public static final String GIFT_GIVE_GIFT = "gift/give_gift";
    public static final String GIFT_PEARL_GIVE_GIFT = "gift_pearl/give_gift";


    /**
     * 背包赠送礼物
     */

    public static final String GIFT_BAG_GIVE = "gift_bag/give";

    /**
     * 背包赠送礼物
     */

    public static final String GIFT_PEARL_BAG_GIVE = "gift_pearl/bag/give";


    /**
     * 赠送礼物
     */

    public static final String MALL_GOODS_SHOPPING = "mall/goods_shopping";

    /**
     * 赠送礼物
     */

    public static final String ROOM_HOT_RECO = "room/hot/reco";

    /**
     * 赠送礼物
     */

    public static final String ROOM_ROOMS = "room/rooms";

    /**
     * 赠送礼物
     */

    public static final String ROOM_AGORA_TOKEN = "room/agora/token";

    /**
     * 赠送礼物
     */

    public static final String ROOM_HOT_HEAD = "room/hot/head";

    public static final String ROOM_HOT_NONE = "room/hot/none";
    public static final String ROOM_HOT_NONE_TYPE = "room/hot/none/type";

    public static final String ROOM_HOT_CBD = "room/hot/cbd";
    public static final String ROOM_HOT_CBD_TYPE = "room/hot/cbd/type";
    /**
     * 房间魅力榜
     */

    public static final String FINANCE_ROOM_CHARM_CHARTS = "finance/room_charm_charts";


    /**
     * 房间富豪榜
     */

    public static final String FINANCE_ROOM_WEALTH_CHARTS = "finance/room_wealth_charts";


    /**
     * 魅力榜
     */

    public static final String FINANCE_CHARM_CHARTS = "finance/charm_charts";

    /**
     * 富豪榜榜
     */

    public static final String FINANCE_WEALTH_CHARTS = "finance/wealth_charts";

    /**
     * 声望排行榜榜
     */

    public static final String FINANCE_REPUTATION_CHARTS_CHARTS = "finance/reputation/charts/list";


    /**
     * 名人排行榜榜
     */

    public static final String FINANCE_CELEBRITY_CHARTS_CHARTS = "finance/celebrity/charts/list";

    /**
     * 房间内名人排行榜列表
     */

    public static final String FINANCE_CELEBRITY_ROOM_USER_CHARTS_CHARTS = "finance/celebrity/room/user/charts/list";

    /**
     * 房间内声望排行榜列表
     */

    public static final String FINANCE_REPUTATION_ROOM_USER_CHARTS_CHARTS = "finance/reputation/room/user/charts/list";


    /**
     * 富豪榜榜
     */

    public static final String MALL_GET_USER_ATTIRE = "mall/get_user_attire";


    /**
     * 控制麦克风
     */

    public static final String ROOM_MICROPHONE = "room/microphone/";


    /**
     * 控制麦克风
     */

    public static final String ROOM_VOLUME_CLOSED = "room/volume_closed";

    /**
     * 房间关闭
     */

    public static final String ROOM_CLOSE = "room/close";

    /**
     * 财富等级
     */

    public static final String FINACE_WEALTH_RANK_LIST = "finance/wealth_rank_list";
    /**
     * 魅力等级
     */

    public static final String FINACE_CHARM_RANK_LIST = "finance/charm_rank_list";
    /**
     * 名人等级
     */

    public static final String FINACE_CELEBRITY_RANK_LIST = "finance/celebrity/rank/list";
    /**
     * 声望等级
     */

    public static final String FINACE_REPUTATION_RANK_LIST = "finance/reputation/rank/list";

    /**
     * 房间总财富榜
     */

    public static final String FINANCE_ROOM_SELF_WEALTH_CHARTS = "finance/room_self_wealth_charts";
    /**
     * 坐骑与加载
     */

    public static final String MALL_ANIMATE_RESOURCES = "mall/animate_resources";
    /**
     * 坐骑与加载
     */

    public static final String FRIENDS_FRIEND_LIST = "friends/friend_list";
    /**
     * 首页图标
     */

    public static final String ROOM_HOME_ICONS = "room/home_icons";

    /**
     * 首页图标
     */

    public static final String USER_AD_SCREEN = "ad/screen";

    /**
     * 首页图标
     */

    public static final String ROOM_LEAVE_CHECK = "room/leave/check";

    /**
     * 首页图标
     */

    public static final String FINANCE_ROOM_WEALTH_DETAIL_TOTAL = "finance/room_wealth_detail_total";


    /**
     * 首页热门列表
     */

    public static final String ROOM_HOT_LIST = "room/hot_list";


    /**
     * 首页新人
     */

    public static final String ROOM_COllECT_LIST = "room/collect_list";


    /**
     * 首页热门列表
     */

    public static final String ROOM_NEW_LIST = "room/new_list";


    /**
     * 首页热门列表
     */

    public static final String ROOM_LIST = "room/list";


    /**
     * 礼物背包列表
     */

    public static final String GIFT_BAG_LIST = "gift_bag/list";


    /**
     * 礼物背包列表
     */

    public static final String GIFT_PEARL_BAG_LIST = "gift_pearl/bag/list";


    /**
     * 收到礼物列表
     */

    public static final String GIFT_OBTAIN_GIFT_LIST = "gift/obtain_gift_list";
    public static final String GIFT_PEARL_OBTAIN_GIFT_LIST = "gift_pearl/obtain_list";

    /**
     * 用户所在房间列表
     */

    public static final String ROOM_ROLE_ROOM_LIST = "room/role_room_list";

    /**
     * 用户所在房间列表
     */

    public static final String FRIENDS_COUNTS = "friends/counts";


    /**
     * 广告bannner
     */

    public static final String AD_BANNER = "ad/banner";


    /**
     * 广告bannner
     */

    public static final String SYSTEM_LOCATIONS = "system/locations";


    /**
     * 发布动态
     */

    public static final String TRENDS_TRENDS_RELEASE = "trends/trends_release";


    /**
     * 动态列表
     */

    public static final String TRENDS_GET_TRENDS_LIST = "trends/get_trends_list";


    /**
     * 用户动态列表
     */

    public static final String TRENDS_GET_TRENDS_USER_LIST = "trends/get_user_trends";


    /**
     * 动态列表
     */

    public static final String TRENDS_GET_TRENDS = "trends/get_trends";


    /**
     * 删除a动态
     */

    public static final String TRENDS_TRENDS_DELETE = "trends/trends_delete";


    /**
     * 点赞动态
     */

    public static final String TRENDS_TRENDS_LiKE = "trends/trends_like";
    public static final String COUPLING_UPDATE = "coupling/update";


    /**
     * 内容阅读计数
     */

    public static final String TRENDS_TRENDS_READ_RELEASE = "trends/trends_read_release";

    /**
     * 内容阅读计数
     */

    public static final String USER_VERIFY_VERIFYCOUNT = "user/verify/verifyCount";


    public static final String ROOM_INFO = "room/info";


    /**
     * 获取评论列表
     */

    public static final String TRENDS_GET_TRENDS_COMMENT_LIST = "trends/get_trends_comment_list";

    /**
     * 获取回复列表
     */

    public static final String TRENDS_GET_TRENDS_REPLY_LIST = "trends/get_trends_reply_list";

    /**
     * 发布回复
     */

    public static final String TRENDS_GET_TRENDS_REPLY_RELEASE = "trends/trends_reply_release";

    /**
     * 发布回复
     */

    public static final String TRENDS_GET_TRENDS_COMMENT_RELEASE = "trends/trends_comment_release";


    /**
     * 评论删除
     */

    public static final String TRENDS_GET_TRENDS_COMMENT_DELETE = "trends/trends_comment_delete";

    /**
     * 回复删除
     */

    public static final String TRENDS_GET_TRENDS_REPLY_DELETE = "trends/trends_reply_delete";


    /**
     * 评论内容点赞or取消点赞
     */

    public static final String TRENDS_GET_TRENDS_COMMENT_LIKE = "trends/trends_comment_like";


    /**
     * 回复内容点赞or取消点赞
     */

    public static final String TRENDS_GET_TRENDS_REPLY_LIKE = "trends/trends_reply_like";

    /**
     * 普通麦上麦
     */

    public static final String ROOM_MICROPHONE_INTO = "room/microphone/into";


    /**
     * 用户当前所在房间
     */

    public static final String ROOM_USER_CURRENT = "room/user_current";

    /**
     * 获取砸蛋信息
     */

    public static final String HAMMER_BALANCE = "hammer/balance";


    /**
     * 获取砸蛋中奖记录
     */

    public static final String HAMMER_PRIZE_RECORD = "hammer/prize_record";


    /**
     * 获取砸蛋本期奖池
     */

    public static final String HAMMER_AWARD_POLL = "hammer/award_poll";

    /**
     * 使用锤子
     */

    public static final String HAMMER_USED = "hammer/used";


    /**
     * 砸蛋兑换锤子
     */

    public static final String HAMMER_EXCHANGE = "hammer/exchange";


    /**
     * 获取砸蛋幸运榜默认前10
     */

    public static final String HAMMER_LUCKY_PRIZE_RECORD = "hammer/lucky_prize_record";


    /**
     * 小黑屋
     */

    public static final String USER_FORBID_GET = "user/forbid/get";
    /**
     * 开屏广告
     */

    public static final String AD_OPEN = "ad/open";


    /**
     * 软件升级
     */

    public static final String SYSTEM_VERSION = "system/version";


    /**
     * 软件升级
     */

    public static final String USER_SEARCH = "user/search";

    /**
     * 房间用户是否存在
     */

    public static final String ROOM_USER_EXIST = "room/user_exist";

    /**
     * 房间随机列表
     */

    public static final String ROOM_RANDOM_LIST = "room/random_list";


    public static final String ROOM_EXP_SPECIAL = "room/exp/special";

    /**
     * 房间收益
     */

    public static final String ROOM_WEALTH_MONTH_LIST = "finance/room_wealth_month_list";


    /**
     * 房间收益
     */

    public static final String ROOM_ROOM_WEALTH_DETAIL_LIST = "finance/room_wealth_detail_list";

    /**
     * 房间随机列表
     */

    public static final String COUPLING_REQ = "coupling/req";
    public static final String COUPLING_ACCEPT = "coupling/accept";
    public static final String COUPLING_CANCEL = "coupling/cancel";
    public static final String COUPLING_GET = "coupling/get";
    public static final String COUPLING_REFUSED = "coupling/refused";
    public static final String COUPLING_GET_RANK = "coupling/get_rank";
    public static final String USER_VERIFY_GET_INFO = "user/verify/get_info";

    /**
     * 壳壳币兑换-设置账户密码
     */

    public static final String KEKE_SET_ACCOUNT_PWD = "finance/set_account_password";

    /**
     * 检测是否有兑换密码
     */
    public static final String CHECK_ACCOUNT_PWD = "finance/check_account_password";

    /**
     * 获取用户CP信息
     */
    public static final String COUPLING_INFO = "user/coupling_info";

    /**
     * 搜索
     */
    public static final String SEARCH = "user/keywords/search";

    /**
     * 守护-获取守护类型列表
     */
    public static final String GUARD_GET_TYPE = "guard/get_type";

    /**
     * 守护-开通（购买）
     */
    public static final String GUARD_PURCHASE = "guard/purchase";

    /**
     * 获取页面数据源（主页）
     */
    public static final String GET_MAIN = "template/view/get";
    /**
     * 块更多
     */
    public static final String GET_MORE_BLOCK = "template/block/get_data";

    /**
     * 踢人列表
     */
    public static final String REMOVE_USER_LIST = "room/shotoff/log";
    /**
     * 屏蔽用户
     */
    public static final String POST_PSHIELDING_USER = "trends/trends_block_user";
    /**
     * 获取签到数据
     */
    public static final String GET_SINGLE = "user/get_check_signin";
    /**
     * 签到
     */
    public static final String GET_SIGLIN_IN = "user/check_signin";
    /**
     * 加入匹配
     */
    public static final String MEET_MATCHING_JOIN = "meet/matching/join";
    /**
     * 加入匹配
     */
    public static final String MEET_MATCHING_GET = "meet/matching/get";
    /**
     * 对话等待对方
     */
    public static final String MEET_WAIT = "meet/wait";
    /**
     * 结束对话
     */
    public static final String MEET_CLOSE = "meet/close";
    /**
     * 喜欢对方
     */
    public static final String MEET_LIKE = "meet/like";
    /**
     * 兑换礼物
     */
    public static final String EXCHANGE_RECEIVE = "exchange/excode";
    /**
     * 喜欢对方
     */
    public static final String MEET_REFUSED = "meet/refused";
    /**
     * 举报
     */
    public static final String MEET_REPORT = "meet/report";

    /**
     * 礼物列表
     */
    public static final String MEET_GIFT_GET = "meet/gift/get";
    /**
     * 送礼物
     */
    public static final String MEET_GIFT_GIVE = "meet/gift/give";
    /**
     * 用户发送消息权限
     */
    public static final String FRIENDS_MSG_AUTH = "friends/msg_auth";
    /*电台房守护*/

    public static final String GUARD_CACHE = "guard/get_data";

    /*房间红包列表*/
    public static final String ROOM_READ_ENVELOP = "red/packet/list";
    /*房间红包详情列表*/
    public static final String ROOM_READ_ENVELOP_DTAILS = "red/packet/log";
    /**
     * 发红包
     */
    public static final String HANDOUT_RED = "red/packet/send";

    /**
     * 抢红包
     */
    public static final String GET_RED = "red/packet/rob";
    /**
     * 我的道具背包接口
     */
    public static final String USER_PACK_LIST = "prop/user_pack/list";
    /**
     * 用户道具背包-使用道具
     */
    public static final String USER_PACK_USE = "prop/user_pack/use";

    /**
     * 心愿墙列表
     */
    public static final String GET_WISH_LIST = "wish/list";
    /**
     * 心愿详情
     */
    public static final String GET_WISH_INFO = "wish/info";
    /**
     * 发布心愿
     */
    public static final String POST_WISH_CREATE = "wish/create";

    /**
     * 话题列表
     */
    public static final String GET_TRENDS_TOPIC_LIST = "trends_topic/get";
    /**
     * 话题详情
     */
    public static final String GET_TRENDS_TOPIC_DETAIL = "trends_topic/detail";

    /**
     * cp隐藏
     */
    public static final String GET_COUPLING_STATUS_UPDATE = "user/coupling/status";

    /**
     * 亲密度详情
     */
    public static final String GET_FRIENDS_INITMATE = "friends/intimate";

    /**
     * 帮助与反馈列表
     */
    public static final String GET_HELP_LIST = "system/help/list";

    /**
     * 同城提交
     */
    public static final String POST_SAME_CITY_CREATE = "samecity/create";

    /**
     * 红包一键参与
     */
    public static final String GET_RED_PACKET_ONEKEY = "red/packet/onekey";

    /**
     * 转盘模块-开启/关闭
     */
    public static final String ROOM_TURNTABLE_STATE = "room/turntable/state";

    /**
     * 转盘模块-开始
     */
    public static final String ROOM_TURNTABLE_PUBLISH = "room/turntable/publish";

    /**
     * 转盘模块-刷新
     */
    public static final String ROOM_TURNTABLE_REFRESH = "room/turntable/refresh";

    /**
     * 停止邂逅
     */
    public static final String GET_MEET_STOP = "meet/stop";

    public static final String ROOM_PK_SET_PUNISH = "room/pk/set/punish";

    public static final String ROOM_PK_STATE = "room/pk/state";
    public static final String ROOM_PK_PUBLISH = "room/pk/publish";

    /**
     * 访客和粉丝新增
     */
    public static final String GET_FRIENDS_NOTICE_NUM = "friends/notice_num";
    /**
     * 同意或拒绝任命角色
     */
    public static final String GET_UNION_ACCEPT_NUION_ROLE = "union/accept_union_role";
    /**
     * 审批入会
     */
    public static final String GET_UNION_APPLY_UNION_STATUS = "union/user_apply_union_status";
    /**
     * 房间查看工会信息
     */
    public static final String GET_UNION_SHOW_UNION_INFO = "union/show_union_info";
    /**
     * 申请加入工会
     */
    public static final String GET_UNION_UNION = "union/union";
    /**
     * 申请加入工会
     */
    public static final String GET_MY_UNION_URL = "union/my_union_url";
    /**
     * 申请加入工会
     */
    public static final String GET_TRENDS_GET_TRENDS_LIKE_LIST = "trends/get_trends_like_list";

    /**
     * 心愿墙条件筛选列表
     */
    public static final String GET_WISH_SEARCH_TYPE_LIST = "wish/search_type_list";

    /**
     * 心愿墙条件筛选列表
     */
    public static final String GET_WISH_TYPE_LIST = "wish/type_list";

    /**
     * 完成心愿
     */
    public static final String GET_WISH_PUSH = "wish/push";

    /**
     * 收集用户信息
     */
    public static final String GET_SYSTEM_PUSH_USER_INFO = "system/push/user_info";

    /**
     * 用户道具背包-拉取选择数据
     */
    public static final String GET_PRO_SUER_PACK_SELECT_DATA = "prop/user_pack/select_data";
 /**
     * 用户道具背包-使用道具
     */
    public static final String GET_PRO_SUER_PACK_USE = "prop/user_pack/use";
/**
     * 用户道具背包-使用道具
     */
    public static final String GET_PRO_SUER_PACK_INVALID = "user/prop/invalid";
/**
     * 用户账号注销申请
     */
    public static final String GET_USER_ACCOUNT_CANCEL = "user/account/cancel";

    /**
     * 道具商城列表
     */
    public static final String GET_PROP_MALL_LIST = "prop/mall/list";

    /**
     * 购买道具
     */
    public static final String GET_PROP_MALL_BUY = "prop/mall/buy";

}
