package com.lunye.mupup.trade.strategy.sell;

import com.lunye.mupup.trade.TradeContext;
import com.lunye.mupup.trade.entity.TradeActionParam;
import com.lunye.mupup.trade.entity.TradeRecord;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class SellCommonUtil {

    private static final Double OneBuyIncomeRate = 1.05;

    public static List<TradeActionParam> getSellPriceActionParam(TradeContext tradeContext) {
        List<TradeActionParam> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(tradeContext.getTradeRecordList())){
            for (TradeRecord tradeRecord : tradeContext.getTradeRecordList()) {
                TradeActionParam buyActionParam = tradeRecord.getBuyActionParam();
                TradeActionParam sellActionParam = tradeRecord.getSellActionParam();
                if (sellActionParam == null){
                    TradeActionParam newParam = new TradeActionParam();
                    Double sellPrice = adjustPrice(buyActionParam.getPrice() * OneBuyIncomeRate);
                    if (sellPrice >= tradeContext.getTodayDailyRecord().getMaxPrice() || sellPrice <= tradeContext.getTodayDailyRecord().getMinPrice()){
                        continue;
                    }
                    newParam.setPrice(sellPrice);
                    newParam.setCount(buyActionParam.getCount());
                    newParam.setTradeType(2);
                    result.add(newParam);
                    tradeRecord.setSellActionParam(newParam);
                }
            }
        }
        return result;
    };

    private static Double adjustPrice(Double input){
        return Math.floor(input * 1000) / 1000.0;
    }

}
