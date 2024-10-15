package com.lunye.mupup.trade.retest;

import lombok.Data;

@Data
public class DailyRecord {

    private String date;

    private Double startPrice;

    private Double endPrice;

    private Double maxPrice;

    private Double minPrice;

    // 开盘情况
    private Double startPosition;

    // 五日均价格
    private Double avgPrice5;

    // 十五日均价
    private Double avgPrice15;

    // 60日均价
    private Double avgPrice60;

    // 连续高开

    // 连续低开
    
}
