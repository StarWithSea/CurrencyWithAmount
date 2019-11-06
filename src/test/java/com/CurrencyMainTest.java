package com;

import com.currency.CurrencyAndAmount;
import com.rate.RateExchange;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CurrencyMainTest {
    private CurrencyAndAmount currencyAndAmount;

    private RateExchange rateExchange ;
    @Before
    public void setBefor(){
        currencyAndAmount = new CurrencyAndAmount();
        rateExchange = new RateExchange();
    }

    @Test
    public void printCurrencyAndAmount() throws IOException {
        String[] commands = {"quit, break",
                "clear, The data enter before is clear please reenter",
                "1, Please enter the data in the format like 'USD 1000'",
                "2, Please enter the file path like 'D:\\Files\\data.txt'",
                "print, "};

        String[] datas = {"USD 1000","HKD 100","USD -100","CNY 2000" };

        String filePath = "E:\\Program Files\\WorkspacesMyEclipse\\CurrencyWithAmount\\src\\main\\java\\com\\currencyData.txt";

        rateExchange.setRate("CNY",7.09);

        int firstCommand = 0;
        for (String str :commands){
            firstCommand = ("1".equals(str.split(",")[0])|| "2".equals(str.split(",")[0]))? Integer.valueOf(str.split(",")[0]):firstCommand;
            if("print".equals(str.split(",")[0])){
                System.out.println(CurrencyMain.printCurrencyAndAmount(str.split(",")[0],currencyAndAmount,rateExchange,firstCommand));
            }
            else
            {
                assertEquals(str.split(",")[1].trim(),CurrencyMain.printCurrencyAndAmount(str.split(",")[0],currencyAndAmount,rateExchange,firstCommand));
                if(firstCommand == 2){
                    System.out.println(CurrencyMain.printCurrencyAndAmount(filePath,currencyAndAmount,rateExchange,firstCommand));
                }

                if(firstCommand == 1){
                    for(String data : datas){
                        System.out.println(CurrencyMain.printCurrencyAndAmount(data,currencyAndAmount,rateExchange,firstCommand));
                    }

                }
            }


        }
    }
}