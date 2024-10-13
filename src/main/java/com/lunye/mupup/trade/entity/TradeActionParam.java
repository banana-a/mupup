package com.lunye.mupup.trade.entity;

import lombok.Data;

@Data
public class TradeActionParam {

    // 1买，2卖
    private Integer tradeType;

    private Double price;

    private Integer count;

}
