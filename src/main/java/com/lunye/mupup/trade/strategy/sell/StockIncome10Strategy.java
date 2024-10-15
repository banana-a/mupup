package com.lunye.mupup.trade.strategy.sell;

import com.lunye.mupup.trade.TradeContext;
import com.lunye.mupup.trade.entity.TradeActionParam;
import com.lunye.mupup.trade.strategy.Strategy;
import com.lunye.mupup.trade.strategy.StrategyType;

public class StockIncome10Strategy implements Strategy {

    private static final Double SoldOutLimit = 0.05;

    @Override
    public StrategyType getStrategyType() {
        return StrategyType.TRADE_PROPOSAL;
    }

    @Override
    public Integer getExecOrder() {
        return 2;
    }

    @Override
    public void exec(TradeContext context, int dateIndex) {
        Double stockIncomeRate = context.getAccount().getStockIncomeRate(context.getTodayDailyRecord().getStartPrice());

        if (stockIncomeRate >= SoldOutLimit){
            TradeActionParam actionParam = new TradeActionParam();
            actionParam.setTradeType(2);
            actionParam.setCount(context.getAccount().getStockCount());
            actionParam.setPrice(context.getTodayDailyRecord().getStartPrice());
            context.getResult().getActionResults().add(actionParam);
        }
    }

}
