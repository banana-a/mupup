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
        List<DailyRecord> dailyRecordList = context.getDataSource().getDailyRecordList();
        DailyRecord dailyRecord = dailyRecordList.get(dateIndex);
        Double startPrice = dailyRecord.getStartPrice();

        TradeActionParam param = new TradeActionParam();
        param.setTradeType(1);

        double originalPrice = startPrice * 1.01;
        double adjustedPrice = Math.floor(originalPrice * 1000) / 1000.0;
        param.setPrice(adjustedPrice);
        param.setPrice(adjustedPrice);

        int count = (int) (5000 / adjustedPrice);
        count = (count / 100) * 100;
        param.setCount(count);

        RetestResult result = context.getResult();
        result.getActionResults().add(param);
    }

}
