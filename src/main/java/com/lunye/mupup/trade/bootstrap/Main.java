package com.lunye.mupup.trade.bootstrap;

import com.lunye.mupup.trade.TradeContext;
import com.lunye.mupup.trade.dataloader.StockData;
import com.lunye.mupup.trade.entity.Account;
import com.lunye.mupup.trade.entity.TradeTax;
import com.lunye.mupup.trade.retest.DailyRecord;
import com.lunye.mupup.trade.retest.DataSource;
import com.lunye.mupup.trade.retest.RetestEngine;
import com.lunye.mupup.trade.retest.RetestResult;
import com.lunye.mupup.trade.strategy.Strategy;
import com.lunye.mupup.trade.strategy.StrategyGroup;
import com.lunye.mupup.trade.strategy.buy.DefaultBuyStrategy;
import com.lunye.mupup.trade.strategy.sell.DefaultSellStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.lunye.mupup.trade.dataloader.ExcelParser.parseExcel;

public class Main {

    private static final Double StartAssert = 1000000.0;
    private static final Double Tax1 = 0.0002;
    private static final Double Tax2 = 0.0005;
    private static final Double Tax3 = 0.00001;

    public static void main(String[] args) {
        RetestEngine retestEngine = new RetestEngine();
        TradeContext tradeContext = initContext();
        retestEngine.doRetest(tradeContext);
    }

    private static TradeContext initContext() {
        TradeContext tradeContext = new TradeContext();
        tradeContext.setAccount(initAccount());
        tradeContext.setDataSource(initDataSource());
        tradeContext.setStrategyGroup(initStrategyGroup());
        tradeContext.setResult(initRetestResult());
        return tradeContext;
    }

    private static Account initAccount() {
        Account account = new Account();
        account.setStartAssets(StartAssert);
        account.setRemainAssets(StartAssert);
        account.setStockCount(0);
        account.setBuyRecord(new ArrayList<>());
        account.setSellRecord(new ArrayList<>());

        // 其他费用，卖卖均收，默认0.001%
        TradeTax tradeTax = new TradeTax();
        tradeTax.setTax1(Tax1);
        tradeTax.setTax2(Tax2);
        tradeTax.setTax3(Tax3);
        account.setTradeTax(tradeTax);
        return account;
    }

    private static DataSource initDataSource() {
        String excelFilePath = "历史数据.xlsx"; // Excel文件路径
        List<StockData> stockDataList = parseExcel(excelFilePath);
        List<DailyRecord> dailyRecordList = stockDataList.stream().map(Main::convertDailyRecord).collect(Collectors.toList());
        DataSource dataSource = new DataSource();
        dataSource.setDailyRecordList(dailyRecordList);
        return dataSource;
    }

    private static DailyRecord convertDailyRecord(StockData item){
        DailyRecord dailyRecord = new DailyRecord();
        dailyRecord.setDate(item.getDate());
        dailyRecord.setStartPrice(item.getOpen());
        dailyRecord.setEndPrice(item.getClose());
        dailyRecord.setMaxPrice(item.getHigh());
        dailyRecord.setMinPrice(item.getLow());
        return dailyRecord;
    }

    private static StrategyGroup initStrategyGroup() {
        StrategyGroup sg = new StrategyGroup();
        List<Strategy> strategyList = new ArrayList<>();
        strategyList.add(new DefaultBuyStrategy());
        strategyList.add(new DefaultSellStrategy());
        sg.setStrategyList(strategyList);
        return sg;
    }


    private static RetestResult initRetestResult() {
        RetestResult result = new RetestResult();
        result.setActionResults(new ArrayList<>());
        return result;
    }

}
