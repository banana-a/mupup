package com.lunye.mupup.trade.strategy.sell;

import com.lunye.mupup.trade.TradeContext;
import com.lunye.mupup.trade.entity.TradeActionParam;
import com.lunye.mupup.trade.retest.DailyRecord;
import com.lunye.mupup.trade.retest.RetestResult;
import com.lunye.mupup.trade.strategy.Strategy;
import com.lunye.mupup.trade.strategy.StrategyType;

import java.util.List;

public class DefaultSellStrategy implements Strategy {

    @Override
    public StrategyType getStrategyType() {
        return StrategyType.TRADE_PROPOSAL;
    }

    @Override
    public Integer getExecOrder() {
        return 1;
    }

    @Override
    public void exec(TradeContext context, int dateIndex) {
        RetestResult result = context.getResult();
        result.getActionResults().addAll(SellCommonUtil.getSellPriceActionParam(context));
    }

}
