package com.lunye.mupup.trade;

import com.lunye.mupup.trade.entity.Account;
import com.lunye.mupup.trade.retest.DataSource;
import com.lunye.mupup.trade.retest.RetestResult;
import com.lunye.mupup.trade.strategy.StrategyGroup;
import lombok.Data;

@Data
public class TradeContext {

    private Account account;

    private StrategyGroup strategyGroup;

    private DataSource dataSource;

    private RetestResult result;

}
