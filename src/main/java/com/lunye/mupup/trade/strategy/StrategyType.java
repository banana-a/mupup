package com.lunye.mupup.trade.strategy;

public enum StrategyType {

    TRADE_PROPOSAL(1),

    TRADE_FILTER(2),

    TRADE_MERGE(3),

    ;

    private Integer type;

    StrategyType(Integer type) {
        this.type = type;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
