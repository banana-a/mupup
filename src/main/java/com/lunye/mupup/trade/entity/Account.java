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

    // 持仓成本
    private Double stockCost = 0.0;

    // 买记录
    private List<Double> buyRecord;

    // 卖记录
    private List<Double> sellRecord;

    // 交易费率
    private TradeTax tradeTax;

    // 总资产
    public Double getAllAsserts(Double stockPrice){
        return stockPrice * stockCount + remainAssets;
    }

    // 仓位
    private Double getPosition(Double stockPrice){
        return stockPrice * stockCount / getAllAsserts(stockPrice);
    }

    // 购买
    public boolean buy(TradeActionParam param){
        if (checkParam(param)){
            stockCount += param.getCount();
            remainAssets -= param.getPrice() * param.getCount();
            remainAssets -= getTex(param);
            stockCost += param.getPrice() * param.getCount();
            stockCost += getTex(param);
            return true;
        }else {
            return false;
        }
    }

    // 售卖
    public boolean sell(TradeActionParam param){
        if (checkParam(param)){
            stockCount -= param.getCount();
            remainAssets += param.getPrice() * param.getCount();
            remainAssets -= getTex(param);
            stockCost = 0.0;
            return true;
        } else {
            return false;
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
        if (param.getTradeType() == 1){
            return remainAssets > param.getCount() * param.getPrice();
        }else if (param.getTradeType() == 2){
            return stockCount >= param.getCount();
        }
        return false;
    }

    public Double getStockIncomeRate(Double stockPrice){
        if (stockCost == null || stockCost == 0 || stockCount == null || stockCount == 0){
            return 0.0;
        }
        return ((stockCount * stockPrice) - stockCost) / (stockCount * stockPrice);
    }

}


