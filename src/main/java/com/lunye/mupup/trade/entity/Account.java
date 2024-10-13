package com.lunye.mupup.trade.entity;

import lombok.Data;

import java.util.List;

@Data
public class Account {

    // 启动资金
    private Double startAssets;

    // 可用流水
    private Double remainAssets;

    // 股数
    private Integer stockCount;

    // 可用股数
    private Integer canUseStockCount;

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

    // 购买
    public void buy(TradeActionParam param){
        if (checkParam(param)){
            stockCount += param.getCount();
            remainAssets -= param.getPrice() * param.getCount();
            remainAssets -= getTex(param);
        }
    }

    // 售卖
    public void sell(TradeActionParam param){
        if (checkParam(param)){
            stockCount -= param.getCount();
            remainAssets += param.getPrice() * param.getCount();
            remainAssets -= getTex(param);
        }
    }

    private Double getTex(TradeActionParam param) {
        double cost = param.getPrice() * param.getCount();
        double res = tradeTax.getTax1() * cost + tradeTax.getTax3() * cost;
        if (param.getTradeType() == 2){
            res += tradeTax.getTax2() * cost;
        }
        return res;
    }

    private boolean checkParam(TradeActionParam param) {
        return true;
    }

}


