package com.rate;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateExchange {
    private static Map<String, Double> rateMap = new ConcurrentHashMap<String, Double>();


    public Map<String, Double> getRateMap(){
        return rateMap;
    }

    public void setRate(String from, double rate){
        rateMap.put(from.toUpperCase() ,rate);
    }
    public Double getRate(String from) {

        return rateMap.get(from);
    }

    public double getExChange(String from,double amount){
        DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
        return Double.valueOf(df.format(amount/rateMap.get(from)));

    }

}
