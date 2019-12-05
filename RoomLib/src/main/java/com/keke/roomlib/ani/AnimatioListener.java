package com.keke.roomlib.ani;

import android.graphics.Bitmap;

public abstract class AnimatioListener {
    public void onAniPreparation(Object carAniBean, String cid) {
    }

    public void onErrro(String mes) {
    }

    public void onAniShow() {
    }

    public void onAniOver() {
    }


    public void onShowAniYulan(int lef, int top, int wid, int hei, Bitmap bitmap){


    }
}