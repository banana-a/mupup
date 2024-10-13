package com.lunye.mupup.trade.strategy;

import com.lunye.mupup.trade.TradeContext;

public interface Strategy {

    StrategyType getStrategyType();

    Integer getExecOrder();

    void exec(TradeContext context, int dateIndex);

}
