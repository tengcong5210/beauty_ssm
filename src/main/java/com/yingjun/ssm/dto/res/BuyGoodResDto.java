package com.yingjun.ssm.dto.res;

import java.io.Serializable;

/**
 * @author tengcongcong
 * @create 2018-05-03 15:59
 * @Version 1.0
 **/
public class BuyGoodResDto implements Serializable {
    private static final long serialVersionUID = 1616980227683932148L;
    //返回编码
    private String resCode;
    //返回描述
    private String resMsg;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }
    @Override
    public String toString() {
        return "BuyGoodResDto{" +
                "resCode='" + resCode + '\'' +
                ", resMsg='" + resMsg + '\'' +
                '}';
    }
}
