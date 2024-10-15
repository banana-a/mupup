package com.lunye.mupup.trade.retest;

import com.lunye.mupup.trade.TradeManager;
import com.lunye.mupup.trade.TradeContext;
import com.lunye.mupup.trade.entity.TradeActionParam;
import com.lunye.mupup.trade.strategy.StrategyManager;

import java.util.List;

public class RetestEngine {

    private static final TradeManager tradeManager = new TradeManager();

    public void doRetest(TradeContext context, String startDate){

        for (int i = 0; i < context.getDataSource().getDailyRecordList().size(); i++) {
            DailyRecord dailyRecord = context.getDataSource().getDailyRecordList().get(i);
            if (dailyRecord.getDate().compareTo(startDate) < 1){
                continue;
            }
            context.setDateIndex(i);
            System.out.printf("%s交易开始，开盘价=%.3f，最高价=%.3f 收盘价=%.3f %n", dailyRecord.getDate(), dailyRecord.getStartPrice(),dailyRecord.getMaxPrice(),dailyRecord.getMinPrice());
            Double allAsserts = context.getAccount().getAllAsserts(dailyRecord.getEndPrice());
            System.out.printf("%s交易开始，交易前，总股数=%d，总资产=%.3f，仓位=%.3f %n", dailyRecord.getDate(), context.getAccount().getStockCount(), allAsserts, (dailyRecord.getEndPrice() * context.getAccount().getStockCount() / context.getAccount().getStartAssets()) * 100);
            List<TradeActionParam> tradeActionParam = StrategyManager.generateTradeAction(context, i);
            for (TradeActionParam actionParam : tradeActionParam) {
                tradeManager.doTrade(context,i,actionParam);
            }
            allAsserts = context.getAccount().getAllAsserts(dailyRecord.getEndPrice());
            System.out.printf("%s交易结束，交易后，总股数=%d，总资产=%.3f，仓位=%.3f %n", dailyRecord.getDate(), context.getAccount().getStockCount(), allAsserts, (dailyRecord.getEndPrice() * context.getAccount().getStockCount() / context.getAccount().getStartAssets()) * 100);
        }
//        saveResult(context);
    }

}
