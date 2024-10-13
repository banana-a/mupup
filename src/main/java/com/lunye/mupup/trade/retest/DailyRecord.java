package com.lunye.mupup.trade.retest;

import lombok.Data;

@Data
public class DailyRecord {

    private String date;

    private Double startPrice;

    private Double endPrice;

    private Double maxPrice;

    private Double minPrice;

}
