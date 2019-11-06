package com;

import com.currency.CurrencyAndAmount;
import com.fileOperation.FileUtil;
import com.rate.RateExchange;
import com.validate.Validates;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CurrencyMain {

    public static void main(String[] args) throws IOException {
        System.out.println("========================================");
        System.out.println("== only can enter commands belows     ==");
        System.out.println("== quit、clear、print、1、2            ==");
        System.out.println("== Types \"quit\" to exit.              ==");
        System.out.println("== Types \"print\" to print the result. ==");
        System.out.println("== Types \"1\" to enter the data        ==");
        System.out.println("== Types \"2\" to enter the file path.  ==");
        System.out.println("== Types \"rate\" to set rates.         ==");
        System.out.println("== Types \"endRate\" stop set rates.    ==");
        System.out.println("========================================");
        Scanner scanner = new Scanner(System.in);

        CurrencyAndAmount currencyAndAmount = new CurrencyAndAmount();
        RateExchange rateExchange = new RateExchange();
        int firstCommand = 0;
        while (true) {
            String text = scanner.nextLine().trim();
            firstCommand = ("1".equals(text) || "2".equals(text)) ? Integer.valueOf(text) : "rate".equals(text) ? 3 : firstCommand;
            String retStr = printCurrencyAndAmount(text, currencyAndAmount, rateExchange, firstCommand);
            if ("break".equals(retStr)) {
                break;
            } else if (!"".equals(retStr)) {
                System.out.println(printCurrencyAndAmount(text, currencyAndAmount, rateExchange, firstCommand));
            }
        }

    }

    public static String printCurrencyAndAmount(String text, CurrencyAndAmount currencyAndAmount, RateExchange rateExchange, int firstCommand) throws IOException {
        String retStr = "";
        if ("rate".equals(text)) {
            retStr = "Please enter the rate to USD";
        } else if ("endRate".equals(text)) {
            retStr = "Set rates success";
        } else if ("quit".equals(text)) {
            retStr = "break";
        } else if ("clear".equals(text)) {
            currencyAndAmount.removeAll();
            retStr = "The data enter before is clear please reenter";
        } else if ("1".equals(text)) {
            retStr = "Please enter the data in the format like 'USD 1000'";

        } else if ("2".equals(text)) {
            retStr = "Please enter the file path like 'D:\\Files\\data.txt'";
        } else if ("print".equals(text)) {
            Map<String, Double> map = currencyAndAmount.getConcurrentCurrencyMap();
            if (map == null || map.size() <= 0) {
                return "You have not enter the data! ";
            } else {
                for (Map.Entry<String, Double> entry : map.entrySet()) {
                    if (rateExchange.getRate(entry.getKey()) == null || entry.getKey().equals("USD")) {
                        retStr += (entry.getKey() + " " + entry.getValue() + "\r\n");
                    } else {
                        retStr += (entry.getKey() + " " + entry.getValue() + "(USD " + rateExchange.getExChange(entry.getKey(), entry.getValue()) + ")\r\n");
                    }
                }
            }

        } else {
            if (!"".equals(text)) {
                if (firstCommand == 0)
                    retStr = "Please enter \"1\" or \"2\"";
                else if (firstCommand == 1) {
                    if (!Validates.getValidates().isValidateInput(text)) {
                        retStr = "Invalid data format. Please enter the data in the format like 'USD 1000'";
                    } else {
                        if (!Double.valueOf(text.split(" ")[1]).equals(0)) {
                            currencyAndAmount.put(text.split(" ")[0], Double.valueOf(text.split(" ")[1]));
                        }
                    }
                } else if (firstCommand == 2) {
                    if (!Validates.getValidates().isValidataFilePath(text)) {
                        retStr = "Invalid file path or has no this file. Please check";
                    } else {
                        List<String> list = FileUtil.getFileUtil().getFileData(text);
                        for (String str : list) {
                            if (!Double.valueOf(str.split(" ")[1]).equals(0)) {
                                currencyAndAmount.put(str.split(" ")[0], Double.valueOf(str.split(" ")[1]));
                            }
                        }
                        retStr = "read file success!";
                    }
                } else if (firstCommand == 3) {
                    if (!Validates.getValidates().isValidateInput(text)) {
                        retStr = "Invalid command: you may want enter endRate";

                    }
                    else{
                        rateExchange.setRate(text.split(" ")[0], Double.valueOf(text.split(" ")[1]));
                    }
                }

            }
        }
        return retStr;
    }


}
