package com.lunye.mupup.trade.dataloader;

import lombok.Data;

public class StockData {
    private String stockName;
    private String stockCode;
    private String date;
    private double open;
    private double close;
    private double high;
    private double low;
    private long volume;
    private double turnover;
    private double amplitude;
    private double chgPct;
    private double chgAmt;
    private double turnoverRate;

    // Getters and Setters
    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public double getChgPct() {
        return chgPct;
    }

    public void setChgPct(double chgPct) {
        this.chgPct = chgPct;
    }

    public double getChgAmt() {
        return chgAmt;
    }

    public void setChgAmt(double chgAmt) {
        this.chgAmt = chgAmt;
    }

    public double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    @Override
    public String toString() {
        return String.format("股票名称: %s, 股票代码: %s, 日期: %s, 开盘: %.3f, 收盘: %.3f, 最高: %.3f, 最低: %.3f, 成交量: %d, 成交额: %.2f, 振幅: %.2f%%, 涨跌幅: %.2f%%, 涨跌额: %.3f, 换手率: %.2f%%",
                stockName, stockCode, date, open, close, high, low, volume, turnover, amplitude, chgPct, chgAmt, turnoverRate);
    }
}
