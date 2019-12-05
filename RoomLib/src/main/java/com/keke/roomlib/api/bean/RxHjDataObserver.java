package com.keke.roomlib.api.bean;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hjq.toast.ToastUtils;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/4/3.
 */

public abstract class RxHjDataObserver<T> implements Observer<EBBaseEntity<T>> {

    private static final String TAG = "RxHjDataObserver";
    protected Context mContext;

    public RxHjDataObserver(Context cxt) {
        this.mContext = cxt;
    }

    public RxHjDataObserver() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();

    }

    @Override
    public void onNext(EBBaseEntity<T> tEBBaseEntity) {
        onRequestEnd();
        try {
            switch (tEBBaseEntity.getStatus()) {
                case EBBaseEntity.SUCCESS_CODE:
                    onSuccees(tEBBaseEntity.getData());
                    break;
                case EBBaseEntity.FAIL_CODE:
                    if (!TextUtils.isEmpty(tEBBaseEntity.getMsg())) {
                        ToastUtils.show(tEBBaseEntity.getMsg());
                    }
                    onCodeError(tEBBaseEntity);
                    break;
                case EBBaseEntity.Expired_CODE:
                case EBBaseEntity.TOKEN_ERRO_CODE:
                    // TODO: 2019/11/30  登录过期
                   // ToastUtils.show("登陆态已过期");
                    //UserManager.getInstance().logout(null);
                    onCodeError(tEBBaseEntity);
                    break;
                case EBBaseEntity.ROOM_NOT_OPEN:
                case EBBaseEntity.USER_WZC:
                case EBBaseEntity.USER_YZCDQYH:
                case EBBaseEntity.USER_YZCFDQYH:
                case EBBaseEntity.USER_YZCWDL:
                case EBBaseEntity.SYSTEM_INIT_UP:
                case EBBaseEntity.ROOM_NEED_PASS_WORD:
                case EBBaseEntity.USER_CP_NOT_HAVE:
                case EBBaseEntity.CP_STATUS_HIDE:
                    onCodeError(tEBBaseEntity);
                    break;
                default:
                    if (!TextUtils.isEmpty(tEBBaseEntity.getMsg())) {
                        ToastUtils.show(tEBBaseEntity.getMsg());
                    }
                    onCodeError(tEBBaseEntity);
                    break;
            }
        } catch (Exception e) {
              Log.e(TAG,e.getMessage());
        }

    }

    @Override
    public void onError(Throwable e) {
//        Log.w(TAG, "onError: ", );这里可以打印错误信息
        if (e == null || TextUtils.isEmpty(e.getMessage())) {
            try {
                onCodeError(new EBBaseEntity<T>());
            } catch (Exception e1) {
                Log.e(TAG,e.getMessage());
            }
            return;
        }
        if (e.getMessage().equals("connect timed out")) {
            ToastUtils.show("连接时间过长，请检查网络");
        }
        onRequestEnd();
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(e, true);
            } else {
                onFailure(e, false);
            }
        } catch (Exception e1) {
            Log.e(TAG,e.getMessage());
        }
    }

    @Override
    public void onComplete() {
    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccees(T t) throws Exception;

    /**
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    protected void onCodeError(EBBaseEntity<T> t) throws Exception {
    }

    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    protected abstract void onFailure(Throwable e, boolean isNetWorkError) throws Exception;

    protected void onRequestStart() {
    }

    protected void onRequestEnd() {
//        closeProgressDialog();
    }

    public void showProgressDialog() {
//        ProgressDialog.show(mContext, false, "请稍后");
    }

    public void closeProgressDialog() {
//        ProgressDialog.cancle();
    }
}
