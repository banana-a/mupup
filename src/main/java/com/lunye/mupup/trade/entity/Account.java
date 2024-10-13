package com.lunye.mupup.trade.entity;

import java.util.List;

public class Account {

    // 启动资金
    private Double startAssets;

    // 可用流水
    private Double remainAssets;

    // 股数
    private Integer stockCount;

    // 平均成本价
    private Double costPrice;

    // 买记录
    private List<Double> buyRecord;

    // 卖记录
    private List<Double> sellRecord;

    // 交易费率
    private TradeTax tradeTax;

    // 总资产
    private Double getAllAsserts(Double stockPrice){
        return stockPrice * stockCount + remainAssets;
    }

    // 仓位
    private Double getPosition(Double stockPrice){
        return stockPrice * stockCount / getAllAsserts(stockPrice);
    }

}


