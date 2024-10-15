package com.lunye.mupup.trade;

import com.lunye.mupup.trade.entity.Account;
import com.lunye.mupup.trade.entity.TradeActionParam;
import com.lunye.mupup.trade.retest.DailyRecord;
import lombok.Data;

@Data
public class TradeManager {

    public void doTrade(TradeContext context, int i, TradeActionParam tradeActionParam) {
        DailyRecord dailyRecord = context.getDataSource().getDailyRecordList().get(i);
        Double price = tradeActionParam.getPrice();
        if (price <= dailyRecord.getMinPrice() || price >= dailyRecord.getMaxPrice()){
            return;
        }
        switch (tradeActionParam.getTradeType()){
            case 1:
                buy(context,i,tradeActionParam);
                break;
            case 2:
                sell(context,i,tradeActionParam);
                break;
        }
    }

    private static void buy(TradeContext context, int i, TradeActionParam param){
        Account account = context.getAccount();
        Double price = param.getPrice();
        DailyRecord dailyRecord = context.getDataSource().getDailyRecordList().get(i);
        if (account.buy(param)){
            System.out.printf("第%d天，尝试以%.3f价格买入成功，最高价为%.3f，最低价为%.3f%n", i, price, dailyRecord.getMaxPrice(), dailyRecord.getMinPrice());
        } else {
            System.out.printf("第%d天，尝试以%.3f价格买入失败，最高价为%.3f，最低价为%.3f%n", i, price, dailyRecord.getMaxPrice(), dailyRecord.getMinPrice());
        }
    }

    private static void sell(TradeContext context, int i, TradeActionParam param){
        Account account = context.getAccount();
        Double price = param.getPrice();
        DailyRecord dailyRecord = context.getDataSource().getDailyRecordList().get(i);
        if (account.sell(param)){
            System.out.printf("第%d天，尝试以%.3f价格卖出成功，最高价为%.3f，最低价为%.3f%n", i, price, dailyRecord.getMaxPrice(), dailyRecord.getMinPrice());
        } else {
            System.out.printf("第%d天，尝试以%.3f价格卖出失败，最高价为%.3f，最低价为%.3f%n", i, price, dailyRecord.getMaxPrice(), dailyRecord.getMinPrice());
        }
    }

}
