package com.lunye.mupup.trade.strategy.buy;

import com.lunye.mupup.trade.TradeContext;
import com.lunye.mupup.trade.entity.TradeActionParam;
import com.lunye.mupup.trade.retest.RetestResult;
import com.lunye.mupup.trade.strategy.Strategy;
import com.lunye.mupup.trade.strategy.StrategyType;

public class DefaultBuyStrategy implements Strategy {
    @Override
    public StrategyType getStrategyType() {
        return StrategyType.TRADE_PROPOSAL;
    }

    @Override
    public Integer getExecOrder() {
        return 1;
    }

    // 每天以开盘价 - 0.02 挂买单
    @Override
    public void exec(TradeContext context, int dateIndex) {
        TradeActionParam param = new TradeActionParam();

        Double price = BuyCommonUtil.getBuyPriceStartDown2(context);
        Integer count = BuyCommonUtil.getBuyCount(price,1);

        param.setTradeType(1);
        param.setPrice(price);
        param.setCount(count);
        RetestResult result = context.getResult();
        result.getActionResults().add(param);
    }
}
