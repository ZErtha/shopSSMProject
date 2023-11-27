package com.gec.utils;

import java.util.Objects;

/**
 * 订单状态枚举类
 * @author 泽申（Ertha)
 * @date 2023/4/25
 * 佛祖保佑
 * 隆金加滑
 */

public enum OrderStateEnum {

    /**
     * 订单状态
     * 0       未支付,去付款
     * 1       已付款,催单
     * 2       确定收货
     * 3       未评价，去评价
     * 4       订单完成，查看订单详情
     * 5       订单已取消
     */
    ORDER_NOT_PAY(0, "ORDER_NOT_PAY", "未支付"),
    ORDER_PAT_SUCCESS(1, "ORDER_PAT_SUCCESS", "已付款"),
    ORDER_CONFIRM(2, "ORDER_CONFIRM", "确定收货"),
    ORDER_NOT_ASSESS(3, "ORDER_NOT_ASSESS", "未评价"),
    ORDER_COMPLETE(4, "ORDER_COMPLETE", "订单完成"),
    ORDER_CANCELLED(5, "ORDER_CANCELLED", "订单已取消"),
    ;

    private final Integer state;
    private final String code;
    private final String des;

    OrderStateEnum(Integer state, String code, String des) {
        this.state = state;
        this.code = code;
        this.des = des;
    }

    //通过ID获取枚举值
    public static OrderStateEnum getByState(Integer state) {
        if(state == null){
            return null;
        }
        for(OrderStateEnum v : values()) {
            if(Objects.equals(v.state, state)) {
                return v;
            }
        }
        return null;
    }
}