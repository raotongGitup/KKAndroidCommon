package com.keke.roomlib.base;

public interface OneToOneListener {

   void  showIlikeYou();
   void  changgeLike(int num);
   void showGiftAni(boolean isMe, String url);
   void showGiftAniBin(boolean isJD, String url, String id);
   void showRemainTime(String time);
   void showTruespeople(OneToOneImMessageBean bean);
   void isRedTime(boolean is);

}
