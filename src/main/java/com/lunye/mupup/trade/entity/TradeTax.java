package com.lunye.mupup.trade.entity;

import lombok.Data;

@Data
public class TradeTax {

    // 佣金，券商收取，买卖均收，0.008%~0.025%之间
    private Double Tax1;

    // 印花税，国家收取，卖出收取，默认0.05%
    private Double Tax2;

    // 其他费用，卖卖均收，默认0.001%
    private Double Tax3;

}
