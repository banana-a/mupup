package com.lunye.mupup.trade.entity;

import lombok.Data;

@Data
public class TradeRecord {

    private TradeActionParam buyActionParam;

    private Double buyTax;

    private TradeActionParam sellActionParam;

    private Double sellTax;

    public Double getProfit(){
        return buyActionParam.getPrice() * buyActionParam.getCount() - sellActionParam.getPrice() * sellActionParam.getCount();
    }

}
