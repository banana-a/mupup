package com.lunye.mupup.trade;

import com.lunye.mupup.trade.entity.TradeActionParam;
import lombok.Data;

@Data
public class TradeManager {

    public void doTrade(TradeContext context, int i, TradeActionParam tradeActionParam) {
        switch (tradeActionParam.getTradeType()){
            case 1:
                buy(context,i,tradeActionParam);
            case 2:
                sell(context,i,tradeActionParam);
        }
    }

    private static void buy(TradeContext context, int i, TradeActionParam param){

    }

    private static void sell(TradeContext context, int i, TradeActionParam param){

    }

}
