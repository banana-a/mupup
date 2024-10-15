package com.lunye.mupup.trade.retest;

import com.lunye.mupup.trade.TradeManager;
import com.lunye.mupup.trade.TradeContext;
import com.lunye.mupup.trade.entity.TradeActionParam;
import com.lunye.mupup.trade.strategy.StrategyManager;

import java.util.List;

public class RetestEngine {

    private static final TradeManager tradeManager = new TradeManager();

    public void doRetest(TradeContext context){

        for (int i = 0; i < context.getDataSource().getDailyRecordList().size(); i++) {
            List<TradeActionParam> tradeActionParam = StrategyManager.generateTradeAction(context, i);
            for (TradeActionParam actionParam : tradeActionParam) {
                tradeManager.doTrade(context,i,actionParam);
            }
            DailyRecord dailyRecord = context.getDataSource().getDailyRecordList().get(i);
            Double allAsserts = context.getAccount().getAllAsserts(dailyRecord.getEndPrice());
            System.out.printf("第%d天交易结束，总股数=%d，总资产=%.3f，仓位=%.3f %n", i, context.getAccount().getStockCount(), allAsserts, (dailyRecord.getEndPrice() * context.getAccount().getStockCount() / context.getAccount().getStartAssets()) * 100);
        }
//        saveResult(context);
    }

}
