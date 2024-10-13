package com.lunye.mupup.trade.strategy;

import com.lunye.mupup.trade.TradeContext;
import com.lunye.mupup.trade.entity.TradeActionParam;
import com.lunye.mupup.trade.retest.RetestResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StrategyManager {

    public static List<TradeActionParam> generateTradeAction(TradeContext context, int dateIndex){

        RetestResult retestResult = new RetestResult();
        retestResult.setActionResults(new ArrayList<>());
        context.setResult(retestResult);

        StrategyGroup strategyGroup = context.getStrategyGroup();

        List<Strategy> strategyList = strategyGroup.getStrategyList();
        List<Strategy> sortedStrategy = strategyList.stream().sorted(Comparator.comparingInt(Strategy::getExecOrder)).collect(Collectors.toList());

        for (Strategy strategy : sortedStrategy) {
            strategy.exec(context,dateIndex);
        }

        return context.getResult().getActionResults();

    }

}
