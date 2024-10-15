package com.lunye.mupup.trade.strategy.buy;

import com.lunye.mupup.trade.TradeContext;
import com.lunye.mupup.trade.strategy.Constant;

public class BuyCommonUtil {

    /**
     * 按照开盘价买
     */
    public static Double getBuyPriceStart(TradeContext context){
        double price = context.getTodayDailyRecord().getStartPrice();
        return adjustPrice(price);
    }

    /**
     * 按照开盘价 -0.02买
     */
    public static Double getBuyPriceStartDown2(TradeContext context){
        double price = context.getTodayDailyRecord().getStartPrice() - 0.02;
        return adjustPrice(price);
    }

    /**
     * 获取购买股票数量
     * @param stockPrice
     * @param unitNum
     * @return
     */
    public static Integer getBuyCount(Double stockPrice,Integer unitNum){
        double count = unitNum * Constant.BaseTradeTotalMoney / stockPrice;
        return ((int)count / 100) * 100;
    }

    private static Double adjustPrice(Double input){
        return Math.floor(input * 1000) / 1000.0;
    }



}
