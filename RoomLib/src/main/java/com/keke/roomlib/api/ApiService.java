package com.keke.roomlib.api;


import com.keke.roomlib.api.bean.EBBaseEntity;
import com.keke.roomlib.bean.DiyMallBean;
import com.keke.roomlib.bean.HeartMoShiBean;
import com.keke.roomlib.bean.RoomBean;
import com.keke.roomlib.bean.RoomHostInfoBean;
import com.keke.roomlib.bean.RoomTallyInfo;
import com.keke.roomlib.bean.UserBean;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/7/2.
 */

public interface ApiService {


    @GET()
    Call<ResponseBody> getdata(@Url String url, @QueryMap Map<String, String> map);

    @GET
    Observable<EBBaseEntity<UserBean>> getUserInfo(@Url String url, @QueryMap Map<String, String> map);

    @GET
    Observable<EBBaseEntity<ArrayList<UserBean>>> getUserInfoList(@Url String url, @QueryMap Map<String, String> map);

    @GET
    Observable<EBBaseEntity<RoomHostInfoBean>> getRoomHostInfo(@Url String url, @QueryMap Map<String, String> map);

    @GET
    Observable<EBBaseEntity<ArrayList<RoomHostInfoBean>>> getRoomPTInfo(@Url String url, @QueryMap Map<String, String> map);

    @GET
    Observable<EBBaseEntity<HeartMoShiBean>> getHeartMoData(@Url String url, @QueryMap Map<String, String> map);

    /**
     * 房间背景图片列表
     **/
    @GET
    Observable<EBBaseEntity<ArrayList<DiyMallBean.DiyItemBean>>> getMyselefDIy(@Url String url, @QueryMap Map<String, String> map);
    //
    @POST
    Call<ResponseBody> getPostData(@Url String url, @Body RequestBody body);

    @GET
    Observable<EBBaseEntity<RoomTallyInfo>> getRoomTallyInfo(@Url String url, @QueryMap Map<String, String> map);

    @GET
    Observable<EBBaseEntity<RoomBean>> getRoomJoin(@Url String url, @QueryMap Map<String, String> map);




    //    @GET
//    Observable<EBBaseEntity<InitBean>> getInit(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ExprVerBean>> getExprVer(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ArrayList<UserBean>>> getUserInfoList(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<UserHomeBean>> getUserInfoDetail(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<FollowUserBean>> getFollowUserList(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<CpBean>> getCpbean(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<MIngXIBean>> getFinancediamondDetailList(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<RoomBean>> getRoomJoin(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ZanDanBean>> getZaDan(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ArrayList<ZaDanTcImbean>>> getZaDanIm(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ArrayList<ZanDanBean>>> getZaDanZj(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<RoomHostInfoBean>> getRoomHostInfo(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ArrayList<RoomHostInfoBean>>> getRoomPTInfo(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ArrayList<RoomTypeBean>>> getRoomType(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ArrayList<GiftDownBean>>> getGiftDown(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ArrayList<UserPackBean>>> getUserPackBean(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ArrayList<RoomCbdBean>>> getRoomHotCbd(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ArrayList<String>>> getRoomHotCbdType(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ArrayList<String>>> getRoomId(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<MeiliZhiBean>> getFinanceCharmDetailList(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<kekebiBean>> getFinanceKekeDetailList(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ChongZhiBean>> getFinancePlayRuler(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ArrayList<UpImageBean>>> getUpImage(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ArrayList<JieZhiLIstBean>>> getJieZhiList(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ShareBindBean>> getUserAccountInit(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<GiftListBean>> getGiftList(@Url String url, @QueryMap Map<String, String> map);
//
//    @POST
//    Observable<EBBaseEntity<GiftListBean>> getGiftpagList(@Url String url, @Body RequestBody body);
//
//    @GET
//    Observable<EBBaseEntity<CPRankListBean>> getCPRankList(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<DiyMallBean>> getDiyGoodList(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<RoomListBean>> getRoomLIstBean(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<UninInfoBean>> getUninInfoBean(@Url String url, @QueryMap Map<String, String> map);

//
//    /**
//     * 手机密码登录
//     **/
//    @POST
//    Observable<EBBaseEntity<UserBean>> getPassWordLogin(@Url String url, @Body RequestBody body);
//
//
//    /**
//     * 支付
//     **/
//    @POST
//    Observable<EBBaseEntity<ZhiFuBean>> getPay(@Url String url, @Body RequestBody body);
//
//    /**
//     * 获取验证码
//     **/
//    @POST
//    Call<ResponseBody> getSmsCode(@Url String url, @Body RequestBody body);
//
//    /**
//     * 黑名单列表
//     **/
//    @GET
//    Observable<EBBaseEntity<FollowBlackBean>> getBlackList(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 管理员列表
//     **/
//    @GET
//    Observable<EBBaseEntity<FollowAdminBean>> getAdminList(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 管理员列表
//     **/
//    @GET
//    Observable<EBBaseEntity<WealthDetailListBean>> getWealthDetai(@Url String url, @QueryMap Map<String, String> map);
//


//
//    /**
//     * 房间背景图片列表
//     **/
//    @GET
//    Observable<EBBaseEntity<ArrayList<RankGiftBean.ListBean.GiftBean>>> getGiftOneToOne(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 房间背景图片列表
//     **/
//    @GET
//    Observable<EBBaseEntity<ArrayList<LocationBean>>> getLocation(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 房间背景图片列表
//     **/
//    @GET
//    Observable<EBBaseEntity<AllGiftListBean>> getRoomLs(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 房间背景图片列表
//     **/
//    @GET
//    Observable<EBBaseEntity<ArrayList<ShouYeItemBean>>> getShouyeItem(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 房间背景图片列表
//     **/
//    @GET
//    Observable<EBBaseEntity<RankGiftBean>> getRankGift(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 当日房间流水详情
//     **/
//    @GET
//    Observable<EBBaseEntity<ArrayList<RoomWealthXQItem>>> getRoomWealthXQ(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 当日房间流水详情
//     **/
//    @GET
//    Observable<EBBaseEntity<RunningWaterbean>> getRoomWealthXQT(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 房间背景图片列表
//     **/
//    @GET
//    Observable<EBBaseEntity<ArrayList<CarDownBean>>> getCarDown(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 房间背景图片列表
//     **/
//    @GET
//    Observable<EBBaseEntity<ArrayList<BackGroundBean>>> getBackGroundList(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 房间用户列表
//     **/
//    @GET
//    Observable<EBBaseEntity<ArrayList<UserBean>>> getRoomUserList(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 评论列表
//     **/
//    @GET
//    Observable<EBBaseEntity<PingLunListBean>> getCommentList(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 回复列表
//     **/
//    @GET
//    Observable<EBBaseEntity<HuiFuListBean>> getReplyList(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 发布评论
//     **/
//    @POST
//    Observable<EBBaseEntity<PingLunBean>> getCommentFabu(@Url String url, @Body RequestBody body);
//
//    /**
//     * 使用锤子
//     **/
//    @POST
//    Observable<EBBaseEntity<ArrayList<ZanDanBean>>> getZaDanShiYong(@Url String url, @Body RequestBody body);
//
//    /**
//     * 发布回复
//     **/
//    @POST
//    Observable<EBBaseEntity<HuiFuBean>> getReplyFabu(@Url String url, @Body RequestBody body);
//
//    /**
//     * 房间用户列表
//     **/
//    @GET
//    Observable<EBBaseEntity<ArrayList<RoomBean>>> getRoomList(@Url String url, @QueryMap Map<String, String> map);
//
//
//    /**
//     * 房间魅力榜 和富豪榜共用
//     **/
//    @GET
//    Observable<EBBaseEntity<FollowRoomBanDanBean>> getRoomSelfCharts(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 房间魅力榜 和富豪榜共用
//     **/
//    @GET
//    Observable<EBBaseEntity<RoomListBean>> getRoomCharts(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 房间魅力榜 和富豪榜共用
//     **/
//    @GET
//    Observable<EBBaseEntity<GuardListBean>> getGuardrank(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 财富等级
//     **/
//    @GET
//    Observable<EBBaseEntity<ArrayList<WealthRankListBean.ListBean>>> getWealthRankList(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 财富等级
//     **/
//    @GET
//    Observable<EBBaseEntity<ArrayList<BannerBean>>> getBannerList(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 财富等级
//     **/
//    @GET
//    Observable<EBBaseEntity<XZbean>> getXunZhangList(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 财富等级
//     **/
//    @GET
//    Observable<EBBaseEntity<BannerBean>> getScreenList(@Url String url, @QueryMap Map<String, String> map);
//
//
//    @GET
//    Observable<EBBaseEntity<AllDTBean>> getDongtaiList(@Url String url, @QueryMap Map<String, String> map);
//
//

//
//
//    @GET
//    Observable<EBBaseEntity<RoomTallyInfo>> getRoomTallyInfo(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<SystemVersionBean>> getSystemVersion(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<DongTaiBean>> getDongtai(@Url String url, @QueryMap Map<String, String> map);
//
//    @Streaming
//    @GET
//    Observable<ResponseBody> downLoadFile(@NonNull @Url String url);
//
//    @GET
//    Observable<EBBaseEntity<CheckAccountPwdBean>> checkAccountPwd(@NonNull @Url String url, @QueryMap Map<String, String> map);
//
//    @Streaming //大文件时要加不然会OOM
//    @GET
//    Call<ResponseBody> downloadFiles(@Url String url);
//
//    @Multipart
//    @PUT
//    Call<ResponseBody> uploadFile(@NonNull @Url String url, @Part MultipartBody.Part file);
//
//
//    @Multipart
//    @POST
//    Call<ResponseBody> uploadMusicFile(@NonNull @Url String url, @Part MultipartBody.Part file);
//
//    @GET
//    Observable<EBBaseEntity<CouplingInfoBean>> getCouplingInfo(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<SearchBean>> getSearch(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ArrayList<GuardBean>>> getGuardType(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<ToolsSelectDataBean>> getToolsSelect(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Call<ResponseBody> getH5Type(@NonNull @Url String url, @QueryMap Map<String, String> map);
//
//
//    @POST
//    Call<ResponseBody> postH5Type(@NonNull @Url String url, @Body RequestBody body);
//
//
//    @GET
//    Observable<EBBaseEntity<GetMainBean>> getMain(@Url String url, @QueryMap Map<String, String> map);
//
//    @GET
//    Observable<EBBaseEntity<TemplateBlockBean>> getMoreBlock(@Url String url, @QueryMap Map<String, String> map);
//
//
//    /**
//     * 屏蔽用户
//     */
//
//    @POST
//    Call<ResponseBody> postShieldingUser(@Url String url, @Body RequestBody body);
//
//
//    @GET
//    Observable<EBBaseEntity<ArrayList<RemoveUserListBean>>> getRemoveUserList(@Url String url, @QueryMap Map<String, String> map);
//
//
//    @GET
//    Observable<EBBaseEntity<DTlistMoreBean>> getTrendsLikeList(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 每日签到判断
//     */
//    @GET
//    Observable<EBBaseEntity<SiginInBean>> getSingle(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 签到
//     */
//    @GET
//    Observable<EBBaseEntity<ArrayList<SignedBean>>> getSindleIn(@Url String url, @QueryMap Map<String, String> map);
//   /*电台房*/
//    @GET
//    Observable<EBBaseEntity<ArrayList<GuardCacheBean>>> getGuardCache(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 房间红包列表
//     */
//    //@GET
//    @GET
//    Observable<EBBaseEntity<ArrayList<RedEnvelopBean>>> getReadEnvelop(@Url String url, @QueryMap Map<String, String> map);
//  /**房间红包详情*/
//    @GET
//    Observable<EBBaseEntity<ArrayList<RedEnvelopBean>>> getReadEvenlopDetalis(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 抢红包
//     **/
//    @POST
//    Observable<EBBaseEntity<GetRedBean>> postGetRed(@Url String url, @Body RequestBody body);
//
//    /**
//     * 心愿墙列表
//     */
//    @POST
//    Observable<EBBaseEntity<ArrayList<WishWallBean>>> postWishList(@Url String url, @Body RequestBody body);
//
//    /**
//     * 心愿详情
//     */
//    @GET
//    Observable<EBBaseEntity<WishWallBean>> getWishInfo(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 话题列表
//     */
//    @GET
//    Observable<EBBaseEntity<TopicListBean>> getTrendsTopicList(@Url String url, @QueryMap Map<String, String> map);
//    /**
//     * 话题详情
//     */
//    @GET
//    Observable<EBBaseEntity<TopicDetailBean>> getTrendsTopicDetail(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 亲密详情
//     */
//    @GET
//    Observable<EBBaseEntity<IntimateBean>> getFriendsIntimate(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 帮助与反馈列表
//     */
//    @GET
//    Observable<EBBaseEntity<HelpListBean>> getHelpList(@Url String url, @QueryMap Map<String, String> map);
//    /**
//     * 一键参与红包
//     */
//    @GET
//    Observable<EBBaseEntity<RedEnvelopBean>> getRedPacketOneKey(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 访客和好友数量
//     */
//    @GET
//    Observable<EBBaseEntity<NoticeNumBean>> getNoticeNum(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 心愿墙条件筛选列表
//     */
//    @GET
//    Observable<EBBaseEntity<ArrayList<WishWallChooseBean>>> getWishSearchTypeList(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 发送心愿列表
//     */
//    @GET
//    Observable<EBBaseEntity<ArrayList<WishWallChooseBean.WishWallChooseItemBean>>> getWishTypeList(@Url String url, @QueryMap Map<String, String> map);
//
//    /**
//     * 发布心愿
//     */
//    @POST
//    Observable<EBBaseEntity<WishWallBean>> postWish(@Url String url, @Body RequestBody body);
}
