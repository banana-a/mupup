package com.lunye.mupup.trade;

import com.lunye.mupup.trade.entity.Account;
import com.lunye.mupup.trade.entity.TradeActionParam;
import com.lunye.mupup.trade.retest.DailyRecord;
import lombok.Data;

@Data
public class TradeManager {

    public void doTrade(TradeContext context, int i, TradeActionParam tradeActionParam) {
        switch (tradeActionParam.getTradeType()){
            case 1:
                buy(context,i,tradeActionParam);
            case 2:
                sell(context,i,tradeActionParam);
        }
    }

    private static void buy(TradeContext context, int i, TradeActionParam param){
        Account account = context.getAccount();
        Double price = param.getPrice();
        DailyRecord dailyRecord = context.getDataSource().getDailyRecordList().get(i);
        if (price <= dailyRecord.getMaxPrice() || price >= dailyRecord.getMinPrice()){
            account.buy(param);
            System.out.printf("第%d天，尝试以%.2f价格买入成功，最高价为%.2f，最低价为%.2f%n", i, price, dailyRecord.getMaxPrice(), dailyRecord.getMinPrice());
        } else {
            System.out.printf("第%d天，尝试以%.2f价格买入失败，最高价为%.2f，最低价为%.2f%n", i, price, dailyRecord.getMaxPrice(), dailyRecord.getMinPrice());
        }
    }

    private static void sell(TradeContext context, int i, TradeActionParam param){
        Account account = context.getAccount();
        Double price = param.getPrice();
        DailyRecord dailyRecord = context.getDataSource().getDailyRecordList().get(i);
        if (price <= dailyRecord.getMaxPrice() || price >= dailyRecord.getMinPrice()){
            account.sell(param);
            System.out.printf("第%d天，尝试以%.2f价格卖出入成功，最高价为%.2f，最低价为%.2f%n", i, price, dailyRecord.getMaxPrice(), dailyRecord.getMinPrice());
        } else {
            System.out.printf("第%d天，尝试以%.2f价格买入失败，最高价为%.2f，最低价为%.2f%n", i, price, dailyRecord.getMaxPrice(), dailyRecord.getMinPrice());
        }
    }

}
