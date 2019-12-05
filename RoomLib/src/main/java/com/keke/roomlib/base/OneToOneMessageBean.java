package com.keke.roomlib.base;

public class OneToOneMessageBean {



    public static final int ONE_TO_ONE_JOIN_SUCCES=1;
    public static final int ONE_TO_ONE_LEAVE=2;






    private int mes_code=-1;
    private Object o;

    public int getMes_code() {
        return mes_code;
    }

    public void setMes_code(int mes_code) {
        this.mes_code = mes_code;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }
}
