package com.validate;

import com.constant.Constants;

import java.io.File;
import java.util.regex.Pattern;

public class Validates {


    private volatile static Validates validates;

    private Validates(){}

    public static Validates getValidates(){
        if(validates == null){
            synchronized (Validates.class){
                if(validates == null){
                    validates = new Validates();
                }
            }
        }
        return validates;
    }

    /**
     * validate the input is correct
     * @param input
     * @return boolean
     */
    public boolean isValidateInput(String input){
        if (!Pattern.matches(Constants.CURRENCY_CODE_REGEX, input)) {
            return false;
        }
        return true;
    }


    public boolean isValidataFilePath(String filePath){

        File file = new File(filePath);
        if(!file.exists()){
            return false;
        }
        return true;

    }
}
