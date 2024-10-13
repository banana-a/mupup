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
        }
//        saveResult(context);
    }

}
