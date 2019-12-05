package com.keke.roomlib.api;

import android.util.Log;

import com.hjq.toast.ToastUtils;
import com.keke.roomlib.R;
import com.keke.roomlib.api.bean.EBBaseEntity;
import com.keke.roomlib.api.bean.RxHjDataObserver;
import com.keke.roomlib.bean.HeartMoShiBean;
import com.keke.roomlib.bean.RoomBean;
import com.keke.roomlib.bean.RoomHostInfoBean;
import com.keke.roomlib.bean.RoomTallyInfo;
import com.keke.roomlib.bean.UserBean;
import com.keke.roomlib.utils.Utlis;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicApi {

    public static void getUserInfo(String user_id, final ResponseListener listener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);

        HashMap<String, String> initMap = Api.initMap(map, Api.BaseUrl + ApiConstant.USER_INFO);
        Api.getDefault().getUserInfo(ApiConstant.USER_INFO, initMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxHjDataObserver<UserBean>() {

                    @Override
                    protected void onSuccees(UserBean userBean) throws Exception {
                        listener.success(userBean);
                        // TODO: 2019/11/30 获取房间背景列表
//                        if (user_id.equals(UserManager.user_id)) {
//                           // getMyselefDIy();
//                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.error(e.getMessage());
                    }
                });
    }

    /**
     * 背景列表
     */
//    public static void getMyselefDIy() {
//        HashMap<String, String> map1 = new HashMap<>();
//        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.MALL_GET_USER_ATTIRE);
//        Api.getDefault().getMyselefDIy(ApiConstant.MALL_GET_USER_ATTIRE, stringStringHashMap)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new RxHjDataObserver<ArrayList<DiyMallBean.DiyItemBean>>() {
//                    @Override
//                    protected void onSuccees(ArrayList<DiyMallBean.DiyItemBean> backGroundBean) throws Exception {
//                        UserManager.getInstance().getUserBean().setAttires(backGroundBean);
//                    }
//
//                    @Override
//                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
//
//                    }
//
//                    @Override
//                    protected void onCodeError(EBBaseEntity<ArrayList<DiyMallBean.DiyItemBean>> t) throws Exception {
//                    }
//                });
//    }
    public static void getSystemPushUesrInfo(String device_token, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("device_token", device_token);
        HashMap<String, String> stringStringHashMap = Api.initMap(true, map1, Api.BaseUrl + ApiConstant.GET_SYSTEM_PUSH_USER_INFO);
        Api.getDefault().getPostData(ApiConstant.GET_SYSTEM_PUSH_USER_INFO, getPOSTbody(stringStringHashMap))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        emplyGetPostNull(response, listener);


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        listener.error(t.getMessage());
                    }
                });
    }


    public static void getFriendsMsgAuth(String user_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();

        map1.put("user_id", user_id);
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.FRIENDS_MSG_AUTH);
        Api.getDefault().getdata(ApiConstant.FRIENDS_MSG_AUTH, stringStringHashMap)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        emplyGetPost(response, new ResponseListener() {
                            @Override
                            public void success(Object o) {

                                listener.success(o);

                            }

                            @Override
                            public void error(String s) {
                                listener.error(s);
                            }

                            @Override
                            public void onCodeError(int errorCode) {
                                listener.onCodeError(errorCode);
                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        listener.error(t.getMessage());
                    }
                });
    }

    public static void getMeetWait(String meet_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("meet_id", meet_id);
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.MEET_WAIT);
        Api.getDefault().getdata(ApiConstant.MEET_WAIT, stringStringHashMap)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        emplyGetPost(response, new ResponseListener() {
                            @Override
                            public void success(Object o) {
                                JSONObject jsonObject = (JSONObject) o;
                                try {
                                    JSONObject data = jsonObject.getJSONObject("data");
                                    long timeout = data.getLong("timeout");
                                    listener.success(timeout);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    listener.error(e.getMessage());
                                    ToastUtils.show(e.getMessage());
                                }

                            }

                            @Override
                            public void error(String s) {
                                listener.error(s);
                            }

                            @Override
                            public void onCodeError(int errorCode) {
                                listener.onCodeError(errorCode);
                            }
                        });


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        listener.error(t.getMessage());
                    }
                });
    }

    //派对管理员设置
    public static void getRoomToken(String room_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("room_id", room_id);
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_AGORA_TOKEN);
        Api.getDefault().getdata(ApiConstant.ROOM_AGORA_TOKEN, stringStringHashMap)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                //返回的结果保存在response.body()中
                                String result = response.body().string();
                                JSONObject jsonObject = new JSONObject(result);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    listener.success(jsonObject.getString("data"));
                                } else {
                                    listener.onCodeError(code);
                                    String msg = jsonObject.getString("msg");
                                    ToastUtils.show(msg);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        listener.error(t.getMessage());
                    }
                });
    }

    // 下麦通知
    public static void getRoomOutmicroConfirm(String room_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("room_id", room_id);
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_OUTMICRO_CONFIRM);
        Api.getDefault().getdata(ApiConstant.ROOM_OUTMICRO_CONFIRM, stringStringHashMap)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        emplyGetPostNull(response, listener);


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        listener.error(t.getMessage());
                    }
                });
    }


    // 上麦通知
    public static void getRoomIntomicroConfirm(String room_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("room_id", room_id);
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_INTOMICRO_CONFIRM);
        Api.getDefault().getdata(ApiConstant.ROOM_INTOMICRO_CONFIRM, stringStringHashMap)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        emplyGetPostNull(response, listener);


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        listener.error(t.getMessage());
                    }
                });
    }

    // 退出房间
    public static void getRoomQuitConfirm(String room_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("room_id", room_id);
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_QUIT_CONFIRM);
        Api.getDefault().getdata(ApiConstant.ROOM_QUIT_CONFIRM, stringStringHashMap)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        emplyGetPostNull(response, listener);

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        listener.error(t.getMessage());
                    }
                });
    }

    // 进入房间
    public static void getRoomjoinConfirm(String room_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("room_id", room_id);
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_JOIN_CONFIRM);
        Api.getDefault().getdata(ApiConstant.ROOM_JOIN_CONFIRM, stringStringHashMap)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        emplyGetPostNull(response, listener);


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        listener.error(t.getMessage());
                    }
                });
    }

    // 排麦人
    public static void getPaiMaiList(String room_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();

        map1.put("room_id", room_id);
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_MICROPHONE_APPLY_LIST);
        Api.getDefault().getUserInfoList(ApiConstant.ROOM_MICROPHONE_APPLY_LIST, stringStringHashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxHjDataObserver<ArrayList<UserBean>>() {
                    @Override
                    protected void onSuccees(ArrayList<UserBean> imageBeans) throws Exception {
                        listener.success(imageBeans);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.error(e.getMessage());

                    }

                    @Override
                    protected void onCodeError(EBBaseEntity<ArrayList<UserBean>> t) throws Exception {
                        listener.onCodeError(t.getStatus());
                    }
                });
    }

    // 获取主持麦上的信息
    public static void getRoomHostInfo(String room_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("room_id", room_id + "");

        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_HOSTMIC_INFO);
        Api.getDefault().getRoomHostInfo(ApiConstant.ROOM_HOSTMIC_INFO, stringStringHashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxHjDataObserver<RoomHostInfoBean>() {
                    @Override
                    protected void onSuccees(RoomHostInfoBean userBean) throws Exception {
                        listener.success(userBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.error(e.getMessage());

                    }

                    @Override
                    protected void onCodeError(EBBaseEntity<RoomHostInfoBean> t) throws Exception {
                        listener.onCodeError(t.getStatus());
                    }
                });
    }

    // 普通麦的信息
    public static void getRoomPTInfo(String room_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();

        map1.put("room_id", room_id);
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_MICROPHONE_LIST);
        Api.getDefault().getRoomPTInfo(ApiConstant.ROOM_MICROPHONE_LIST, stringStringHashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxHjDataObserver<ArrayList<RoomHostInfoBean>>() {
                    @Override
                    protected void onSuccees(ArrayList<RoomHostInfoBean> imageBeans) throws Exception {
                        listener.success(imageBeans);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.error(e.getMessage());

                    }

                    @Override
                    protected void onCodeError(EBBaseEntity<ArrayList<RoomHostInfoBean>> t) throws Exception {
                        listener.onCodeError(t.getStatus());
                    }
                });
    }
    // 房间心动或获取

    public static void getRoomHeartinfo(String room_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("room_id", room_id + "");

        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_HEART_INFO);
        Api.getDefault().getHeartMoData(ApiConstant.ROOM_HEART_INFO, stringStringHashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxHjDataObserver<HeartMoShiBean>() {
                    @Override
                    protected void onSuccees(HeartMoShiBean followRoomBanDanBean) throws Exception {
                        listener.success(followRoomBanDanBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.error(e.getMessage());

                    }

                    @Override
                    protected void onCodeError(EBBaseEntity<HeartMoShiBean> t) throws Exception {
                        listener.onCodeError(t.getStatus());
                    }
                });
    }
     // 房间计数的获取
    public static void getRoomTallyinfo(String room_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("room_id", room_id + "");

        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_TALLY_INFO);
        Api.getDefault().getRoomTallyInfo(ApiConstant.ROOM_TALLY_INFO, stringStringHashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxHjDataObserver<RoomTallyInfo>() {
                    @Override
                    protected void onSuccees(RoomTallyInfo followRoomBanDanBean) throws Exception {
                        listener.success(followRoomBanDanBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.error(e.getMessage());

                    }

                    @Override
                    protected void onCodeError(EBBaseEntity<RoomTallyInfo> t) throws Exception {
                        listener.onCodeError(t.getStatus());
                    }
                });
    }

    // 判断用户是否存在
    public static void getUserExst(String room_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("room_id", room_id);
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_USER_EXIST);
        Api.getDefault().getdata(ApiConstant.ROOM_USER_EXIST, stringStringHashMap)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                //返回的结果保存在response.body()中
                                String result = response.body().string();
                                JSONObject jsonObject = new JSONObject(result);
                                int code = jsonObject.getInt("code");
                                if (code == 1003) {
                                    listener.success(null);

                                } else {
                                    listener.onCodeError(code);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        listener.error(t.getMessage());
                    }
                });
    }

    // 房间信息
    public static void getRoomInfo(String room_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("room_id", room_id + "");
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_INFO);
        Api.getDefault().getRoomJoin(ApiConstant.ROOM_INFO, stringStringHashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxHjDataObserver<RoomBean>() {
                    @Override
                    protected void onSuccees(RoomBean roomBean) throws Exception {
                        listener.success(roomBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.error(e.getMessage());

                    }

                    @Override
                    protected void onCodeError(EBBaseEntity<RoomBean> t) throws Exception {
                        listener.onCodeError(t.getStatus());
                        switch (t.getStatus()) {
                            case EBBaseEntity.ROOM_NOT_OPEN:
                                ToastUtils.show(KKRoom.getInstance().mContext.getString(R.string.fangjianweikaiqi));
                                break;
                        }

                    }
                });
    }

    // 进入房间

    public static void getRoomJoin(String room_id, final ResponseListener listener) {
        getRoomJoin(room_id, null, listener);
    }

    public static void getRoomJoin(String room_id, String password, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("room_id", room_id + "");
        if (!Utlis.isEmp(password)) {
            map1.put("password", password + "");
        }
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_JOIN);
        Api.getDefault().getRoomJoin(ApiConstant.ROOM_JOIN, stringStringHashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxHjDataObserver<RoomBean>() {
                    @Override
                    protected void onSuccees(RoomBean roomBean) throws Exception {
                        listener.success(roomBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.error(e.getMessage());

                    }

                    @Override
                    protected void onCodeError(EBBaseEntity<RoomBean> t) throws Exception {
                        listener.onCodeError(t.getStatus());
                        switch (t.getStatus()) {
                            case EBBaseEntity.ROOM_NOT_OPEN:
                                ToastUtils.show(KKRoom.getInstance().mContext.getString(R.string.fangjianweikaiqi));
                                break;
                        }

                    }
                });
    }

    // 主持人上麦
    public static void getInToZhuChiMai(String room_id, String hostmicrophone_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("room_id", room_id);
        map1.put("hostmicrophone_id", hostmicrophone_id);
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_HOSTMIC_INTO);
        Api.getDefault().getdata(ApiConstant.ROOM_HOSTMIC_INTO, stringStringHashMap).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                emplyGetPostNull(response, listener);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }


    public static void getMKFZhuChiMai(String room_id, String hostmicrophone_id, boolean is, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("room_id", room_id);
        map1.put("hostmicrophone_id", hostmicrophone_id);
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_HOSTMIC_MKF + (!is ? "open" : "close"));
        Api.getDefault().getdata(ApiConstant.ROOM_HOSTMIC_MKF + (!is ? "open" : "close"), stringStringHashMap).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                emplyGetPostNull(response, listener);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }
    public static void kongzhiPTM(String room_id, String microphone_id, boolean is, int self, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("room_id", room_id);
        map1.put("microphone_id", microphone_id);
        map1.put("self", self + "");
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_MICROPHONE + (is ? "close" : "open"));
        Api.getDefault().getdata(ApiConstant.ROOM_MICROPHONE + (is ? "close" : "open"), stringStringHashMap)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        emplyGetPostNull(response, listener);


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        listener.error(t.getMessage());
                    }
                });
    }
    public static void getProSuerPackInvalid(final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.GET_PRO_SUER_PACK_INVALID);
        Api.getDefault().getdata(ApiConstant.GET_PRO_SUER_PACK_INVALID, stringStringHashMap)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        emplyGetPostNull(response, listener);


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        listener.error(t.getMessage());
                    }
                });
    }


    // 进入房间
    public static void roomQUit(String room_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("room_id", room_id);
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.ROOM_QUit);
        Api.getDefault().getdata(ApiConstant.ROOM_QUit, stringStringHashMap)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        emplyGetPostNull(response, listener);


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        listener.error(t.getMessage());
                    }
                });
    }

    // 一对一结束群聊对话
    public static void getMeetCloces(String meet_id, final ResponseListener listener) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("meet_id", meet_id);
        HashMap<String, String> stringStringHashMap = Api.initMap(map1, Api.BaseUrl + ApiConstant.MEET_CLOSE);
        Api.getDefault().getdata(ApiConstant.MEET_CLOSE, stringStringHashMap)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        emplyGetPostNull(response, listener);

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        listener.error(t.getMessage());
                    }
                });
    }










    public static RequestBody getPOSTbody(HashMap<String, String> map) {
        JSONObject jsonObject = new JSONObject(map);
        return RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),
                toBiao(map));
    }

    public static String toBiao(HashMap<String, String> map) {
        Set<String> strings = map.keySet();
        String s = "";
        for (String string : strings) {
            s = s + string + "=" + map.get(string) + "&";
        }
        return s.substring(0, s.length() - 1);
    }

    private static void emplyGetPostNull(Response<ResponseBody> response, final ResponseListener listener, boolean isToast) {
        emplyGetPost(response, new ResponseListener() {
            @Override
            public void success(Object o) {
                listener.success(null);
            }

            @Override
            public void error(String s) {
                listener.error(s);
            }

            @Override
            public void onCodeError(int errorCode) {
                super.onCodeError(errorCode);
                listener.onCodeError(errorCode);
            }
        }, isToast);
    }

    private static void emplyGetPostNull(Response<ResponseBody> response, ResponseListener listener) {
        emplyGetPostNull(response, listener, true);
    }


    private static final String TAG = "PublicApi";

    private static void emplyGetPost(Response<ResponseBody> response, ResponseListener listener
            , boolean isToast) {
        if (response.isSuccessful()) {


            try {
                //返回的结果保存在response.body()中
                String result = response.body().string();
                JSONObject jsonObject = new JSONObject(result);
                int code = jsonObject.getInt("code");
                if (code == 0) {
                    listener.success(jsonObject);
                } else {
                    listener.onCodeError(code);
                    String msg = jsonObject.getString("msg");
                    listener.onCodeError(code, msg);
                    if (isToast) {
                        ToastUtils.show(msg);
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, "JSONException   " + " " + e.getMessage());

            } catch (Exception e) {
                e.printStackTrace();
                listener.error(e.getMessage());
                Log.e(TAG, " " + e.getMessage());
                ToastUtils.show(e.getMessage());

            }
        } else {
            String s = "";
            if (response.errorBody() != null) {
                s = response.errorBody().toString();
            }
            if (Utlis.isEmp(s)) {
                listener.error("");
            } else {
                listener.error(s);
                ToastUtils.show(s);
            }
        }
    }

    private static void emplyGetPost(Response<ResponseBody> response, ResponseListener listener) {
        emplyGetPost(response, listener, true);
    }

}
