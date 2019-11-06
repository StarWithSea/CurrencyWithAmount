package com.currency;

import com.rate.RateExchange;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CurrencyAndAmount {
    private static Map<String, Double> concurrentCurrencyMap = new ConcurrentHashMap<String, Double>();



    /**
     * add element to Map
     * @param currencyUnit
     * @param amount
     */
    public void put(String currencyUnit, Double amount) {
        try {

            Double preAmounnt = concurrentCurrencyMap.put(currencyUnit.toUpperCase(), amount);
            if (preAmounnt != null) {
                concurrentCurrencyMap.put(currencyUnit.toUpperCase(), amount + preAmounnt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Double> getConcurrentCurrencyMap(){
        return concurrentCurrencyMap;
    }


    public void removeAll(){
        concurrentCurrencyMap.clear();
    }


}
