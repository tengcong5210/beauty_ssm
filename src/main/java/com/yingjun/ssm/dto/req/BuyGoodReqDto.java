package com.yingjun.ssm.dto.req;

import java.io.Serializable;

/**
 * 购买商品请求对象
 * @author tengcongcong
 * @create 2018-05-03 15:56
 * @Version 1.0
 **/
public class BuyGoodReqDto implements Serializable {

    private static final long serialVersionUID = -4126286554976873065L;
    //用户手机号
    private String userTel;
    //商品id
    private String goodId;

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    @Override
    public String toString() {
        return "BuyGoodReqDto{" +
                "userTel='" + userTel + '\'' +
                ", goodId='" + goodId + '\'' +
                '}';
    }
}
